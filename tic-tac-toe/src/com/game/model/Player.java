package com.game.model;

import java.util.UUID;

public class Player {
    private String playerId;
    private String name;
    private char choice;

    public Player(String name, char choice) {
        this.playerId = UUID.randomUUID().toString();
        this.name = name;
        this.choice = choice;
    }

    public Player() {
    }

    public String getPlayerId() {
        return playerId;
    }

    public String getName() {
        return name;
    }

    public char getChoice() {
        return choice;
    }

    public void setPlayerId(String playerId) {
        this.playerId = playerId;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setChoice(char choice) {
        this.choice = choice;
    }

    @Override
    public String toString() {
        return "Player{" +
                "playerId='" + playerId + '\'' +
                ", name='" + name + '\'' +
                ", choice=" + choice +
                '}';
    }
}
