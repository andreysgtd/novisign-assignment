package com.novisign.assignment.models;

import jakarta.persistence.*;

@Entity
public class SlideshowImage {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ManyToOne
    private Image image;
    private Integer durationMillis;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Image getImage() {
        return image;
    }

    public void setImage(Image image) {
        this.image = image;
    }

    public Integer getDurationMillis() {
        return durationMillis;
    }

    public void setDurationMillis(Integer durationMillis) {
        this.durationMillis = durationMillis;
    }
}
