package org.rupasree.entities;

import org.rupasree.exceptions.InvalidGameConstructionParametersException;
import org.rupasree.exceptions.InvalidMoveException;
import org.rupasree.strategies.gamewinningstrategy.GameWinningStrategy;
import org.rupasree.strategies.gamewinningstrategy.OrderOneGameWinningStrategy;

import java.util.*;

public class Game {
    private Board board;
    private Deque<Player> players;
    private GameStatus gameStatus;
    private GameWinningStrategy gameWinningStrategy;
    private List<Move> moves;

    private Player winner;
    public Board getBoard() {
        return board;
    }

    public void setBoard(Board board) {
        this.board = board;
    }

    public Deque<Player> getPlayers() {
        return players;
    }

    public void setPlayers(Deque<Player> players) {
        this.players = players;
    }

    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public GameWinningStrategy getGameWinningStrategy() {
        return gameWinningStrategy;
    }

    public void setGameWinningStrategy(GameWinningStrategy gameWinningStrategy) {
        this.gameWinningStrategy = gameWinningStrategy;
    }
    public void displayBoard() {
        this.board.displayBoard();
    }


    public void makeNextMove()  {
        Player turnPlayer = players.pollFirst();
        System.out.println("It is " + turnPlayer.getName() + "'s turn.");
        Move move;
        try {
            move = turnPlayer.makeMove(board);
        }
        catch (InvalidMoveException e){
            System.out.println(e.getMessage());
            players.offerFirst(turnPlayer);
            return;
        }
        int row = move.getCell().getRow();
        int col = move.getCell().getCol();
        board.getBoard().get(row).get(col).setCellType(CellType.FILLED);
        board.getBoard().get(row).get(col).setPlayer(turnPlayer);
        this.moves.add(move);

        boolean iswinner = gameWinningStrategy.decideWinner(board, turnPlayer, move.getCell());
        if (iswinner){
            this.gameStatus = GameStatus.ENDED;
            this.winner = turnPlayer;
        }
        players.offerLast(turnPlayer);
    }

    public Player getWinner() {
        return this.winner;
    }
    public static GameBuilder getBuilder() {
        return new GameBuilder();
    }

    public List<Move> getMoves() {
        return moves;
    }

    public void setMoves(List<Move> moves) {
        this.moves = moves;
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
