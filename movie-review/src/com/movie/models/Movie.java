package com.movie.models;

import lombok.Data;

import java.util.UUID;

public @Data class Movie {
    private String movieId;
    private String movieName;
    private String genre;
    private int releaseYear;
    private boolean releaseFlag;
    private double rating;

    public Movie(String movieName, String genre, int releaseYear, boolean releaseFlag) {
        this.movieId = UUID.randomUUID().toString();
        this.movieName = movieName;
        this.genre = genre;
        this.releaseYear = releaseYear;
        this.releaseFlag = releaseFlag;
        this.rating = 0.0;
    }
}
