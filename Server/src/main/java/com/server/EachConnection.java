package com.server;

import com.game.GameRoom;
import com.game.Player;
import com.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.lang.reflect.Array;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import org.json.simple.JSONObject;

public class EachConnection implements Runnable {

    private Socket clientSocket;
    private static Server server;
    private PlayerStatus clientStatus;
    private int clientNum;
    private InputStream in;
    private OutputStream out;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private String clientName;
    private int tableId;
    private Player player;
    static Logger logger = LoggerFactory.getLogger(EachConnection.class);
    private Map<String, String> listToSend = new HashMap<String, String>();



    public EachConnection(Socket clientSocket, int clientNum) throws IOException{
        this.clientSocket = clientSocket;
        this.clientNum = clientNum;
        player = new Player();
        out = clientSocket.getOutputStream();
        oos = new ObjectOutputStream(out);

        in = clientSocket.getInputStream();
        ois = new ObjectInputStream(in);
    }


    // listening from client messages
    @Override
    public void run() {
        try {
            while (true){
                Message clientMsg = (Message)ois.readObject();
                if (clientMsg != null){
                    switch (clientMsg.getPlayerStatus()){
                        case SET_NAME:
                            inSetName(clientMsg);
                            break;
                        case IN_HALL:
                            inHall(clientMsg);
                            break;
                        case IN_ROOM:
                            inRoom(clientMsg);
                            break;
                        case IN_GAME:
                            inGame(clientMsg);
                            break;
                    }
                }
            }
        } catch (Exception e) {
            if (clientName != null) {
                player.setusername(clientName);
                ServerState.UserList.remove(player.getusername());
                ServerState.clientList.remove(player.getusername());
            }
            if (clientStatus == PlayerStatus.IN_GAME){
                setClientStatus(PlayerStatus.LEAVING);
                GameRoom game = getCurrentGame();
                game.deletePlayer(player);
                table_information();
            }
            if (clientStatus == PlayerStatus.IN_ROOM)
            { setClientStatus(PlayerStatus.LEAVING);
                GameRoom game = getCurrentGame();
                game.deletePlayer(player);
                table_information();
            }
            setClientStatus(PlayerStatus.LEAVING);
            ServerState.getClientInstance().clientDisconnected(this);
            ServerState.clientList.remove(player.getusername());
            updateGameList();
            hall_information();
            e.printStackTrace();
            logger.info("Client on port " + clientSocket.getPort() + " exited.");
        }
    }

    private void hall_information() {
        Message toALL = new Message();
        updateGameList();
        List<EachConnection> clients = ServerState.getClientInstance().getConnectedClients();
        toALL.setPlayerStatus(PlayerStatus.IN_HALL);
        toALL.setPlayerAction(PlayerAction.HALL_WAITING);
        toALL.setConnectedClients(ServerState.clientList);
        toALL.setCreatedGames(ServerState.gameList);
        List<EachConnection> inHall = new ArrayList<>();
        for (EachConnection inhall : clients){
            if (inhall.getClientStatus() == PlayerStatus.IN_HALL){
                inHall.add(inhall);
            }
        }
        broadCast(clients,toALL);
    }
    private void table_information() {
        Message toPlayers = new Message();
        GameRoom game = getCurrentGame();
        toPlayers.setPlayerStatus(PlayerStatus.IN_ROOM);
        toPlayers.setPlayerAction(PlayerAction.GAME_WAITING);
        ArrayList<Player> players = game.getPlayerList();
        toPlayers.setPlayerList(getPlayerGameStatus());
        roombroadCast(players,toPlayers);
    }
    private void game_information(){
        Message toPlayers = new Message();
        GameRoom game = getCurrentGame();
        toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
        toPlayers.setPlayerAction(PlayerAction.GAME_CONTENT);
        ArrayList<Player> players= game.getPlayerList();
        toPlayers.setBoard(game.getBoard());
        toPlayers.setPlayerList(getPlayerGameStatus());
        roombroadCast(players,toPlayers);
    }
    // in setName status
    private synchronized void inSetName (Message message) throws IOException{
        String name = message.getClientName();
        player.setusername(name);
        Message toClient = new Message();
        if (!ServerState.UserList.contains(name)){
            setClientStatus(PlayerStatus.IN_HALL);
            setClientName(name);
            ServerState.UserList.add(name);
            ServerState.clientList.put(player.getusername(),"Online");
            toClient.setPlayerStatus(PlayerStatus.SET_NAME);
            updateGameList();
            toClient.setClientName(player.getusername());
            toClient.setFeedBackMessage("ValidName");
            toClient.setPlayer(player);
            oos.writeObject(toClient);
            updateGameList();
            hall_information();
        }else{
            toClient.setPlayerStatus(PlayerStatus.SET_NAME);
            toClient.setClientName(player.getusername());
            toClient.setFeedBackMessage("InvalidName");
            toClient.setPlayer(player);
            oos.writeObject(toClient);
        }
    }


