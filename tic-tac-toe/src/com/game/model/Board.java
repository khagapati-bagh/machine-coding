package com.game.model;

public class Board {
    private int size;
    private int emptyCell;
    private char[][] gameBoard;

    public Board() {
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public int getEmptyCell() {
        return emptyCell;
    }

    public void setEmptyCell(int emptyCell) {
        this.emptyCell = emptyCell;
    }

    public char[][] getGameBoard() {
        return gameBoard;
    }

    public void setGameBoard(char[][] gameBoard) {
        this.gameBoard = gameBoard;
    }
}
