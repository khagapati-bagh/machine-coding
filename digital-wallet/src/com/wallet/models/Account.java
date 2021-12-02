package com.wallet.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.atomic.AtomicInteger;

public @Data class Account {
    public static final AtomicInteger count = new AtomicInteger(0);
    private String id;
    private int number;
    private double balance;
    private double fdBalance;
    private String history;
    private List<String> statements;
    private int noOfTransaction;
    private int noOfTransactionAfterFD;

    public Account(double balance) {
        this.id = UUID.randomUUID().toString();
        this.number = count.incrementAndGet();
        this.balance = balance;
        this.fdBalance = 0.0;
        this.history = "";
        this.statements = new ArrayList<>();
        this.noOfTransaction = 0;
        this.noOfTransactionAfterFD = -1;
    }

    public void addStatement(String statement) {
        this.statements.add(statement);
    }
}
