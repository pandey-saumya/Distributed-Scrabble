package com.server;

import com.game.GameRoom;
import com.messages.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class EachConnection implements Runnable {

    private int score;
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
    static Logger logger = LoggerFactory.getLogger(EachConnection.class);
    private Map<String, String> listToSend = new HashMap<String, String>();



    public EachConnection(Socket clientSocket, int clientNum) throws IOException{
        this.clientSocket = clientSocket;
        this.clientNum = clientNum;

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
                Message clientMsg = (Message) ois.readObject();

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
                            /*
                        case IN_GAME:
                            inGame(clientMsg);
                            break;
                            */
                    }
                }
            }
        } catch (Exception e) {
            if (clientName != null) {
                ServerState.UserList.remove(clientName);
                ServerState.clientList.remove(clientName);
            }
            if (clientStatus == PlayerStatus.IN_GAME){
                setClientStatus(PlayerStatus.LEAVING);
                GameRoom game = getCurrentGame();
                game.deletePlayer(clientNum,clientName);
                table_information();
            }
            if (clientStatus == PlayerStatus.IN_ROOM)
            { setClientStatus(PlayerStatus.LEAVING);
                GameRoom game = getCurrentGame();
                game.deletePlayer(clientNum,clientName);
                table_information();
            }
            setClientStatus(PlayerStatus.LEAVING);
            ServerState.getClientInstance().clientDisconnected(this);
            ServerState.clientList.remove(clientName);
            updateGameList();
            hall_information();
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
        EachConnection[] players = game.getPlayerList();
        toPlayers.setPlayerList(game.getPlayerStatus());
        roombroadCast(players,toPlayers);
    }
    private void game_information(){
        Message toPlayers = new Message();
        GameRoom game = getCurrentGame();
        toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
        toPlayers.setPlayerAction(PlayerAction.GAME_CONTENT);
        toPlayers.setPlayerScore(game.getPlayerScore());
        EachConnection[] players = game.getPlayerList();
        toPlayers.setBoard(game.getBoard());
        toPlayers.setPlayerList(game.getPlayerStatus());
        roombroadCast(players,toPlayers);
    }
    // in setName status
    private synchronized void inSetName (Message m) throws IOException{
        String name = m.getClientName();
        Message toClient = new Message();
        if (!ServerState.UserList.contains(name)){
            setClientStatus(PlayerStatus.IN_HALL);
            setClientName(name);
            ServerState.UserList.add(name);
            ServerState.clientList.put(clientName,"Online");
            toClient.setPlayerStatus(PlayerStatus.SET_NAME);
            updateGameList();
            toClient.setClientName(name);
            toClient.setFeedBackMessage("ValidName");
            oos.writeObject(toClient);
            updateGameList();
            hall_information();
        }else{
            toClient.setPlayerStatus(PlayerStatus.SET_NAME);
            toClient.setClientName(name);
            toClient.setFeedBackMessage("InvalidName");
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

        switch (tableValid(tableId,gameRooms)){
            case "ValidTable":
                this.tableId = tableId;
                GameRoom game = getCurrentGame();
                game.addPlayer(this.clientNum);
                setClientStatus(PlayerStatus.IN_ROOM);
                ServerState.clientList.replace(clientName,"In Game");
                toClient.setPlayerStatus(PlayerStatus.IN_HALL);
                toClient.setPlayerAction(PlayerAction.JOIN_TABLE);
                toClient.setFeedBackMessage("ValidTable");
                oos.writeObject(toClient);
                hall_information();
                table_information();
                break;
            case "TableNotExist":
                GameRoom gameRoom = new GameRoom(this.clientNum,tableId);
                ServerState.getGameInstance().gameConnected(gameRoom);
                this.tableId = tableId;
                setClientStatus(PlayerStatus.IN_ROOM);
                ServerState.clientList.replace(clientName,"In Game");
                // set message
                toClient.setPlayerStatus(PlayerStatus.IN_HALL);
                toClient.setPlayerAction(PlayerAction.JOIN_TABLE);
                toClient.setFeedBackMessage("ValidTable");
                oos.writeObject(toClient);
                hall_information();
                table_information();
                break;
            case "InvalidTable":
                toClient.setPlayerStatus(PlayerStatus.IN_HALL);
                toClient.setPlayerAction(PlayerAction.JOIN_TABLE);
                toClient.setFeedBackMessage("InvalidTable");
                oos.writeObject(toClient);
                break;
        }
    }

    private String tableValid(int tableId,List<GameRoom> gameRooms){
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
            ServerState.clientList.replace(clientName,"Online");
            GameRoom game = getCurrentGame();
            game.deletePlayer(clientNum,clientName);
            hall_information();
            table_information();
        }
    }

    private void ready() throws IOException{
        int numReady= 0;
        GameRoom game = getCurrentGame();
        EachConnection[] players = game.getPlayerList();
        game.playerReady(clientName);
        // logic part
        setClientStatus(PlayerStatus.READY);
        table_information();
        //check how many players are in ready condition
        for (int i = 0; i < game.getNumOfPlayer(); i++) {
            if (players[i].getClientStatus().equals(PlayerStatus.READY)){
                numReady +=1;
            }
        }
        if ( (numReady == game.getNumOfPlayer()) && (numReady >= GameRoom.getMinimumPlayerNumber()) ){
            Message toPlayers = new Message();
            toPlayers.setPlayerStatus(PlayerStatus.IN_HALL);
            toPlayers.setGameStatus(GameStatus.ALL_READY);
            roombroadCast(players,toPlayers);
            game.initialGame();
            game_information();
        }
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

    private void roombroadCast(EachConnection[] players,Message m){
        GameRoom game = getCurrentGame();
        for (int i = 0; i < game.getNumOfPlayer(); i++) {
            players[i].write(m);
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
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getClientNum() {
        return clientNum;
    }

    public void setClientNum(int clientNum) {
        this.clientNum = clientNum;
    }

    public PlayerStatus getClientStatus() {
        return clientStatus;
    }

    public void setClientStatus(PlayerStatus clientStatus) {
        this.clientStatus = clientStatus;
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
        EachConnection[] players= game.getPlayerList();
        for (int i = 0; i < game.getNumOfPlayer(); i++) {
            playerList.put(players[i].getClientName(),
                    players[i].getClientStatus().toString());
        }
        return playerList;
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

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }
}
