package com.company;
import javax.swing.*;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.BufferedImage;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.ListIterator;

public class Main extends JFrame implements MouseListener {
    private static final int Height=700;
    private static final int Width=1110;
    private Time timer;
    public static int timeLeft=60;
    public static Main MainBoard;
    private static Pawn[] whitePawn;
    private static Pawn[] blackPawn;
    private static Rook whiteRook1, whiteRook2, blackRook1, blackRook2;
    private static Queen whiteQueen, blackQueen;
    private static King whiteKing, blackKing;
    private static Knight whiteKnight1, whiteKnight2, blackKnight1, blackKnight2;
    private static Bishop whiteBishop1, whiteBishop2, blackBishop1, blackBishop2;
    private Square selectedSquare, previousSquare;
    private int turn =0;
    private Square boardState[][];
    private ArrayList<Square> destinationList =new ArrayList<Square>();
    private Player White=null;
    private Player Black=null;
    private JPanel board=new JPanel(new GridLayout(8,8));
    private JPanel wdetails=new JPanel(new GridLayout(3,3));
    private JPanel bdetails=new JPanel(new GridLayout(3,3));
    private JPanel wcombopanel=new JPanel();
    private JPanel bcombopanel=new JPanel();
    private JPanel controlPanel,WhitePlayer,BlackPlayer,temp,displayTime,showPlayer,time;
    private JSplitPane split;
    private JLabel label,mov;
    private static JLabel CHNC;
    private boolean selected=false,end=false;
    private Container content;
    private ArrayList<Player> wplayer,bplayer;
    private ArrayList<String> Wnames=new ArrayList<String>();
    private ArrayList<String> Bnames=new ArrayList<String>();
    private JComboBox<String> wcombo,bcombo;
    private String wname=null,bname=null,winner=null;
    static String move;
    private Player tempPlayer;
    private JScrollPane wscroll,bscroll;
    private String[] WNames={},BNames={};
    private JSlider timeSlider;
    private BufferedImage image;
    private Button start,wselect,bselect,WNewPlayer,BNewPlayer;

    public static void main(String[] args){
        whiteRook1 =new Rook("WR01","White_T.png",0);
        whiteRook2 =new Rook("WR02","White_T.png",0);
        blackRook1 =new Rook("BR01","Black_T.png",1);
        blackRook2 =new Rook("BR02","Black_T.png",1);
        whiteKnight1 =new Knight("WK01","White_H.png",0);
        whiteKnight2 =new Knight("WK02","White_H.png",0);
        blackKnight1 =new Knight("BK01","Black_H.png",1);
        blackKnight2 =new Knight("BK02","Black_H.png",1);
        whiteBishop1 =new Bishop("WB01","White_B.png",0);
        whiteBishop2 =new Bishop("WB02","White_B.png",0);
        blackBishop1 =new Bishop("BB01","Black_B.png",1);
        blackBishop2 =new Bishop("BB02","Black_B.png",1);
        whiteQueen =new Queen("WQ","White_Q.png",0);
        blackQueen =new Queen("BQ","Black_Q.png",1);
        whiteKing =new King(7,3,"WK","White_K.png",0);
        blackKing =new King(0,3,"BK","Black_K.png",1);

        whitePawn =new Pawn[8];
        blackPawn =new Pawn[8];
        for(int i=0;i < 8;i++){
            whitePawn[i]=new Pawn("WP0"+(i+1),"White_P.png",0);
            blackPawn[i]=new Pawn("BP0"+(i+1),"Black_P.png",1);
        }

        MainBoard=new Main();
        MainBoard.setVisible(true);
        MainBoard.setResizable(false);
    }

