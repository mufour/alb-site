package fr.alb.backend.controller;

import fr.alb.backend.dto.response.MediaResponse;
import fr.alb.backend.service.MediaService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {

    private final MediaService mediaService;

    public MediaController(MediaService mediaService) {
        this.mediaService = mediaService;
    }

    @GetMapping
    public List<MediaResponse> getAll() {
        return mediaService.getAll();
    }

    @GetMapping("/{id}")
    public MediaResponse getById(@PathVariable Long id) {
        return mediaService.getById(id);
    }

    @GetMapping("/filename/{fileName}")
    public MediaResponse getByFileName(@PathVariable String fileName) {
        return mediaService.getByFileName(fileName);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        mediaService.delete(id);
    }
}