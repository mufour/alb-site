package fr.alb.backend.repository;

import fr.alb.backend.model.entity.Partner;
import fr.alb.backend.model.enums.PartnerType;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PartnerRepository extends JpaRepository<Partner, Long> {

    List<Partner> findByTypeOrderByDisplayOrderAsc(PartnerType type);
}