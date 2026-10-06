package fr.alb.backend.service;

import fr.alb.backend.exception.BadRequestException;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.net.MalformedURLException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.Locale;
import java.util.Set;
import java.util.UUID;

@Service
public class FileStorageService {

    private static final Set<String> ALLOWED_CONTENT_TYPES = Set.of("image/jpeg", "image/png", "image/webp");
    private static final Set<String> ALLOWED_EXTENSIONS = Set.of("jpg", "jpeg", "png", "webp");

    private final Path uploadRoot;

    public FileStorageService(@Value("${app.upload.dir:uploads}") String uploadDir) {
        this.uploadRoot = Path.of(uploadDir).toAbsolutePath().normalize();
    }

    @PostConstruct
    void initialize() {
        try {
            Files.createDirectories(uploadRoot);
        } catch (IOException exception) {
            throw new IllegalStateException("Impossible d'initialiser le dossier d'upload.", exception);
        }
    }

    public StoredFile store(MultipartFile file) {
        if (file == null || file.isEmpty()) {
            throw new BadRequestException("Le fichier est vide.");
        }

        String contentType = file.getContentType();
        if (contentType == null || !ALLOWED_CONTENT_TYPES.contains(contentType.toLowerCase(Locale.ROOT))) {
            throw new BadRequestException("Type de fichier non autorisé. Formats acceptés : JPEG, PNG et WebP.");
        }

        String originalName = StringUtils.cleanPath(file.getOriginalFilename() == null ? "image" : file.getOriginalFilename());
        String extension = extensionOf(originalName);
        if (!ALLOWED_EXTENSIONS.contains(extension)) {
            throw new BadRequestException("Extension de fichier non autorisée.");
        }

        String storedName = UUID.randomUUID() + "." + extension;
        Path destination = uploadRoot.resolve(storedName).normalize();
        if (!destination.getParent().equals(uploadRoot)) {
            throw new BadRequestException("Chemin de fichier invalide.");
        }

        try {
            Files.copy(file.getInputStream(), destination, StandardCopyOption.REPLACE_EXISTING);
            return new StoredFile(storedName, "/uploads/" + storedName, contentType, file.getSize());
        } catch (IOException exception) {
            throw new BadRequestException("Impossible d'enregistrer le fichier.", exception);
        }
    }

    public Resource load(String storedName) {
        try {
            Path file = uploadRoot.resolve(storedName).normalize();
            if (!file.getParent().equals(uploadRoot)) {
                throw new BadRequestException("Chemin de fichier invalide.");
            }
            Resource resource = new UrlResource(file.toUri());
            if (!resource.exists() || !resource.isReadable()) {
                throw new fr.alb.backend.exception.ResourceNotFoundException("Le fichier n'a pas été trouvé.");
            }
            return resource;
        } catch (MalformedURLException exception) {
            throw new BadRequestException("Chemin de fichier invalide.", exception);
        }
    }

    public void delete(String storedName) {
        try {
            Files.deleteIfExists(uploadRoot.resolve(storedName).normalize());
        } catch (IOException exception) {
            throw new BadRequestException("Impossible de supprimer le fichier.", exception);
        }
    }

    private String extensionOf(String fileName) {
        int index = fileName.lastIndexOf('.');
        if (index < 0 || index == fileName.length() - 1) {
            throw new BadRequestException("Le fichier doit avoir une extension.");
        }
        return fileName.substring(index + 1).toLowerCase(Locale.ROOT);
    }

    public record StoredFile(String fileName, String filePath, String contentType, long size) {
    }
}
