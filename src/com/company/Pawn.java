package com.company;
import java.util.ArrayList;

public class Pawn extends Piece{
    public Pawn(String id, String path, int color){
       setColor(color);
       setPath(path);
       setId(id);
    }

    public ArrayList<Square> move(Square[][] pos, int x, int y){
        /* One of the more complex pieces
         * It can move only 1 square except the 1st move when it can move either 1 or 2
         * It can attack only in diagonal
         * It can only move forward
         * Special move: en passant (if a pawn of the opposite color moved 2 spaces and now is to the left or right
         *                           of the other player's pawn, this pawn can take in diagonal and move 1 space
         *                           forward reaching behind the square where the taken pawn was   This turn only!)
         * When a pawn reaches the end of the board, it can become one of the other pieces except a king/pawn
         */

        possibleMoves.clear();

        if(getColor()==0){
            if(x==0){
                return possibleMoves;
            }
            if(pos[x-1][y].getPiece()==null){
                possibleMoves.add(pos[x-1][y]);
                if(x==6){
                    if(pos[4][y].getPiece()==null){
                        possibleMoves.add(pos[4][y]);
                    }
                }
            }
            if((y>0) && (pos[x-1][y-1].getPiece()!=null) && (pos[x-1][y-1].getPiece().getColor()!=this.getColor())){
                possibleMoves.add(pos[x-1][y-1]);
            }
            if((y<7) && (pos[x-1][y+1].getPiece()!=null) && (pos[x-1][y+1].getPiece().getColor()!=this.getColor())){
                possibleMoves.add(pos[x-1][y+1]);
            }
        }
        else{
            if(x==8){
                return possibleMoves;
            }
            if(pos[x+1][y].getPiece()==null){
                possibleMoves.add(pos[x+1][y]);
                if(x==1){
                    if(pos[3][y].getPiece()==null){
                        possibleMoves.add(pos[3][y]);
                    }
                }
            }
            if((y>0) && (pos[x+1][y-1].getPiece()!=null) && (pos[x+1][y-1].getPiece().getColor()!=this.getColor())){
                possibleMoves.add(pos[x+1][y-1]);
            }
            if((y<7) && (pos[x+1][y+1].getPiece()!=null) && (pos[x+1][y+1].getPiece().getColor()!=this.getColor())){
                possibleMoves.add(pos[x+1][y+1]);
            }
        }

        return possibleMoves;
    }
}
