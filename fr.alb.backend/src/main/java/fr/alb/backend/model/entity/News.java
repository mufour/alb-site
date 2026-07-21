package fr.alb.backend.model.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.*;

import fr.alb.backend.model.base.BaseEntity;

@Entity
@Table(name = "news")
public class News extends BaseEntity {

    @NotBlank
    @Size(max = 150)
    @Column(nullable = false)
    private String title;

    @Size(max = 350)
    private String subtitle;

    @NotBlank
    @Column(nullable = false, columnDefinition = "TEXT")
    private String content;

    @ManyToOne
    @JoinColumn(name = "image_id")
    private Media image;

    private Boolean published = false;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubtitle() {
        return subtitle;
    }

    public void setSubtitle(String subtitle) {
        this.subtitle = subtitle;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Media getImage() {
        return image;
    }

    public void setImage(Media image) {
        this.image = image;
    }

    public Boolean getPublished() {
        return published;
    }

    public void setPublished(Boolean published) {
        this.published = published;
    }
}