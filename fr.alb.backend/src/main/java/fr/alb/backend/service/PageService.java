package fr.alb.backend.service;

import fr.alb.backend.exception.ResourceNotFoundException;

import org.springframework.stereotype.Service;

import fr.alb.backend.dto.request.CreatePageRequest;
import fr.alb.backend.dto.request.UpdatePageRequest;
import fr.alb.backend.dto.response.PageResponse;
import fr.alb.backend.mapper.PageMapper;
import fr.alb.backend.model.entity.Media;
import fr.alb.backend.model.entity.Page;
import fr.alb.backend.repository.MediaRepository;
import fr.alb.backend.repository.PageRepository;

import java.util.List;

@Service
public class PageService {

    private final PageRepository pageRepository;
    private final PageMapper pageMapper;
    private final MediaRepository mediaRepository;

    public PageService(PageRepository pageRepository, PageMapper pageMapper, MediaRepository mediaRepository) {
        this.pageRepository = pageRepository;
        this.pageMapper = pageMapper;
        this.mediaRepository = mediaRepository;
    }

    public List<PageResponse> getAll() {
        return pageRepository.findAll()
                .stream()
                .map(pageMapper::toResponse)
                .toList();
    }

    public PageResponse getById(Long id) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La page n'a pas été trouvé"));
        return pageMapper.toResponse(page);
    }

    public PageResponse getBySlug(String slug) {
        Page page = pageRepository.findBySlug(slug)
                .orElseThrow(() -> new ResourceNotFoundException("La page n'a pas été trouvé"));
        return pageMapper.toResponse(page);
    }

    public PageResponse create(CreatePageRequest request) {
        Page page = pageMapper.toEntity(request);
         if (request.getImageId() != null) {
            Media media = mediaRepository.findById(request.getImageId())
                    .orElseThrow(() -> new ResourceNotFoundException("Le média n'a pas été trouvé"));
            page.setImage(media);
        }
        Page saved = pageRepository.save(page);
        return pageMapper.toResponse(saved);
    }

    public PageResponse update(Long id, UpdatePageRequest request) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La page n'a pas été trouvé"));
        pageMapper.updateEntity(request, page);
         if (request.getImageId() != null) {
            Media media = mediaRepository.findById(request.getImageId())
                    .orElseThrow(() -> new ResourceNotFoundException("Le média n'a pas été trouvé"));
            page.setImage(media);
        } else {
            page.setImage(null);
        }
        Page saved = pageRepository.save(page);
        return pageMapper.toResponse(saved);
    }

    public void delete(Long id) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("La page n'a pas été trouvé"));
        pageRepository.delete(page);
    }
}