package fr.alb.backend.model.entity;

import fr.alb.backend.model.base.BaseEntity;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "banner_images")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BannerImage extends BaseEntity {

    @NotBlank
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    @NotNull
    @ManyToOne(optional = false)
    @JoinColumn(name = "image_id")
    private Media image;

    private Integer displayOrder;

    private Boolean active = true;
}