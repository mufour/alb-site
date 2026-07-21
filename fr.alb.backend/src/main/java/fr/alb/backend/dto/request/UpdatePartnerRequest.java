package fr.alb.backend.dto.request;

import fr.alb.backend.model.enums.PartnerType;

import jakarta.validation.constraints.NotBlank;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UpdatePartnerRequest {
    
    @NotBlank
    private String name;

    private PartnerType type;

    private String websiteUrl;

    private Long imageId;

    private Integer displayOrder;
}
