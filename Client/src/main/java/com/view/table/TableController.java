package com.view.table;

import com.Game;
import com.view.hall.HallController;
import javafx.application.Platform;
import javafx.beans.property.StringProperty;
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
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.*;
import java.util.regex.Pattern;


public class TableController implements Initializable{


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
    @FXML private GridPane playerBoard;
    //</editor-fold>
    private static TableController instance;
    private static Stage readyStage;
    public static ReadyController readyController;

    @FXML private List<SCell> cells;

    private List<SCell> selectWords=new ArrayList<>();
    private boolean isInputOnce=false;
    private int[] inputPos=new int[2];
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

    @Override
    public void initialize(URL location, ResourceBundle resources) {

        /** initial some input just for test scoring function*/

        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                boardData[i][j]="";
            }
        }

        boardData[0][0]="a";
        boardData[0][1]="b";
        boardData[0][2]="c";
        boardData[1][0]="b";
        boardData[2][0]="c";

        boardData[2][8]="a";
        boardData[3][8]="b";
        boardData[5][8]="b";
        boardData[6][8]="c";

        boardData[6][2]="a";
        boardData[6][3]="b";
        boardData[6][5]="b";
        boardData[6][6]="c";

        boardData[19][17]="a";
        boardData[19][18]="b";
        boardData[19][19]="c";
        boardData[18][19]="b";
        boardData[17][19]="c";

        for(int i=0;i<20;i++){
            for(int j=0;j<20;j++){
                ((SCell)playerBoard.getChildren().get(40+i*20+j)).setText(boardData[i][j]);
            }
        }

        /** */


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
        //<editor-fold defaultstate="collapsed" desc="//Initialize Player Status">

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

        //</editor-fold>
    }


    public void resetPlayerStatus(){
        Platform.runLater(()->{
            String emptyPlayerName = "Empty";
            player1Name.setText(emptyPlayerName);
            player2Name.setText(emptyPlayerName);
            player3Name.setText(emptyPlayerName);
            player4Name.setText(emptyPlayerName);

            String emptyPlayerScore = "0";
            player1Score.setText(emptyPlayerScore);
            player2Score.setText(emptyPlayerScore);
            player3Score.setText(emptyPlayerScore);
            player4Score.setText(emptyPlayerScore);

            player1Ready.setVisible(false);
            player2Ready.setVisible(false);
            player3Ready.setVisible(false);
            player4Ready.setVisible(false);
        });
    }

    public void refreshPlayerStatus(String name,String status){
        String playerName = name;
        String readyStatus = status;
        Platform.runLater(()->{
            if (player1Name.getText().equals("Empty")){
                player1Name.setText(playerName);
                if (readyStatus.equals("NotReady")){
                    player1Ready.setVisible(false);
                }else if (readyStatus.equals("Ready")){
                    player1Ready.setVisible(true);
                }
            }else if (player2Name.getText().equals("Empty")){
                player2Name.setText(playerName);
                if (readyStatus.equals("NotReady")){
                    player2Ready.setVisible(false);
                }else if (readyStatus.equals("Ready")){
                    player2Ready.setVisible(true);
                }
            }else if (player3Name.getText().equals("Empty")){
                player3Name.setText(playerName);
                if (readyStatus.equals("NotReady")){
                    player3Ready.setVisible(false);
                }else if (readyStatus.equals("Ready")){
                    player3Ready.setVisible(true);
                }
            }else if (player4Name.getText().equals("Empty")){
                player4Name.setText(playerName);
                if (readyStatus.equals("NotReady")){
                    player4Ready.setVisible(false);
                }else if (readyStatus.equals("Ready")){
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

    @FXML
    private void confirm() {
        //TODO - send to server (playerStatus = inGame, playerAction = game_content)
        if(!isInputOnce) {
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Please choose a block and enter a letter!");
            alert.showAndWait();
        }
        else {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Confirmation");
            alert.setHeaderText("Choose a direction that word to be assessed");
            alert.setContentText("Choose your option.");

            ButtonType buttonTypeOne = new ButtonType("Horizontal");
            ButtonType buttonTypeTwo = new ButtonType("Vertical");
            ButtonType buttonTypeCancel = new ButtonType("Let me think", ButtonBar.ButtonData.CANCEL_CLOSE);

            alert.getButtonTypes().setAll(buttonTypeOne, buttonTypeTwo, buttonTypeCancel);

            Optional<ButtonType> result = alert.showAndWait();
            if (result.get() == buttonTypeOne) {
                System.out.println(getHorizontalWord());

            } else if (result.get() == buttonTypeTwo) {
                System.out.println(getVerticalWord());
            }
        }
    }

    @FXML
    private void pass(){
        //TODO - send to server (playerStatus = inGame, playerAction = pass)
    }

    @FXML
    private void help(){
        //TODO - UI - <help>
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
        String input = e.getText();
        SCell cell = ((SCell) e.getSource());
        // set cell text from input, reset cell if input is 0
        if (!cell.status.equals(CStatus.LOCK)) {
            if (!cell.getText().isEmpty()){
                if (input.equals("0")){
                    cell.setText("");
                    cell.setInitStatus();
                    isInputOnce=false;
                    boardData[inputPos[0]-1][inputPos[1]-1]="";
                    inputPos[1]=0;
                    inputPos[0]=0;
                    if(selectWords.contains(cell)) {
                        selectWords.remove(cell);
                    }
                }
                return;
            }
            if (!isInputOnce && !input.isEmpty() && Pattern.matches("[a-z]",input)) {
                cell.setInitStatus();
                cell.setText(input);
                isInputOnce=true;
                inputPos[1]=playerBoard.getColumnIndex((SCell) e.getSource());
                inputPos[0]=playerBoard.getRowIndex((SCell) e.getSource());
                boardData[inputPos[0]-1][inputPos[1]-1]=input;
            }
        }

    }

    // click mouse to select words
    @FXML private void mouseListener(MouseEvent e) {
        SCell cell = (SCell) e.getSource();
        if(!cell.getText().isEmpty()) {
            highLight(cell);
        }
    }

    private void highLight(SCell cell) {
        // if the cell is highlighted, unhighlight it.
        if (cell.isHighLight) {
            cell.setInitStatus();
            if(selectWords.contains(cell)) {
                selectWords.remove(cell);
            }

            // if the cell is not highlighted, highlight it.
        } else {
            cell.setHighLightStatus();
            selectWords.add(cell);
        }
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
