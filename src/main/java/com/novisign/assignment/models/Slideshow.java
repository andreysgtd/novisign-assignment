package com.novisign.assignment.models;

import jakarta.persistence.*;

import java.util.List;

@Entity
public class Slideshow {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    @OneToMany(cascade = CascadeType.ALL)
    private List<SlideshowImage> images;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<SlideshowImage> getImages() {
        return images;
    }

    public void setImages(List<SlideshowImage> images) {
        this.images = images;
    }
}
