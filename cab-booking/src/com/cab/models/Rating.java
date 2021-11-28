package com.cab.models;

import lombok.Data;

public @Data class Rating {
    private String name;
    private int rate;

    public Rating(String name, int rating) {
        this.name = name;
        this.rate = rating;
    }
}
