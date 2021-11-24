package com.game.service;

import com.game.model.Board;
import com.game.model.Player;

public class BoardService {
    Board board;

    public BoardService() {
        this.board = new Board();
    }

    public void initialize(int size) {
        this.board.setSize(size);
        this.board.setEmptyCell(size * size);

        char[][] board = new char [size][size];
        for (int i = 0; i < size; i++) {
            for (int j = 0; j < size; j++) {
                board[i][j] = '-';
            }
        }
        this.board.setGameBoard(board);
        System.out.println("Board initialized successfully");
        printGameBoard();
    }

    private boolean checkLeftToRightDiagonal(char choice) {
        char [][] gameBoard = this.board.getGameBoard();
        int size = gameBoard.length;
        int i = 0, j = 0;
        while (i < size && j < size) {
            if (gameBoard[i][j] != choice) {
                return false;
            }
            i++;
            j++;
        }
        return true;
    }

    private boolean checkRightToLeftDiagonal(char choice) {
        char [][] gameBoard = this.board.getGameBoard();
        int size = gameBoard.length;
        int i = 0, j = size - 1;
        while (i < size && j >= 0) {
            if (gameBoard[i][j] != choice) {
                return false;
            }
            i++;
            j--;
        }
        return true;
    }

    private boolean checkRow(int x, char choice) {
        char [][] gameBoard = this.board.getGameBoard();
        for (int i = 0; i < gameBoard.length; i++) {
            if (gameBoard[x][i] != choice) {
                return false;
            }
        }
        return true;
    }

    private boolean checkColumn(int y, char choice) {
        char [][] gameBoard = this.board.getGameBoard();
        for (char[] chars : gameBoard) {
            if (chars[y] != choice) {
                return false;
            }
        }
        return true;
    }

    private boolean checkDiagonal(char choice) {
        return checkLeftToRightDiagonal(choice) || checkRightToLeftDiagonal(choice);
    }

    public boolean isGameOver(int x, int y, char choice) {
        return checkRow(x, choice) || checkColumn(y, choice) || checkDiagonal(choice);
    }

    private void printGameBoard() {
        char [][] gameBoard = this.board.getGameBoard();
        for (char[] chars : gameBoard) {
            for (char aChar : chars) {
                System.out.print(aChar + " ");
            }
            System.out.println();
        }
    }

    private boolean isValidMove(int x, int y) {
        char [][] gameBoard = this.board.getGameBoard();
        return x < gameBoard.length && x >= 0 &&
                y < gameBoard.length && y >= 0 &&
                gameBoard[x][y] == '-';
    }

    public boolean makeMove(int x, int y, Player player) {
        if (isValidMove(x, y)) {
            this.board.getGameBoard()[x][y] = player.getChoice();
            int emptyCell = this.board.getEmptyCell();
            this.board.setEmptyCell(emptyCell-1);
            printGameBoard();
        } else {
            System.out.println("Invalid move");
            return false;
        }
        return true;
    }

    public boolean isBoardFilled() {
        return this.board.getEmptyCell() == 0;
    }
}
