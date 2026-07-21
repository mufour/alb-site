package fr.alb.backend.dto.response;

import java.time.LocalDate;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EventResponse {
    
    private Long id;

    private String title;

    private String description;

    private LocalDate startDate;
    
    private LocalDate endDate;

    private String location;

    private Long imageId;

    private Boolean published = false;
}
