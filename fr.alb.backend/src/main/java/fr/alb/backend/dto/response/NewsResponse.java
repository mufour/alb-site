package fr.alb.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class NewsResponse {
    
    private Long id;

    private String title;

    private String subtitle;

    private String content;

    private Long imageId;

    private Boolean published = false;
}
