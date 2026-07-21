package fr.alb.backend.dto.request;

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
    
    private String title;

    private String description;

    private LocalDate startDate;
    
    private LocalDate endDate;

    private String location;

    private Long imageId;

    private Boolean published = false;
}
