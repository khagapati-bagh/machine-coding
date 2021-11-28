package com.trello.models;

import lombok.Data;

public @Data
class User {
    private String userId;
    private String userName;
    private String userEmail;

    public User(String userId, String userName, String userEmail) {
        this.userId = userId;
        this.userName = userName;
        this.userEmail = userEmail;
    }

    @Override
    public String toString() {
        return "{ id : " + userId + ", name : " + userName + ", email: " + userEmail + " }";
    }
}
