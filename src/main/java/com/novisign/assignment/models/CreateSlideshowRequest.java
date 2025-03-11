package com.novisign.assignment.models;

import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.List;

public class CreateSlideshowRequest {
    @NotBlank
    private String name;
    @NotNull
    private List<@Valid CreateSlideShowRequestImage> images;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<CreateSlideShowRequestImage> getImages() {
        return images;
    }

    public void setImages(List<CreateSlideShowRequestImage> images) {
        this.images = images;
    }

    public static class CreateSlideShowRequestImage {
        @NotNull
        private Long id;
        @NotNull
        @Min(0)
        private Integer durationMillis;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }

        public Integer getDurationMillis() {
            return durationMillis;
        }

        public void setDurationMillis(Integer durationMillis) {
            this.durationMillis = durationMillis;
        }
    }
}
