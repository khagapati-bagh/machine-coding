package com.trello.models;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public @Data class BoardList {
    private String boardListId;
    private String boardListName;
    private List<Card> cards;
    private Board board;

    public BoardList(String boardListName, Board board) {
        this.boardListId = UUID.randomUUID().toString();
        this.boardListName = boardListName;
        this.cards = new ArrayList<>();
        this.board = board;
    }

    public void removeCard(Card card) {
        this.cards.remove(card);
    }
    public void addCard(Card card) {
        this.cards.add(card);
    }

    @Override
    public String toString() {
        StringBuilder displayString = new StringBuilder("{ id : " + boardListId + ", name : " + boardListName);
        if (cards.size() > 0) {
            displayString.append(", cards : [");
            for (int index = 0; index < cards.size(); index++) {
                displayString.append(cards.get(index));
                if (index < cards.size() - 1) {
                    displayString.append(", ");
                }
            }
            displayString.append("]");
        }
        displayString.append(" }");
        return new String(displayString);
    }
}
