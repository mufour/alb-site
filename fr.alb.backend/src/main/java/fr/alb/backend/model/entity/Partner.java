package fr.alb.backend.model.entity;

import fr.alb.backend.model.enums.PartnerType;
import jakarta.persistence.*;

@Entity
@Table(name = "partners")
public class Partner {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @Enumerated(EnumType.STRING)
    private PartnerType type;

    private String websiteUrl;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Media image;

    private Integer displayOrder;

    // getters / setters
}