package com.movie.models;

import lombok.Data;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public @Data class Application {
    Map<String, User> users;
    Map<String, Movie> movies;
    Map<String, Review> reviews;
    Map<String, List<String>> userReview;
    Map<String, String> movieReview;

    public Application() {
        this.users = new HashMap<>();
        this.movies = new HashMap<>();
        this.reviews = new HashMap<>();
        this.userReview = new HashMap<>();
        this.movieReview = new HashMap<>();
    }
}
