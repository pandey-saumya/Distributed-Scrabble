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
import javafx.scene.layout.Pane;
import javafx.scene.paint.Color;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;
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
//    @FXML private TextField T0;
//    @FXML private TextField T1;
//    @FXML private TextField T2;
//    @FXML private TextField T3;
//    @FXML private TextField T4;
//    @FXML private TextField T5;
//    @FXML private TextField T6;
//    @FXML private TextField T7;
//    @FXML private TextField T8;
//    @FXML private TextField T9;
//    @FXML private TextField T10;
//    @FXML private TextField T11;
//    @FXML private TextField T12;
//    @FXML private TextField T13;
//    @FXML private TextField T14;
//    @FXML private TextField T15;
//    @FXML private TextField T16;
//    @FXML private TextField T17;
//    @FXML private TextField T18;
//    @FXML private TextField T19;
//    @FXML private TextField T20;
//    @FXML private TextField T21;
//    @FXML private TextField T22;
//    @FXML private TextField T23;
//    @FXML private TextField T24;
//    @FXML private TextField T25;
//    @FXML private TextField T26;
//    @FXML private TextField T27;
//    @FXML private TextField T28;
//    @FXML private TextField T29;
//    @FXML private TextField T30;
//    @FXML private TextField T31;
//    @FXML private TextField T32;
//    @FXML private TextField T33;
//    @FXML private TextField T34;
//    @FXML private TextField T35;
//    @FXML private TextField T36;
//    @FXML private TextField T37;
//    @FXML private TextField T38;
//    @FXML private TextField T39;
//    @FXML private TextField T40;
//    @FXML private TextField T41;
//    @FXML private TextField T42;
//    @FXML private TextField T43;
//    @FXML private TextField T44;
//    @FXML private TextField T45;
//    @FXML private TextField T46;
//    @FXML private TextField T47;
//    @FXML private TextField T48;
//    @FXML private TextField T49;
//    @FXML private TextField T50;
//    @FXML private TextField T51;
//    @FXML private TextField T52;
//    @FXML private TextField T53;
//    @FXML private TextField T54;
//    @FXML private TextField T55;
//    @FXML private TextField T56;
//    @FXML private TextField T57;
//    @FXML private TextField T58;
//    @FXML private TextField T59;
//    @FXML private TextField T60;
//    @FXML private TextField T61;
//    @FXML private TextField T62;
//    @FXML private TextField T63;
//    @FXML private TextField T64;
//    @FXML private TextField T65;
//    @FXML private TextField T66;
//    @FXML private TextField T67;
//    @FXML private TextField T68;
//    @FXML private TextField T69;
//    @FXML private TextField T70;
//    @FXML private TextField T71;
//    @FXML private TextField T72;
//    @FXML private TextField T73;
//    @FXML private TextField T74;
//    @FXML private TextField T75;
//    @FXML private TextField T76;
//    @FXML private TextField T77;
//    @FXML private TextField T78;
//    @FXML private TextField T79;
//    @FXML private TextField T80;
//    @FXML private TextField T81;
//    @FXML private TextField T82;
//    @FXML private TextField T83;
//    @FXML private TextField T84;
//    @FXML private TextField T85;
//    @FXML private TextField T86;
//    @FXML private TextField T87;
//    @FXML private TextField T88;
//    @FXML private TextField T89;
//    @FXML private TextField T90;
//    @FXML private TextField T91;
//    @FXML private TextField T92;
//    @FXML private TextField T93;
//    @FXML private TextField T94;
//    @FXML private TextField T95;
//    @FXML private TextField T96;
//    @FXML private TextField T97;
//    @FXML private TextField T98;
//    @FXML private TextField T99;
//    @FXML private TextField T100;
//    @FXML private TextField T101;
//    @FXML private TextField T102;
//    @FXML private TextField T103;
//    @FXML private TextField T104;
//    @FXML private TextField T105;
//    @FXML private TextField T106;
//    @FXML private TextField T107;
//    @FXML private TextField T108;
//    @FXML private TextField T109;
//    @FXML private TextField T110;
//    @FXML private TextField T111;
//    @FXML private TextField T112;
//    @FXML private TextField T113;
//    @FXML private TextField T114;
//    @FXML private TextField T115;
//    @FXML private TextField T116;
//    @FXML private TextField T117;
//    @FXML private TextField T118;
//    @FXML private TextField T119;
//    @FXML private TextField T120;
//    @FXML private TextField T121;
//    @FXML private TextField T122;
//    @FXML private TextField T123;
//    @FXML private TextField T124;
//    @FXML private TextField T125;
//    @FXML private TextField T126;
//    @FXML private TextField T127;
//    @FXML private TextField T128;
//    @FXML private TextField T129;
//    @FXML private TextField T130;
//    @FXML private TextField T131;
//    @FXML private TextField T132;
//    @FXML private TextField T133;
//    @FXML private TextField T134;
//    @FXML private TextField T135;
//    @FXML private TextField T136;
//    @FXML private TextField T137;
//    @FXML private TextField T138;
//    @FXML private TextField T139;
//    @FXML private TextField T140;
//    @FXML private TextField T141;
//    @FXML private TextField T142;
//    @FXML private TextField T143;
//    @FXML private TextField T144;
//    @FXML private TextField T145;
//    @FXML private TextField T146;
//    @FXML private TextField T147;
//    @FXML private TextField T148;
//    @FXML private TextField T149;
//    @FXML private TextField T150;
//    @FXML private TextField T151;
//    @FXML private TextField T152;
//    @FXML private TextField T153;
//    @FXML private TextField T154;
//    @FXML private TextField T155;
//    @FXML private TextField T156;
//    @FXML private TextField T157;
//    @FXML private TextField T158;
//    @FXML private TextField T159;
//    @FXML private TextField T160;
//    @FXML private TextField T161;
//    @FXML private TextField T162;
//    @FXML private TextField T163;
//    @FXML private TextField T164;
//    @FXML private TextField T165;
//    @FXML private TextField T166;
//    @FXML private TextField T167;
//    @FXML private TextField T168;
//    @FXML private TextField T169;
//    @FXML private TextField T170;
//    @FXML private TextField T171;
//    @FXML private TextField T172;
//    @FXML private TextField T173;
//    @FXML private TextField T174;
//    @FXML private TextField T175;
//    @FXML private TextField T176;
//    @FXML private TextField T177;
//    @FXML private TextField T178;
//    @FXML private TextField T179;
//    @FXML private TextField T180;
//    @FXML private TextField T181;
//    @FXML private TextField T182;
//    @FXML private TextField T183;
//    @FXML private TextField T184;
//    @FXML private TextField T185;
//    @FXML private TextField T186;
//    @FXML private TextField T187;
//    @FXML private TextField T188;
//    @FXML private TextField T189;
//    @FXML private TextField T190;
//    @FXML private TextField T191;
//    @FXML private TextField T192;
//    @FXML private TextField T193;
//    @FXML private TextField T194;
//    @FXML private TextField T195;
//    @FXML private TextField T196;
//    @FXML private TextField T197;
//    @FXML private TextField T198;
//    @FXML private TextField T199;
//    @FXML private TextField T200;
//    @FXML private TextField T201;
//    @FXML private TextField T202;
//    @FXML private TextField T203;
//    @FXML private TextField T204;
//    @FXML private TextField T205;
//    @FXML private TextField T206;
//    @FXML private TextField T207;
//    @FXML private TextField T208;
//    @FXML private TextField T209;
//    @FXML private TextField T210;
//    @FXML private TextField T211;
//    @FXML private TextField T212;
//    @FXML private TextField T213;
//    @FXML private TextField T214;
//    @FXML private TextField T215;
//    @FXML private TextField T216;
//    @FXML private TextField T217;
//    @FXML private TextField T218;
//    @FXML private TextField T219;
//    @FXML private TextField T220;
//    @FXML private TextField T221;
//    @FXML private TextField T222;
//    @FXML private TextField T223;
//    @FXML private TextField T224;
//    @FXML private TextField T225;
//    @FXML private TextField T226;
//    @FXML private TextField T227;
//    @FXML private TextField T228;
//    @FXML private TextField T229;
//    @FXML private TextField T230;
//    @FXML private TextField T231;
//    @FXML private TextField T232;
//    @FXML private TextField T233;
//    @FXML private TextField T234;
//    @FXML private TextField T235;
//    @FXML private TextField T236;
//    @FXML private TextField T237;
//    @FXML private TextField T238;
//    @FXML private TextField T239;
//    @FXML private TextField T240;
//    @FXML private TextField T241;
//    @FXML private TextField T242;
//    @FXML private TextField T243;
//    @FXML private TextField T244;
//    @FXML private TextField T245;
//    @FXML private TextField T246;
//    @FXML private TextField T247;
//    @FXML private TextField T248;
//    @FXML private TextField T249;
//    @FXML private TextField T250;
//    @FXML private TextField T251;
//    @FXML private TextField T252;
//    @FXML private TextField T253;
//    @FXML private TextField T254;
//    @FXML private TextField T255;
//    @FXML private TextField T256;
//    @FXML private TextField T257;
//    @FXML private TextField T258;
//    @FXML private TextField T259;
//    @FXML private TextField T260;
//    @FXML private TextField T261;
//    @FXML private TextField T262;
//    @FXML private TextField T263;
//    @FXML private TextField T264;
//    @FXML private TextField T265;
//    @FXML private TextField T266;
//    @FXML private TextField T267;
//    @FXML private TextField T268;
//    @FXML private TextField T269;
//    @FXML private TextField T270;
//    @FXML private TextField T271;
//    @FXML private TextField T272;
//    @FXML private TextField T273;
//    @FXML private TextField T274;
//    @FXML private TextField T275;
//    @FXML private TextField T276;
//    @FXML private TextField T277;
//    @FXML private TextField T278;
//    @FXML private TextField T279;
//    @FXML private TextField T280;
//    @FXML private TextField T281;
//    @FXML private TextField T282;
//    @FXML private TextField T283;
//    @FXML private TextField T284;
//    @FXML private TextField T285;
//    @FXML private TextField T286;
//    @FXML private TextField T287;
//    @FXML private TextField T288;
//    @FXML private TextField T289;
//    @FXML private TextField T290;
//    @FXML private TextField T291;
//    @FXML private TextField T292;
//    @FXML private TextField T293;
//    @FXML private TextField T294;
//    @FXML private TextField T295;
//    @FXML private TextField T296;
//    @FXML private TextField T297;
//    @FXML private TextField T298;
//    @FXML private TextField T299;
//    @FXML private TextField T300;
//    @FXML private TextField T301;
//    @FXML private TextField T302;
//    @FXML private TextField T303;
//    @FXML private TextField T304;
//    @FXML private TextField T305;
//    @FXML private TextField T306;
//    @FXML private TextField T307;
//    @FXML private TextField T308;
//    @FXML private TextField T309;
//    @FXML private TextField T310;
//    @FXML private TextField T311;
//    @FXML private TextField T312;
//    @FXML private TextField T313;
//    @FXML private TextField T314;
//    @FXML private TextField T315;
//    @FXML private TextField T316;
//    @FXML private TextField T317;
//    @FXML private TextField T318;
//    @FXML private TextField T319;
//    @FXML private TextField T320;
//    @FXML private TextField T321;
//    @FXML private TextField T322;
//    @FXML private TextField T323;
//    @FXML private TextField T324;
//    @FXML private TextField T325;
//    @FXML private TextField T326;
//    @FXML private TextField T327;
//    @FXML private TextField T328;
//    @FXML private TextField T329;
//    @FXML private TextField T330;
//    @FXML private TextField T331;
//    @FXML private TextField T332;
//    @FXML private TextField T333;
//    @FXML private TextField T334;
//    @FXML private TextField T335;
//    @FXML private TextField T336;
//    @FXML private TextField T337;
//    @FXML private TextField T338;
//    @FXML private TextField T339;
//    @FXML private TextField T340;
//    @FXML private TextField T341;
//    @FXML private TextField T342;
//    @FXML private TextField T343;
//    @FXML private TextField T344;
//    @FXML private TextField T345;
//    @FXML private TextField T346;
//    @FXML private TextField T347;
//    @FXML private TextField T348;
//    @FXML private TextField T349;
//    @FXML private TextField T350;
//    @FXML private TextField T351;
//    @FXML private TextField T352;
//    @FXML private TextField T353;
//    @FXML private TextField T354;
//    @FXML private TextField T355;
//    @FXML private TextField T356;
//    @FXML private TextField T357;
//    @FXML private TextField T358;
//    @FXML private TextField T359;
//    @FXML private TextField T360;
//    @FXML private TextField T361;
//    @FXML private TextField T362;
//    @FXML private TextField T363;
//    @FXML private TextField T364;
//    @FXML private TextField T365;
//    @FXML private TextField T366;
//    @FXML private TextField T367;
//    @FXML private TextField T368;
//    @FXML private TextField T369;
//    @FXML private TextField T370;
//    @FXML private TextField T371;
//    @FXML private TextField T372;
//    @FXML private TextField T373;
//    @FXML private TextField T374;
//    @FXML private TextField T375;
//    @FXML private TextField T376;
//    @FXML private TextField T377;
//    @FXML private TextField T378;
//    @FXML private TextField T379;
//    @FXML private TextField T380;
//    @FXML private TextField T381;
//    @FXML private TextField T382;
//    @FXML private TextField T383;
//    @FXML private TextField T384;
//    @FXML private TextField T385;
//    @FXML private TextField T386;
//    @FXML private TextField T387;
//    @FXML private TextField T388;
//    @FXML private TextField T389;
//    @FXML private TextField T390;
//    @FXML private TextField T391;
//    @FXML private TextField T392;
//    @FXML private TextField T393;
//    @FXML private TextField T394;
//    @FXML private TextField T395;
//    @FXML private TextField T396;
//    @FXML private TextField T397;
//    @FXML private TextField T398;
//    @FXML private TextField T399;
    //</editor-fold>
    private static TableController instance;
    private static Stage readyStage;
    public static ReadyController readyController;

    @FXML private List<SCell> cells;

    private List<SCell> selectWords=new ArrayList<>();


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
        if (e.getCode() == KeyCode.ENTER || e.getCode() == KeyCode.SPACE) {
            if (cell.status.equals(CStatus.INIT) && !cell.getText().isEmpty()) {
                highLight(cell);
                cell.setText("X");
            }
        } else {
            setInputText(cell,input);
        }
    }

    // click mouse to select words
    @FXML private void mouseListener(MouseEvent e) {
        SCell cell = (SCell) e.getSource();
        if(!cell.getText().isEmpty()) {
            highLight(cell);
        }
    }

    // set cell text from input, reset cell if input is 0
    private void setInputText(SCell cell, String input) {
        if (!cell.getText().isEmpty()){
            if (input.equals("0")){
                cell.setText("");
                cell.setInitStatus();
                if(selectWords.contains(cell)) {
                    selectWords.remove(cell);
                }
            }
            return;
        }
        if (!input.isEmpty() && Pattern.matches("[a-z]",input)) {
            cell.setInitStatus();
            cell.setText(input);
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



}
