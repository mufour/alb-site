package fr.alb.backend.mapper;

import org.springframework.stereotype.Component;

import fr.alb.backend.dto.response.MediaResponse;
import fr.alb.backend.model.entity.Media;

@Component
public class MediaMapper {

    public MediaResponse toResponse(Media media) {

        MediaResponse response = new MediaResponse();

        response.setId(media.getId());
        response.setFileName(media.getFileName());
        response.setFilePath(media.getFilePath());
        response.setContentType(media.getContentType());
        response.setSize(media.getSize());
        response.setUploadedAt(media.getCreatedAt());

        return response;
    }

}