    // in hall status
    private synchronized void inHall(Message m) throws IOException{
        if (m.getPlayerAction() == PlayerAction.JOIN_TABLE){
            tableId = m.getTableId();
            join(tableId);
        }
    }


    private void join(int tableId)throws IOException{
        Message toClient = new Message();
        List<GameRoom> gameRooms = ServerState.getGameInstance().getConnectedGames();
        System.out.println(tableValid(tableId,gameRooms));
        switch (tableValid(tableId,gameRooms)){
            case "ValidTable":
                this.tableId = tableId;
                GameRoom game = getCurrentGame();
                System.out.println("inside ValidTable");
                game.addPlayer(player);
                setClientStatus(PlayerStatus.IN_ROOM);
                ServerState.clientList.replace(player.getusername(),"In Game");
                toClient.setPlayerStatus(PlayerStatus.IN_HALL);
                toClient.setPlayerAction(PlayerAction.JOIN_TABLE);
                toClient.setFeedBackMessage("ValidTable");
                toClient.setPlayer(player);
                oos.writeObject(toClient);
                hall_information();
                table_information();
                break;
            case "TableNotExist":
                System.out.println("inside TableNotExist");
                GameRoom gameRoom = new GameRoom(player,tableId);
                ServerState.getGameInstance().gameConnected(gameRoom);
                System.out.println("here");
                this.tableId = tableId;
                setClientStatus(PlayerStatus.IN_ROOM);
                ServerState.clientList.replace(player.getusername(),"In Game");
                System.out.println("or here");
                // set message
                toClient.setPlayerStatus(PlayerStatus.IN_HALL);
                toClient.setPlayerAction(PlayerAction.JOIN_TABLE);
                toClient.setFeedBackMessage("ValidTable");
                toClient.setPlayer(player);
                oos.writeObject(toClient);
                hall_information();
                table_information();
                break;
            case "InvalidTable":
                System.out.println("inside InvalidTable");
                toClient.setPlayerStatus(PlayerStatus.IN_HALL);
                toClient.setPlayerAction(PlayerAction.JOIN_TABLE);
                toClient.setFeedBackMessage("InvalidTable");
                toClient.setPlayer(player);
                oos.writeObject(toClient);
                break;
        }
    }

    private String tableValid(int tableId,List<GameRoom> gameRooms){
        System.out.println("called");
        for (int i = 0; i < gameRooms.size(); i++) {
            if (gameRooms.get(i).getTableId()== tableId
                    && gameRooms.get(i).getNumOfPlayer() < gameRooms.get(i).getMaximumPlayerNumber()){
                return "ValidTable";
            }else if (gameRooms.get(i).getTableId()== tableId
                    && gameRooms.get(i).getNumOfPlayer() == gameRooms.get(i).getMaximumPlayerNumber()){
                return "InvalidTable";
            }
        }
        return "TableNotExist";
    }

    private int tableIndex(int tableId,List<GameRoom> gameRooms) {
        int index = -1;
        for (int i = 0; i < gameRooms.size(); i++) {
            if (gameRooms.get(i).getTableId() == tableId) {
                index = i;
                return index;
            }
        }
        return index;
    }
    // in room status
    private synchronized void inRoom(Message m) throws IOException{
        if (m.getPlayerAction() == PlayerAction.READY){
            ready();
        }
        if (m.getPlayerAction() == PlayerAction.RETURN_HALL){
            setClientStatus(PlayerStatus.IN_HALL);
            ServerState.clientList.replace(player.getusername(),"Online");
            GameRoom game = getCurrentGame();
            game.deletePlayer(player);
            hall_information();
            table_information();
        }
    }

    private void ready() throws IOException{
        int numReady= 0;
        GameRoom game = getCurrentGame();
        ArrayList<Player> players= game.getPlayerList();
        game.playerReady(player);
        // logic part
        setClientStatus(PlayerStatus.READY);
        table_information();
        //check how many players are in ready condition
        for (int i = 0; i < game.getNumOfPlayer(); i++) {
            if (players.get(i).getStatus().equals(PlayerStatus.READY)){
                numReady +=1;
            }
        }
        if ( (numReady == game.getNumOfPlayer()) && (numReady >= GameRoom.getMinimumPlayerNumber()) ){
            Message toPlayers = new Message();
            toPlayers.setPlayerStatus(PlayerStatus.IN_HALL);
            toPlayers.setGameStatus(GameStatus.ALL_READY);
            toPlayers.setPlayerList(getPlayerGameStatus());
            roombroadCast(players,toPlayers);
            game.initialGame(player);
            game_information();
        }
    }

    private synchronized void inGame(Message m) throws IOException{
        System.out.println("inGame inside each connection");
        if (m.getPlayerAction() == PlayerAction.VOTING){
            voting(m);
        }
//        if (m.getPlayerAction() == PlayerAction.PASS){
//            pass(m);
//        }
    }

