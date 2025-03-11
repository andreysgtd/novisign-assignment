package com.novisign.assignment.models;

import jakarta.validation.constraints.NotNull;

public class ImageSearchRequest {
    @NotNull
    private String[] keywords;
    @NotNull
    private Integer minDurationMillis;
    @NotNull
    private Integer maxDurationMillis;

    public String[] getKeywords() {
        return keywords;
    }

    public void setKeywords(String[] keywords) {
        this.keywords = keywords;
    }

    public Integer getMinDurationMillis() {
        return minDurationMillis;
    }

    public void setMinDurationMillis(Integer minDurationMillis) {
        this.minDurationMillis = minDurationMillis;
    }

    public Integer getMaxDurationMillis() {
        return maxDurationMillis;
    }

    public void setMaxDurationMillis(Integer maxDurationMillis) {
        this.maxDurationMillis = maxDurationMillis;
    }
}
