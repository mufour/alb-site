package fr.alb.backend.mapper;

import org.springframework.stereotype.Component;

import fr.alb.backend.dto.request.CreateBannerImageRequest;
import fr.alb.backend.dto.request.UpdateBannerImageRequest;
import fr.alb.backend.dto.response.BannerImageResponse;
import fr.alb.backend.model.entity.BannerImage;

@Component
public class BannerImageMapper {

    public BannerImage toEntity(CreateBannerImageRequest request) {

        BannerImage bannerImage = new BannerImage();

        bannerImage.setTitle(request.getTitle());
        bannerImage.setDescription(request.getDescription());
        bannerImage.setDisplayOrder(request.getDisplayOrder());
        bannerImage.setActive(request.getActive());

        return bannerImage;
    }

    public BannerImageResponse toResponse(BannerImage bannerImage) {

        BannerImageResponse response = new BannerImageResponse();

        response.setId(bannerImage.getId());
        response.setTitle(bannerImage.getTitle());
        response.setDescription(bannerImage.getDescription());
        response.setDisplayOrder(bannerImage.getDisplayOrder());
        response.setActive(bannerImage.getActive());

        if (bannerImage.getImage() != null) {
            response.setImageId(bannerImage.getImage().getId());
        }

        return response;
    }

    public void updateEntity(UpdateBannerImageRequest request, BannerImage bannerImage) {

        bannerImage.setTitle(request.getTitle());
        bannerImage.setDescription(request.getDescription());
        bannerImage.setDisplayOrder(request.getDisplayOrder());
        bannerImage.setActive(request.getActive());
    }
}
