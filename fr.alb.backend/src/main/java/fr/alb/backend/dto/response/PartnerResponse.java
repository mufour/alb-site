package fr.alb.backend.dto.response;

import fr.alb.backend.model.enums.PartnerType;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class PartnerResponse {

    private Long id;

    private String name;

    private PartnerType type;

    private String websiteUrl;

    private Integer displayOrder;

    private Long imageId;
}