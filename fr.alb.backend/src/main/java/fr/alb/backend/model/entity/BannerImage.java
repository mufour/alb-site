package fr.alb.backend.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "banner_images")
public class BannerImage {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;

    private String description;

    @ManyToOne(optional = false)
    @JoinColumn(name = "image_id")
    private Media image;

    private Integer displayOrder;

    private Boolean active = true;

    // getters / setters
}