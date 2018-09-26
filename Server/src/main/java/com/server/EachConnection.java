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
    private boolean myTurn;
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
                        case IN_GAME:
                            inGame(clientMsg);
                            break;
                    }
                }
            }
        } catch (SocketException socketException) {
            if (clientName != null) {
                ServerState.UserList.remove(clientName);
                ServerState.clientList.remove(clientName);
            }
            Message toALL = new Message();
            ServerState.getClientInstance().clientDisconnected(this);
            ServerState.clientList.remove(clientName);
            updateGameList();
            toALL.setPlayerStatus(PlayerStatus.IN_HALL);
            toALL.setConnectedClients(ServerState.clientList);
            toALL.setCreatedGames(ServerState.gameList);
            broadCast(ServerState.getClientInstance().getConnectedClients(),toALL);
            logger.info("Client on port " + clientSocket.getPort() + " exited.");
        }catch (EOFException e){
            Message toALL = new Message();
            ServerState.getClientInstance().clientDisconnected(this);
            ServerState.clientList.remove(clientName);
            updateGameList();
            toALL.setPlayerStatus(PlayerStatus.IN_HALL);
            toALL.setConnectedClients(ServerState.clientList);
            toALL.setCreatedGames(ServerState.gameList);
            broadCast(ServerState.getClientInstance().getConnectedClients(),toALL);
        } catch (Exception e){
            e.printStackTrace();
        }

    }

    // in setName status
    private synchronized void inSetName (Message m) throws IOException{
        String name = m.getClientName();
        nameCheck(name);
    }

    private void nameCheck(String name) throws IOException{
        Message toClient = new Message();
        Message toALL = new Message();
        List<EachConnection> clients = ServerState.getClientInstance().getConnectedClients();

        if (!ServerState.UserList.contains(name)){
            setClientStatus(PlayerStatus.IN_HALL);
            setClientName(name);
            // TODO settting two lists
            toClient.setPlayerStatus(PlayerStatus.SET_NAME);
            updateGameList();
            toClient.setCreatedGames(ServerState.gameList);
            toClient.setClientName(name);
            toClient.setFeedBackMessage("ValidName");
            ServerState.UserList.add(name);
            ServerState.clientList.put(clientName,"Online");
            toClient.setConnectedClients(ServerState.clientList);
            oos.writeObject(toClient);


            //broadcasting here
            updateGameList();
            toALL.setPlayerStatus(PlayerStatus.IN_HALL);
            toALL.setConnectedClients(ServerState.clientList);
            toALL.setCreatedGames(ServerState.gameList);
            // to all in-hall
            List<EachConnection> inHall = new ArrayList<>();
            for (EachConnection inhall : clients){
                if (inhall.getClientStatus() == PlayerStatus.IN_HALL){
                    inHall.add(inhall);
                }
            }
            broadCast(inHall,toALL);

        }else{
            toClient.setPlayerStatus(PlayerStatus.SET_NAME);
            toClient.setClientName(name);
            toClient.setFeedBackMessage("InvalidName");
            oos.writeObject(toClient);
        }
    }
   // in game hall status
   private synchronized void inHall(Message m) throws IOException{
       Message toClient = new Message();
       Message toALL = new Message();
       List<EachConnection> clients = ServerState.getClientInstance().getConnectedClients();

       if (m.getPlayerAction() == PlayerAction.JOIN_GAME){
           tableId = m.getTableId();
           join(tableId);
       } else if (m.getPlayerAction() == PlayerAction.RETURN_HALL){
           setClientStatus(PlayerStatus.IN_HALL);
           toClient.setPlayerStatus(PlayerStatus.IN_HALL);
           ServerState.clientList.replace(clientName,"Online");
           toClient.setConnectedClients(ServerState.clientList);
           oos.writeObject(toClient);
           // TODO - update game list & user list
           GameRoom game = getCurrentGame();
           game.deletePlayer(clientNum);
           updateGameList();
           toALL.setPlayerStatus(PlayerStatus.IN_HALL);
           toALL.setConnectedClients(ServerState.clientList);
           toALL.setCreatedGames(ServerState.gameList);
           broadCast(clients,toALL);
       }
   }


   private void join(int tableId)throws IOException{
       Message toClient = new Message();
       Message toALL = new Message();
       List<EachConnection> clients = ServerState.getClientInstance().getConnectedClients();
       List<GameRoom> gameRooms = ServerState.getGameInstance().getConnectedGames();

       switch (tableValid(tableId,gameRooms)){
           case "ValidTable":
               this.tableId = tableId;
               GameRoom game = getCurrentGame();
               game.addPlayer(this.clientNum);
               setClientStatus(PlayerStatus.IN_ROOM);
               toClient.setPlayerStatus(PlayerStatus.JOIN_TABLE);
               toClient.setPlayerList(this.getPlayerList());
               toClient.setFeedBackMessage("ValidTable");
               ServerState.clientList.replace(clientName,"In Game");
               toClient.setConnectedClients(ServerState.clientList);
               oos.writeObject(toClient);
               break;
           case "TableNotExist":
               GameRoom gameRoom = new GameRoom(this.clientNum,tableId);
               ServerState.getGameInstance().gameConnected(gameRoom);
               this.tableId = tableId;
               // TODO- insert game into game list
               setClientStatus(PlayerStatus.IN_ROOM);

               // set message
               toClient.setPlayerStatus(PlayerStatus.JOIN_TABLE);
               toClient.setPlayerList(this.getPlayerList());
               toClient.setFeedBackMessage("ValidTable");
               ServerState.clientList.replace(clientName,"In Game");
               toClient.setConnectedClients(ServerState.clientList);
               oos.writeObject(toClient);
               break;
           case "InvalidTable":
               toClient.setPlayerStatus(PlayerStatus.JOIN_TABLE);
               toClient.setFeedBackMessage("InvalidTable");
               oos.writeObject(toClient);
               break;
       }
       // broadcast
       updateGameList();
       toALL.setPlayerStatus(PlayerStatus.IN_HALL);
       toALL.setConnectedClients(ServerState.clientList);
       toALL.setCreatedGames(ServerState.gameList);
       List<EachConnection> inHall = new ArrayList<>();
       for (EachConnection inhall : clients){
           if (inhall.getClientStatus() == PlayerStatus.IN_HALL){
               inHall.add(inhall);
           }
       }
       broadCast(inHall,toALL);
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


   // in room status -ready to join game 
   private synchronized void inRoom(Message m) throws IOException{
       if (m.getPlayerAction() == PlayerAction.READY){
           ready();
       }
   }

   private void ready() throws IOException{
       Message toClient = new Message();
       Message toPlayers = new Message();
       int numReady= 0;
       GameRoom game = getCurrentGame();
       EachConnection[] players = game.getPlayerList();

       // logic part
       setClientStatus(PlayerStatus.READY);

       //check how many players are in ready condition
       for (int i = 0; i < game.getNumOfPlayer(); i++) {
           if (players[i].getClientStatus().equals(PlayerStatus.READY)){
               numReady +=1;
           }
       }

       if ( (numReady == game.getNumOfPlayer()) && (numReady >= GameRoom.getMinimumPlayerNumber()) ){
           //TODO game status - all ready
           toPlayers.setGameStatus(GameStatus.ALL_READY);
           setClientStatus(PlayerStatus.IN_GAME);
           toPlayers.setPlayerStatus(PlayerStatus.IN_ROOM);
           toPlayers.setPlayerAction(PlayerAction.READY);
           toPlayers.setGameInfo(getGameInfo());
           roombroadCast(players,toPlayers);
       }else {
           toPlayers.setPlayerStatus(PlayerStatus.IN_ROOM);
           toPlayers.setGameStatus(GameStatus.NOT_ALLREADY);
           toPlayers.setPlayerList(this.getPlayerList());
           roombroadCast(players,toPlayers);
       }
   }

   // in game status
   private synchronized void inGame(Message m){
       switch (m.getPlayerAction()){
           case GAME_CONTENT:
               gameContent(m);
               break;
           case VOTING:
               voting(m);
               break;
           case PASS:
               pass();
               break;


       }
       getCurrentGame().addOneTurn();
   }

   private String sequenceDecision(){
       //return the messages that who should go first
       GameRoom game = getCurrentGame();
       EachConnection[] players = game.getPlayerList();

       switch (game.getTotalTurn() % game.getMaximumPlayerNumber()){
           case 0:
               players[0].setMyTurn(true);
               players[1].setMyTurn(false);
               players[2].setMyTurn(false);
               players[3].setMyTurn(false);
               break;
           case 1:
               players[0].setMyTurn(false);
               players[1].setMyTurn(true);
               players[2].setMyTurn(false);
               players[3].setMyTurn(false);
               break;
           case 2:
               players[0].setMyTurn(false);
               players[1].setMyTurn(false);
               players[2].setMyTurn(true);
               players[3].setMyTurn(false);
               break;
           case 3:
               players[0].setMyTurn(false);
               players[1].setMyTurn(false);
               players[2].setMyTurn(false);
               players[3].setMyTurn(true);
               break;
       }
       if (myTurn){
           return Integer.toString(score)+" "+"True";
       }else{
           return Integer.toString(score)+" "+"False";
       }
   }

    private void gameContent(Message m){
        GameRoom game = getCurrentGame();
        EachConnection[] players = game.getPlayerList();
       Message toPlayers = new Message();

       //broadcast game content
       toPlayers.setGameCharacter(m.getGameCharacter());
       toPlayers.setGameLocation(m.getGameLocation());
       toPlayers.setGameWord(m.getGameWord());
       toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
       toPlayers.setPlayerAction(PlayerAction.GAME_CONTENT);
       roombroadCast(players,toPlayers);
   }

   private void voting(Message m){
       GameRoom game = getCurrentGame();
       EachConnection[] players = game.getPlayerList();
       Message toPlayers = new Message();
       // add voting number
       int votingNum = m.getVotingNum();
       game.voting(votingNum);
       toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
       toPlayers.setPlayerAction(PlayerAction.VOTING);

       switch (game.votingResult()){
           case "Accpet":
               score += m.getGameWord().length();
               toPlayers.setFeedBackMessage("WordAccepted");
               break;
           case "Reject":
               toPlayers.setFeedBackMessage("WordRejected");
               break;
           case "inProgress":
               break;
       }
       // check if game end
       if (!game.gameEnd()){
           toPlayers.setGameInfo(getGameInfo());
           roombroadCast(players,toPlayers);
       }else{
           //TODO Game END part
           setClientStatus(PlayerStatus.IN_HALL);
           // return game result
           String [] result = game.gameResult();
           toPlayers.setGameStatus(GameStatus.ENDING);
           toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
           toPlayers.setGameResult(result);
           roombroadCast(players,toPlayers);

//           //broadcast
//           Message toALL = new Message();
//           updateGameList();
//           toALL.setPlayerStatus(PlayerStatus.IN_HALL);
//           toALL.setConnectedClients(ServerState.clientList);
//           toALL.setCreatedGames(ServerState.gameList);
//           // to all in-hall
//           List<EachConnection> inHall = new ArrayList<>();
//           for (EachConnection inhall : ServerState.getClientInstance().getConnectedClients()){
//               if (inhall.getClientStatus() == PlayerStatus.IN_HALL){
//                   inHall.add(inhall);
//               }
//           }
//           broadCast(inHall,toALL);
       }
   }

   //TODO pass logic ??
   private void pass(){
       GameRoom game = getCurrentGame();
       EachConnection[] players = game.getPlayerList();
       Message toPlayers = new Message();
       game.pass(1);
       toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
       toPlayers.setPlayerAction(PlayerAction.VOTING);
       switch (game.passResult()){
           case "GameEnd":
               setClientStatus(PlayerStatus.IN_HALL);
               // return game result
               String [] result = game.gameResult();
               toPlayers.setGameStatus(GameStatus.ENDING);
               toPlayers.setPlayerStatus(PlayerStatus.IN_GAME);
               toPlayers.setGameResult(result);
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
   }

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
//CHECK NOT WORKING
//    private Map<String,String> getClientLists(){
//        Map<String,String> clientLists = new HashMap<>();
//        List<EachConnection> clients = ServerState.getClientInstance().getConnectedClients();
//        for (EachConnection client : clients){
//            clientLists.put(client.getClientName(),client.getClientStatus().toString());
//        }
//        return clientLists;
//    }


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

    private Map<String,String> getGameInfo(){
        Map<String,String> gameInfo = new HashMap<>();
        GameRoom game = getCurrentGame();
        EachConnection[] players= game.getPlayerList();
        for (int i = 0; i < game.getNumOfPlayer(); i++) {
            gameInfo.put(players[i].getClientName(), players[i].sequenceDecision());
        }
        return gameInfo;
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

    public boolean isMyTurn() {
        return myTurn;
    }

    public void setMyTurn(boolean myTurn) {
        this.myTurn = myTurn;
    }
}
