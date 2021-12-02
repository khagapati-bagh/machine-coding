package com.wallet.models;

import com.wallet.models.Account;
import com.wallet.models.User;
import lombok.Data;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public @Data
class Wallet {
    private Map<String, User> users;
    private Map<Integer, Account> accounts;
    private List<User> userList;

    public Wallet() {
        this.users = new HashMap<String, User>();
        this.accounts = new HashMap<>();
        this.userList = new ArrayList<>();
    }
}
