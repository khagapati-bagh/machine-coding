package com.wallet.models;

import lombok.Data;

import java.util.concurrent.atomic.AtomicInteger;

public @Data
class User {
    public static final AtomicInteger count = new AtomicInteger(0);
    private String userName;
    private int userId;
    private int accountNumber;

    public User(String userName, int accountNumber) {
        this.userName = userName;
        this.userId = count.incrementAndGet();
        this.accountNumber = accountNumber;
    }
}
