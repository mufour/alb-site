package fr.alb.backend.dto.request;

import fr.alb.backend.model.enums.PartnerType;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CreatePartnerRequest {

    @NotBlank
    private String name;

    @NotNull
    private PartnerType type;

    private String websiteUrl;

    private Long imageId;

    private Integer displayOrder;
}