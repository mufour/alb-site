package fr.alb.backend.dto.request;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePageRequest {

    @NotBlank(message = "Le slug est obligatoire.")
    private String slug;

    @NotBlank(message = "Le titre est obligatoire.")
    private String title;

    @NotBlank(message = "Le contenu est obligatoire.")
    private String content;

    private Long imageId;
}
