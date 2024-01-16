package org.rupasree.entities;

import org.rupasree.exceptions.InvalidMoveException;

import java.util.Scanner;

public abstract class Player {
    private String name;
    private char Symbol;
    private PlayerType playerType;

    public Player(String name, char symbol, PlayerType playerType) {
        this.name = name;
        Symbol = symbol;
        this.playerType = playerType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public char getSymbol() {
        return Symbol;
    }

    public void setSymbol(char symbol) {
        Symbol = symbol;
    }

    public PlayerType getPlayerType() {
        return playerType;
    }

    public void setPlayerType(PlayerType playerType) {
        this.playerType = playerType;
    }

    public Move makeMove(Board board) throws InvalidMoveException {
        Move move;
        Scanner scanner = new Scanner(System.in);
        System.out.println("Please tell the row, starting from :");
        int row = scanner.nextInt();
        System.out.println("Please tell the col, starting from 0:");
        int col = scanner.nextInt();
        if (row >= board.getBoard().size() || col >= board.getBoard().size()){
            throw new InvalidMoveException("No Cell at this Place. Please try Again");
        }
        if (board.getBoard().get(row).get(col).getCellType().equals(CellType.EMPTY)) {
            move = new Move(new Cell(row, col), this);
        } else {
            throw new InvalidMoveException("Cell is not empty. Please try Again");
        }
        return move;
    }

}
