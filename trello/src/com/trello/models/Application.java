package com.trello.models;

import lombok.Data;

import java.util.HashMap;
import java.util.Map;

public @Data class Application {
    private Map<String, Board> boards;
    private Map<String, User> users;
    private Map<String, BoardList> lists;
    private Map<String, Card> cards;

    public Application() {
        this.boards = new HashMap<>();
        this.users = new HashMap<>();
        this.lists = new HashMap<>();
        this.cards = new HashMap<>();
    }
}
