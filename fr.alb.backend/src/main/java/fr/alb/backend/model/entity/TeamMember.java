package fr.alb.backend.model.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "team_members")
public class TeamMember {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String firstName;
    private String lastName;

    private String role;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Media image;

    private Integer displayOrder;

    // getters / setters
}
