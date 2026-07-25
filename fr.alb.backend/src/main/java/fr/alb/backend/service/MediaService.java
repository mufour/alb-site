package fr.alb.backend.service;

import org.springframework.stereotype.Service;
import fr.alb.backend.dto.response.MediaResponse;
import fr.alb.backend.repository.MediaRepository;
import fr.alb.backend.mapper.MediaMapper;
import fr.alb.backend.model.entity.Media;

import java.util.List;


@Service
public class MediaService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;

    public MediaService(MediaRepository mediaRepository,
                        MediaMapper mediaMapper) {
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
    }

    public List<MediaResponse> getAll() {
        return mediaRepository.findAll()
                .stream()
                .map(mediaMapper::toResponse)
                .toList();
    }

    public MediaResponse getById(Long id) {

        Media media = mediaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Le média n'a pas été trouvé"));

        return mediaMapper.toResponse(media);
    }

    public MediaResponse getByFileName(String fileName) {

        Media media = mediaRepository.findByFileName(fileName)
                .orElseThrow(() ->
                        new RuntimeException("Le média n'a pas été trouvé"));

        return mediaMapper.toResponse(media);
    }

    public void delete(Long id) {

        Media media = mediaRepository.findById(id)
                .orElseThrow(() ->
                        new RuntimeException("Le média n'a pas été trouvé"));

        mediaRepository.delete(media);
    }
}
