package com.company;
import java.awt.*;
import javax.swing.*;

public class Square extends JPanel implements Cloneable {
    public static final Color CUSTOM_BROWN = new Color(110,60, 0);
    public static final Color CUSTOM_GRAY = new Color(178, 187, 176, 255);
    private Piece piece;
    int x;
    int y;
    private JLabel content;
    private boolean isSelected=false;
    private boolean isCheck=false;
    private boolean isValidMove=false;

    public Square(int x, int y, Piece piece) {
        this.x=x;
        this.y=y;

        if((x+y)%2 == 0){
            setBackground(CUSTOM_BROWN);
        }
        else{
            setBackground(CUSTOM_GRAY);
        }
        if(piece!=null){
            setPiece(piece);
        }
    }

    //Constructor
    public Square(Square square) throws CloneNotSupportedException{
        this.x=square.x;
        this.y=square.y;
        setLayout(new BorderLayout());
        if((x+y)%2==0){
            setBackground(CUSTOM_BROWN);}
        else{
             setBackground(CUSTOM_GRAY);
        }
        if(square.getPiece()!=null){
            setPiece(square.getPiece().getCopy());
        }
        else{
            piece=null;
        }
    }

    public Piece getPiece(){
        return this.piece;
    }

    public void setPiece(Piece p) {
        this.piece = p;
        ImageIcon img=new javax.swing.ImageIcon(this.getClass().getResource((p.getPath())));
        content=new JLabel (img);
        this.add(content);
    }

    public void removePiece(){
        piece=null;
        this.remove(content);
    }

    public boolean isValidMove(){
        return this.isValidMove;
    }

    public void select(){
        this.setBorder(BorderFactory.createLineBorder(Color.BLUE,5));
        this.isSelected=true;
    }

    public boolean isSelected(){
        return this.isSelected;
    }

    public void deselect(){
        this.isSelected=false;
        this.setBorder(null);
    }

    public boolean isCheck(){
        return isCheck;
    }

    public void setCheck(){
        this.setBackground(Color.RED);
        this.isCheck=true;
    }

    public void removeCheck(){
        this.setBorder(null);
        if((x+y)%2==0){
            this.setBackground(CUSTOM_BROWN);
        }
        else{
            this.setBackground(CUSTOM_GRAY);
        }
        this.isCheck=false;
    }

    public void setPossibleDestination(){
        this.setBorder(BorderFactory.createLineBorder(Color.GREEN,5));
        this.isValidMove=true;
    }

    public void removePossibleDestination(){
        this.setBorder(null);
        this.isValidMove=false;
    }

}
