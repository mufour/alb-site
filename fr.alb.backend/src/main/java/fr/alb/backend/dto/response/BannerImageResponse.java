package fr.alb.backend.dto.response;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BannerImageResponse {
 
    private Long id;

    private String title;

    private String description;

    private Long imageId;

    private Integer displayOrder;

    private Boolean active = true;
}
