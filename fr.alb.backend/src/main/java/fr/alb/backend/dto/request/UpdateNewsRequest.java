package fr.alb.backend.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNewsRequest {
    
    @NotBlank(message = "Le titre est obligatoire.")
    @Size(max = 150)
    private String title;

    @Size(max = 350)
    private String subtitle;

    @NotBlank(message = "Le contenu est obligatoire.")
    private String content;

    private Long imageId;

    private Boolean published = false;
}
