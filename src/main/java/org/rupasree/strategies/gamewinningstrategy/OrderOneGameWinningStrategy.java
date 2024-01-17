package org.rupasree.strategies.gamewinningstrategy;

import org.rupasree.entities.Board;
import org.rupasree.entities.Cell;
import org.rupasree.entities.Player;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class OrderOneGameWinningStrategy implements GameWinningStrategy{
    private final List<HashMap<Character, Integer>> rowSymbolCounts = new ArrayList<>();
    private final List<HashMap<Character, Integer>> colSymbolCounts = new ArrayList<>();
    private final HashMap<Character, Integer> topLeftDiagSymbolCounts = new HashMap<>();
    private final HashMap<Character, Integer> topRightDiagSymbolCounts = new HashMap<>();
    public boolean isCellOnTopLeftDiag(int row, int col) {
        return row == col;
    }
    public boolean isCellOnTopRightDiag(int row, int col, int dimension) {
        return row + col == dimension -1;
    }
    @Override
    public boolean decideWinner(Board board,Player player, Cell cell) {
        char symbol = player.getSymbol();
        int dimension = board.getBoard().size();
        int row = cell.getRow();
        int col = cell.getCol();

        if(!rowSymbolCounts.get(row).containsKey(symbol)){ rowSymbolCounts.get(row).put(symbol,0); }
        rowSymbolCounts.get(row).put(symbol,rowSymbolCounts.get(row).get(symbol) + 1);

        if(!colSymbolCounts.get(col).containsKey(symbol)){ colSymbolCounts.get(col).put(symbol,0); }
        colSymbolCounts.get(col).put(symbol,colSymbolCounts.get(col).get(symbol) + 1);

        if(isCellOnTopLeftDiag(row,col)){
            if(!topLeftDiagSymbolCounts.containsKey(symbol)){ topLeftDiagSymbolCounts.put(symbol,0);}
            topLeftDiagSymbolCounts.put(symbol,topLeftDiagSymbolCounts.get(symbol) + 1 );
        }
        if(isCellOnTopRightDiag(row,col,dimension)){
            if(!topRightDiagSymbolCounts.containsKey(symbol)) topRightDiagSymbolCounts.put(symbol,0);
            topRightDiagSymbolCounts.put(symbol, topRightDiagSymbolCounts.get(symbol) + 1 );
        }
        if (rowSymbolCounts.get(row).get(symbol) == dimension || colSymbolCounts.get(col).get(symbol) == dimension) {
            return true;
        }
        if (isCellOnTopRightDiag(row, col, dimension) && topRightDiagSymbolCounts.get(symbol) == dimension) return true;

        return isCellOnTopLeftDiag(row, col) && topLeftDiagSymbolCounts.get(symbol) == dimension;
    }
    public OrderOneGameWinningStrategy(int dimension) {
        for (int i = 0; i < dimension; ++i) {
            rowSymbolCounts.add(new HashMap<>());
            colSymbolCounts.add(new HashMap<>());
        }

    }
}
