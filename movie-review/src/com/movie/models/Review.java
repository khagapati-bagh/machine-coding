package com.movie.models;

import lombok.Data;

import java.util.UUID;

public @Data class Review {
    private String reviewId;
    private int rate;
    private String description;
    private String movieName;

    public Review(int rate, String description, String movieName) {
        this.reviewId = UUID.randomUUID().toString();
        this.rate = rate;
        this.description = description;
        this.movieName = movieName;
    }
}
