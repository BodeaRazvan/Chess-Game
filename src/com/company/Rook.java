package com.company;
import java.util.ArrayList;

public class Rook extends Piece{
    public Rook(String id, String path, int color){
        setColor(color);
        setPath(path);
        setId(id);
    }

    public ArrayList<Square> move(Square[][] pos, int x, int y){
        /*
         * Rooks can move up or down
         * Rooks cannot jump over pieces
         * We use while statements to check if the next square can be moved into
         * Stop after we reached a piece
         */
        possibleMoves.clear();

        int X,Y;

        // right
        X=x+1;
        while(X < 8){
            if(pos[X][y].getPiece()==null){
                possibleMoves.add(pos[X][y]);
            }else
            if(pos[X][y].getPiece().getColor()!=this.getColor()){
                possibleMoves.add(pos[X][y]);
                break;
            }else{
                break;
            }
            X++;
        }

        // left
        X=x-1;
        while(X >= 0) {
            if (pos[X][y].getPiece() == null) {
                possibleMoves.add(pos[X][y]);
            } else if (pos[X][y].getPiece().getColor() != this.getColor()) {
                possibleMoves.add(pos[X][y]);
                break;
            } else {
                break;
            }
            X--;
        }

        //up
        Y=y+1;
        while(Y < 8){
            if(pos[x][Y].getPiece()==null){
                possibleMoves.add(pos[x][Y]);
            }else
                if(pos[x][Y].getPiece().getColor()!=this.getColor()){
                    possibleMoves.add(pos[x][Y]);
                    break;
                }else{
                    break;
                }
                Y++;
        }

        //down
        Y=y-1;
        while(Y >= 0){
            if(pos[x][Y].getPiece()==null){
                possibleMoves.add(pos[x][Y]);
            }else
                if(pos[x][Y].getPiece().getColor()!=this.getColor()){
                    possibleMoves.add(pos[x][Y]);
                    break;
                }else{
                    break;
                }
                Y--;
        }

        return possibleMoves;
    }
}
