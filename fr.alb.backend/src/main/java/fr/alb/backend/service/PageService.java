package fr.alb.backend.service;

import org.springframework.stereotype.Service;

import fr.alb.backend.dto.request.CreatePageRequest;
import fr.alb.backend.dto.request.UpdatePageRequest;
import fr.alb.backend.dto.response.PageResponse;
import fr.alb.backend.mapper.PageMapper;
import fr.alb.backend.model.entity.Page;
import fr.alb.backend.repository.PageRepository;

import java.util.List;

@Service
public class PageService {

    private final PageRepository pageRepository;
    private final PageMapper pageMapper;

    public PageService(PageRepository pageRepository, PageMapper pageMapper) {
        this.pageRepository = pageRepository;
        this.pageMapper = pageMapper;
    }

    public List<PageResponse> getAll() {
        return pageRepository.findAll()
                .stream()
                .map(pageMapper::toResponse)
                .toList();
    }

    public PageResponse getById(Long id) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La page n'a pas été trouvé"));
        return pageMapper.toResponse(page);
    }

    public PageResponse getBySlug(String slug) {
        Page page = pageRepository.findBySlug(slug)
                .orElseThrow(() -> new RuntimeException("La page n'a pas été trouvé"));
        return pageMapper.toResponse(page);
    }

    public PageResponse create(CreatePageRequest request) {
        Page page = pageMapper.toEntity(request);
        Page saved = pageRepository.save(page);
        return pageMapper.toResponse(saved);
    }

    public PageResponse update(Long id, UpdatePageRequest request) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La page n'a pas été trouvé"));
        pageMapper.updateEntity(request, page);
        Page saved = pageRepository.save(page);
        return pageMapper.toResponse(saved);
    }

    public void delete(Long id) {
        Page page = pageRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("La page n'a pas été trouvé"));
        pageRepository.delete(page);
    }
}