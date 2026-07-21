package fr.alb.backend.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePageRequest {
 
    private String slug;

    private String title;

    private String content;
}