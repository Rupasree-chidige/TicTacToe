package org.rupasree.entities;

import lombok.Getter;
import lombok.Setter;
import org.rupasree.exceptions.InvalidGameConstructionParametersException;
import org.rupasree.exceptions.InvalidMoveException;
import org.rupasree.strategies.gamewinningstrategy.GameWinningStrategy;
import org.rupasree.strategies.gamewinningstrategy.OrderOneGameWinningStrategy;

import java.util.*;

@Setter
@Getter
public class Game {
    private Board board;
    private Deque<Player> players;
    private GameStatus gameStatus;
    private GameWinningStrategy gameWinningStrategy;
    private List<Move> moves;
    private Timer timer;
    private Player winner;

    public void displayBoard() {
        this.board.displayBoard();
    }
    public void makeNextMove() {
        Player turnPlayer = players.pollFirst();
        System.out.println("It is " + turnPlayer.getName() + "'s turn.");

        try {
            Move move = turnPlayer.makeMove(board);
            processMove(turnPlayer, move);
        } catch (InvalidMoveException e) {
            handleInvalidMove(turnPlayer, e.getMessage());
            return;
        }
        players.offerLast(turnPlayer);
    }

    private void processMove(Player turnPlayer, Move move) {
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();

        board.getBoard().get(row).get(col).setCellType(CellType.FILLED);
        board.getBoard().get(row).get(col).setPlayer(turnPlayer);

        moves.add(move);

        if (gameWinningStrategy.decideWinner(board, turnPlayer, move.getCell())) {
            endGame(turnPlayer);
        }
    }

    private void handleInvalidMove(Player turnPlayer, String errorMessage) {
        System.out.println(errorMessage);
        players.offerFirst(turnPlayer);
    }

    private void endGame(Player turnPlayer) {
        gameStatus = GameStatus.ENDED;
        winner = turnPlayer;
    }

    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

    public static class  GameBuilder{
        int dimension;
        Deque<Player> players;

        public GameBuilder setDimension(int dimension){
            this.dimension = dimension;
            return this;
        }
        public GameBuilder setPlayers(Deque<Player> players){
            this.players = players;
            return this;
        }
        private boolean valid() throws InvalidGameConstructionParametersException, Exception {
            if (this.dimension < 3) {
                throw new InvalidGameConstructionParametersException("Dimension of game can't be 1");
            }

            if (this.players.size() != this.dimension - 1) {
                throw new InvalidGameConstructionParametersException("Number of Players must be Dimension - 1");
            }
            // Validate no 2 people with same char
                Set<Character> symbolsSet = new HashSet<>();

                for (Player player : players) {
                    char symbol = player.getSymbol();
                    if (symbolsSet.contains(symbol)) throw new Exception("Players cannot have same Symbol");
                    symbolsSet.add(symbol);
                }
            // Validate all 1 bot

            return true;
        }
        public Game build() throws InvalidGameConstructionParametersException {
            try {
                valid();
            } catch (Exception e) {
                throw new InvalidGameConstructionParametersException(e.getMessage());
            }

            Game game = new Game();
            game.setGameStatus(GameStatus.IN_PROGRESS);
            game.setPlayers(players);
            game.setBoard(new Board(dimension));
            game.setMoves(new ArrayList<>());
            game.setGameWinningStrategy(new OrderOneGameWinningStrategy(dimension));
            return game;
        }
    }
}
