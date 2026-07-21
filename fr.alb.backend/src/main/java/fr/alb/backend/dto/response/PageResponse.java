package fr.alb.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PageResponse {
    
    private Long id;

    private String slug;

    private String title;

    private String content;
}
