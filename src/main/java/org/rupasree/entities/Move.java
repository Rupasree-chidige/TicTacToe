package org.rupasree.entities;

public class Move {
    public Move(Cell cell, Player player) {
        this.cell = cell;
        this.player = player;
    }

    Cell cell;
    Player player;


    public Player getPlayer() {
        return player;
    }

    public void setPlayer(Player player) {
        this.player = player;
    }

    public Cell getCell() {
        return cell;
    }

    public void setCell(Cell cell) {
        this.cell = cell;
    }
}
