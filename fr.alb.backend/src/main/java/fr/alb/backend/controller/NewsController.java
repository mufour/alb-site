package fr.alb.backend.controller;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.*;
import fr.alb.backend.service.NewsService;
import fr.alb.backend.dto.request.CreateNewsRequest;
import fr.alb.backend.dto.request.UpdateNewsRequest;
import fr.alb.backend.dto.response.NewsResponse;

import java.util.List;

@RestController
@RequestMapping({"/api/news", "/api/admin/news"})
public class NewsController {

    private final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }

    @GetMapping
    public List<NewsResponse> getAll() {
        return newsService.getAll();
    }

    @GetMapping("/{id}")
    public NewsResponse getById(@PathVariable Long id) {
        return newsService.getById(id);
    }

    @GetMapping("/title/{title}")
    public NewsResponse getByTitle(@PathVariable String title) {
        return newsService.getByTitle(title);
    }

    @GetMapping("/subtitle/{subtitle}")
    public NewsResponse getBySubtitle(@PathVariable String subtitle) {
        return newsService.getBySubtitle(subtitle);
    }

    @PostMapping
    public NewsResponse create(@Valid @RequestBody CreateNewsRequest request) {
        return newsService.create(request);
    }

    @PutMapping("/{id}")
    public NewsResponse update(@PathVariable Long id, @Valid @RequestBody UpdateNewsRequest request) {
        return newsService.update(id, request);
    }

    @PatchMapping("/{id}/publish")
    public NewsResponse publish(@PathVariable Long id) {
        return newsService.setPublished(id, true);
    }

    @PatchMapping("/{id}/unpublish")
    public NewsResponse unpublish(@PathVariable Long id) {
        return newsService.setPublished(id, false);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        newsService.delete(id);
    }
}