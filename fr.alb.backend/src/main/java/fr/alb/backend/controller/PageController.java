package fr.alb.backend.controller;

import org.springframework.web.bind.annotation.*;
import fr.alb.backend.service.PageService;
import fr.alb.backend.dto.request.CreatePageRequest;
import fr.alb.backend.dto.request.UpdatePageRequest;
import fr.alb.backend.dto.response.PageResponse;

import java.util.List;

@RestController
@RequestMapping("/api/pages")
public class PageController {
    
    private final PageService pageService;

    public PageController(PageService pageService) {
        this.pageService = pageService;
    }

    @GetMapping
    public List<PageResponse> getAll() {
        return pageService.getAll();
    }

    @GetMapping("/{id}")
    public PageResponse getById(@PathVariable Long id) {
        return pageService.getById(id);
    }

    @GetMapping("/slug/{slug}")
    public PageResponse getBySlug(@PathVariable String slug) {
        return pageService.getBySlug(slug);
    }

    @PostMapping
    public PageResponse create(@RequestBody CreatePageRequest request) {
        return pageService.create(request);
    }

    @PutMapping("/{id}")
    public PageResponse update(@PathVariable Long id, @RequestBody UpdatePageRequest request) {
        return pageService.update(id, request);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        pageService.delete(id);
    }
}