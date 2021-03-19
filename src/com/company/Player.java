package com.company;

import java.io.*;
import javax.swing.JOptionPane;
import java.util.ArrayList;

public class Player implements Serializable {
    private String name;
    private Integer gamesplayed;
    private Integer gameswon;

    public Player(String name)
    {
        this.name = name.trim();
        gamesplayed = 0;
        gameswon = 0;
    }

    public String name(){
        return name;
    }

    public Integer gamesPlayed()
    {
        return gamesplayed;
    }

    public Integer gamesWon()
    {
        return gameswon;
    }

    public Integer winPercent()
    {
        return (gameswon * 100) / gamesplayed;
    }

    public void updateGamesPlayed()
    {
        gamesplayed++;
    }

    public void updateGamesWon()
    {
        gameswon++;
    }

    public static ArrayList<Player> fetch_players()         //Function to fetch the list of the players
    {
        Player tempplayer;
        ObjectInputStream input = null;
        ArrayList<Player> players = new ArrayList<Player>();
        try
        {
            File infile = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
            input = new ObjectInputStream(new FileInputStream(infile));
            try
            {
                while(true)
                {
                    tempplayer = (Player) input.readObject();
                    players.add(tempplayer);
                }
            }
            catch(EOFException e)
            {
                input.close();
            }
        }
        catch (FileNotFoundException e)
        {
            players.clear();
            return players;
        }
        catch (IOException e)
        {
            e.printStackTrace();
            try {input.close();} catch (IOException e1) {}
            JOptionPane.showMessageDialog(null, "Unable to read file");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Game Data File Corrupted");
        } catch (Exception e1) {
            e1.printStackTrace();
        }
        return players;
    }

    public void Update_Player(){           //Function to update the statistics of a player
        ObjectInputStream input = null;
        ObjectOutputStream output = null;
        Player temp_player;
        File inputfile=null;
        File outputfile=null;
        try
        {
            inputfile = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
            outputfile = new File(System.getProperty("user.dir")+ File.separator + "tempfile.dat");
        } catch (SecurityException e)
        {
            JOptionPane.showMessageDialog(null, "Error");
            System.exit(0);
        }
        boolean playerdonotexist;
        try
        {
            if(!outputfile.exists())
                outputfile.createNewFile();
            if(!inputfile.exists())
            {
                output = new ObjectOutputStream(new java.io.FileOutputStream(outputfile,true));
                output.writeObject(this);
            }
            else
            {
                input = new ObjectInputStream(new FileInputStream(inputfile));
                output = new ObjectOutputStream(new FileOutputStream(outputfile));
                playerdonotexist=true;
                try
                {
                    while(true)
                    {
                        temp_player = (Player)input.readObject();
                        if (temp_player.name().equals(name()))
                        {
                            output.writeObject(this);
                            playerdonotexist = false;
                        }
                        else
                            output.writeObject(temp_player);
                    }
                }
                catch(EOFException e){
                    input.close();
                }
                if(playerdonotexist)
                    output.writeObject(this);
            }
            inputfile.delete();
            output.close();
            File newf = new File(System.getProperty("user.dir")+ File.separator + "chessgamedata.dat");
            if(outputfile.renameTo(newf)==false)
                System.out.println("File Renameing Unsuccessful");
        }
        catch (FileNotFoundException e)
        {
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Unable to read/write Game data");
        }
        catch (ClassNotFoundException e)
        {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Game Data File Corrupted");
        }
        catch (Exception e)
        {

        }
    }
}
