package fr.alb.backend.service;

import fr.alb.backend.dto.request.CreatePartnerRequest;
import fr.alb.backend.dto.request.UpdatePartnerRequest;
import fr.alb.backend.dto.response.PartnerResponse;
import fr.alb.backend.mapper.PartnerMapper;
import fr.alb.backend.model.entity.Partner;
import fr.alb.backend.repository.PartnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerService {

    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;

    public PartnerService(PartnerRepository partnerRepository, PartnerMapper partnerMapper) {
        this.partnerRepository = partnerRepository;
        this.partnerMapper = partnerMapper;
    }

    public List<PartnerResponse> getAll() {
        return partnerRepository.findAll()
                .stream()
                .map(partnerMapper::toResponse)
                .toList();
    }

    public PartnerResponse getById(Long id) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Le partenaire n'a pas été trouvé"));
        return partnerMapper.toResponse(partner);
    }

    public PartnerResponse create(CreatePartnerRequest request) {
        Partner partner = partnerMapper.toEntity(request);
        Partner saved = partnerRepository.save(partner);
        return partnerMapper.toResponse(saved);
    }

    public PartnerResponse update(Long id, UpdatePartnerRequest request) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Le partenaire n'a pas été trouvé"));
        partnerMapper.updateEntity(request, partner);
        Partner saved = partnerRepository.save(partner);
        return partnerMapper.toResponse(saved);
    }

    public void delete(Long id) {
        partnerRepository.deleteById(id);
    }
}
