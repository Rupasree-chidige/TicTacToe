package org.rupasree.entities;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Setter
@Getter
public class Board {
    private List<List<Cell>> board;

    public void displayBoard(){
        for(int i=0; i <board.size(); i++){
            for(int j=0; j <board.size(); j++){
                if(board.get(i).get(j).getCellType().equals(CellType.EMPTY)){
                    System.out.print("| | ");
                }else{
                    System.out.printf("| "+board.get(i).get(j).getPlayer().getSymbol()+" | ");
                }

            }
            System.out.println();
        }
    }

    Board(int dimension) {
        this.board = new ArrayList<>();
        for( int i=0;i< dimension; i++){
            this.board.add(new ArrayList<>());
            for( int j=0;j< dimension; j++){
                this.board.get(i).add(new Cell(i,j));
            }
        }
    }
}
