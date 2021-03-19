package com.company;
import java.util.ArrayList;

public class King extends Piece{
    //the most complex chess piece
    //must be checked if the squares the king moves to are attacked or not
    //must check if the king is in check
    //check mate

    private int x,y;

    public King (int x, int y, String id, String path, int color){
        setX(x);
        setY(y);
        setPath(path);
        setId(id);
        setColor(color);
    }

    public void setX(int x){
        this.x=x;
    }

    public void setY(int y){
        this.y=y;
    }

    public int getX(){
        return this.x;
    }

    public int getY(){
        return this.y;
    }

    public ArrayList<Square> move(Square[][] pos, int x, int y){
        possibleMoves.clear();
        int[] X ={x,x,x+1,x+1,x+1,x-1,x-1,x-1};
        int[] Y ={y-1,y+1,y-1,y,y+1,y-1,y,y+1};
        for(int i=0;i<8;i++){
            if((X[i]>=0 && X[i]<8) && (Y[i]>=0 && Y[i]<8)){
                if(pos[X[i]][Y[i]].getPiece()==null || pos[X[i]][Y[i]].getPiece().getColor()!=this.getColor()){
                    possibleMoves.add(pos[X[i]][Y[i]]);
                }
            }
        }
        return possibleMoves;
    }

    public boolean isAttacked(Square[][] pos){
        //for up and down  check if there is a Queen or a rook directly connected to the king
        //for diagonal Check if there is a Queen or Bishop directly connected to the king
        int diagX=x+1,diagY=y-1;
        //check if the king is attacked by a knight
        int[] KnightX ={x+1,x+1,x+2,x+2,x-1,x-1,x-2,x-2};
        int[] KnightY ={y-2,y+2,y-1,y+1,y-2,y+2,y-1,y+1};
        //check if the king is attacked by a pawn (the direction of the check depends on the color of the pawn)
        //check if the king can move because of the other king
        int[] X ={x+1,x+1,x+1,x,x,x-1,x-1,x-1};
        int[] Y ={y-1,y+1,y,y+1,y-1,y+1,y-1,y};

        for(int i=x+1;i<8;i++) {
            if(pos[i][y].getPiece()==null)
                continue;
            else if(pos[i][y].getPiece().getColor()==this.getColor())
                break;
            else {
                if ((pos[i][y].getPiece() instanceof Rook) || (pos[i][y].getPiece() instanceof Queen))
                    return true;
                else
                    break;
            }
        }

        for(int i=x-1;i>=0;i--) {
            if(pos[i][y].getPiece()==null)
                continue;
            else if(pos[i][y].getPiece().getColor()==this.getColor())
                break;
            else {
                if ((pos[i][y].getPiece() instanceof Rook) || (pos[i][y].getPiece() instanceof Queen))
                    return true;
                else
                    break;
            }
        }

        for(int i=y+1;i<8;i++) {
            if(pos[x][i].getPiece()==null)
                continue;
            else if(pos[x][i].getPiece().getColor()==this.getColor())
                break;
            else {
                if ((pos[x][i].getPiece() instanceof Rook) || (pos[x][i].getPiece() instanceof Queen))
                    return true;
                else
                    break;
            }
        }

        for(int i=y-1;i>=0;i--) {
            if(pos[x][i].getPiece()==null)
                continue;
            else if(pos[x][i].getPiece().getColor()==this.getColor())
                break;
            else
            {
                if ((pos[x][i].getPiece() instanceof Rook) || (pos[x][i].getPiece() instanceof Queen))
                    return true;
                else
                    break;
            }
        }

        while(diagX<8 && diagY>=0) {
            if(pos[diagX][diagY].getPiece()==null) {
                diagX++;
                diagY--;
            }
            else if(pos[diagX][diagY].getPiece().getColor()==this.getColor())
                break;
            else {
                if (pos[diagX][diagY].getPiece() instanceof Bishop || pos[diagX][diagY].getPiece() instanceof Queen)
                    return true;
                else
                    break;
            }
        }

        diagX=x-1;diagY=y+1;
        while(diagX>=0 && diagY<8) {
            if(pos[diagX][diagY].getPiece()==null) {
                diagX--;
                diagY++;
            }
            else if(pos[diagX][diagY].getPiece().getColor()==this.getColor())
                break;
            else {
                if (pos[diagX][diagY].getPiece() instanceof Bishop || pos[diagX][diagY].getPiece() instanceof Queen)
                    return true;
                else
                    break;
            }
        }

        diagX=x-1;diagY=y-1;
        while(diagX>=0 && diagY>=0) {
            if(pos[diagX][diagY].getPiece()==null) {
                diagX--;
                diagY--;
            }
            else if(pos[diagX][diagY].getPiece().getColor()==this.getColor())
                break;
            else {
                if (pos[diagX][diagY].getPiece() instanceof Bishop || pos[diagX][diagY].getPiece() instanceof Queen)
                    return true;
                else
                    break;
            }
        }

        diagX=x+1;diagY=y+1;
        while(diagX<8 && diagY<8) {
            if(pos[diagX][diagY].getPiece()==null) {
                diagX++;
                diagY++;
            }
            else if(pos[diagX][diagY].getPiece().getColor()==this.getColor())
                break;
            else {
                if (pos[diagX][diagY].getPiece() instanceof Bishop || pos[diagX][diagY].getPiece() instanceof Queen)
                    return true;
                else
                    break;
            }
        }

        for(int i=0;i<8;i++)
            if((KnightX[i]>=0&&KnightX[i]<8&&KnightY[i]>=0&&KnightY[i]<8))
                if(pos[KnightX[i]][KnightY[i]].getPiece()!=null && pos[KnightX[i]][KnightY[i]].getPiece().getColor()!=this.getColor() && (pos[KnightX[i]][KnightY[i]].getPiece() instanceof Knight)) {
                    return true;
                }

        for(int i=0;i<8;i++)
            if((X[i]>=0&&X[i]<8&&Y[i]>=0&&Y[i]<8))
                if(pos[X[i]][Y[i]].getPiece()!=null && pos[X[i]][Y[i]].getPiece().getColor()!=this.getColor() && (pos[X[i]][Y[i]].getPiece() instanceof King)) {
                    return true;
                }

        if(getColor()==0) {
            if(x>0&&y>0&& pos[x-1][y-1].getPiece()!=null&& pos[x-1][y-1].getPiece().getColor()==1&&(pos[x-1][y-1].getPiece() instanceof Pawn))
                return true;
            if(x>0&&y<7&& pos[x-1][y+1].getPiece()!=null&& pos[x-1][y+1].getPiece().getColor()==1&&(pos[x-1][y+1].getPiece() instanceof Pawn))
                return true;
        }
        else {
            if(x<7&&y>0&& pos[x+1][y-1].getPiece()!=null&& pos[x+1][y-1].getPiece().getColor()==0&&(pos[x+1][y-1].getPiece() instanceof Pawn))
                return true;
            if(x<7&&y<7&& pos[x+1][y+1].getPiece()!=null&& pos[x+1][y+1].getPiece().getColor()==0&&(pos[x+1][y+1].getPiece() instanceof Pawn))
                return true;
        }
        return false;
    }
}
