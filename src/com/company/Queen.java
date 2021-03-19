package com.company;
import java.util.ArrayList;

public class Queen extends Piece{
    public Queen(String id, String path, int color){
        setColor(color);
        setPath(path);
        setId(id);
    }

    public ArrayList<Square> move(Square[][] pos, int x, int y){
        /*
         * A queen can move in all 8 possible directions
         * The queen moves are a combination of the Rook and Bishop moves
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

        //right and up diagonal
        X=x+1;
        Y=y+1;
        while(Y<8 && X<8){
            if(pos[X][Y].getPiece()==null){
                possibleMoves.add(pos[X][Y]);
            }else
            if(pos[X][Y].getPiece().getColor()!=this.getColor()){
                possibleMoves.add(pos[X][Y]);
                break;
            }
            else{
                break;
            }
            X++;
            Y++;
        }

        //left down diagonal
        X=x-1;
        Y=y-1;
        while(X>=0 && Y>=0){
            if(pos[X][Y].getPiece()==null){
                possibleMoves.add(pos[X][Y]);
            }else
            if(pos[X][Y].getPiece().getColor()!=this.getColor()){
                possibleMoves.add(pos[X][Y]);
                break;
            }
            else{
                break;
            }
            X--;
            Y--;
        }

        //left up diagonal
        X=x-1;
        Y=y+1;
        while(X>=0 && Y<8){
            if(pos[X][Y].getPiece()==null){
                possibleMoves.add(pos[X][Y]);
            }else
            if(pos[X][Y].getPiece().getColor()!=this.getColor()){
                possibleMoves.add(pos[X][Y]);
                break;
            }
            else{
                break;
            }
            X--;
            Y++;
        }

        //right down diagonal
        X=x+1;
        Y=y-1;
        while(X<8 && Y>=0){
            if(pos[X][Y].getPiece()==null){
                possibleMoves.add(pos[X][Y]);
            }else
            if(pos[X][Y].getPiece().getColor()!=this.getColor()){
                possibleMoves.add(pos[X][Y]);
                break;
            }
            else{
                break;
            }
            X++;
            Y--;
        }
        return possibleMoves;
    }
}
