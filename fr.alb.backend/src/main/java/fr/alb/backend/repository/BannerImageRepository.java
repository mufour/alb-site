package fr.alb.backend.repository;

import fr.alb.backend.model.entity.BannerImage;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface BannerImageRepository extends JpaRepository<BannerImage, Long> {

    List<BannerImage> findByActiveTrueOrderByDisplayOrderAsc();
}