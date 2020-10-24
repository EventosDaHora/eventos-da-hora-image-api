package br.com.eventosdahora.api.service;

import br.com.eventosdahora.api.exceptions.FileStorageException;
import br.com.eventosdahora.api.exceptions.MyFileNotFoundException;
import br.com.eventosdahora.api.model.Image;
import br.com.eventosdahora.api.repository.ImageRepository;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import java.io.IOException;
import java.util.UUID;

@Service
public class ImageService {
	
	private final ImageRepository imageRepository;
	
	public ImageService(ImageRepository imageRepository) {
		this.imageRepository = imageRepository;
	}
	
	public Image save(MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			Image image = new Image(fileName, file.getContentType(), file.getBytes());
			
			return imageRepository.save(image);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}
	
	public Image update(UUID idImage, MultipartFile file) {
		String fileName = StringUtils.cleanPath(file.getOriginalFilename());
		
		try {
			if (fileName.contains("..")) {
				throw new FileStorageException("Sorry! Filename contains invalid path sequence " + fileName);
			}
			Image image = new Image(idImage, fileName, file.getContentType(), file.getBytes());
			
			return imageRepository.save(image);
		} catch (IOException ex) {
			throw new FileStorageException("Could not store file " + fileName + ". Please try again!", ex);
		}
	}
	
	public void delete(UUID idImage) {
		imageRepository.deleteById(idImage);
	}
	
	public Image getFile(UUID fileId) {
		return imageRepository.findById(fileId)
		                      .orElseThrow(() -> new MyFileNotFoundException("File not found with id " + fileId));
	}
}
