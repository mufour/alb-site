package fr.alb.backend.dto.response;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class MediaResponse {
    
    private Long id;

    private String fileName;

    private String filePath;

    private String contentType;

    private Long size;

    private LocalDateTime uploadedAt;
}
