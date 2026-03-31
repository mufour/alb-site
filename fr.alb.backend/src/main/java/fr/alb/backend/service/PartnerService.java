package fr.alb.backend.service;

import fr.alb.backend.model.entity.Partner;
import fr.alb.backend.repository.PartnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PartnerService {
     private final PartnerRepository partnerRepository;

    public PartnerService(PartnerRepository partnerRepository) {
        this.partnerRepository = partnerRepository;
    }

    public List<Partner> getAll() {
        return partnerRepository.findAll();
    }

    public Optional<Partner> getById(Long id) {
        return partnerRepository.findById(id);
    }

    public Partner create(Partner partner) {
        return partnerRepository.save(partner);
    }

    public Partner update(Long id, Partner updated) {
        return partnerRepository.findById(id)
                .map(existing -> {
                    existing.setName(updated.getName());
                    existing.setType(updated.getType());
                    existing.setWebsiteUrl(updated.getWebsiteUrl());
                    existing.setDisplayOrder(updated.getDisplayOrder());
                    return partnerRepository.save(existing);
                })
                .orElseThrow(() -> new RuntimeException("Le partenaire n'a pas été trouvé"));
    }

    public void delete(Long id) {
        partnerRepository.deleteById(id);
    }
}
