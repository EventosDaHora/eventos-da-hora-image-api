package br.com.eventosdahora.api.repository;

import br.com.eventosdahora.api.model.Image;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ImageRepository extends JpaRepository<Image, UUID> {

}
