package fr.alb.backend.dto.request;

import jakarta.validation.constraints.NotBlank;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateEventRequest {
    
    @NotBlank(message = "Le titre est obligatoire.")
    private String title;

    @NotBlank(message = "La description est obligatoire.")
    private String description;

    private LocalDate startDate;
    
    private LocalDate endDate;

    private String location;

    private Long imageId;

    private Boolean published = false;
}