    private Main(){
        timeLeft =60;
        timeSlider=new JSlider();
        move="White";
        wname=null;
        bname=null;
        winner=null;
        board=new JPanel(new GridLayout(8,8));
        wdetails=new JPanel(new GridLayout(3,3));
        bdetails=new JPanel(new GridLayout(3,3));
        bcombopanel=new JPanel();
        wcombopanel=new JPanel();
        Wnames=new ArrayList<String>();
        Bnames=new ArrayList<String>();
        board.setMinimumSize(new Dimension(800,700));

        timeSlider.setMinimum(1);
        timeSlider.setMaximum(15);
        timeSlider.setValue(1);
        timeSlider.setMajorTickSpacing(2);
        timeSlider.setPaintLabels(true);
        timeSlider.setPaintTicks(true);
        timeSlider.addChangeListener(new TimeChange());

        wplayer= Player.fetch_players();
        for (Player player : wplayer) {
            Wnames.add(player.name());
        }
        bplayer= Player.fetch_players();
        for (Player player : bplayer) {
            Bnames.add(player.name());
        }
        WNames=Wnames.toArray(WNames);
        BNames=Bnames.toArray(BNames);

        Square square;
        board.setBorder(BorderFactory.createLoweredBevelBorder());
        Piece Piece;
        content=getContentPane();
        setSize(Width,Height);
        setTitle("Chess");
        content.setBackground(Color.black);
        controlPanel=new JPanel();
        content.setLayout(new BorderLayout());
        controlPanel.setLayout(new GridLayout(3,3));
        controlPanel.setBorder(BorderFactory.createTitledBorder(null, "Statistics", TitledBorder.TOP,TitledBorder.CENTER, new Font("Lucida Calligraphy",Font.PLAIN,20), Color.ORANGE));

        WhitePlayer=new JPanel();
        WhitePlayer.setBorder(BorderFactory.createTitledBorder(null, "White Player", TitledBorder.TOP,TitledBorder.CENTER, new Font("times new roman",Font.BOLD,18), Color.RED));
        WhitePlayer.setLayout(new BorderLayout());

        BlackPlayer=new JPanel();
        BlackPlayer.setBorder(BorderFactory.createTitledBorder(null, "Black Player", TitledBorder.TOP,TitledBorder.CENTER, new Font("times new roman",Font.BOLD,18), Color.BLUE));
        BlackPlayer.setLayout(new BorderLayout());

        JPanel whitestats=new JPanel(new GridLayout(3,3));
        JPanel blackstats=new JPanel(new GridLayout(3,3));
        wcombo=new JComboBox<String>(WNames);
        bcombo=new JComboBox<String>(BNames);
        wscroll=new JScrollPane(wcombo);
        bscroll=new JScrollPane(bcombo);
        wcombopanel.setLayout(new FlowLayout());
        bcombopanel.setLayout(new FlowLayout());
        wselect=new Button("Select");
        bselect=new Button("Select");
        wselect.addActionListener(new SelectHandler(0));
        bselect.addActionListener(new SelectHandler(1));
        WNewPlayer=new Button("New Player");
        BNewPlayer=new Button("New Player");
        WNewPlayer.addActionListener(new Handler(0));
        BNewPlayer.addActionListener(new Handler(1));
        wcombopanel.add(wscroll);
        wcombopanel.add(wselect);
        wcombopanel.add(WNewPlayer);
        bcombopanel.add(bscroll);
        bcombopanel.add(bselect);
        bcombopanel.add(BNewPlayer);
        WhitePlayer.add(wcombopanel,BorderLayout.NORTH);
        BlackPlayer.add(bcombopanel,BorderLayout.NORTH);
        whitestats.add(new JLabel("Name   :"));
        whitestats.add(new JLabel("Played :"));
        whitestats.add(new JLabel("Won    :"));
        blackstats.add(new JLabel("Name   :"));
        blackstats.add(new JLabel("Played :"));
        blackstats.add(new JLabel("Won    :"));
        WhitePlayer.add(whitestats,BorderLayout.WEST);
        BlackPlayer.add(blackstats,BorderLayout.WEST);
        controlPanel.add(WhitePlayer);
        controlPanel.add(BlackPlayer);

        boardState=new Square[8][8];
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {
                Piece=null;
                if(i==0&&j==0)
                    Piece= blackRook1;
                else if(i==0&&j==7)
                    Piece= blackRook2;
                else if(i==7&&j==0)
                    Piece= whiteRook1;
                else if(i==7&&j==7)
                    Piece= whiteRook2;
                else if(i==0&&j==1)
                    Piece= blackKnight1;
                else if (i==0&&j==6)
                    Piece= blackKnight2;
                else if(i==7&&j==1)
                    Piece= whiteKnight1;
                else if (i==7&&j==6)
                    Piece= whiteKnight2;
                else if(i==0&&j==2)
                    Piece= blackBishop1;
                else if (i==0&&j==5)
                    Piece= blackBishop2;
                else if(i==7&&j==2)
                    Piece= whiteBishop1;
                else if(i==7&&j==5)
                    Piece= whiteBishop2;
                else if(i==0&&j==3)
                    Piece= blackKing;
                else if(i==0&&j==4)
                    Piece= blackQueen;
                else if(i==7&&j==3)
                    Piece= whiteKing;
                else if(i==7&&j==4)
                    Piece= whiteQueen;
                else if(i==1)
                    Piece= blackPawn[j];
                else if(i==6)
                    Piece= whitePawn[j];
                square=new Square(i,j,Piece);
                square.addMouseListener(this);
                board.add(square);
                boardState[i][j]=square;
            }
        showPlayer=new JPanel(new FlowLayout());
        showPlayer.add(timeSlider);
        JLabel setTime=new JLabel("Set Timer(in mins):");
        start=new Button("Start");
        start.setBackground(Color.black);
        start.setForeground(Color.white);
        start.addActionListener(new START());
        start.setPreferredSize(new Dimension(120,40));
        setTime.setFont(new Font("Arial",Font.BOLD,16));
        label = new JLabel("Time Starts now", JLabel.CENTER);
        label.setFont(new Font("SERIF", Font.BOLD, 30));
        displayTime=new JPanel(new FlowLayout());
        time=new JPanel(new GridLayout(3,3));
        time.add(setTime);
        time.add(showPlayer);
        displayTime.add(start);
        time.add(displayTime);
        controlPanel.add(time);
        board.setMinimumSize(new Dimension(800,700));
        controlPanel.setMinimumSize(new Dimension(285,700));
        split = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,temp, controlPanel);
        content.add(split);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
}

    @Override
    public void mouseClicked(MouseEvent arg0){
        selectedSquare =(Square) arg0.getSource();
        if (previousSquare ==null)
        {
            if(selectedSquare.getPiece()!=null)
            {
                if(selectedSquare.getPiece().getColor()!= turn)
                    return;
                selectedSquare.select();
                previousSquare = selectedSquare;
                destinationList.clear();
                destinationList= selectedSquare.getPiece().move(boardState, selectedSquare.x, selectedSquare.y);
                if(selectedSquare.getPiece() instanceof King)
                    destinationList=filterdestination(destinationList, selectedSquare);
                else
                {
                    if(boardState[getKing(turn).getX()][getKing(turn).getY()].isCheck())
                        destinationList = new ArrayList<Square>(filterdestination(destinationList, selectedSquare));
                    else if(!destinationList.isEmpty() && willkingbeindanger(selectedSquare,destinationList.get(0)))
                        destinationList.clear();
                }
                highlightdestinations(destinationList);
            }
        }
        else
        {
            if(selectedSquare.x== previousSquare.x && selectedSquare.y== previousSquare.y)
            {
                selectedSquare.deselect();
                clearDestinationList(destinationList);
                destinationList.clear();
                previousSquare =null;
            }
            else if(selectedSquare.getPiece()==null || (previousSquare.getPiece().getColor()!= selectedSquare.getPiece().getColor()))
            {
                if(selectedSquare.isValidMove())
                {
                    if(selectedSquare.getPiece()!=null)
                        selectedSquare.removePiece();
                    selectedSquare.setPiece(previousSquare.getPiece());
                    if (previousSquare.isCheck())
                        previousSquare.removeCheck();
                    previousSquare.removePiece();
                    if(getKing(turn ^1).isAttacked(boardState))
                    {
                        boardState[getKing(turn ^1).getX()][getKing(turn ^1).getY()].setCheck();
                        if (checkmate(getKing(turn ^1).getColor()))
                        {
                            previousSquare.deselect();
                            if(previousSquare.getPiece()!=null)
                                previousSquare.removePiece();
                            gameend();
                        }
                    }
                    if(!getKing(turn).isAttacked(boardState))
                        boardState[getKing(turn).getX()][getKing(turn).getY()].removeCheck();
                    if(selectedSquare.getPiece() instanceof King)
                    {
                        ((King) selectedSquare.getPiece()).setX(selectedSquare.x);
                        ((King) selectedSquare.getPiece()).setY(selectedSquare.y);
                    }
                    endTurn();
                    if(!end)
                    {
                        timer.reset();
                        timer.start();
                    }
                }
                if(previousSquare !=null)
                {
                    previousSquare.deselect();
                    previousSquare =null;
                }
                clearDestinationList(destinationList);
                destinationList.clear();
            }
            else if(previousSquare.getPiece().getColor()== selectedSquare.getPiece().getColor())
            {
                previousSquare.deselect();
                clearDestinationList(destinationList);
                destinationList.clear();
                selectedSquare.select();
                previousSquare = selectedSquare;
                destinationList= selectedSquare.getPiece().move(boardState, selectedSquare.x, selectedSquare.y);
                if(selectedSquare.getPiece() instanceof King)
                    destinationList=filterdestination(destinationList, selectedSquare);
                else
                {
                    if(boardState[getKing(turn).getX()][getKing(turn).getY()].isCheck())
                        destinationList = new ArrayList<Square>(filterdestination(destinationList, selectedSquare));
                    else if(!destinationList.isEmpty() && willkingbeindanger(selectedSquare,destinationList.get(0)))
                        destinationList.clear();
                }
                highlightdestinations(destinationList);
            }
        }
        if(selectedSquare.getPiece()!=null && selectedSquare.getPiece() instanceof King)
        {
            ((King) selectedSquare.getPiece()).setX(selectedSquare.x);
            ((King) selectedSquare.getPiece()).setY(selectedSquare.y);
        }
    }

    public void endTurn(){
        if (boardState[getKing(turn).getX()][getKing(turn).getY()].isCheck())
        {
            turn ^=1;
            gameend();
        }
        if(!destinationList.isEmpty())
            clearDestinationList(destinationList);
        if(previousSquare !=null)
            previousSquare.deselect();
        previousSquare =null;
        turn ^=1;
        if(!end && timer!=null)
        {
            timer.reset();
            timer.start();
            showPlayer.remove(CHNC);
            if(Main.move.equals("White"))
                Main.move="Black";
            else
                Main.move="White";
            CHNC.setText(Main.move);
            showPlayer.add(CHNC);
        }
    }

    private King getKing(int color)
    {
        if (color==0)
            return whiteKing;
        else
            return blackKing;
    }

    private void clearDestinationList(ArrayList<Square> destlist)
    {
        for (Square square : destlist) square.removePossibleDestination();
    }

    private void highlightdestinations(ArrayList<Square> destlist)
    {
        for (Square square : destlist) square.setPossibleDestination();
    }

    private boolean willkingbeindanger(Square fromcell,Square tocell)
    {
        Square[][] newboardstate = new Square[8][8];
        for(int i=0;i<8;i++)
            for(int j=0;j<8;j++)
            {	try { newboardstate[i][j] = new Square(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace(); System.out.println("There is a problem with cloning !!"); }}

        if(newboardstate[tocell.x][tocell.y].getPiece()!=null)
            newboardstate[tocell.x][tocell.y].removePiece();

        newboardstate[tocell.x][tocell.y].setPiece(newboardstate[fromcell.x][fromcell.y].getPiece());
        if(newboardstate[tocell.x][tocell.y].getPiece() instanceof King)
        {
            ((King)(newboardstate[tocell.x][tocell.y].getPiece())).setX(tocell.x);
            ((King)(newboardstate[tocell.x][tocell.y].getPiece())).setY(tocell.y);
        }
        newboardstate[fromcell.x][fromcell.y].removePiece();
        if (((King) (newboardstate[getKing(turn).getX()][getKing(turn).getY()].getPiece())).isAttacked(newboardstate))
            return true;
        else
            return false;
    }

    private ArrayList<Square> filterdestination (ArrayList<Square> destlist, Square fromcell)
    {
        ArrayList<Square> newlist = new ArrayList<Square>();
        Square[][] newboardstate = new Square[8][8];
        ListIterator<Square> it = destlist.listIterator();
        int x,y;
        while (it.hasNext())
        {
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                {	try { newboardstate[i][j] = new Square(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}

            Square tempc = it.next();
            if(newboardstate[tempc.x][tempc.y].getPiece()!=null)
                newboardstate[tempc.x][tempc.y].removePiece();
            newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getPiece());
            x=getKing(turn).getX();
            y=getKing(turn).getY();
            if(newboardstate[fromcell.x][fromcell.y].getPiece() instanceof King)
            {
                ((King)(newboardstate[tempc.x][tempc.y].getPiece())).setX(tempc.x);
                ((King)(newboardstate[tempc.x][tempc.y].getPiece())).setY(tempc.y);
                x=tempc.x;
                y=tempc.y;
            }
            newboardstate[fromcell.x][fromcell.y].removePiece();
            if ((!((King) (newboardstate[x][y].getPiece())).isAttacked(newboardstate)))
                newlist.add(tempc);
        }
        return newlist;
    }

    private ArrayList<Square> incheckfilter (ArrayList<Square> destlist, Square fromcell, int color)
    {
        ArrayList<Square> newlist = new ArrayList<Square>();
        Square[][] newboardstate = new Square[8][8];
        ListIterator<Square> it = destlist.listIterator();
        int x,y;
        while (it.hasNext())
        {
            for(int i=0;i<8;i++)
                for(int j=0;j<8;j++)
                {	try { newboardstate[i][j] = new Square(boardState[i][j]);} catch (CloneNotSupportedException e){e.printStackTrace();}}
            Square tempc = it.next();
            if(newboardstate[tempc.x][tempc.y].getPiece()!=null)
                newboardstate[tempc.x][tempc.y].removePiece();
            newboardstate[tempc.x][tempc.y].setPiece(newboardstate[fromcell.x][fromcell.y].getPiece());
            x=getKing(color).getX();
            y=getKing(color).getY();
            if(newboardstate[tempc.x][tempc.y].getPiece() instanceof King)
            {
                ((King)(newboardstate[tempc.x][tempc.y].getPiece())).setX(tempc.x);
                ((King)(newboardstate[tempc.x][tempc.y].getPiece())).setY(tempc.y);
                x=tempc.x;
                y=tempc.y;
            }
            newboardstate[fromcell.x][fromcell.y].removePiece();
            if ((!((King) (newboardstate[x][y].getPiece())).isAttacked(newboardstate)))
                newlist.add(tempc);
        }
        return newlist;
    }

    public boolean checkmate(int color)
    {
        ArrayList<Square> dlist = new ArrayList<Square>();
        for(int i=0;i<8;i++)
        {
            for(int j=0;j<8;j++)
            {
                if (boardState[i][j].getPiece()!=null && boardState[i][j].getPiece().getColor()==color)
                {
                    dlist.clear();
                    dlist=boardState[i][j].getPiece().move(boardState, i, j);
                    dlist=incheckfilter(dlist,boardState[i][j],color);
                    if(dlist.size()!=0)
                        return false;
                }
            }
        }
        return true;
    }

    @SuppressWarnings("deprecation")
    private void gameend()
    {
        clearDestinationList(destinationList);
        displayTime.disable();
        timer.countdownTimer.stop();
        if(previousSquare !=null)
            previousSquare.removePiece();
        if(turn ==0)
        {	White.updateGamesWon();
            White.Update_Player();
            winner=White.name();
        }
        else
        {
            Black.updateGamesWon();
            Black.Update_Player();
            winner=Black.name();
        }
        JOptionPane.showMessageDialog(board,"Checkmate!!!\n"+winner+" wins");
        WhitePlayer.remove(wdetails);
        BlackPlayer.remove(bdetails);
        displayTime.remove(label);

        displayTime.add(start);
        showPlayer.remove(mov);
        showPlayer.remove(CHNC);
        showPlayer.revalidate();
        showPlayer.add(timeSlider);

        split.remove(board);
        split.add(temp);
        WNewPlayer.enable();
        BNewPlayer.enable();
        wselect.enable();
        bselect.enable();
        end=true;
        MainBoard.disable();
        MainBoard.dispose();
        MainBoard = new Main();
        MainBoard.setVisible(true);
        MainBoard.setResizable(false);
    }

    @Override
    public void mousePressed(MouseEvent e) {

    }

    @Override
    public void mouseReleased(MouseEvent e) {

    }

    @Override
    public void mouseEntered(MouseEvent e) {

    }

    @Override
    public void mouseExited(MouseEvent e) {

    }

    class TimeChange implements ChangeListener
    {
        @Override
        public void stateChanged(ChangeEvent arg0)
        {
            timeLeft =timeSlider.getValue()*60;
        }
    }

    class SelectHandler implements ActionListener
    {
        private int color;

        SelectHandler(int i)
        {
            color=i;
        }
        @Override
        public void actionPerformed(ActionEvent arg0)
        {
            tempPlayer=null;
            String n=(color==0)?wname:bname;
            JComboBox<String> jc=(color==0)?wcombo:bcombo;
            JComboBox<String> ojc=(color==0)?bcombo:wcombo;
            ArrayList<Player> pl=(color==0)?wplayer:bplayer;
            //ArrayList<Player> otherPlayer=(color==0)?bplayer:wplayer;
            ArrayList<Player> opl=Player.fetch_players();
            if(opl.isEmpty())
                return;
            JPanel det=(color==0)?wdetails:bdetails;
            JPanel PL=(color==0)?WhitePlayer:BlackPlayer;
            if(selected)
                det.removeAll();
            n=(String)jc.getSelectedItem();
            Iterator<Player> it=pl.iterator();
            Iterator<Player> oit=opl.iterator();
            while(it.hasNext())
            {
                Player p=it.next();
                if(p.name().equals(n))
                {tempPlayer=p;
                    break;}
            }
            while(oit.hasNext())
            {
                Player p=oit.next();
                if(p.name().equals(n))
                {opl.remove(p);
                    break;}
            }

            if(tempPlayer==null)
                return;
            if(color==0)
                White=tempPlayer;
            else
                Black=tempPlayer;
            bplayer=opl;
            ojc.removeAllItems();
            for (Player s:opl)
                ojc.addItem(s.name());
            det.add(new JLabel(" "+tempPlayer.name()));
            det.add(new JLabel(" "+tempPlayer.gamesWon()));
            det.add(new JLabel(" "+tempPlayer.gamesWon()));

            PL.revalidate();
            PL.repaint();
            PL.add(det);
            selected=true;
        }

    }
    class Handler implements ActionListener{
        private int color;
        Handler(int i)
        {
            color=i;
        }
        @Override
        public void actionPerformed(ActionEvent e) {
            String n=(color==0)?wname:bname;
            JPanel j=(color==0)?WhitePlayer:BlackPlayer;
            ArrayList<Player> N=Player.fetch_players();
            Iterator<Player> it=N.iterator();
            JPanel det=(color==0)?wdetails:bdetails;
            n=JOptionPane.showInputDialog(j,"Enter your name");

            if(n!=null)
            {

                while(it.hasNext())
                {
                    if(it.next().name().equals(n))
                    {JOptionPane.showMessageDialog(j,"Player exists");
                        return;}
                }

                if(n.length()!=0)
                {Player tem=new Player(n);
                    tem.Update_Player();
                    if(color==0)
                        White=tem;
                    else
                        Black=tem;
                }
                else return;
            }
            else
                return;
            det.removeAll();
            det.add(new JLabel(" "+n));
            det.add(new JLabel(" 0"));
            det.add(new JLabel(" 0"));
            j.revalidate();
            j.repaint();
            j.add(det);
            selected=true;
        }
    }

    class START implements ActionListener
    {

        @SuppressWarnings("deprecation")
        @Override
        public void actionPerformed(ActionEvent arg0) {

            if(White==null||Black==null)
            {JOptionPane.showMessageDialog(controlPanel, "Fill in the details");
                return;}
            White.updateGamesPlayed();
            White.Update_Player();
            Black.updateGamesPlayed();
            Black.Update_Player();
            WNewPlayer.disable();
            BNewPlayer.disable();
            wselect.disable();
            bselect.disable();
            split.add(board);
            showPlayer.remove(timeSlider);
            mov=new JLabel("Move:");
            mov.setFont(new Font("Comic Sans MS",Font.PLAIN,20));
            mov.setForeground(Color.red);
            showPlayer.add(mov);
            CHNC=new JLabel(move);
            CHNC.setFont(new Font("Comic Sans MS",Font.BOLD,20));
            CHNC.setForeground(Color.blue);
            showPlayer.add(CHNC);
            displayTime.remove(start);
            displayTime.add(label);
            timer=new Time(label);
            timer.start();
        }
    }

}