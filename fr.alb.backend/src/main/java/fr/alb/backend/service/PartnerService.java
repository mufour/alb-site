package fr.alb.backend.service;

import fr.alb.backend.exception.ResourceNotFoundException;

import fr.alb.backend.dto.request.CreatePartnerRequest;
import fr.alb.backend.dto.request.UpdatePartnerRequest;
import fr.alb.backend.dto.response.PartnerResponse;
import fr.alb.backend.mapper.PartnerMapper;
import fr.alb.backend.model.entity.Media;
import fr.alb.backend.model.entity.Partner;
import fr.alb.backend.model.enums.PartnerType;
import fr.alb.backend.repository.MediaRepository;
import fr.alb.backend.repository.PartnerRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PartnerService {

    private final PartnerRepository partnerRepository;
    private final PartnerMapper partnerMapper;
    private final MediaRepository mediaRepository;

    public PartnerService(PartnerRepository partnerRepository, PartnerMapper partnerMapper,MediaRepository mediaRepository) {
        this.partnerRepository = partnerRepository;
        this.partnerMapper = partnerMapper;
        this.mediaRepository = mediaRepository;
    }

    public List<PartnerResponse> getAll() {
        return partnerRepository.findAll()
                .stream()
                .map(partnerMapper::toResponse)
                .toList();
    }

    public PartnerResponse getById(Long id) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le partenaire n'a pas été trouvé"));
        return partnerMapper.toResponse(partner);
    }

    public List<PartnerResponse> getByType(PartnerType type) {

        List<PartnerResponse> partners = partnerRepository
                .findByTypeOrderByDisplayOrderAsc(type)
                .stream()
                .map(partnerMapper::toResponse)
                .toList();
        if (partners.isEmpty()) {
            throw new ResourceNotFoundException("Aucun partenaire trouvé pour le type " + type);
        }

        return partners;
    }

    public PartnerResponse create(CreatePartnerRequest request) {
        Partner partner = partnerMapper.toEntity(request);
         if (request.getImageId() != null) {
            Media media = mediaRepository.findById(request.getImageId())
                    .orElseThrow(() -> new ResourceNotFoundException("Le média n'a pas été trouvé"));
            partner.setImage(media);
        }
        Partner saved = partnerRepository.save(partner);
        return partnerMapper.toResponse(saved);
    }

    public PartnerResponse update(Long id, UpdatePartnerRequest request) {
        Partner partner = partnerRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le partenaire n'a pas été trouvé"));
        partnerMapper.updateEntity(request, partner);
         if (request.getImageId() != null) {
            Media media = mediaRepository.findById(request.getImageId())
                    .orElseThrow(() -> new ResourceNotFoundException("Le média n'a pas été trouvé"));
            partner.setImage(media);
        } else {
            partner.setImage(null);
        }
        Partner saved = partnerRepository.save(partner);
        return partnerMapper.toResponse(saved);
    }

    public void delete(Long id) {
        partnerRepository.deleteById(id);
    }
}