    // for single player
    private void voting(Message m)throws IOException{
        GameRoom game = getCurrentGame();
        Message toPlayers = new Message();
        toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
        toPlayers.setPlayerAction(PlayerAction.VOTING);
        player.setVote(m.getVotingNum());
        // score to be set for the person in current turn
        toPlayers.setScore(game.votingResult(player));
        toPlayers.setClientName(player.getusername());
        toPlayers.setFeedBackMessage("VotingDone");
        ArrayList<Player> players = game.getPlayerList();
        toPlayers.setPlayerList(getPlayerGameStatus());
        roombroadCast(players,toPlayers);
//        score should be updated after voting in the game

    }

//    private void pass(Message m)throws IOException{
//        GameRoom game = getCurrentGame();
//        Message toPlayers = new Message();
//        toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
//        toPlayers.setPlayerAction(PlayerAction.PASS);
//        int n = m.getPassNum();
//        player.setPass(m.getPassNum());
//        String gameStatus = game.PassResult(player);
//        switch (gameStatus)){
//            case "GameEnd":
//                toPlayers.setFeedBackMessage("GameEnd");
//                roombroadCast(players,toPlayers);
//                break;
//            case "GameContinue":
//                toPlayers.setFeedBackMessage("GameContinue");
//                roombroadCast(players,toPlayers);
//                sequenceDecision();
//                break;
//            case "inProgress":
//                break;
//        }
//    }

    private void endgame(Message m)throws IOException{

    }
    /*
   // in game status

           //TODO Game END part
           // return game result
           game.gameResult();
       }
   }
   */

    //TODO pass logic is wrong
   /*private void pass(){
       EachConnection[] players = getPlayerList();
       GameRoom game = getCurrentGame();
       Message toPlayers = new Message();
       game.pass(1);
       toPlayers.setServerAction(ServerAction.RESPONSE);
       toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
       toPlayers.setPlayerAction(PlayerAction.VOTING);
       switch (game.passResult()){
           case "GameEnd":
               toPlayers.setFeedBackMessage("GameEnd");
               roombroadCast(players,toPlayers);
               break;
           case "GameContinue":
               toPlayers.setFeedBackMessage("GameContinue");
               roombroadCast(players,toPlayers);
               sequenceDecision();
               break;
           case "inProgress":
               break;
       }
   }*/

    private void roombroadCast(ArrayList<Player> players,Message m){
        GameRoom game = getCurrentGame();
        List<EachConnection> playerList = ServerState.getClientInstance().getConnectedClients();
        for(int i=0; i< game.getNumOfPlayer(); i++)
        {
            if (playerList.get(i).getClientNum() == players.get(i).getClientnum()){
                playerList.get(i).player = players.get(i);
                playerList.get(i).write(m);
            }
        }

    }

    private void broadCast(List<EachConnection> clients, Message m){
        for(EachConnection client : clients) {
            client.write(m);
        }
    }

    private synchronized void write(Message m) {
        try {
            oos.writeObject(m);
            oos.flush();
            oos.reset();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public String getClientName() {
        return player.getusername();
    }

    public void setClientName(String clientName) {
        player.setusername(clientName);
    }

    public int getClientNum() {
        return player.getClientnum();
    }

    public void setClientNum(int clientNum) {
        player.setClientnum(clientNum);
    }

    public PlayerStatus getClientStatus() {
        return player.getStatus();
    }

    public void setClientStatus(PlayerStatus clientStatus) {
        player.setStatus(clientStatus);
    }

    private Map<String,String> getClientLists(){
        Map<String,String> clientLists = new HashMap<>();
        List<EachConnection> clients = ServerState.getClientInstance().getConnectedClients();
        for (EachConnection client : clients){
            clientLists.put(client.getClientName(),client.getClientStatus().toString());
        }
        return clientLists;
    }

    private Map<String,String> getPlayerList (){
        Map<String,String> playerList = new HashMap<>();
        GameRoom game = getCurrentGame();
        ArrayList<Player> players= game.getPlayerList();
        for (int i = 0; i < game.getNumOfPlayer(); i++) {
            playerList.put(players.get(i).getusername(),
                    players.get(i).getStatus().toString());
        }
        return playerList;
    }

    private Map<String,String> getPlayerGameStatus (){
        Map<String,String> playerGameStatus = new HashMap<>();
        GameRoom game = getCurrentGame();
        ArrayList<Player> players= game.getPlayerList();
        for (int i = 0; i < game.getNumOfPlayer(); i++) {
            playerGameStatus.put(players.get(i).getusername(),
                    players.get(i).getgamestatus());
            System.out.println("this is the game status "+ playerGameStatus);
        }
        return playerGameStatus;
    }

    private void updateGameList(){
        List<GameRoom> gameRooms = ServerState.getGameInstance().getConnectedGames();
        for (GameRoom gameRoom : gameRooms){
            ServerState.gameList.put(gameRoom.getTableId(),gameRoom.getNumOfPlayer());
        }
    }

    private GameRoom getCurrentGame (){
        List<GameRoom> gameRooms = ServerState.getGameInstance().getConnectedGames();
        int index = tableIndex(tableId,gameRooms);
        return gameRooms.get(index);
    }

//    public int getScore() {
//        return score;
//    }
//
//    public void setScore(int score) {
//        this.score = score;
//    }
}
