package br.com.eventosdahora.api.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Arrays;
import java.util.UUID;

@Entity
@Table(name="tb_image")
public class Image implements Serializable {
	private static final long serialVersionUID = 1L;

	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	@Column(name="id_image")
	private UUID idImage;
	
	@Column(name="nm_image")
	private String nmImage;
	
	@Column(name="ds_image_type")
	private String dsImageType;

	@Column(name="file")
	private String file;

	public Image() {
	}
	
	public Image(String nmImage, String dsImageType, String file) {
		this.nmImage = nmImage;
		this.dsImageType = dsImageType;
		this.file = file;
	}
	
	public Image(UUID idImage, String nmImage, String dsImageType, String file) {
		this.idImage = idImage;
		this.nmImage = nmImage;
		this.dsImageType = dsImageType;
		this.file = file;
	}
	
	@JsonIgnore
	public String getFile() {
		return this.file;
	}

	@JsonProperty
	public void setFile(String file) {
		this.file = file;
	}
	
	public static long getSerialVersionUID() {
		return serialVersionUID;
	}
	
	public UUID getIdImage() {
		return idImage;
	}
	
	public void setIdImage(final UUID idImage) {
		this.idImage = idImage;
	}
	
	public String getNmImage() {
		return nmImage;
	}
	
	public void setNmImage(final String nmImage) {
		this.nmImage = nmImage;
	}
	
	public String getDsImageType() {
		return dsImageType;
	}
	
	public void setDsImageType(final String dsImageType) {
		this.dsImageType = dsImageType;
	}
	
	@Override
	public String toString() {
		return "Image{" +
		       "idImage=" + idImage +
		       ", nmImage='" + nmImage + '\'' +
		       ", dsImageType='" + dsImageType + '\'' +
		       ", file=" + (file) +
		       '}';
	}
}
