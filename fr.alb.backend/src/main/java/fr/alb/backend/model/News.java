package fr.alb.backend.model;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.validation.constraints.NotBlank;

@Entity
public class News {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String title;

    private String subtitle;

    @NotBlank
    @Column(columnDefinition = "TEXT")
    private String content;

    private String imagePath;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;
}