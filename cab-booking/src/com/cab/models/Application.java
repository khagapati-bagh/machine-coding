package com.cab.models;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public @Data class Application {
    private Map<String, User> customers;
    private Map<String, Driver> drivers;
    private StringBuilder history;

    public Application() {
        this.customers = new HashMap<>();
        this.drivers = new HashMap<>();
        this.history = new StringBuilder();
    }
}
