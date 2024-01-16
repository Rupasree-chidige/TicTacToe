package org.rupasree.strategies.botstrategies;

import org.rupasree.entities.Board;
import org.rupasree.entities.Move;
import org.rupasree.entities.Player;

public interface BotPlayingStrategy {
    Move makeMove(Board board, Player player);
}
