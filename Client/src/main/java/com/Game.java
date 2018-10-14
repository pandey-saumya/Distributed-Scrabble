package com;

import com.model.exception.ExceptionDialog;
import com.view.login.LoginController;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.*;
import java.net.Socket;
import java.util.List;
import java.util.Random;
import java.util.StringTokenizer;
import com.messages.*;
import javafx.stage.WindowEvent;
import com.view.hall.HallController;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Game extends Application {

    //<editor-fold defaultstate="collapsed" desc="//Random namePool for usernames for logging in the game arena ">
    private static String[] namepool={
            "Ahri","Akali","Alistar","Amumu","Anivia","Annie",
            "Ashe","Aurelion Sol","Azir","Bard","Blitzcrank","Brand","Braum",
            "Caitlyn","Camille","Cassiopeia","Cho\'Gath","Corki","Darius","Diana",
            "Mundo","Draven","Ekko","Elise","Evelynn","Ezreal","Fiddlesticks",
            "Fiora","Fizz","Galio","Gangplank","Garen","Gnar","Gragas",
            "Graves","Hecarim","Heimerdinger","Illaoi","Irelia","Ivern","Janna",
            "Jarvan","Jax","Jayce","Jhin","Jinx","Kai\'Sa","Kalista",
            "Karma","Karthus","Kassadin","Katarina","Kayle","Kayn","Kennen",
            "Kha\'Zix","Kindred","Kled","Kog\'Maw","LeBlanc","Lee Sin","Leona",
            "Lissandra","Lucian","Lulu","Lux","Malphite","Malzahar","Maokai",
            "Master Yi","Miss Fortune","Mordekaiser","Morgana","Nami","Nasus","Nautilus",
            "Nidalee","Nocturne","Nunu","Olaf","Orianna","Ornn","Pantheon",
            "Poppy","Pyke","Quinn","Rakan","Rammus","Rek\'Sai","Renekton",
            "Rengar","Riven","Rumble","Ryze","Sejuani","Shaco","Shen",
            "Shyvana","Singed","Sion","Sivir","Skarner","Sona","Soraka",
            "Swain","Syndra","Tahm Kench","Taliyah","Talon","Taric","Teemo",
            "Thresh","Tristana","Trundle","Tryndamere","Twisted Fate","Twitch","Udyr",
            "Urgot","Varus","Vayne","Veigar","Vel\'Koz","Vi","Viktor",
            "Vladimir","Volibear","Warwick","Wukong","Xayah","Xerath","Xin Zhao",
            "Yasuo","Yorick","Zed","Ziggs","Zilean","Zoe","Zyra"
    };
    //</editor-fold>

    private static Stage primaryStageObj;
    public static final int LoginWidth = 500;
    public static final int LoginHeight = 600;
    public static InputStream in;
    public static OutputStream out;
    public static ObjectInputStream ois;
    public static ObjectOutputStream oos;
    public static Socket socket;
    public static boolean turn = false;
    public static Listener m1;
    public static Map<String,Integer> inviteList = new HashMap<>();

    public void start(Stage primaryStage) throws Exception {
        primaryStageObj = primaryStage;
        Parent root = FXMLLoader.load(getClass().getClassLoader().getResource("fxml/login.fxml"));
        primaryStage.initStyle(StageStyle.UNDECORATED);
        Scene scene = new Scene(root, LoginWidth, LoginHeight);
        primaryStage.setScene(scene);
        primaryStage.setResizable(false);
        primaryStage.show();
        primaryStage.setOnCloseRequest(e -> Platform.exit());
    }

    public static Stage getPrimaryStage() {
        return primaryStageObj;
    }

    public static void main(String[] args) {
        launch(args);
    }

    public static void connect(String host, int port) {
        try {
            socket = new Socket(host, port);
            LoginController.getInstance().showUsernameScene();
            out = socket.getOutputStream();
            oos = new ObjectOutputStream(out);
            in = socket.getInputStream();
            ois = new ObjectInputStream(in);
            m1 = new Listener(ois);
            m1.start();
        } catch (IOException e) {
            LoginController.getInstance().loginFailure("Could not connect to server");
        }
    }

    public static void updateInvite(String name, Integer tableId){
        if (inviteList.isEmpty()){
            inviteList.put(name,tableId);
        }
        else {
            {
                if (inviteList.containsKey(name)) {
                    if (!inviteList.containsValue(tableId)) {
                        inviteList.replace(name, tableId);
                    }
                } else {
                    inviteList.put(name, tableId);
                }
            }
        }
        HallController.getInstance().beInvited(inviteList);
    }

//    public static void sendcharacter(String character, String location, String word){
//        sendmsg("setCharacter|" + character+" " + location +" "+word);
//    }
//    public static void passturn(){
//        sendmsg("passturn");
//    }
//    public static void voting(String word){
//        //TODO show the word
//        boolean vote = true;
//        //TODO get the voting result from UI.
//        if (vote == true){
//            sendmsg("votingresult|true");
//        }
//        else{
//            sendmsg("votingresult|false");
//        }
//    }

    public static void sendmsg(Message msg) {
        try {
            oos.writeObject(msg);
            oos.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static String randomeUser() {
        Random rand = new Random();
        int i;
        i = rand.nextInt(namepool.length);
        return (namepool[i]);
    }

    public static void invite() {
        Message message = new Message();
        message.setPlayerStatus(PlayerStatus.IN_ROOM);
        message.setPlayerAction(PlayerAction.INVITE);
        sendmsg(message);
    }

    public static void pass() {
        Message message = new Message();
        message.setPlayerStatus(PlayerStatus.IN_GAME);
        message.setPlayerAction(PlayerAction.PASS);
        sendmsg(message);
    }

    public static void sendCharacter(int[] position, String character, String word, String name) {
        Message message = new Message();
        message.setPlayerStatus(PlayerStatus.IN_GAME);
        message.setPlayerAction(PlayerAction.SET_CHARACTER);
        message.setGameLocation(position);
        message.setGameCharacter(character);
        message.setGameWord(word);
        message.setClientName(name);
        sendmsg(message);
    }

    public static void startVoting(boolean startVoting,String name,String word) {
        Message message = new Message();
        message.setPlayerStatus(PlayerStatus.IN_GAME);
        message.setPlayerAction(PlayerAction.WANTS_VOTING);
        message.setClientName(name);
        message.setClientToVoteFor(name);
        message.setGameWord(word);
        message.setStartVoting(startVoting);
        sendmsg(message);
    }

    public static void voting(int votingNum,String name,String word, String clientToVoteFor) {
        Message message = new Message();
        message.setPlayerStatus(PlayerStatus.IN_GAME);
        message.setPlayerAction(PlayerAction.VOTING);
        message.setClientName(name);
        message.setClientToVoteFor(clientToVoteFor);
        message.setGameWord(word);
        message.setVotingNum(votingNum);
        sendmsg(message);
    }

    public static void invitePlayer(String name) {
        Message message = new Message();
        message.setPlayerStatus(PlayerStatus.IN_ROOM);
        message.setPlayerAction(PlayerAction.INVITE_PLAYER);
        message.setClientName(name);
        sendmsg(message);
    }

    public static void ready() {
        Message message = new Message();
        message.setPlayerStatus(PlayerStatus.IN_ROOM);
        message.setPlayerAction(PlayerAction.READY);
        sendmsg(message);
    }

    public static void returnToHall() {
        Message message = new Message();
        message.setPlayerStatus(PlayerStatus.IN_ROOM);
        message.setPlayerAction(PlayerAction.RETURN_HALL);
        sendmsg(message);
    }

    public static void setUsername(String username) {
        Message message = new Message();
        message.setPlayerStatus(PlayerStatus.SET_NAME);
        message.setClientName(username);
        sendmsg(message);
    }

    public static void entryTable(int tableNumber) {
        Message message = new Message();
        message.setPlayerStatus(PlayerStatus.IN_HALL);
        message.setPlayerAction(PlayerAction.JOIN_TABLE);
        message.setTableId(tableNumber);
        sendmsg(message);
    }
    public static void gameStart(){
        Message message = new Message();
        message.setPlayerStatus(PlayerStatus.IN_ROOM);
        message.setGameStatus(GameStatus.ALL_READY);
        sendmsg(message);
    }


    //TODO
    public static void inviteReject(String name){
        Message message = new Message();
        message.setPlayerStatus(PlayerStatus.IN_ROOM);
        message.setPlayerAction(PlayerAction.INVITE_FEEDBACK);
        message.setClientName(name);
        sendmsg(message);
    }

   
//    public static String horizontal(int location,String[] board){
//        String word = board[location];
//        int index = location;
//        while (((index-1) % 20 != 0)&&(!board[index-1].equals(""))){
//            index = index -1;
//            word = board[index] +word;
//        }
//        index = location;
//        while (((index+1 % 20) != 0) && (!board[index+1].equals(""))){
//            index = index+1;
//            word = word+board[index];
//        }
//        return word;
//    }
//    public static String vertical(int location,String[] board){
//        String word = board[location];
//        int index = location;
//        while (!(index < 20) && (!board[index-20].equals(""))){
//            index = index - 20;
//            word = board[index] + word;
//        }
//        index = location;
//        while (!(index >379) && (!board[index+20].equals(""))){
//            index = index + 20;
//            word = board[index] +word;
//        }
//        return word;
//    }

}
