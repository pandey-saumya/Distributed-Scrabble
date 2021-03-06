package com.view.table;

import com.Game;
import com.view.hall.HallController;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import java.io.IOException;
import java.net.URL;

import java.util.Optional;
import java.util.ResourceBundle;
import javafx.geometry.Insets;

import java.util.*;
import java.util.regex.Pattern;



public class TableController implements Initializable{

    public static String[] board = new String[400];
    public static final int TableWidth = 1100;
    public static final int TableHeight = 825;
    private double xOffset;
    private double yOffset;
    //<editor-fold defaultstate="collapsed" desc="// initialize for game board (Do not open)" (P.S. this work is exhausting) >
    @FXML private BorderPane borderPane;
    @FXML public Label title;
    @FXML private Label player1Name;
    @FXML private Label player1Score;
    @FXML public Label player1Ready;
    @FXML private ImageView player1Turn;
    @FXML private Label player2Name;
    @FXML private Label player2Score;
    @FXML private Label player2Ready;
    @FXML private ImageView player2Turn;
    @FXML private Label player3Name;
    @FXML private Label player3Score;
    @FXML private Label player3Ready;
    @FXML private ImageView player3Turn;
    @FXML private Label player4Name;
    @FXML private Label player4Score;
    @FXML private Label player4Ready;
    @FXML private ImageView player4Turn;
    @FXML private TextField chatMessage;
    @FXML private TextArea chatArea;


    @FXML private GridPane playerBoard;

    //</editor-fold>
    private static TableController instance;
    private static Stage readyStage;
    private static Stage timerStage;
    public static ReadyController readyController;
    public static TimerController timerController;
    public static String currentPlayer;


    @FXML private List<SCell> cells;

    private List<SCell> selectWords=new ArrayList<>();
    private boolean isInputOnce=false;
    private int[] inputPos=new int[2]; // storing letter input position
    private int orientation=-1; // 0 for horizontal,1 for vertical, -1 is initial value
    private String[][] boardData=new String[20][20];

    public TableController(){
        instance = this;
    }

    public static TableController getInstance() {
        return instance;
    }

    public Stage getReadyStage(){
        return readyStage;
    }

    public Stage getTimerStage() {
        return timerStage;
    }

    public void setCurrentPlayer(String name){
        currentPlayer = name;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                boardData[i][j]="";
            }
        }
        //<editor-fold defaultstate="collapsed" desc="//Drag and Drop animation">
        borderPane.setOnMousePressed(event -> {
            xOffset = HallController.getStage().getX() - event.getScreenX();
            yOffset = HallController.getStage().getY() - event.getScreenY();
            borderPane.setCursor(Cursor.CLOSED_HAND);
        });

        borderPane.setOnMouseDragged(event -> {
            HallController.getStage().setX(event.getScreenX() + xOffset);
            HallController.getStage().setY(event.getScreenY() + yOffset);

        });

        borderPane.setOnMouseReleased(event -> {
            borderPane.setCursor(Cursor.DEFAULT);
        });
        //</editor-fold>
        //<editor-fold defaultstate="collapsed" desc="//Initialize Player Status do not open">

        // player name
        String emptyPlayerName = "Empty";
        player1Name.setText(emptyPlayerName);
        player2Name.setText(emptyPlayerName);
        player3Name.setText(emptyPlayerName);
        player4Name.setText(emptyPlayerName);

        //player score
        String emptyPlayerScore = "0";
        player1Score.setText(emptyPlayerScore);
        player2Score.setText(emptyPlayerScore);
        player3Score.setText(emptyPlayerScore);
        player4Score.setText(emptyPlayerScore);

        //player ready
        player1Ready.setVisible(false);
        player2Ready.setVisible(false);
        player3Ready.setVisible(false);
        player4Ready.setVisible(false);

        //player turn                                              change src to "images/true.png" to show green light
        player1Turn.setImage(new Image(getClass().getClassLoader().getResource("images/false.png").toString()));
        player2Turn.setImage(new Image(getClass().getClassLoader().getResource("images/false.png").toString()));
        player3Turn.setImage(new Image(getClass().getClassLoader().getResource("images/false.png").toString()));
        player4Turn.setImage(new Image(getClass().getClassLoader().getResource("images/false.png").toString()));

        //</editor-fold> do

        chatArea.textProperty().addListener(new ChangeListener<Object>() {
            @Override
            public void changed(ObservableValue<?> observable, Object oldValue,
                                Object newValue) {
                chatArea.setScrollTop(Double.MAX_VALUE); //this will scroll to the bottom
                //use Double.MIN_VALUE to scroll to the top
            }
        });

    }
