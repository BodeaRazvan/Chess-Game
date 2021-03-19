package com.company;
import java.util.ArrayList;

//Cloneable used to make copy of the pieces
public abstract class Piece implements Cloneable{
    private int color;
    private String path;
    private String id=null;
    protected ArrayList<Square> possibleMoves = new ArrayList<Square>();   //access to subclasses

    //the move method is overridden by each piece
    public abstract ArrayList<Square> move(Square[][] pos, int x, int y);

    //SETTERS
    public void setColor(int color){
        //set color of the piece
        this.color=color;
    }

    public void setId(String id){
        //set id of the piece
        this.id=id;
    }

    public void setPath(String path){
        //set path
       this.path=path;
    }

    //GETTERS
    public Piece getCopy() throws CloneNotSupportedException{
        //return a copy of the piece with different reference
        return (Piece) this.clone();
    }

    public int getColor(){
        //return color of the piece
        return this.color;
    }

    public String getPath(){
        //get path (for picture)
        return this.path;
    }

    public String getId(){
        return this.id;
    }


}

