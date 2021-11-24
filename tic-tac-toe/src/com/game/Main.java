package com.game;

import com.game.constant.Constant;
import com.game.model.Player;
import com.game.service.BoardService;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {
        // write your code here
        BoardService boardService = new BoardService();

        List<Player> playerList = new ArrayList<>();

        Scanner scanner = new Scanner(System.in);

        int numberOfPlayer = 0;
        while (numberOfPlayer == 0) {
            //System.out.println("Enter Number of player");
            numberOfPlayer = scanner.nextInt();
            if (numberOfPlayer == 0) {
                //System.out.println("Game can't be started without any player");
            }
        }

        for (int i = 0; i < numberOfPlayer; i++) {
            //System.out.println("Enter Choice and name like: X Radhe");
            String choice = scanner.next();
            String name = scanner.next();
            Player player = new Player(name, choice.toUpperCase().charAt(0));
            playerList.add(player);
            System.out.println(player.toString());
        }

        //System.out.println("Enter Board Size");
        int boardSize = scanner.nextInt();
        int index = 0, playerListSize = playerList.size();

        boardService.initialize(boardSize);

        while (true) {
            Player player = playerList.get(index % playerListSize);
            //System.out.println(player.getName() + " Enter location to insert " + player.getChoice());
            String input1 = scanner.next();
            if (input1.equalsIgnoreCase(Constant.EXIT)) {
                System.out.println("Game Exited");
                break;
            }
            String input2 = scanner.next();
            int x = Integer.parseInt(input1) - 1;
            int y = Integer.parseInt(input2) - 1;
            if (boardService.makeMove(x, y, player)) {
                if (boardService.isGameOver(x, y, player.getChoice())) {
                    System.out.println(player.getName() + " Won the game");
                    break;
                }
                if (boardService.isBoardFilled()) {
                    break;
                }
                index++;
            }
        }
        System.out.println("Game Over");
    }
}
