package com.trello;

import com.trello.constants.Constants;
import com.trello.services.ApplicationService;

import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
	// write your code here
        Scanner input = new Scanner(System.in);
        ApplicationService applicationService = new ApplicationService();

        while(input.hasNextLine()) {
            String line = input.nextLine().trim();
            if (line.equalsIgnoreCase("EXIT")) {
                break;
            }
            String[] commands = line.split(" ");
            int inputLength = commands.length;
            switch (commands[0].toUpperCase()) {
                case Constants.SHOW:
                    if (inputLength == 1) {
                        applicationService.showAll();
                    } else if (inputLength >= 3) {
                        switch (commands[1].toUpperCase()) {
                            case Constants.BOARD:
                                applicationService.showBoard(commands[2]);
                                break;
                            case Constants.LIST:
                                applicationService.showList(commands[2]);
                                break;
                            case Constants.CARD:
                                applicationService.showCard(commands[2]);
                                break;
                            default:
                                System.out.println("Invalid Input");
                                break;
                        }
                    }
                    break;
                case Constants.BOARD:
                    if (inputLength >= 3) {
                        if (commands[1].equalsIgnoreCase(Constants.CREATE)) {
                            applicationService.createBoard(commands[2]);
                        } else if (commands[1].equalsIgnoreCase(Constants.DELETE)) {
                            applicationService.deleteBoard(commands[2]);
                        } else if (commands[2].equalsIgnoreCase(Constants.NAME)) {
                            applicationService.setBoardName(commands[1], commands[3]);
                        } else if (commands[2].equalsIgnoreCase(Constants.PRIVACY)) {
                            applicationService.changeBoardPrivacy(commands[1], commands[3]);
                        } else if (commands[2].equalsIgnoreCase(Constants.ADD_MEMBER)) {
                            applicationService.addMemberToBard(commands[1], commands[3]);
                        } else if (commands[2].equalsIgnoreCase(Constants.REMOVE_MEMBER)) {
                            applicationService.removeUserFromBoard(commands[1], commands[3]);
                        }
                    } else {
                        System.out.println("Invalid Input");
                    }
                    break;
                case Constants.LIST:
                    if (inputLength > 1) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int index = 3; index < inputLength; index++) {
                            stringBuilder.append(commands[index]);
                            if (index < inputLength - 1) {
                                stringBuilder.append(' ');
                            }
                        }
                        if (commands[1].equalsIgnoreCase(Constants.CREATE)) {
                            applicationService.createList(commands[2], new String(stringBuilder));
                        } else if (commands[1].equalsIgnoreCase(Constants.DELETE)) {
                            applicationService.deleteList(commands[2]);
                        } else if (commands[2].equalsIgnoreCase(Constants.NAME)) {
                            applicationService.setListName(commands[1], new String(stringBuilder));
                        }
                    } else {
                        System.out.println("Invalid input");
                    }
                    break;
                case Constants.CARD:
                    if (inputLength > 1) {
                        StringBuilder stringBuilder = new StringBuilder();
                        for (int index = 3; index < inputLength; index++) {
                            stringBuilder.append(commands[index]);
                            if (index < inputLength - 1) {
                                stringBuilder.append(' ');
                            }
                        }
                        if (commands[1].equalsIgnoreCase(Constants.CREATE)) {
                            applicationService.createCard(commands[2], new String(stringBuilder));
                        } else if (commands[1].equalsIgnoreCase(Constants.DELETE)) {
                            applicationService.deleteCard(commands[2]);
                        } else if (commands[2].equalsIgnoreCase(Constants.NAME)) {
                            applicationService.setCardName(commands[1], new String(stringBuilder));
                        } else if (commands[2].equalsIgnoreCase(Constants.DESCRIPTION)) {
                            applicationService.setCardDescription(commands[1], new String(stringBuilder));
                        } else if (commands[2].equalsIgnoreCase(Constants.ASSIGN)) {
                            applicationService.assignCardToMember(commands[1], commands[3]);
                        } else if (commands[2].equalsIgnoreCase(Constants.UN_ASSIGN)) {
                            applicationService.unAssignCardToMember(commands[1]);
                        } else if (commands[2].equalsIgnoreCase(Constants.MOVE)) {
                            applicationService.moveCardToDifferentList(commands[1], commands[3]);
                        }
                    }
                    break;
                case Constants.USER:
                    if (commands[1].equalsIgnoreCase(Constants.CREATE)) {
                        applicationService.createUser(commands[2], commands[3], commands[4]);
                    } else if (commands[1].equalsIgnoreCase(Constants.DELETE)) {
                        applicationService.deleteUser(commands[2]);
                    }
                    break;
            }
        }

        input.close();
    }
}
