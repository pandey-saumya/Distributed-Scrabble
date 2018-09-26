package com;

import com.messages.Message;
import com.model.player.Player;
import com.view.hall.HallController;
import com.view.login.LoginController;
import com.view.username.UsernameController;
import javafx.application.Platform;
import javafx.scene.Scene;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;
import java.util.StringTokenizer;

public class Listener extends Thread {

    private static boolean nameValid;
    private ObjectInputStream ois;
    public static String name;
    public static Message msg = null;
    public Listener(ObjectInputStream ois) {
        this.ois = ois;
    }

    @Override
    public void run(){
        try {
            //Read messages from the server
            while((msg = (Message) ois.readObject()) != null) {
                switch (msg.getPlayerStatus()){
                        case SET_NAME:
                        if (msg.getFeedBackMessage().equals("ValidName")){
                            Set<String> keys = msg.getConnectedClients().keySet();
                            Iterator<String> iterator = keys.iterator();

                            while (iterator.hasNext()) {
                                String key = iterator.next().toString();
                                Player player = new Player(key, msg.getConnectedClients().get(key));
                                HallController.getInstance().updateStatus(player);
                            }
                            HallController.getInstance().refreshTable();
                            UsernameController.getInstance().showHall();
                        }
                        else{
                            UsernameController.getInstance().duplicatedUsername();
                        }
                        break;
                    case IN_HALL:
                        Set<String> keys = msg.getConnectedClients().keySet();
                        Iterator<String> iterator = keys.iterator();
                        HallController.getInstance().clearTable();
                        while (iterator.hasNext()) {
                            String key = iterator.next().toString();
                            Player player = new Player(key, msg.getConnectedClients().get(key));
                            HallController.getInstance().updateStatus(player);
                        }
                        Set<Integer> tableKeys = msg.getCreatedGames().keySet();
                        Iterator<Integer> iteratorTable = tableKeys.iterator();
                        while (iteratorTable.hasNext()) {
                            int tableKey = iteratorTable.next();
                            int playerInTable = msg.getCreatedGames().get(tableKey);
                            HallController.getInstance().refreshTableNum(tableKey,playerInTable);
                        }
                        break;
                    case JOIN_TABLE:
                        if ((msg.getFeedBackMessage()!=null) && (msg.getFeedBackMessage().equals("ValidTable"))) {
                            Platform.runLater(new Runnable() {
                                public void run() {
                                    HallController.getInstance().showTable();
                                }
                            });
                        }
                        else{
                            HallController.getInstance().joinTableFailure();
                        }
                        break;
                }
                /*StringTokenizer st = new StringTokenizer(msg, "|");
                String operation = st.nextToken();
                String msg = st.nextToken();
                if (operation.equals("hallplayer")){
                    //TODO update playerlist realtime
                }
                if (operation.equals("halltable")){
                    //TODO referesh table list realtime
                }
                if (operation.equals("gameplay")){
                    //TODO referesh game playerlist realtime
                }
                if (operation.equals("voting")){
                    Game.voting(msg);
                }
                if (operation.equals("gamestart")){
                    //TODO show game start message
                }
                if (operation.equals("turn")){
                    if (msg.equals("yourturn")){
                        Game.turn = true;
                    }
                    else{
                        Game.turn =false;
                    }
                }*/
            }
        } catch (SocketException e) {
            LoginController.getInstance().connectionLost("Connection lost!");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    public Message getMessage(){
        return msg;
    }
    public static boolean checkNameValid(){
        return nameValid;
    }

}