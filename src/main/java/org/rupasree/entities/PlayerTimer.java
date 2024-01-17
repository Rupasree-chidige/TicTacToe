package org.rupasree.entities;


import java.util.TimerTask;

public class PlayerTimer extends TimerTask {
    private final Player turnPlayer;
    private boolean moveCompleted;

    public PlayerTimer(Player turnPlayer, boolean moveCompleted) {
        this.turnPlayer = turnPlayer;
        this.moveCompleted = moveCompleted;
    }

    @Override
    public void run() {
        if (!moveCompleted) {
            System.out.println(turnPlayer.getName() + " took too long to make a move. Game over!");
            // Implement logic to end the game or take appropriate action
        }
    }

    public void setMoveCompleted(boolean moveCompleted) {
        this.moveCompleted = moveCompleted;
    }
}
