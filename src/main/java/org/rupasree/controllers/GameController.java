package org.rupasree.controllers;

import org.rupasree.entities.Game;
import org.rupasree.entities.GameStatus;
import org.rupasree.entities.Player;
import org.rupasree.exceptions.InvalidGameConstructionParametersException;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.List;

public class GameController {
//    public void undo(Game game) {
//        game.undo();
//    }

    public Game createGame(int dimension, Deque<Player> players) {
        try {
            return Game.getBuilder()
                    .setDimension(dimension)
                    .setPlayers(players)
                    .build();
        } catch(Exception e) {
            throw new RuntimeException(e);
        } catch (InvalidGameConstructionParametersException e) {
            throw new RuntimeException(e);
        }

    }

    public void displayBoard(Game game) {
        game.displayBoard();
    }

    public GameStatus getGameStatus(Game game) {
        return game.getGameStatus();
    }

    public void executeNextMove(Game game) {
        game.makeNextMove();
    }

    public Player getWinner(Game game) {
        return game.getWinner();
    }
}
