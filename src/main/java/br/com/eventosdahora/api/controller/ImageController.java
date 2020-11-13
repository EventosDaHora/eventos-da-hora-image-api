package br.com.eventosdahora.api.controller;

import br.com.eventosdahora.api.model.Image;
import br.com.eventosdahora.api.service.ImageService;
import io.swagger.annotations.*;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.Base64;
import java.util.List;
import java.util.UUID;

@CrossOrigin
@RestController
@RequestMapping("/images")
@Api(value="file", description="Files API")
public class ImageController {

	private final ImageService imageService;

	public ImageController(ImageService imageService) {
		this.imageService = imageService;
	}
	
	@GetMapping
	@ApiModelProperty(position = 1)
	@ApiOperation(value = "Find File", response = Image.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Consulta realizada com sucesso"),
			@ApiResponse(code = 400, message = "Erro na requisição")
	})
	public ResponseEntity<?> getAll() {
		return ResponseEntity.ok().body(imageService.getAll());
	}
	
	@GetMapping("/{idImage}")
	@ApiModelProperty(position = 1)
	@ApiOperation(value = "Find File", response = Image.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Consulta realizada com sucesso"),
			@ApiResponse(code = 400, message = "Erro na requisição")
	})
    public ResponseEntity<Resource> downloadFile(@PathVariable("idImage") UUID idImage) {
		Image image = imageService.getFile(idImage);
		
        return ResponseEntity.ok()
                .contentType(MediaType.parseMediaType(image.getDsImageType()))
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + image.getNmImage() + "\"")
                .header("file-name",  "\"" + image.getNmImage() + "\"")
                .body(new ByteArrayResource(Base64.getDecoder().decode(image.getFile())));
    }

	@PostMapping
	@ApiModelProperty(position = 4)
	@ApiOperation(value = "Save Files", response = List.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso ao persistir objeto"),
			@ApiResponse(code = 400, message = "Erro na requisição")
	})
	public ResponseEntity<List<Image>> uploadFiles(@RequestParam("files") MultipartFile[] files) {
		return ResponseEntity.ok().body(imageService.save(files));
	}
	
	@PutMapping("/{idImage}")
	@ApiModelProperty(position = 5)
	@ApiOperation(value = "Update File", response = Image.class)
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso ao persistir objeto"),
			@ApiResponse(code = 400, message = "Erro na requisição")
	})
    public ResponseEntity<Image> updateFile(    @PathVariable("idImage") UUID idImage,
    											@RequestParam("file") MultipartFile file) {
		return ResponseEntity.ok().body(imageService.update(idImage, file));
    }
	
	@DeleteMapping("/{idImage}")
	@ApiModelProperty(position = 6)
	@ApiOperation(value = "Delete File")
	@ApiResponses(value = {
			@ApiResponse(code = 200, message = "Sucesso ao persistir objeto"),
			@ApiResponse(code = 400, message = "Erro na requisição")
	})
    public ResponseEntity<?> delete(@PathVariable("idImage") UUID idImage) {
		imageService.delete(idImage);
		return ResponseEntity.ok().build();
    }
}