//do not open text fields for UI
    public void setEditable(){
        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                if(!boardData[i][j].isEmpty()) {
                    cells.get(i*20+j).setLockStatus();
                }
            }
        }
    }

    public void setBoard(String[][] board){
        boardData=board;
        Platform.runLater(()-> {
            for (int i = 0; i < 20; i++) {
                for (int j = 0; j < 20; j++) {
                    cells.get(i*20+j).setText(boardData[i][j]);
                }
            }
            this.setEditable();
        });
    }

    public void deHilightAll(){
        Platform.runLater(()->{
            for(int i=0;i<20;i++){
                for(int j=0;j<20;j++){
                    if(!boardData[i][j].isEmpty()) {
                        cells.get(i*20+j).setDeHilightStatus();
                    }
                }
            }
        });
    }

    public String[][] getBoard(){
        return boardData;
    }

    public void resetPlayerStatus(){
        Platform.runLater(()->{
            player1Name.setText("Empty");
            player2Name.setText("Empty");
            player3Name.setText("Empty");
            player4Name.setText("Empty");
            player1Score.setText("0");
            player2Score.setText("0");
            player3Score.setText("0");
            player4Score.setText("0");
            player1Ready.setVisible(false);
            player2Ready.setVisible(false);
            player3Ready.setVisible(false);
            player4Ready.setVisible(false);
        });
    }

    // Update Players' Turn
    public void refreshPlayerTurn(String name,Boolean turn){
        Platform.runLater(()->{
            if (player1Name.getText().equals(name)){
                if (turn){
                    player1Turn.setImage(new Image(getClass().getClassLoader().getResource("images/true.png").toString()));
                }else {
                    player1Turn.setImage(new Image(getClass().getClassLoader().getResource("images/false.png").toString()));
                }
            } else if (player2Name.getText().equals(name)){
                if (turn){
                    player2Turn.setImage(new Image(getClass().getClassLoader().getResource("images/true.png").toString()));
                }else {
                    player2Turn.setImage(new Image(getClass().getClassLoader().getResource("images/false.png").toString()));
                }
            } else if (player3Name.getText().equals(name)){
                if (turn){
                    player3Turn.setImage(new Image(getClass().getClassLoader().getResource("images/true.png").toString()));
                }else {
                    player3Turn.setImage(new Image(getClass().getClassLoader().getResource("images/false.png").toString()));
                }
            } else if (player4Name.getText().equals(name)){
                if (turn){
                    player4Turn.setImage(new Image(getClass().getClassLoader().getResource("images/true.png").toString()));
                }else {
                    player4Turn.setImage(new Image(getClass().getClassLoader().getResource("images/false.png").toString()));
                }
            }
        });
    }

    //set ready status to invisible
    public void setAllReady(){
        Platform.runLater(()->{
            player1Ready.setVisible(false);
            player2Ready.setVisible(false);
            player3Ready.setVisible(false);
            player4Ready.setVisible(false);
        });
    }

    //Updating player score here
    public void refreshPlayerScore(String name,String score){
        Platform.runLater(()->{
            if (player1Name.getText().equals(name)){
                player1Score.setText(score);
            } else if (player2Name.getText().equals(name)){
                player2Score.setText(score);
            } else if (player3Name.getText().equals(name)){
                player3Score.setText(score);
            } else if (player4Name.getText().equals(name)){
                player4Score.setText(score);
            }
        });
    }

    // Refresh Player's ready or not Status
    public void refreshPlayerStatus(String name,String status){
        Platform.runLater(()->{
            if (player1Name.getText().equals("Empty")){
                player1Name.setText(name);
                if (status.equals("NotReady")){
                    player1Ready.setVisible(false);
                }else if (status.equals("Ready")){
                    player1Ready.setVisible(true);
                }
            }else if (player2Name.getText().equals("Empty")){
                player2Name.setText(name);
                if (status.equals("NotReady")){
                    player2Ready.setVisible(false);
                }else if (status.equals("Ready")){
                    player2Ready.setVisible(true);
                }
            }else if (player3Name.getText().equals("Empty")){
                player3Name.setText(name);
                if (status.equals("NotReady")){
                    player3Ready.setVisible(false);
                }else if (status.equals("Ready")){
                    player3Ready.setVisible(true);
                }
            }else if (player4Name.getText().equals("Empty")){
                player4Name.setText(name);
                if (status.equals("NotReady")){
                    player4Ready.setVisible(false);
                }else if (status.equals("Ready")){
                    player4Ready.setVisible(true);
                }
            }
        });
    }
    @FXML
    public void showReadyStage() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/ready.fxml"));
        Parent window = (Pane) fxmlLoader.load();
        readyController = fxmlLoader.getController();
        readyController.title.setText(HallController.tableNumber);
        Scene scene = new Scene(window);
        scene.setFill(null);

        Platform.runLater(() -> {
            readyStage = new Stage();
            // Paternity
            readyStage.initOwner(title.getScene().getWindow());
            readyStage.initStyle(StageStyle.UNDECORATED);
            readyStage.initStyle(StageStyle.TRANSPARENT);
            readyStage.initModality(Modality.APPLICATION_MODAL);
            readyStage.setWidth(TableWidth);
            readyStage.setHeight(TableHeight);
            readyStage.setScene(scene);
            readyStage.show();
        });
    }

    //game timer here 5 4 3 2 1.... start
    public void gameStart() throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/timer.fxml"));
        Parent window = (Pane) fxmlLoader.load();
        timerController = fxmlLoader.getController();
        Scene timerScene = new Scene(window);
        timerScene.setFill(null);
        Platform.runLater(()->{
            timerStage = new Stage();
            timerStage.initOwner(title.getScene().getWindow());
            timerStage.initStyle(StageStyle.UNDECORATED);
            timerStage.initStyle(StageStyle.TRANSPARENT);
            timerStage.initModality(Modality.APPLICATION_MODAL);
            timerStage.setWidth(TableWidth);
            timerStage.setHeight(TableHeight);
            timerStage.setX(HallController.getStage().getX());
            timerStage.setY(HallController.getStage().getY());
            timerStage.setScene(timerScene);
            timerStage.show();
        });
        this.setAllReady();
    }

    @FXML
    private void confirm() {

        if (Game.turn) {
            Platform.runLater(() -> {
                String word = null;
                if (!isInputOnce) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please choose a block and enter a letter!");
                    alert.showAndWait();
                } else if(orientation!=-1){

                    if (orientation==0) {
                        isInputOnce=false;
                        word = getHorizontalWord();
                        Game.sendCharacter(getBoardInputPosition(), orientation, boardData[inputPos[0]-1][inputPos[1]-1].toUpperCase(), word, currentPlayer);

                    } else if (orientation==1) {
                        isInputOnce=false;
                        word = getVerticalWord();
                        Game.sendCharacter(getBoardInputPosition(), orientation, boardData[inputPos[0]-1][inputPos[1]-1].toUpperCase(), word, currentPlayer);
                    }
                }else{
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please choose word orientation!");
                    alert.showAndWait();
                }
            });

        } else {

            Platform.runLater(() -> {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Not your turn!");
                alert.setContentText("Please wait for others...");
                alert.showAndWait();
            });
        }
    }

    @FXML
    private void pass(){
        if (Game.turn){
            Platform.runLater(()-> {
                Game.pass();
                isInputOnce = false;
                deHilightAll();
            });
        }else {
            Platform.runLater(()->{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Not your turn!");
                alert.setContentText("Please wait for others...");
                alert.showAndWait();
            });
        }
    }

    @FXML
    private void chooseHorizontalWord(){
        Platform.runLater(()->{
            if(Game.turn) {
                if (!isInputOnce) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please choose a block and enter a letter!");
                    alert.showAndWait();
                }else {
                    deHilightAll();
                    highLight(getBoardInputPosition(), 0);
                    orientation=0;
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Not your turn!");
                alert.setContentText("Please wait for others...");
                alert.showAndWait();
            }
        });
    }


    @FXML
    private void chooseVerticalWord(){
        Platform.runLater(()->{
            if(Game.turn) {
                if (!isInputOnce) {
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Please choose a block and enter a letter!");
                    alert.showAndWait();
                }else {
                    deHilightAll();
                    highLight(getBoardInputPosition(), 1);
                    orientation=1;
                }
            }else {
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Not your turn!");
                alert.setContentText("Please wait for others...");
                alert.showAndWait();
            }
        });
    }

    @FXML
    private void sendChatMessage(){
        Platform.runLater(()->{
            if(chatMessage.getText().isEmpty()){
                    Alert alert = new Alert(Alert.AlertType.ERROR);
                    alert.setTitle("Error");
                    alert.setHeaderText("Can't send empty message!");
                    alert.showAndWait();
            }else{
                String m=chatMessage.getText();
                chatMessage.setText("");
                Game.sendChatMessage(currentPlayer+":"+m);
            }
        });
    }

    public void addChatMessage(String m){
        Platform.runLater(()->{
            chatArea.appendText(m+"\n");
        });
    }


    public void voting(String name,String word, String voteFor){
        Platform.runLater(()->{
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Vote");
            alert1.setHeaderText("Do you really think < "+word+" > is a word ?");
            alert1.setContentText("Do you really think < "+word+" > is a word ?");
            ButtonType buttonyes = new ButtonType("Yes");
            ButtonType buttonno = new ButtonType("No");
            alert1.getButtonTypes().setAll(buttonyes,buttonno);
            Optional<ButtonType> result1 = alert1.showAndWait();
            //add condition to not allow self voting by the player
            if(result1.get()==buttonyes) {
                Game.voting(1,name,word,voteFor);
            }
            else if(result1.get()==buttonno) {
                Game.voting(0,name,word, voteFor);
            }
        });
    }

    @FXML
    private void help(){
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.INFORMATION);
            alert.setTitle("Welcome to the Game by Coding Ninjas");
            alert.setHeaderText("About Distributed Scrabble:");
            alert.setContentText("Click on Exit if you wish to exit the game"
                    +"Each player will place on character in each turn \r\n\r\n Player will then choose the word from the grid and Press confirm to submit"
                    + "Score is equal to the length of the valid word"
                    + "he word is considered valid if all the other players vote in favour of the word"
                    +"The game ends when one player quits \r\n\r\n or if there is no place left in the grid");
            alert.showAndWait();
        });
    }

    // return to game hall
    public void returnHall() {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Exit");
        alert.setHeaderText("Confirmation");
        alert.setContentText("Are you sure you want to exit this game ?");
        Optional result = alert.showAndWait();
        if (result.get() == ButtonType.OK) {
            HallController.getStage().close();
            Game.getPrimaryStage().show();
            Game.returnToHall();
        }
    }

    // Minimize Window
    public void minimizeWindow(){
        HallController.getStage().setIconified(true);
    }



    // read letter input from cell
    @FXML private void keyListener(KeyEvent e) {
        if(Game.turn) {
            String input = e.getText();
            SCell cell = ((SCell) e.getSource());

            // set cell text from input, reset cell if input is 0
            if (!cell.status.equals(CStatus.LOCK)) {
                if (!cell.getText().isEmpty()) {
                    if (input.equals("0")) {
                        cell.setText("");
                        cell.setInitStatus();
                        isInputOnce = false;
                        boardData[inputPos[0] - 1][inputPos[1] - 1] = "";
                        inputPos[1] = 0;
                        inputPos[0] = 0;
                        if (selectWords.contains(cell)) {
                            selectWords.remove(cell);
                        }
                    }
                    return;
                }
                if (!isInputOnce && !input.isEmpty() && Pattern.matches("[a-zA-Z]", input)) {
                    cell.setInitStatus();
                    cell.setText(input.toUpperCase());
                    isInputOnce = true;
                    inputPos[1] = playerBoard.getColumnIndex((SCell) e.getSource());
                    inputPos[0] = playerBoard.getRowIndex((SCell) e.getSource());
                    boardData[inputPos[0] - 1][inputPos[1] - 1] = input.toUpperCase();
                }
            }
        }else{
            Platform.runLater(()->{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Not your turn!");
                alert.setContentText("Please wait for others...");
                alert.showAndWait();
            });
        }
    }

    // click mouse to select words
    @FXML private void mouseListener(MouseEvent e) {

    }

    public void highLight(int[] letterPos, int wordOrientation) {
        Platform.runLater(()-> {
            int x = letterPos[0];
            int y = letterPos[1];
            int m;

            if(wordOrientation == 0) {
                m = 0;
                while (y - m >= 0) {
                    if (boardData[x][y - m].isEmpty()) {
                        break;
                    }
                    cells.get(20 * x + y - m).setHighLightStatus();
                    m++;
                }
                m = 1;
                while (y + m < 20) {
                    if (boardData[x][y + m].isEmpty()) {
                        break;
                    }
                    cells.get(20 * x + y + m).setHighLightStatus();
                    m++;
                }
            }else{
                m=0;
                while(x-m >= 0){
                    if(boardData[x-m][y].isEmpty()){
                        break;
                    }
                    cells.get(20 * (x-m) + y).setHighLightStatus();
                    m++;
                }
                m=1;
                while(x+m < 20){
                    if(boardData[x+m][y].isEmpty()){
                        break;
                    }
                    cells.get(20 * (x+m) + y).setHighLightStatus();
                    m++;
                }
            }
        });
    }

    // because inputPos records the position on the 21*21 gridPane, need to -1 get real position on board
    private int[] getBoardInputPosition(){
        int[] position=new int[2];
        position[0]=inputPos[0]-1;
        position[1]=inputPos[1]-1;
        return position;
    }


    private String getHorizontalWord(){
        int m=0;
        StringBuilder s=new StringBuilder();
        String nextLetter;

        while(inputPos[1]-m>0){
            nextLetter =boardData[inputPos[0]-1][inputPos[1]-1-m];
            if(nextLetter.isEmpty()){
                break;
            }
            s.append(nextLetter);
            m++;
        }
        s.reverse();
        m=1;
        while(inputPos[1]+m<21){
            nextLetter =boardData[inputPos[0]-1][inputPos[1]-1+m];
            if(nextLetter.isEmpty()){
                break;
            }
            s.append(nextLetter);
            m++;
        }
        return s.toString();

    }

    private String getVerticalWord(){
        int m=0;
        StringBuilder s=new StringBuilder();
        String nextLetter;

        while(inputPos[0]-m>0){
            nextLetter =boardData[inputPos[0]-1-m][inputPos[1]-1];
            if(nextLetter.isEmpty()){
                break;
            }
            s.append(nextLetter);
            m++;
        }
        s.reverse();
        m=1;
        while(inputPos[0]+m<21){
            nextLetter =boardData[inputPos[0]-1+m][inputPos[1]-1];
            if(nextLetter.isEmpty()){
                break;
            }
            s.append(nextLetter);
            m++;
        }
        return s.toString();
    }

}