package fr.alb.backend.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdateNewsRequest {
    
    private String title;

    private String subtitle;

    private String content;

    private Long imageId;

    private Boolean published = false;
}
