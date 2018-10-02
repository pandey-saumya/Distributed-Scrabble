package com.game;


import com.messages.PlayerStatus;
import java.io.*;

public class Player implements Serializable{
    //  private final SimpleStringProperty username;
    //  private final SimpleStringProperty status;

    private PlayerStatus Status;
    private String username;
    private int score;
    private int clientnum;
    private String gamestatus;
    private int vote = 1;
    private int pass = 0;

    public Player(String username, PlayerStatus status, int score, int clientnum, String gamestatus, int vote, int pass){
        this.username = username;
        this.Status = status;
        this.score = score;
        this.clientnum = clientnum;
        this.gamestatus = gamestatus;
        this.vote = vote;
        this.pass = pass;
    }

    public Player(){

    }

    public String getgamestatus() {
        return this.gamestatus;
    }

    public void setgamestatus(String gamestatus) {

        this.gamestatus = gamestatus;
    }

    public String getusername() {
        return this.username;
    }

    public void setusername(String username) {

        this.username = username;
    }

    public PlayerStatus getStatus() {
        return this.Status;
    }

    public void setStatus(PlayerStatus status) {
        this.Status = status;

    }
    public int getscore(){
        return this.score;
    }

    public void setscore(int score){
        this.score = score;
    }

    public int getClientnum(){
        return this.clientnum;
    }

    public void setClientnum(int score){
        this.clientnum = clientnum;
    }

    public int getVote(){
        return this.vote;
    }

    public void setVote(int vote){
        this.vote = vote;
    }

    public int getPass(){
        return this.pass;
    }

    public void setPass(int pass){
        this.pass = pass;
    }

}
