package com.game;

import com.messages.Message;
import com.server.EachConnection;
import com.server.ServerState;

import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.OutputStream;
import java.util.*;


public class GameRoom{
    // assuming there are up to 4 players in each game room
    private static int MAXIMUM_PLAYER_NUMBER = 4;
    private static int MINIMUM_PLAYER_NUMBER = 2;
    // tracking number of total players in one game room
    private int numOfPlayer;
    private int tableId;
    private EachConnection[] playerList = new EachConnection[MAXIMUM_PLAYER_NUMBER];
    private InputStream in;
    private OutputStream out;
    private ObjectInputStream ois;
    private ObjectOutputStream oos;
    private int votingNum = 0;
    private int spaceRemain = 400;
    private int turnNum = 0;
    private int passNum = 0;
    private int totalTurn = 0;
    boolean allPass = false;
    ArrayList<Player> playerarray=new ArrayList<Player>();
    //    private Map<String,String> playerStatus = new HashMap();
    private String[] board;
    //    private Map<String,Integer> playerScore = new HashMap();
    public GameRoom( Player player, int tableId) {
        addPlayer(player);
        initialBoard();
        this.tableId = tableId;
    }
    public void initialGame(Player player){
        for(int i=0;i<playerarray.size();i++)
        {
            playerarray.get(i).setgamestatus("NotTurn");
        }
//        String[] keys = playerStatus.keySet().toArray(new String[0]);
//        Random random = new Random();
//        String randomKey = keys[random.nextInt(keys.length)];
//        playerStatus.replace(randomKey,"Turn");
//        player.setgamestatus("Turn");
        player.setgamestatus("Turn");
    }

    public void initialBoard(){
        board = new String[400];
        for (int i =0; i<400;i++){
            board[i] = "0";
        }
    }
    public void addCharacter(int index,String character){
        board[index] = character;
    }
    public String[] getBoard(){
        return board;
    }
    public void setPlayerScore(Player player,Integer score){
        player.setscore(score);
    }


    public ArrayList<Player> getAll(){
        return playerarray;
    }

    public void addPlayer(Player player){
        System.out.println("inside this");
        List<EachConnection> clients = ServerState.getClientInstance().getConnectedClients();
        for (EachConnection client : clients){
            if (client.getClientNum() == player.getClientnum()){
                playerarray.add(player);
                playerList[numOfPlayer] = client;
                player.setgamestatus("NotReady");
                player.setscore(0);
            }
        }
        this.numOfPlayer+=1;
    }

    public void deletePlayer(Player player){
//        int index = indexOf(player.getClientnum());
        playerarray.remove(player);
        this.numOfPlayer-=1;
//        if (index != -1){
//            playerList[index] = null;
//            for (int x = 0; x < numOfPlayer ; x++) {
//                if(x>=index){
//                    playerList[x] = playerList[x+1];
//                }
//            }
//        }
    }

    public void playerReady(Player player){
        player.setgamestatus("Ready");
    }

//    public void playerTurn(String name) {
//        playerStatus.replace(name,"Turn");
//        for (String key : playerStatus.keySet()) {
//            if (!key.equals(name)) {
//                playerStatus.replace(key, "NotTurn");
//            }
//        }
//    }


//TODO VOTING RESULT CONDITIONS ADD HERE
   /* public String votingResult(){
        if (turnNum == getturnnumber  && votingNum == MAXIMUM_PLAYER_NUMBER && getAcceptcount && getRejectcount){
            setTurnNum(0);
            return "Accept";
        }else if (turnNum == getturnnumber  && votingNum == !MAXIMUM_PLAYER_NUMBER && getAcceptcount && getRejectcount){
            setTurnNum(0);
            return "Reject";
        }else{
            return "inProgress";
        }
    }*/

    //ADD PASS RESULT CONDITIONS HERE. BELOW IS ONE CASE
    public String passResult(){
        if (turnNum == 4 && passNum == MAXIMUM_PLAYER_NUMBER){
            setTurnNum(0);
            return "GameEnd";
        }else if (turnNum == 4 && passNum != MAXIMUM_PLAYER_NUMBER){
            setTurnNum(0);
            return "GameContinue";
        }else{
            return "inProgress";
        }
    }

    public int votingResult(Player player){
        for(int i=0;i<playerarray.size();i++)
        {
            int vote = playerarray.get(i).getVote();

            if(vote == 0){
                player.setscore(20);
                return player.getscore();
            }
        }
        player.setscore(50);
        int score = player.getscore();
        return score;
    }

    public void PassResult(Player player){
        int totalpass = 0;
        for(int i=0;i<playerarray.size();i++)
        {
            int pass = playerarray.get(i).getPass();
            totalpass += pass;
            if(totalpass == playerarray.size()){
                allPass = true;
                player.setscore(0);
                // set score doing dummy
                // call game result pending
                gameEnd();
            }
        }

    }

    public boolean gameEnd(){
        if (numOfPlayer < MINIMUM_PLAYER_NUMBER || spaceRemain == 0){
            return true;
        }
        if(allPass){
            return true;
        }
        return false;
    }

    //TODO gameResult display format
    public void gameResult(){

    }

    public int getTableId() {
        return tableId;
    }

    public int getNumOfPlayer() {
        return numOfPlayer;
    }

    public static int getMaximumPlayerNumber() {
        return MAXIMUM_PLAYER_NUMBER;
    }

    public static int getMinimumPlayerNumber() {
        return MINIMUM_PLAYER_NUMBER;
    }

    public void setNumOfPlayer(int numOfPlayer) {
        this.numOfPlayer = numOfPlayer;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public int getSpaceRemain() {
        return spaceRemain;
    }

    public void setSpaceRemain(int spaceRemain) {
        this.spaceRemain = spaceRemain;
    }

    public ArrayList<Player> getPlayerList() {
        return playerarray;
    }

    public void setPlayerList(ArrayList<Player> playerarray) {
        this.playerarray = playerarray;
    }

    public int getVotingNum() {
        return votingNum;
    }


    public int getTurnNum() {
        return turnNum;
    }

    public void setTurnNum(int turnNum) {
        this.turnNum = turnNum;
    }

    public int getPassNum() {
        return passNum;
    }

    public void pass(int passNum) {
        this.passNum += passNum;
        this.turnNum += 1;
    }

    public int getTotalTurn() {
        return totalTurn;
    }

    public void setTotalTurn(int totalTurn) {
        this.totalTurn = totalTurn;
    }

    public void addOneTurn(){
        this.totalTurn += 1;
    }
}
