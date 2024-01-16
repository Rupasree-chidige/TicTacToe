package org.rupasree.strategies.gamewinningstrategy;

import org.rupasree.entities.Board;
import org.rupasree.entities.Player;

public interface GameWinningStrategy {

    Player decideWinner(Board board);
}
