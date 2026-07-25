package fr.alb.backend.service;

import org.springframework.stereotype.Service;
import fr.alb.backend.dto.request.CreateBannerImageRequest;
import fr.alb.backend.dto.request.UpdateBannerImageRequest;
import fr.alb.backend.dto.response.BannerImageResponse;
import fr.alb.backend.mapper.BannerImageMapper;
import fr.alb.backend.model.entity.BannerImage;
import fr.alb.backend.repository.BannerImageRepository;

import java.util.List;

@Service
public class BannerImageService {

    private final BannerImageRepository bannerImageRepository;
    private final BannerImageMapper bannerImageMapper;

    public BannerImageService(BannerImageRepository bannerImageRepository, BannerImageMapper bannerImageMapper) {
        this.bannerImageRepository = bannerImageRepository;
        this.bannerImageMapper = bannerImageMapper;
    }

    public List<BannerImageResponse> getAll() {
        return bannerImageRepository.findAll()
                .stream()
                .map(bannerImageMapper::toResponse)
                .toList();
    }

    public BannerImageResponse getById(Long id) {
        BannerImage bannerImage = bannerImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("L'image n'a pas été trouve"));
        return bannerImageMapper.toResponse(bannerImage);
    }

    public BannerImageResponse getByTitle(String title) {
        BannerImage bannerImage = bannerImageRepository.findByTitle(title)
                .orElseThrow(() -> new RuntimeException("L'image n'a pas été trouve"));
        return bannerImageMapper.toResponse(bannerImage);
    }

    public BannerImageResponse create(CreateBannerImageRequest request) {
        BannerImage bannerImage = bannerImageMapper.toEntity(request);
        BannerImage saved = bannerImageRepository.save(bannerImage);
        return bannerImageMapper.toResponse(saved);
    }

    public BannerImageResponse update(Long id, UpdateBannerImageRequest request) {
        BannerImage bannerImage = bannerImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("L'image n'a pas été trouvé"));
        bannerImageMapper.updateEntity(request, bannerImage);
        BannerImage saved = bannerImageRepository.save(bannerImage);
        return bannerImageMapper.toResponse(saved);
    }

    public void delete(Long id) {
        BannerImage bannerImage = bannerImageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("L'image n'a pas été trouvé"));
        bannerImageRepository.delete(bannerImage);
    }
}
