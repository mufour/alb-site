package fr.alb.backend.service;

import fr.alb.backend.dto.response.MediaResponse;
import fr.alb.backend.exception.ResourceNotFoundException;
import fr.alb.backend.mapper.MediaMapper;
import fr.alb.backend.model.entity.Media;
import fr.alb.backend.repository.MediaRepository;
import org.springframework.core.io.Resource;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@Service
public class MediaService {

    private final MediaRepository mediaRepository;
    private final MediaMapper mediaMapper;
    private final FileStorageService fileStorageService;

    public MediaService(MediaRepository mediaRepository, MediaMapper mediaMapper, FileStorageService fileStorageService) {
        this.mediaRepository = mediaRepository;
        this.mediaMapper = mediaMapper;
        this.fileStorageService = fileStorageService;
    }

    public List<MediaResponse> getAll() {
        return mediaRepository.findAll().stream().map(mediaMapper::toResponse).toList();
    }

    public MediaResponse getById(Long id) {
        return mediaMapper.toResponse(findMedia(id));
    }

    public MediaResponse getByFileName(String fileName) {
        Media media = mediaRepository.findByFileName(fileName)
                .orElseThrow(() -> new ResourceNotFoundException("Le média n'a pas été trouvé."));
        return mediaMapper.toResponse(media);
    }

    @Transactional
    public MediaResponse create(MultipartFile file) {
        FileStorageService.StoredFile storedFile = fileStorageService.store(file);
        try {
            Media media = new Media();
            media.setFileName(storedFile.fileName());
            media.setFilePath(storedFile.filePath());
            media.setContentType(storedFile.contentType());
            media.setSize(storedFile.size());
            return mediaMapper.toResponse(mediaRepository.save(media));
        } catch (RuntimeException exception) {
            fileStorageService.delete(storedFile.fileName());
            throw exception;
        }
    }

    public Resource loadFile(Long id) {
        return fileStorageService.load(findMedia(id).getFileName());
    }

    @Transactional
    public void delete(Long id) {
        Media media = findMedia(id);
        try {
            mediaRepository.delete(media);
            mediaRepository.flush();
        } catch (DataIntegrityViolationException exception) {
            throw exception;
        }
        fileStorageService.delete(media.getFileName());
    }

    private Media findMedia(Long id) {
        return mediaRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Le média n'a pas été trouvé."));
    }
}
