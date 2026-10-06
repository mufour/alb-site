package fr.alb.backend.controller;

import fr.alb.backend.dto.response.MediaResponse;
import fr.alb.backend.service.MediaService;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/api/media")
public class MediaController {
    private final MediaService mediaService;

    public MediaController(MediaService mediaService) { this.mediaService = mediaService; }

    @GetMapping
    public List<MediaResponse> getAll() { return mediaService.getAll(); }

    @GetMapping("/{id}")
    public MediaResponse getById(@PathVariable Long id) { return mediaService.getById(id); }

    @GetMapping("/filename/{fileName}")
    public MediaResponse getByFileName(@PathVariable String fileName) { return mediaService.getByFileName(fileName); }

    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public ResponseEntity<MediaResponse> upload(@RequestParam("file") MultipartFile file) {
        return ResponseEntity.status(201).body(mediaService.create(file));
    }

    @GetMapping("/{id}/file")
    public ResponseEntity<Resource> getFile(@PathVariable Long id) {
        MediaResponse media = mediaService.getById(id);
        return fileResponse(media, mediaService.loadFile(id));
    }

    @GetMapping("/file/{fileName}")
    public ResponseEntity<Resource> getFileByName(@PathVariable String fileName) {
        MediaResponse media = mediaService.getByFileName(fileName);
        return fileResponse(media, mediaService.loadFile(media.getId()));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        mediaService.delete(id);
        return ResponseEntity.noContent().build();
    }

    private ResponseEntity<Resource> fileResponse(MediaResponse media, Resource resource) {
        MediaType mediaType;
        try {
            mediaType = MediaType.parseMediaType(media.getContentType());
        } catch (Exception exception) {
            mediaType = MediaType.APPLICATION_OCTET_STREAM;
        }
        return ResponseEntity.ok()
                .contentType(mediaType)
                .header(HttpHeaders.CONTENT_DISPOSITION, "inline; filename=\"" + media.getFileName() + "\"")
                .body(resource);
    }
}
