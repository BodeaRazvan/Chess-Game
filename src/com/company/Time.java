package com.company;

import java.awt.event.*;
import javax.swing.*;

public class Time {
    private JLabel label;
    Timer countdownTimer;
    int timeRemain;

    public Time(JLabel passedLabel){
        countdownTimer = new Timer(1000,new CountDownTimerListener());
        this.label=passedLabel;
        timeRemain=Main.timeLeft;
    }

    class CountDownTimerListener implements ActionListener{
        public void actionPerformed(ActionEvent e){
            int min,sec;
            if(timeRemain >0){
                min=timeRemain/60;
                sec=timeRemain%60;
                label.setText(String.valueOf(min)+":"+(sec>10?String.valueOf(sec):"0"+String.valueOf(sec)));
                timeRemain--;
            }
            else{
                label.setText("Time ended!");
                reset();
                start();
                Main.MainBoard.endTurn();
            }
        }
    }

    public void start(){
        countdownTimer.start();
    }

    public void reset(){
        timeRemain=Main.timeLeft;
    }


}
