package com.trello.services;

import com.trello.constants.Constants;
import com.trello.enums.Access;
import com.trello.models.*;

import java.util.Map;

public class ApplicationService {
    private final Application application;

    public ApplicationService() {
        this.application = new Application();
    }

    public void createBoard(String boardName) {
        Board board = new Board(boardName);
        application.getBoards().put(board.getBoardId(), board);
        System.out.println("Created board: " + board.getBoardId());
    }

    public void deleteBoard(String boardId) {
        if (!application.getBoards().containsKey(boardId)) {
            System.out.println("The Board which you are trying to delete doesn't exit");
            return;
        }
        Board boardToDelete = application.getBoards().get(boardId);
        for (int index = 0; index < boardToDelete.getBoardLists().size(); index++) {
            deleteList(boardToDelete.getBoardLists().get(index).getBoardListId());
        }
        application.getBoards().remove(boardId);
    }

    public void setBoardName(String boardId, String boardName) {
        if (!application.getBoards().containsKey(boardId)) {
            System.out.println("The Board which you are trying to rename doesn't exit");
            return;
        }
        application.getBoards().get(boardId).setBoardName(boardName);
    }

    public void changeBoardPrivacy(String boardId, String privacy) {
        if (!application.getBoards().containsKey(boardId)) {
            System.out.println("The Board which you are trying to edit doesn't exit");
            return;
        }
        if (privacy.equalsIgnoreCase(Constants.PRIVATE)) {
            application.getBoards().get(boardId).setPrivacy(Access.PRIVATE);
        } else if (privacy.equalsIgnoreCase(Constants.PUBLIC)) {
            application.getBoards().get(boardId).setPrivacy(Access.PUBLIC);
        }
    }

    public void addMemberToBard(String boardId, String email) {
        if (!application.getBoards().containsKey(boardId)) {
            System.out.println("The Board which you are trying to edit doesn't exit");
            return;
        }
        if (!application.getUsers().containsKey(email)) {
            System.out.println("The User which you are trying to add doesn't exit");
            return;
        }
        User user = application.getUsers().get(email);
        application.getBoards().get(boardId).getUsers().add(user);
    }

    public void removeUserFromBoard(String boardId, String email) {
        if (!application.getBoards().containsKey(boardId)) {
            System.out.println("The Board which you are trying to edit doesn't exit");
            return;
        }
        if (!application.getUsers().containsKey(email)) {
            System.out.println("The User which you are trying to add doesn't exit");
            return;
        }
        User user = application.getUsers().get(email);
        application.getBoards().get(boardId).getUsers().remove(user);
    }

    public void showBoard(String boardId) {
        if (!application.getBoards().containsKey(boardId)) {
            System.out.println("The Board which you are trying to print doesn't exit");
            return;
        }
        System.out.println(application.getBoards().get(boardId));
    }

    public void createList(String boardId, String name) {
        if (!application.getBoards().containsKey(boardId)) {
            System.out.println("The Board where you are trying to add list doesn't exit");
            return;
        }
        BoardList newList = new BoardList(name, application.getBoards().get(boardId));

        application.getBoards().get(boardId).addList(newList);
        application.getLists().put(newList.getBoardListId(), newList);
        System.out.println("Created List: " + newList.getBoardListId());
    }

    public void deleteList(String boardListId) {
        if (!application.getLists().containsKey(boardListId)) {
            System.out.println("The List you are trying to delete doesn't exits");
            return;
        }
        BoardList listToDelete = application.getLists().get(boardListId);
        while (listToDelete.getCards() != null && listToDelete.getCards().size() > 0) {
            deleteCard(listToDelete.getCards().get(0).getCardId());
        }
        application.getBoards().get(listToDelete.getBoard().getBoardId()).removeList(listToDelete);
        application.getLists().remove(boardListId);
    }

    public void setListName(String boardListId, String listName) {
        if (!application.getLists().containsKey(boardListId)) {
            System.out.println("The List you are trying to edit doesn't exits");
            return;
        }

        application.getLists().get(boardListId).setBoardListName(listName);
    }

