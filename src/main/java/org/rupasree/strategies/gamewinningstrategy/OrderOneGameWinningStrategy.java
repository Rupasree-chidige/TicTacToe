package org.rupasree.strategies.gamewinningstrategy;

import org.rupasree.entities.Board;
import org.rupasree.entities.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy{
    private List<HashMap<Character, Integer>> rowSymbolCounts = new ArrayList<>();
    private List<HashMap<Character, Integer>> colSymbolCounts = new ArrayList<>();
    @Override
    public Player decideWinner(Board board) {
        return null;
    }
    public OrderOneGameWinningStrategy(int dimension) {
        for (int i = 0; i < dimension; ++i) {
            rowSymbolCounts.add(new HashMap<>());
            colSymbolCounts.add(new HashMap<>());
        }
    }
}
