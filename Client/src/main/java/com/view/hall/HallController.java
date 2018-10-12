package com.view.hall;

import com.Game;
import com.model.player.Player;
import com.view.table.TableController;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Cursor;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import javafx.stage.WindowEvent;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class HallController implements Initializable {

    //<editor-fold defaultstate="collapsed" desc="// initialize for Game Hall (Do not open table labels and button)" >
    @FXML private BorderPane borderPane;
    @FXML private TableView<Player> playerList;
    @FXML private TableColumn username;
    @FXML private TableColumn status;
    @FXML private void handleButton1() throws IOException {enterTable(1);}
    @FXML private void handleButton2() throws IOException {enterTable(2);}
    @FXML private void handleButton3() throws IOException {enterTable(3);}
    @FXML private void handleButton4() throws IOException {enterTable(4);}
    @FXML private void handleButton5() throws IOException {enterTable(5);}
    @FXML private void handleButton6() throws IOException {enterTable(6);}
    @FXML private void handleButton7() throws IOException {enterTable(7);}
    @FXML private void handleButton8() throws IOException {enterTable(8);}
    @FXML private void handleButton9() throws IOException {enterTable(9);}
    @FXML private void handleButton10() throws IOException {enterTable(10);}
    @FXML private void handleButton11() throws IOException {enterTable(11);}
    @FXML private void handleButton12() throws IOException {enterTable(12);}
    @FXML public Label tableNum1;
    @FXML public Label tableNum2;
    @FXML public Label tableNum3;
    @FXML public Label tableNum4;
    @FXML public Label tableNum5;
    @FXML public Label tableNum6;
    @FXML public Label tableNum7;
    @FXML public Label tableNum8;
    @FXML public Label tableNum9;
    @FXML public Label tableNum10;
    @FXML public Label tableNum11;
    @FXML public Label tableNum12;
    //</editor-fold>

    public void refreshTableNum(int tableName, int playerInTable){
        String name = "tableNum"+tableName;
        String players = Integer.toString(playerInTable);
        // <editor-fold defaultstate = "collapsed" desc = "Refresh players in Table" >
        Platform.runLater(()->{
            if (name.equals(tableNum1.getId())){
                tableNum1.setText(players);
            }else if (name.equals(tableNum2.getId())){
                tableNum2.setText(players);
            }else if (name.equals(tableNum3.getId())){
                tableNum3.setText(players);
            }else if (name.equals(tableNum4.getId())){
                tableNum4.setText(players);
            }else if (name.equals(tableNum5.getId())){
                tableNum5.setText(players);
            }else if (name.equals(tableNum6.getId())){
                tableNum6.setText(players);
            }else if (name.equals(tableNum7.getId())){
                tableNum7.setText(players);
            }else if (name.equals(tableNum8.getId())){
                tableNum8.setText(players);
            }else if (name.equals(tableNum9.getId())){
                tableNum9.setText(players);
            }else if (name.equals(tableNum10.getId())){
                tableNum10.setText(players);
            }else if (name.equals(tableNum11.getId())){
                tableNum11.setText(players);
            }else if (name.equals(tableNum12.getId())){
                tableNum12.setText(players);
            }
            // </editor-fold>
        });
    }

    public static final int HallWidth = 1100;
    public static final int HallHeight = 800;
    public static ObservableList<Player> data = FXCollections.observableArrayList();
    public static TableController tableController;
    private double xOffset;
    private double yOffset;
    public Scene scene;
    private static Stage stage;
    public static String tableNumber;
    public static String currentPlayer;
    private static HallController instance;

    public HallController() {
        instance = this;
    }

    public static HallController getInstance() {
        return instance;
    }

    public static Stage getStage() {
        return stage;
    }

    public void clearTable(){
        data.clear();
    }

    public void updateStatus(Player player) {
        data.add(player);
    }

    public void refreshTable(){
        playerList.setItems(data);
    }

    public void getCurrentPlayer(String name){
        currentPlayer = name;
    }

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        //Drag and Drop animation
        //<editor-fold defaultstate="collapsed" desc=" Drag and Drop">
        borderPane.setOnMousePressed(event -> {
            xOffset = Game.getPrimaryStage().getX() - event.getScreenX();
            yOffset = Game.getPrimaryStage().getY() - event.getScreenY();
            borderPane.setCursor(javafx.scene.Cursor.CLOSED_HAND);
        });

        borderPane.setOnMouseDragged(event -> {
            Game.getPrimaryStage().setX(event.getScreenX() + xOffset);
            Game.getPrimaryStage().setY(event.getScreenY() + yOffset);

        });

        borderPane.setOnMouseReleased(event -> {
            borderPane.setCursor(Cursor.DEFAULT);
        });
        //</editor-fold>

        username.setCellValueFactory(new PropertyValueFactory<>("username"));
        status.setCellValueFactory(new PropertyValueFactory<>("status"));
    }

    // Terminates Application
    public void closeSystem(){
        Platform.exit();
        System.exit(0);
    }

    // Minimize Window
    public void minimizeWindow(){
        Game.getPrimaryStage().setIconified(true);
    }

    public void enterTable(int tableName) throws IOException{
        tableNumber = "Table "+String.valueOf(tableName)+" "+currentPlayer;

        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getClassLoader().getResource("fxml/table.fxml"));
        Parent window = (Pane) fxmlLoader.load();
        tableController = fxmlLoader.getController();
        tableController.title.setText(tableNumber);

        Game.entryTable(tableName);
        this.scene = new Scene(window);
    }

    public void showTable(){
        Platform.runLater(() -> {
            stage = new Stage();
            stage.setResizable(false);
            stage.setWidth(TableController.TableWidth);
            stage.setHeight(TableController.TableHeight);
            stage.initStyle(StageStyle.UNDECORATED);
            stage.setOnCloseRequest((WindowEvent e) -> Game.getPrimaryStage().show());
            stage.setScene(this.scene);
            stage.centerOnScreen();
            stage.show();
            // Show ready stage
            try {
                Game.getPrimaryStage().hide();
                TableController.getInstance().showReadyStage();
            } catch (IOException e) {
                e.printStackTrace();
            }
        });
    }

    public void joinTableFailure(){
        Platform.runLater(()->{
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Enter table failure");
            alert.setContentText("Please change a table.");
            alert.showAndWait();
        });
    }

    @FXML
    public void help(){
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("HELP");
        alert.setHeaderText("Click any of table to join the game");
        alert.setContentText("Please click a table on the left to begin playing.");
        alert.showAndWait();
    }
}
