package com.cab.models;

import lombok.Data;

public @Data
class Location {
    private int x;
    private int y;

    public Location() {
        this.x = 0;
        this.y = 0;
    }

    public Location(int x, int y) {
        this.x = x;
        this.y = y;
    }
}
