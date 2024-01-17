package org.rupasree.strategies.gamewinningstrategy;

import org.rupasree.entities.Board;
import org.rupasree.entities.Cell;
import org.rupasree.entities.Player;

public interface GameWinningStrategy {

    boolean decideWinner(Board board, Player player, Cell cell);

}
