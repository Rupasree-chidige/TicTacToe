package org.rupasree.strategies.botstrategies;

import org.rupasree.entities.*;

public class BotEasyPlayingStrategy implements BotPlayingStrategy {
    @Override
    public Move makeMove(Board board, Player player) {
        for (int i = 0; i < board.getBoard().size(); ++i) {
            for (int j = 0; j < board.getBoard().size(); ++j) {
                if (board.getBoard().get(i).get(j).getCellType().equals(CellType.EMPTY)) {
                    return new Move(new Cell(i, j), player);
                }
            }
        }
        return null;
    }
}
