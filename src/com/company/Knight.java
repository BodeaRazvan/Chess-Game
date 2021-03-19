package com.company;
import java.util.ArrayList;

public class Knight extends Piece{

public Knight(String id,String path,int color){
    setColor(color);
    setPath(path);
    setId(id);
}
    public ArrayList<Square> move(Square[][] pos, int x, int y){
    /*
     * A knight has only 8 possible moves and it moves in the shape of an L
     * We use 2 direction vectors that calculate the next possible moves depending on the current position of the knight
     * The only piece that can jump over other pieces
     */
    possibleMoves.clear();

    int[] X ={x+1,x+1,x+2,x+2,x-1,x-1,x-2,x-2};
    int[] Y ={y-2,y+2,y-1,y+1,y-2,y+2,y-1,y+1};

    for(int i=0;i<8;i++){
        //check if the move is inside the chess board
        if(X[i]>=0 && X[i]<8 && Y[i]>=0 && Y[i]<8){
            //if the square is empty or there is a piece of the other color, the move is allowed
           if(pos[X[i]][Y[i]].getPiece()==null || pos[X[i]][Y[i]].getPiece().getColor()!=this.getColor()){
               possibleMoves.add(pos[X[i]][Y[i]]);
           }
        }
    }
        return possibleMoves;
    }
}
