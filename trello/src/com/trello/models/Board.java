package com.trello.models;

import com.trello.enums.Access;
import lombok.Data;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public @Data
class Board {
    private String boardId;
    private String boardName;
    private Access privacy;
    private List<User> users;
    private List<BoardList> boardLists;

    public Board(String boardName) {
        this.boardId = UUID.randomUUID().toString();
        this.boardName = boardName;
        this.privacy = Access.PUBLIC;
        this.users = new ArrayList<>();
        this.boardLists = new ArrayList<>();
    }

    public void removeList(BoardList boardList) {
        this.boardLists.remove(boardList);
    }

    public void addList(BoardList boardList) {
        this.boardLists.add(boardList);
    }

    @Override
    public String toString() {
        StringBuilder displayString = new StringBuilder("{ id : " + boardId + ", name : " + boardName + ", privacy : " + privacy);
        if (boardLists.size() > 0) {
            displayString.append(", lists: [");
            for (int index = 0; index < boardLists.size(); index++) {
                displayString.append(boardLists.get(index));
                if (index < boardLists.size() - 1) {
                    displayString.append(", ");
                }
            }
            displayString.append("]");
        }
        if (users.size() > 0) {
            displayString.append(", members : [");
            for (int index = 0; index < users.size(); index++) {
                displayString.append(users.get(index));
                if (index < users.size() - 1) {
                    displayString.append(", ");
                }
            }
            displayString.append("]");
        }
        displayString.append(" }");
        return new String(displayString);
    }
}
