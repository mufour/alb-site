package fr.alb.backend.repository;

import fr.alb.backend.model.entity.Page;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface PageRepository extends JpaRepository<Page, Long> {

    Optional<Page> findBySlug(String slug);
}
