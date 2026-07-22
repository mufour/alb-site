package fr.alb.backend.model.entity;

import fr.alb.backend.model.base.BaseEntity;
import fr.alb.backend.model.enums.PartnerType;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "partners")
public class Partner extends BaseEntity {

    @NotBlank
    private String name;

    @Enumerated(EnumType.STRING)
    private PartnerType type;

    private String websiteUrl;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Media image;

    private Integer displayOrder;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getWebsiteUrl() {
        return websiteUrl;
    }

    public void setWebsiteUrl(String websiteUrl) {
        this.websiteUrl = websiteUrl;
    }

    public Integer getDisplayOrder() {
        return displayOrder;
    }

    public void setDisplayOrder(Integer displayOrder) {
        this.displayOrder = displayOrder;
    }

    public PartnerType getType() {
        return type;
    }

    public void setType(PartnerType type) {
        this.type = type;
    }

    public Media getImage() {
        return image;
    }

    public void setImage(Media image) {
        this.image = image;
    }
}