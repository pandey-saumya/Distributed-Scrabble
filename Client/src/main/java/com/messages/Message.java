package com.messages;

import java.io.Serializable;
import java.util.Map;

public class Message implements Serializable{
    private String[] board;
    private Map<String,Integer> playerScore;
    private Map<String,String> connectedClients;
    private Map<Integer,Integer> createdGames;
    private Map<String,String> playerList;
    private Map<String,String> gameInfo;
    private String[] gameResult;
    private PlayerAction playerAction;
    private GameStatus gameStatus;
    private PlayerStatus playerStatus;
    private Character gameCharacter;
    private String gameLocation;
    private String gameWord;
    private String clientName;
    private String feedBackMessage;
    private int clientNum;
    private int tableId;
    private int votingNum;
    private static final long serialVersionUID = 1L;


    public GameStatus getGameStatus() {
        return gameStatus;
    }

    public void setPlayerScore(Map<String,Integer> playerScore){
        this.playerScore = playerScore;
    }

    public Map<String,Integer> getPlayerScore(){
        return getPlayerScore();
    }

    public void setBoard(String[] board) {
        this.board = board;
    }

    public String[] getBoard() {
        return board;
    }

    public void setGameStatus(GameStatus gameStatus) {
        this.gameStatus = gameStatus;
    }

    public int getClientNum() {
        return clientNum;
    }

    public void setClientNum(int clientNum) {
        this.clientNum = clientNum;
    }

    public PlayerStatus getPlayerStatus() {
        return playerStatus;
    }

    public void setPlayerStatus(PlayerStatus playerStatus) {
        this.playerStatus = playerStatus;
    }

    public PlayerAction getPlayerAction() {
        return playerAction;
    }

    public void setPlayerAction(PlayerAction playerAction) {
        this.playerAction = playerAction;
    }

    public Character getGameCharacter() {
        return gameCharacter;
    }

    public void setGameCharacter(Character gameCharacter) {
        this.gameCharacter = gameCharacter;
    }

    public String getGameLocation() {
        return gameLocation;
    }

    public void setGameLocation(String gameLocation) {
        this.gameLocation = gameLocation;
    }

    public String getGameWord() {
        return gameWord;
    }

    public void setGameWord(String gameWord) {
        this.gameWord = gameWord;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public int getTableId() {
        return tableId;
    }

    public void setTableId(int tableId) {
        this.tableId = tableId;
    }

    public String getFeedBackMessage() {
        return feedBackMessage;
    }

    public void setFeedBackMessage(String feedBackMessage) {
        this.feedBackMessage = feedBackMessage;
    }

    public int getVotingNum() {
        return votingNum;
    }

    public void setVotingNum(int votingNum) {
        this.votingNum = votingNum;
    }

    public Map<String, String> getConnectedClients() {
        return connectedClients;
    }

    public void setConnectedClients(Map<String, String> connectedClients) {
        this.connectedClients = connectedClients;
    }

    public Map<Integer, Integer> getCreatedGames() {
        return createdGames;
    }

    public void setCreatedGames(Map<Integer, Integer> createdGames) {
        this.createdGames = createdGames;
    }

    public Map<String, String> getPlayerList() {
        return playerList;
    }

    public void setPlayerList(Map<String, String> playerList) {
        this.playerList = playerList;
    }

    public Map<String, String> getGameInfo() {
        return gameInfo;
    }

    public void setGameInfo(Map<String, String> gameInfo) {
        this.gameInfo = gameInfo;
    }

    public String[] getGameResult() {
        return gameResult;
    }

    public void setGameResult(String[] gameResult) {
        this.gameResult = gameResult;
    }
}
