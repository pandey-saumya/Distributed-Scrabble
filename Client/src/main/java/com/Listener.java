package com;

import com.messages.GameStatus;
import com.messages.Message;
import com.messages.PlayerAction;
import com.model.player.Player;
import com.view.hall.HallController;
import com.view.login.LoginController;
import com.view.table.ReadyController;
import com.view.table.TableController;
import com.view.username.UsernameController;
import javafx.application.Platform;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Tab;

import java.io.*;
import java.net.Socket;
import java.net.SocketException;
import java.util.*;

public class Listener extends Thread {


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
                            UsernameController.getInstance().showHall();
                            name = msg.getClientName();
                        }
                        else{
                            UsernameController.getInstance().duplicatedUsername();
                        }
                        break;
                    case IN_HALL:
                        HallController.getInstance().getCurrentPlayer(name);
                        if (msg.getPlayerAction() == PlayerAction.JOIN_TABLE){
                            if ((msg.getFeedBackMessage()!=null) && (msg.getFeedBackMessage().equals("ValidTable"))) {
                                Platform.runLater(()-> {
                                    HallController.getInstance().showTable();
                                });
                            }
                            else{
                                HallController.getInstance().joinTableFailure();
                            }
                        }
                        if (msg.getPlayerAction() == PlayerAction.HALL_WAITING) {
                            Set<String> keys = msg.getConnectedClients().keySet();
                            Iterator<String> iterator = keys.iterator();
                            HallController.getInstance().clearTable();
                            while (iterator.hasNext()) {
                                String key = iterator.next().toString();
                                Player player = new Player(key, msg.getConnectedClients().get(key));
                                HallController.getInstance().updateStatus(player);
                            }
                            HallController.getInstance().refreshTable();
                            Set<Integer> tableKeys = msg.getCreatedGames().keySet();
                            Iterator<Integer> iteratorTable = tableKeys.iterator();
                            while (iteratorTable.hasNext()) {
                                int tableKey = iteratorTable.next();
                                int playerInTable = msg.getCreatedGames().get(tableKey);
                                HallController.getInstance().refreshTableNum(tableKey, playerInTable);
                            }
                        }
                        if (msg.getPlayerAction() == PlayerAction.INVITE_PLAYER){
                            //HallController.getInstance().beInvited(msg.getTableId(),msg.getClientName());
                            Game.updateInvite(msg.getClientName(),msg.getTableId());
                        }
                        break;
                    case IN_ROOM:
                        if (msg.getPlayerAction() == PlayerAction.GAME_WAITING){
                            Set<String> keys_player = msg.getPlayerList().keySet();
                            Iterator<String> iterator_player = keys_player.iterator();
                            TableController.getInstance().resetPlayerStatus();
                            while (iterator_player.hasNext()) {
                                String key_player = iterator_player.next();
                                String playerStatus = msg.getPlayerList().get(key_player);
                                TableController.getInstance().refreshPlayerStatus(key_player,playerStatus);
                            }
                        }
                        if (msg.getPlayerAction() == PlayerAction.INVITE){
                            Set<String> keys_invitePlayer = msg.getPlayerList().keySet();
                            Iterator<String> iterator_invitePlayer = keys_invitePlayer.iterator();
                            List<String> inviteList = new ArrayList<>();
                            while (iterator_invitePlayer.hasNext()) {
                                String key_player = iterator_invitePlayer.next();
                                inviteList.add(key_player);
                            }
                            ReadyController.getInstance().updateInviteList(inviteList);
                        }
                        if (msg.getPlayerAction() == PlayerAction.INVITE_FEEDBACK){
                            String feedbackMsg = msg.getFeedBackMessage();
                            ReadyController.getInstance().invitationRejected(feedbackMsg);
                      //      ReadyController.getInstance().updateInviteList(inviteList);
                            Set<String> keys_invitePlayer = msg.getPlayerList().keySet();
                            Iterator<String> iterator_invitePlayer = keys_invitePlayer.iterator();
                            List<String> inviteList = new ArrayList<>();
                            while (iterator_invitePlayer.hasNext()) {
                                String key_player = iterator_invitePlayer.next();
                                inviteList.add(key_player);
                            }
                            ReadyController.getInstance().updateInviteList(inviteList);
                        }
                        if (msg.getGameStatus() == GameStatus.ALL_READY){
                            Game.gameStart();
                            //show count down timer & start game
                            TableController.getInstance().gameStart();
                        }
                        break;
                    case IN_GAME:
                        TableController.getInstance().setCurrentPlayer(name);
                        if (msg.getPlayerAction() == PlayerAction.GAME_CONTENT) {
                            // Player name & turn
                            Set<String> keys_player = msg.getPlayerList().keySet();
                            Iterator<String> iterator_player = keys_player.iterator();
                            while (iterator_player.hasNext()) {
                                String key_player = iterator_player.next();
                                boolean turn = false;
                                if (msg.getPlayerList().get(key_player).equals("Turn")){
                                    turn = true;
                                }
                                else{
                                    turn = false;
                                }
                                TableController.getInstance().refreshPlayerTurn(key_player,turn);
                            }
                            if (msg.getPlayerList().get(name).equals("Turn")){
                                Game.turn = true;
                            }
                            else{
                                Game.turn = false;
                            }
                            // Player name & score
                            Set<String> keys_score = msg.getPlayerScore().keySet();
                            Iterator<String> iterator_score = keys_score.iterator();
                            while (iterator_score.hasNext()) {
                                String key_score = iterator_score.next();
                                String score = msg.getPlayerScore().get(key_score).toString();
                                TableController.getInstance().refreshPlayerScore(key_score,score);
                            }
                            TableController.getInstance().setBoard(msg.getBoard());
                            TableController.getInstance().deHilightAll();
                        }
//                        if(msg.getPlayerAction()== PlayerAction.WANTS_VOTING){
//                            String name = msg.getClientName();
//                            String word = msg.getGameWord();
//                            TableController.getInstance().startVoting(name,word);
//                        }
                        if(msg.getPlayerAction()== PlayerAction.VOTING){
                            String name = msg.getClientName();
                            String word = msg.getGameWord();

                            String toVoteFor = msg.getClientToVoteFor();
                            TableController.getInstance().voting(name,word,toVoteFor);
                            TableController.getInstance().highLight(msg.getGameLocation(),msg.getWordOrientation());

                        }
                        if(msg.getPlayerAction() == PlayerAction.SEND_CHAT_MESSAGE) {
                            String m=msg.getChatMessage();
                            TableController.getInstance().addChatMessage(m);
                        }
                        if (msg.getGameStatus()==GameStatus.ENDING){
                            String winner = msg.getGameResult();
                            //show winner;
                            Platform.runLater(()->{
                                Alert alert = new Alert(Alert.AlertType.INFORMATION);
                                alert.setTitle("Game Result");
                                alert.setHeaderText("Winner");
                                alert.setContentText(winner);
                                alert.showAndWait();
                                HallController.getStage().close();
                                Game.getPrimaryStage().show();
                                Game.returnToHall();
                            });
                        }
                        break;
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
            LoginController.getInstance().connectionLost("Connection lost!");
        }
    }
}