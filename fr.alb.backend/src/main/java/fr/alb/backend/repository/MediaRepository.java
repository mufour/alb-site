package fr.alb.backend.repository;

import fr.alb.backend.model.entity.Media;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface MediaRepository extends JpaRepository<Media, Long> {

    Optional<Media> findByFilePath(String filePath);
}
