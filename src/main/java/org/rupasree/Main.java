package org.rupasree;

import org.rupasree.controllers.GameController;
import org.rupasree.entities.*;

import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        GameController gameController = new GameController();

        System.out.println("What should be the dimension of game?");
        int dimension = scanner.nextInt();

        System.out.println("Will there be any bot? y/n");
        String isBotString = scanner.next();

        Deque<Player> players = new ArrayDeque<>();
        int noOfPlayers = dimension - 1;
        if(isBotString.equals('y')){
            noOfPlayers= dimension-2;
        }
        for (int i=0; i< noOfPlayers; i++){
            System.out.println("What is the name of the player");
            String name = scanner.next();

            System.out.println("What is the Character of the Player");
            String symbol = scanner.next();
            Player player = new Player(name, symbol.charAt(0), PlayerType.HUMAN);
            players.add(player);
        }
        if(isBotString.equals('y')){
            System.out.println("What is the name of the Bot");
            String name = scanner.next();

            System.out.println("What is the Character of the Bot");
            String symbol = scanner.next();

            Player player = new Bot(name, symbol.charAt(0), PlayerType.BOT, BotDifficultyLevel.EASY);
            players.add(player);
        }

        Game game = gameController.createGame(dimension, players);

        while (gameController.getGameStatus(game).equals(GameStatus.IN_PROGRESS)) {
            System.out.println("This is the current board:");
            gameController.displayBoard(game);
            gameController.executeNextMove(game);

        }
    }
}