package fr.alb.backend.model.entity;

import fr.alb.backend.model.base.BaseEntity;
import jakarta.persistence.*;

import lombok.Getter;
import lombok.Setter;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "team_members")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class TeamMember extends BaseEntity {

    private String firstName;
    private String lastName;

    private String role;

    @Column(columnDefinition = "TEXT")
    private String bio;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Media image;

    private Integer displayOrder;
}
