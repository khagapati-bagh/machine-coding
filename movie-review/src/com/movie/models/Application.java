package com.movie.models;

import lombok.Data;

import java.util.ArrayList;
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
        this.users = new HashMap<String, User>();
        this.movies = new HashMap<String, Movie>();
        this.reviews = new HashMap<String , Review>();
        this.userReview = new HashMap<String, List<String>>();
        this.movieReview = new HashMap<String, String >();
    }
}
