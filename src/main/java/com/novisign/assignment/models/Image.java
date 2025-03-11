package com.novisign.assignment.models;

import com.novisign.assignment.validators.ImageValidation;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;

import java.util.Date;

@Entity
public class Image {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @ImageValidation(message = "not a valid URL of an image")
    private String url;
    @NotNull
    @Min(0)
    private Integer durationMillis;
    private Date createDate;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public Integer getDurationMillis() {
        return durationMillis;
    }

    public void setDurationMillis(Integer durationMillis) {
        this.durationMillis = durationMillis;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }
}