    public void showList(String boardListId) {
        if (!application.getLists().containsKey(boardListId)) {
            System.out.println("The List you are trying to print doesn't exits");
            return;
        }
        System.out.println(application.getLists().get(boardListId));
    }

    public void createCard(String boardListId, String cardName) {
        if (!application.getLists().containsKey(boardListId)) {
            System.out.println("The List you are trying to add card doesn't exits");
            return;
        }

        Card newCard = new Card(cardName, application.getLists().get(boardListId));
        application.getLists().get(boardListId).addCard(newCard);
        application.getCards().put(newCard.getCardId(), newCard);
        System.out.println("Created Card: + " + newCard.getCardId());
    }

    public void deleteCard(String cardId) {
        if (!application.getCards().containsKey(cardId)) {
            System.out.println("The Card you are trying to delete doesn't exist");
            return;
        }
        Card cardToDelete = application.getCards().get(cardId);
        application.getLists().get(cardToDelete.getBoardList().getBoardListId()).removeCard(cardToDelete);
        application.getCards().remove(cardId);

    }

    public void assignCardToMember(String cardId, String email) {
        if (!application.getCards().containsKey(cardId)) {
            System.out.println("The card which you are trying ot assign doesn't exist");
            return;
        }
        if (!application.getUsers().containsKey(email)) {
            System.out.println("The user is not exist");
            return;
        }
        application.getCards().get(cardId).setAssignee(application.getUsers().get(email));
    }

    public void unAssignCardToMember(String cardId) {
        if (!application.getCards().containsKey(cardId)) {
            System.out.println("The card which you are trying ot unAssign doesn't exist");
            return;
        }
        application.getCards().get(cardId).setAssignee(null);
    }

    public void setCardName(String cardId, String cardName) {
        if (!application.getCards().containsKey(cardId)) {
            System.out.println("The card which you are trying to edit doesn't exist");
            return;
        }
        application.getCards().get(cardId).setCardName(cardName);
    }

    public void setCardDescription(String cardId, String cardDescription) {
        if (!application.getCards().containsKey(cardId)) {
            System.out.println("The card which you are trying to edit doesn't exist");
            return;
        }
        application.getCards().get(cardId).setCardDescription(cardDescription);
    }

    public void moveCardToDifferentList(String cardId, String boardListId) {
        if (!application.getCards().containsKey(cardId)) {
            System.out.println("The card which you are trying to edit doesn't exist");
            return;
        }
        if (!application.getLists().containsKey(boardListId)) {
            System.out.println("The board which you are trying to edit doesn't exist");
            return;
        }
        Card card = application.getCards().get(cardId);
        BoardList parentList = card.getBoardList();
        if (parentList.getBoard().equals(application.getLists().get(boardListId).getBoard())) {
            parentList.getCards().remove(card);
            application.getLists().get(boardListId).addCard(card);
            card.setBoardList(application.getLists().get(boardListId));
        } else {
            System.out.println("The List Id which you provided is not the same board");
        }

    }

    public void showCard(String cardId) {
        if (!application.getCards().containsKey(cardId)) {
            System.out.println("The card which you are trying to print doesn't exist");
            return;
        }
        System.out.println(application.getCards().get(cardId));
    }

    public void showAll() {
        if (application.getBoards().isEmpty()) {
            System.out.println("No Board");
            return;
        }
        StringBuilder displayString = new StringBuilder("[ ");
        for (Map.Entry<String, Board> board : application.getBoards().entrySet()) {
            displayString.append(board.getValue());
            displayString.append(", ");
        }
        displayString.deleteCharAt(displayString.length()-2);
        displayString.append("]");
        System.out.println(new String(displayString));
    }

    public void createUser(String userId, String name, String email) {
        User user = new User(userId, name, email);
        application.getUsers().put(email, user);
        System.out.println("Created User: " + user.getUserId());
    }
    public void deleteUser(String email) {
        if (!application.getUsers().containsKey(email)) {
            System.out.println("The User you trying to delete doesn't exist");
            return;
        }
        application.getUsers().remove(email);
    }
}
