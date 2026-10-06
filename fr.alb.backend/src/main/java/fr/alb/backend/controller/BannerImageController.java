package fr.alb.backend.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import fr.alb.backend.service.BannerImageService;
import fr.alb.backend.dto.request.CreateBannerImageRequest;
import fr.alb.backend.dto.request.UpdateBannerImageRequest;
import fr.alb.backend.dto.response.BannerImageResponse;

import java.util.List;

@RestController
@RequestMapping("/api/banner-images")
public class BannerImageController {

    private final BannerImageService bannerImageService;

    public BannerImageController(BannerImageService bannerImageService) {
        this.bannerImageService = bannerImageService;
    }

    @GetMapping
    public List<BannerImageResponse> getAll() {
        return bannerImageService.getAll();
    }

    @GetMapping("/{id}")
    public BannerImageResponse getById(@PathVariable Long id) {
        return bannerImageService.getById(id);
    }

    @GetMapping("/title/{title}")
    public BannerImageResponse getByTitle(@PathVariable String title) {
        return bannerImageService.getByTitle(title);
    }

    @PostMapping
    public BannerImageResponse create(@Valid @RequestBody CreateBannerImageRequest request) {
        return bannerImageService.create(request);
    }

    @PutMapping("/{id}")
    public BannerImageResponse update(@PathVariable Long id, @Valid @RequestBody UpdateBannerImageRequest request) {
        return bannerImageService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        bannerImageService.delete(id);
    }
}