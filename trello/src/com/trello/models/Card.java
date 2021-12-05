package com.trello.models;

import lombok.Data;

import java.util.UUID;

public @Data class Card {
    private String cardId;
    private String cardName;
    private String cardDescription;
    private User assignee;
    private BoardList boardList;

    public Card(String cardName, BoardList boardList) {
        this.cardId = UUID.randomUUID().toString();
        this.cardName = cardName;
        this.boardList = boardList;
    }

    @Override
    public String toString() {
        StringBuilder displayString = new StringBuilder("{ id : " + cardId + ", name : " + cardName);
        if (cardDescription != null && cardDescription.length() > 0) {
            displayString.append(", description : ").append(cardDescription);
        }
        if (assignee != null) {
            displayString.append(", assignTo : ").append(assignee.getUserEmail());
        }
        displayString.append(" }");
        return new String(displayString);
    }
}
