package fr.alb.backend.dto.request;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreateBannerImageRequest {

    private String title;

    private String description;

    private Long imageId;

    private Integer displayOrder;

    private Boolean active = true;
}
