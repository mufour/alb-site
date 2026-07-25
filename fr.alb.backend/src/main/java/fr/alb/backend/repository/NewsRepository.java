package fr.alb.backend.repository;

import fr.alb.backend.model.entity.News;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface NewsRepository extends JpaRepository<News, Long> {

    List<News> findByPublishedTrueOrderByCreatedAtDesc();
    Optional<News> findByTitle(String title);
    Optional<News> findBySubtitle(String subtitle);
}