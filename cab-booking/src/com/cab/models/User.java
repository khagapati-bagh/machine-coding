package com.cab.models;

import javafx.util.Pair;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public @Data
class User {
    private String name;
    Map<String, Pair<Integer, Integer>> rate;
    List<Rating> ratings;
    private int totalRating;
    private Location currentLocation;

    public User(String name) {
        this.name = name;
        this.rate = new HashMap<>();
        //this.ratings = new ArrayList<>();
        this.totalRating = 0;
        this.currentLocation = new Location();
    }

    public void addRating(String name, int rating) {
        Pair<Integer, Integer> currentRate = new Pair<>(0, 0);
        if (rate.containsKey(name)) {
            currentRate = rate.get(name);
        }
        rate.put(name, new Pair<>(currentRate.getKey() + rating, currentRate.getValue() + 1));
        //ratings.add(new Rating(name, rating));
        totalRating += rating;
    }

    public double getAverageRating() {
        if (rate.size() == 0) {
            return 0;
        }
        double rating = (double) totalRating/rate.size();
        return Double.parseDouble(String.format("%.2f", rating));
    }

    public double getRateAverageRating(String name) {
        Pair<Integer, Integer> currentRating = rate.getOrDefault(name, new Pair<>(0,1));
        double rating = currentRating.getKey() / currentRating.getValue();
        return Double.parseDouble(String.format("%.2f", rating));
    }
}
