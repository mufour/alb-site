package fr.alb.backend.model.entity;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "events")
public class Event {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private LocalDate startDate;
    private LocalDate endDate;

    private String location;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Media image;

    private Boolean published = false;

    // getters / setters
}