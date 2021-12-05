package com.movie.models;

import com.movie.enums.CategoryType;
import lombok.Data;

import java.util.UUID;

public @Data class User {
    private String userId;
    private String userName;
    private CategoryType categoryType;

    public User(String userName) {
        this.userId = UUID.randomUUID().toString();
        this.userName = userName;
        this.categoryType = CategoryType.VIEWER;
    }
}
