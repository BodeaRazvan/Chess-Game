package com.company;
import java.util.ArrayList;

public class Bishop extends Piece{
    public Bishop(String id, String path, int color){
        setColor(color);
        setPath(path);
        setId(id);
    }

     public ArrayList<Square> move(Square pos[][],int x,int y){
        /*
         * Similar with the rook movement
         * A bishop can move diagonally
         * We use while statements to check if the squares on the diagonals are empty
         * Stop when we reach a piece
         */
         possibleMoves.clear();

         int X,Y;

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
