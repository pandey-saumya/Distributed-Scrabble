package com.view.table;

import com.Game;
import com.view.hall.HallController;
import javafx.application.Platform;
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
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.Pane;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;


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
    @FXML private TextField T0;
    @FXML private TextField T1;
    @FXML private TextField T2;
    @FXML private TextField T3;
    @FXML private TextField T4;
    @FXML private TextField T5;
    @FXML private TextField T6;
    @FXML private TextField T7;
    @FXML private TextField T8;
    @FXML private TextField T9;
    @FXML private TextField T10;
    @FXML private TextField T11;
    @FXML private TextField T12;
    @FXML private TextField T13;
    @FXML private TextField T14;
    @FXML private TextField T15;
    @FXML private TextField T16;
    @FXML private TextField T17;
    @FXML private TextField T18;
    @FXML private TextField T19;
    @FXML private TextField T20;
    @FXML private TextField T21;
    @FXML private TextField T22;
    @FXML private TextField T23;
    @FXML private TextField T24;
    @FXML private TextField T25;
    @FXML private TextField T26;
    @FXML private TextField T27;
    @FXML private TextField T28;
    @FXML private TextField T29;
    @FXML private TextField T30;
    @FXML private TextField T31;
    @FXML private TextField T32;
    @FXML private TextField T33;
    @FXML private TextField T34;
    @FXML private TextField T35;
    @FXML private TextField T36;
    @FXML private TextField T37;
    @FXML private TextField T38;
    @FXML private TextField T39;
    @FXML private TextField T40;
    @FXML private TextField T41;
    @FXML private TextField T42;
    @FXML private TextField T43;
    @FXML private TextField T44;
    @FXML private TextField T45;
    @FXML private TextField T46;
    @FXML private TextField T47;
    @FXML private TextField T48;
    @FXML private TextField T49;
    @FXML private TextField T50;
    @FXML private TextField T51;
    @FXML private TextField T52;
    @FXML private TextField T53;
    @FXML private TextField T54;
    @FXML private TextField T55;
    @FXML private TextField T56;
    @FXML private TextField T57;
    @FXML private TextField T58;
    @FXML private TextField T59;
    @FXML private TextField T60;
    @FXML private TextField T61;
    @FXML private TextField T62;
    @FXML private TextField T63;
    @FXML private TextField T64;
    @FXML private TextField T65;
    @FXML private TextField T66;
    @FXML private TextField T67;
    @FXML private TextField T68;
    @FXML private TextField T69;
    @FXML private TextField T70;
    @FXML private TextField T71;
    @FXML private TextField T72;
    @FXML private TextField T73;
    @FXML private TextField T74;
    @FXML private TextField T75;
    @FXML private TextField T76;
    @FXML private TextField T77;
    @FXML private TextField T78;
    @FXML private TextField T79;
    @FXML private TextField T80;
    @FXML private TextField T81;
    @FXML private TextField T82;
    @FXML private TextField T83;
    @FXML private TextField T84;
    @FXML private TextField T85;
    @FXML private TextField T86;
    @FXML private TextField T87;
    @FXML private TextField T88;
    @FXML private TextField T89;
    @FXML private TextField T90;
    @FXML private TextField T91;
    @FXML private TextField T92;
    @FXML private TextField T93;
    @FXML private TextField T94;
    @FXML private TextField T95;
    @FXML private TextField T96;
    @FXML private TextField T97;
    @FXML private TextField T98;
    @FXML private TextField T99;
    @FXML private TextField T100;
    @FXML private TextField T101;
    @FXML private TextField T102;
    @FXML private TextField T103;
    @FXML private TextField T104;
    @FXML private TextField T105;
    @FXML private TextField T106;
    @FXML private TextField T107;
    @FXML private TextField T108;
    @FXML private TextField T109;
    @FXML private TextField T110;
    @FXML private TextField T111;
    @FXML private TextField T112;
    @FXML private TextField T113;
    @FXML private TextField T114;
    @FXML private TextField T115;
    @FXML private TextField T116;
    @FXML private TextField T117;
    @FXML private TextField T118;
    @FXML private TextField T119;
    @FXML private TextField T120;
    @FXML private TextField T121;
    @FXML private TextField T122;
    @FXML private TextField T123;
    @FXML private TextField T124;
    @FXML private TextField T125;
    @FXML private TextField T126;
    @FXML private TextField T127;
    @FXML private TextField T128;
    @FXML private TextField T129;
    @FXML private TextField T130;
    @FXML private TextField T131;
    @FXML private TextField T132;
    @FXML private TextField T133;
    @FXML private TextField T134;
    @FXML private TextField T135;
    @FXML private TextField T136;
    @FXML private TextField T137;
    @FXML private TextField T138;
    @FXML private TextField T139;
    @FXML private TextField T140;
    @FXML private TextField T141;
    @FXML private TextField T142;
    @FXML private TextField T143;
    @FXML private TextField T144;
    @FXML private TextField T145;
    @FXML private TextField T146;
    @FXML private TextField T147;
    @FXML private TextField T148;
    @FXML private TextField T149;
    @FXML private TextField T150;
    @FXML private TextField T151;
    @FXML private TextField T152;
    @FXML private TextField T153;
    @FXML private TextField T154;
    @FXML private TextField T155;
    @FXML private TextField T156;
    @FXML private TextField T157;
    @FXML private TextField T158;
    @FXML private TextField T159;
    @FXML private TextField T160;
    @FXML private TextField T161;
    @FXML private TextField T162;
    @FXML private TextField T163;
    @FXML private TextField T164;
    @FXML private TextField T165;
    @FXML private TextField T166;
    @FXML private TextField T167;
    @FXML private TextField T168;
    @FXML private TextField T169;
    @FXML private TextField T170;
    @FXML private TextField T171;
    @FXML private TextField T172;
    @FXML private TextField T173;
    @FXML private TextField T174;
    @FXML private TextField T175;
    @FXML private TextField T176;
    @FXML private TextField T177;
    @FXML private TextField T178;
    @FXML private TextField T179;
    @FXML private TextField T180;
    @FXML private TextField T181;
    @FXML private TextField T182;
    @FXML private TextField T183;
    @FXML private TextField T184;
    @FXML private TextField T185;
    @FXML private TextField T186;
    @FXML private TextField T187;
    @FXML private TextField T188;
    @FXML private TextField T189;
    @FXML private TextField T190;
    @FXML private TextField T191;
    @FXML private TextField T192;
    @FXML private TextField T193;
    @FXML private TextField T194;
    @FXML private TextField T195;
    @FXML private TextField T196;
    @FXML private TextField T197;
    @FXML private TextField T198;
    @FXML private TextField T199;
    @FXML private TextField T200;
    @FXML private TextField T201;
    @FXML private TextField T202;
    @FXML private TextField T203;
    @FXML private TextField T204;
    @FXML private TextField T205;
    @FXML private TextField T206;
    @FXML private TextField T207;
    @FXML private TextField T208;
    @FXML private TextField T209;
    @FXML private TextField T210;
    @FXML private TextField T211;
    @FXML private TextField T212;
    @FXML private TextField T213;
    @FXML private TextField T214;
    @FXML private TextField T215;
    @FXML private TextField T216;
    @FXML private TextField T217;
    @FXML private TextField T218;
    @FXML private TextField T219;
    @FXML private TextField T220;
    @FXML private TextField T221;
    @FXML private TextField T222;
    @FXML private TextField T223;
    @FXML private TextField T224;
    @FXML private TextField T225;
    @FXML private TextField T226;
    @FXML private TextField T227;
    @FXML private TextField T228;
    @FXML private TextField T229;
    @FXML private TextField T230;
    @FXML private TextField T231;
    @FXML private TextField T232;
    @FXML private TextField T233;
    @FXML private TextField T234;
    @FXML private TextField T235;
    @FXML private TextField T236;
    @FXML private TextField T237;
    @FXML private TextField T238;
    @FXML private TextField T239;
    @FXML private TextField T240;
    @FXML private TextField T241;
    @FXML private TextField T242;
    @FXML private TextField T243;
    @FXML private TextField T244;
    @FXML private TextField T245;
    @FXML private TextField T246;
    @FXML private TextField T247;
    @FXML private TextField T248;
    @FXML private TextField T249;
    @FXML private TextField T250;
    @FXML private TextField T251;
    @FXML private TextField T252;
    @FXML private TextField T253;
    @FXML private TextField T254;
    @FXML private TextField T255;
    @FXML private TextField T256;
    @FXML private TextField T257;
    @FXML private TextField T258;
    @FXML private TextField T259;
    @FXML private TextField T260;
    @FXML private TextField T261;
    @FXML private TextField T262;
    @FXML private TextField T263;
    @FXML private TextField T264;
    @FXML private TextField T265;
    @FXML private TextField T266;
    @FXML private TextField T267;
    @FXML private TextField T268;
    @FXML private TextField T269;
    @FXML private TextField T270;
    @FXML private TextField T271;
    @FXML private TextField T272;
    @FXML private TextField T273;
    @FXML private TextField T274;
    @FXML private TextField T275;
    @FXML private TextField T276;
    @FXML private TextField T277;
    @FXML private TextField T278;
    @FXML private TextField T279;
    @FXML private TextField T280;
    @FXML private TextField T281;
    @FXML private TextField T282;
    @FXML private TextField T283;
    @FXML private TextField T284;
    @FXML private TextField T285;
    @FXML private TextField T286;
    @FXML private TextField T287;
    @FXML private TextField T288;
    @FXML private TextField T289;
    @FXML private TextField T290;
    @FXML private TextField T291;
    @FXML private TextField T292;
    @FXML private TextField T293;
    @FXML private TextField T294;
    @FXML private TextField T295;
    @FXML private TextField T296;
    @FXML private TextField T297;
    @FXML private TextField T298;
    @FXML private TextField T299;
    @FXML private TextField T300;
    @FXML private TextField T301;
    @FXML private TextField T302;
    @FXML private TextField T303;
    @FXML private TextField T304;
    @FXML private TextField T305;
    @FXML private TextField T306;
    @FXML private TextField T307;
    @FXML private TextField T308;
    @FXML private TextField T309;
    @FXML private TextField T310;
    @FXML private TextField T311;
    @FXML private TextField T312;
    @FXML private TextField T313;
    @FXML private TextField T314;
    @FXML private TextField T315;
    @FXML private TextField T316;
    @FXML private TextField T317;
    @FXML private TextField T318;
    @FXML private TextField T319;
    @FXML private TextField T320;
    @FXML private TextField T321;
    @FXML private TextField T322;
    @FXML private TextField T323;
    @FXML private TextField T324;
    @FXML private TextField T325;
    @FXML private TextField T326;
    @FXML private TextField T327;
    @FXML private TextField T328;
    @FXML private TextField T329;
    @FXML private TextField T330;
    @FXML private TextField T331;
    @FXML private TextField T332;
    @FXML private TextField T333;
    @FXML private TextField T334;
    @FXML private TextField T335;
    @FXML private TextField T336;
    @FXML private TextField T337;
    @FXML private TextField T338;
    @FXML private TextField T339;
    @FXML private TextField T340;
    @FXML private TextField T341;
    @FXML private TextField T342;
    @FXML private TextField T343;
    @FXML private TextField T344;
    @FXML private TextField T345;
    @FXML private TextField T346;
    @FXML private TextField T347;
    @FXML private TextField T348;
    @FXML private TextField T349;
    @FXML private TextField T350;
    @FXML private TextField T351;
    @FXML private TextField T352;
    @FXML private TextField T353;
    @FXML private TextField T354;
    @FXML private TextField T355;
    @FXML private TextField T356;
    @FXML private TextField T357;
    @FXML private TextField T358;
    @FXML private TextField T359;
    @FXML private TextField T360;
    @FXML private TextField T361;
    @FXML private TextField T362;
    @FXML private TextField T363;
    @FXML private TextField T364;
    @FXML private TextField T365;
    @FXML private TextField T366;
    @FXML private TextField T367;
    @FXML private TextField T368;
    @FXML private TextField T369;
    @FXML private TextField T370;
    @FXML private TextField T371;
    @FXML private TextField T372;
    @FXML private TextField T373;
    @FXML private TextField T374;
    @FXML private TextField T375;
    @FXML private TextField T376;
    @FXML private TextField T377;
    @FXML private TextField T378;
    @FXML private TextField T379;
    @FXML private TextField T380;
    @FXML private TextField T381;
    @FXML private TextField T382;
    @FXML private TextField T383;
    @FXML private TextField T384;
    @FXML private TextField T385;
    @FXML private TextField T386;
    @FXML private TextField T387;
    @FXML private TextField T388;
    @FXML private TextField T389;
    @FXML private TextField T390;
    @FXML private TextField T391;
    @FXML private TextField T392;
    @FXML private TextField T393;
    @FXML private TextField T394;
    @FXML private TextField T395;
    @FXML private TextField T396;
    @FXML private TextField T397;
    @FXML private TextField T398;
    @FXML private TextField T399;
    //</editor-fold>
    private static TableController instance;
    private static Stage readyStage;
    private static Stage timerStage;
    public static ReadyController readyController;
    public static TimerController timerController;
    private int index;
    private static Boolean myTurn;

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

        for (int i = 0; i<400; i ++){
            board[i]="";
        }
        this.setBoard(board);
    }
//do not open text fields for UI
    public void setEditable(){
        if (!T0.getText().isEmpty()) { T0.setEditable(false);T0.getStyleClass().add("NotEditable");}
        if (!T1.getText().isEmpty()) { T1.setEditable(false);T1.getStyleClass().add("NotEditable");}
        if (!T2.getText().isEmpty()) { T2.setEditable(false);T2.getStyleClass().add("NotEditable");}
        if (!T3.getText().isEmpty()) { T3.setEditable(false);T3.getStyleClass().add("NotEditable");}
        if (!T4.getText().isEmpty()) { T4.setEditable(false);T4.getStyleClass().add("NotEditable");}
        if (!T5.getText().isEmpty()) { T5.setEditable(false);T5.getStyleClass().add("NotEditable");}
        if (!T6.getText().isEmpty()) { T6.setEditable(false);T6.getStyleClass().add("NotEditable");}
        if (!T7.getText().isEmpty()) { T7.setEditable(false);T7.getStyleClass().add("NotEditable");}
        if (!T8.getText().isEmpty()) { T8.setEditable(false);T8.getStyleClass().add("NotEditable");}
        if (!T9.getText().isEmpty()) { T9.setEditable(false);T9.getStyleClass().add("NotEditable");}
        if (!T10.getText().isEmpty()) { T10.setEditable(false);T10.getStyleClass().add("NotEditable");}
        if (!T11.getText().isEmpty()) { T11.setEditable(false);T11.getStyleClass().add("NotEditable");}
        if (!T12.getText().isEmpty()) { T12.setEditable(false);T12.getStyleClass().add("NotEditable");}
        if (!T13.getText().isEmpty()) { T13.setEditable(false);T13.getStyleClass().add("NotEditable");}
        if (!T14.getText().isEmpty()) { T14.setEditable(false);T14.getStyleClass().add("NotEditable");}
        if (!T15.getText().isEmpty()) { T15.setEditable(false);T15.getStyleClass().add("NotEditable");}
        if (!T16.getText().isEmpty()) { T16.setEditable(false);T16.getStyleClass().add("NotEditable");}
        if (!T17.getText().isEmpty()) { T17.setEditable(false);T17.getStyleClass().add("NotEditable");}
        if (!T18.getText().isEmpty()) { T18.setEditable(false);T18.getStyleClass().add("NotEditable");}
        if (!T19.getText().isEmpty()) { T19.setEditable(false);T19.getStyleClass().add("NotEditable");}
        if (!T20.getText().isEmpty()) { T20.setEditable(false);T20.getStyleClass().add("NotEditable");}
        if (!T21.getText().isEmpty()) { T21.setEditable(false);T21.getStyleClass().add("NotEditable");}
        if (!T22.getText().isEmpty()) { T22.setEditable(false);T22.getStyleClass().add("NotEditable");}
        if (!T23.getText().isEmpty()) { T23.setEditable(false);T23.getStyleClass().add("NotEditable");}
        if (!T24.getText().isEmpty()) { T24.setEditable(false);T24.getStyleClass().add("NotEditable");}
        if (!T25.getText().isEmpty()) { T25.setEditable(false);T25.getStyleClass().add("NotEditable");}
        if (!T26.getText().isEmpty()) { T26.setEditable(false);T26.getStyleClass().add("NotEditable");}
        if (!T27.getText().isEmpty()) { T27.setEditable(false);T27.getStyleClass().add("NotEditable");}
        if (!T28.getText().isEmpty()) { T28.setEditable(false);T28.getStyleClass().add("NotEditable");}
        if (!T29.getText().isEmpty()) { T29.setEditable(false);T29.getStyleClass().add("NotEditable");}
        if (!T30.getText().isEmpty()) { T30.setEditable(false);T30.getStyleClass().add("NotEditable");}
        if (!T31.getText().isEmpty()) { T31.setEditable(false);T31.getStyleClass().add("NotEditable");}
        if (!T32.getText().isEmpty()) { T32.setEditable(false);T32.getStyleClass().add("NotEditable");}
        if (!T33.getText().isEmpty()) { T33.setEditable(false);T33.getStyleClass().add("NotEditable");}
        if (!T34.getText().isEmpty()) { T34.setEditable(false);T34.getStyleClass().add("NotEditable");}
        if (!T35.getText().isEmpty()) { T35.setEditable(false);T35.getStyleClass().add("NotEditable");}
        if (!T36.getText().isEmpty()) { T36.setEditable(false);T36.getStyleClass().add("NotEditable");}
        if (!T37.getText().isEmpty()) { T37.setEditable(false);T37.getStyleClass().add("NotEditable");}
        if (!T38.getText().isEmpty()) { T38.setEditable(false);T38.getStyleClass().add("NotEditable");}
        if (!T39.getText().isEmpty()) { T39.setEditable(false);T39.getStyleClass().add("NotEditable");}
        if (!T40.getText().isEmpty()) { T40.setEditable(false);T40.getStyleClass().add("NotEditable");}
        if (!T41.getText().isEmpty()) { T41.setEditable(false);T41.getStyleClass().add("NotEditable");}
        if (!T42.getText().isEmpty()) { T42.setEditable(false);T42.getStyleClass().add("NotEditable");}
        if (!T43.getText().isEmpty()) { T43.setEditable(false);T43.getStyleClass().add("NotEditable");}
        if (!T44.getText().isEmpty()) { T44.setEditable(false);T44.getStyleClass().add("NotEditable");}
        if (!T45.getText().isEmpty()) { T45.setEditable(false);T45.getStyleClass().add("NotEditable");}
        if (!T46.getText().isEmpty()) { T46.setEditable(false);T46.getStyleClass().add("NotEditable");}
        if (!T47.getText().isEmpty()) { T47.setEditable(false);T47.getStyleClass().add("NotEditable");}
        if (!T48.getText().isEmpty()) { T48.setEditable(false);T48.getStyleClass().add("NotEditable");}
        if (!T49.getText().isEmpty()) { T49.setEditable(false);T49.getStyleClass().add("NotEditable");}
        if (!T50.getText().isEmpty()) { T50.setEditable(false);T50.getStyleClass().add("NotEditable");}
        if (!T51.getText().isEmpty()) { T51.setEditable(false);T51.getStyleClass().add("NotEditable");}
        if (!T52.getText().isEmpty()) { T52.setEditable(false);T52.getStyleClass().add("NotEditable");}
        if (!T53.getText().isEmpty()) { T53.setEditable(false);T53.getStyleClass().add("NotEditable");}
        if (!T54.getText().isEmpty()) { T54.setEditable(false);T54.getStyleClass().add("NotEditable");}
        if (!T55.getText().isEmpty()) { T55.setEditable(false);T55.getStyleClass().add("NotEditable");}
        if (!T56.getText().isEmpty()) { T56.setEditable(false);T56.getStyleClass().add("NotEditable");}
        if (!T57.getText().isEmpty()) { T57.setEditable(false);T57.getStyleClass().add("NotEditable");}
        if (!T58.getText().isEmpty()) { T58.setEditable(false);T58.getStyleClass().add("NotEditable");}
        if (!T59.getText().isEmpty()) { T59.setEditable(false);T59.getStyleClass().add("NotEditable");}
        if (!T60.getText().isEmpty()) { T60.setEditable(false);T60.getStyleClass().add("NotEditable");}
        if (!T61.getText().isEmpty()) { T61.setEditable(false);T61.getStyleClass().add("NotEditable");}
        if (!T62.getText().isEmpty()) { T62.setEditable(false);T62.getStyleClass().add("NotEditable");}
        if (!T63.getText().isEmpty()) { T63.setEditable(false);T63.getStyleClass().add("NotEditable");}
        if (!T64.getText().isEmpty()) { T64.setEditable(false);T64.getStyleClass().add("NotEditable");}
        if (!T65.getText().isEmpty()) { T65.setEditable(false);T65.getStyleClass().add("NotEditable");}
        if (!T66.getText().isEmpty()) { T66.setEditable(false);T66.getStyleClass().add("NotEditable");}
        if (!T67.getText().isEmpty()) { T67.setEditable(false);T67.getStyleClass().add("NotEditable");}
        if (!T68.getText().isEmpty()) { T68.setEditable(false);T68.getStyleClass().add("NotEditable");}
        if (!T69.getText().isEmpty()) { T69.setEditable(false);T69.getStyleClass().add("NotEditable");}
        if (!T70.getText().isEmpty()) { T70.setEditable(false);T70.getStyleClass().add("NotEditable");}
        if (!T71.getText().isEmpty()) { T71.setEditable(false);T71.getStyleClass().add("NotEditable");}
        if (!T72.getText().isEmpty()) { T72.setEditable(false);T72.getStyleClass().add("NotEditable");}
        if (!T73.getText().isEmpty()) { T73.setEditable(false);T73.getStyleClass().add("NotEditable");}
        if (!T74.getText().isEmpty()) { T74.setEditable(false);T74.getStyleClass().add("NotEditable");}
        if (!T75.getText().isEmpty()) { T75.setEditable(false);T75.getStyleClass().add("NotEditable");}
        if (!T76.getText().isEmpty()) { T76.setEditable(false);T76.getStyleClass().add("NotEditable");}
        if (!T77.getText().isEmpty()) { T77.setEditable(false);T77.getStyleClass().add("NotEditable");}
        if (!T78.getText().isEmpty()) { T78.setEditable(false);T78.getStyleClass().add("NotEditable");}
        if (!T79.getText().isEmpty()) { T79.setEditable(false);T79.getStyleClass().add("NotEditable");}
        if (!T80.getText().isEmpty()) { T80.setEditable(false);T80.getStyleClass().add("NotEditable");}
        if (!T81.getText().isEmpty()) { T81.setEditable(false);T81.getStyleClass().add("NotEditable");}
        if (!T82.getText().isEmpty()) { T82.setEditable(false);T82.getStyleClass().add("NotEditable");}
        if (!T83.getText().isEmpty()) { T83.setEditable(false);T83.getStyleClass().add("NotEditable");}
        if (!T84.getText().isEmpty()) { T84.setEditable(false);T84.getStyleClass().add("NotEditable");}
        if (!T85.getText().isEmpty()) { T85.setEditable(false);T85.getStyleClass().add("NotEditable");}
        if (!T86.getText().isEmpty()) { T86.setEditable(false);T86.getStyleClass().add("NotEditable");}
        if (!T87.getText().isEmpty()) { T87.setEditable(false);T87.getStyleClass().add("NotEditable");}
        if (!T88.getText().isEmpty()) { T88.setEditable(false);T88.getStyleClass().add("NotEditable");}
        if (!T89.getText().isEmpty()) { T89.setEditable(false);T89.getStyleClass().add("NotEditable");}
        if (!T90.getText().isEmpty()) { T90.setEditable(false);T90.getStyleClass().add("NotEditable");}
        if (!T91.getText().isEmpty()) { T91.setEditable(false);T91.getStyleClass().add("NotEditable");}
        if (!T92.getText().isEmpty()) { T92.setEditable(false);T92.getStyleClass().add("NotEditable");}
        if (!T93.getText().isEmpty()) { T93.setEditable(false);T93.getStyleClass().add("NotEditable");}
        if (!T94.getText().isEmpty()) { T94.setEditable(false);T94.getStyleClass().add("NotEditable");}
        if (!T95.getText().isEmpty()) { T95.setEditable(false);T95.getStyleClass().add("NotEditable");}
        if (!T96.getText().isEmpty()) { T96.setEditable(false);T96.getStyleClass().add("NotEditable");}
        if (!T97.getText().isEmpty()) { T97.setEditable(false);T97.getStyleClass().add("NotEditable");}
        if (!T98.getText().isEmpty()) { T98.setEditable(false);T98.getStyleClass().add("NotEditable");}
        if (!T99.getText().isEmpty()) { T99.setEditable(false);T99.getStyleClass().add("NotEditable");}
        if (!T100.getText().isEmpty()) { T100.setEditable(false);T100.getStyleClass().add("NotEditable");}
        if (!T101.getText().isEmpty()) { T101.setEditable(false);T101.getStyleClass().add("NotEditable");}
        if (!T102.getText().isEmpty()) { T102.setEditable(false);T102.getStyleClass().add("NotEditable");}
        if (!T103.getText().isEmpty()) { T103.setEditable(false);T103.getStyleClass().add("NotEditable");}
        if (!T104.getText().isEmpty()) { T104.setEditable(false);T104.getStyleClass().add("NotEditable");}
        if (!T105.getText().isEmpty()) { T105.setEditable(false);T105.getStyleClass().add("NotEditable");}
        if (!T106.getText().isEmpty()) { T106.setEditable(false);T106.getStyleClass().add("NotEditable");}
        if (!T107.getText().isEmpty()) { T107.setEditable(false);T107.getStyleClass().add("NotEditable");}
        if (!T108.getText().isEmpty()) { T108.setEditable(false);T108.getStyleClass().add("NotEditable");}
        if (!T109.getText().isEmpty()) { T109.setEditable(false);T109.getStyleClass().add("NotEditable");}
        if (!T110.getText().isEmpty()) { T110.setEditable(false);T110.getStyleClass().add("NotEditable");}
        if (!T111.getText().isEmpty()) { T111.setEditable(false);T111.getStyleClass().add("NotEditable");}
        if (!T112.getText().isEmpty()) { T112.setEditable(false);T112.getStyleClass().add("NotEditable");}
        if (!T113.getText().isEmpty()) { T113.setEditable(false);T113.getStyleClass().add("NotEditable");}
        if (!T114.getText().isEmpty()) { T114.setEditable(false);T114.getStyleClass().add("NotEditable");}
        if (!T115.getText().isEmpty()) { T115.setEditable(false);T115.getStyleClass().add("NotEditable");}
        if (!T116.getText().isEmpty()) { T116.setEditable(false);T116.getStyleClass().add("NotEditable");}
        if (!T117.getText().isEmpty()) { T117.setEditable(false);T117.getStyleClass().add("NotEditable");}
        if (!T118.getText().isEmpty()) { T118.setEditable(false);T118.getStyleClass().add("NotEditable");}
        if (!T119.getText().isEmpty()) { T119.setEditable(false);T119.getStyleClass().add("NotEditable");}
        if (!T120.getText().isEmpty()) { T120.setEditable(false);T120.getStyleClass().add("NotEditable");}
        if (!T121.getText().isEmpty()) { T121.setEditable(false);T121.getStyleClass().add("NotEditable");}
        if (!T122.getText().isEmpty()) { T122.setEditable(false);T122.getStyleClass().add("NotEditable");}
        if (!T123.getText().isEmpty()) { T123.setEditable(false);T123.getStyleClass().add("NotEditable");}
        if (!T124.getText().isEmpty()) { T124.setEditable(false);T124.getStyleClass().add("NotEditable");}
        if (!T125.getText().isEmpty()) { T125.setEditable(false);T125.getStyleClass().add("NotEditable");}
        if (!T126.getText().isEmpty()) { T126.setEditable(false);T126.getStyleClass().add("NotEditable");}
        if (!T127.getText().isEmpty()) { T127.setEditable(false);T127.getStyleClass().add("NotEditable");}
        if (!T128.getText().isEmpty()) { T128.setEditable(false);T128.getStyleClass().add("NotEditable");}
        if (!T129.getText().isEmpty()) { T129.setEditable(false);T129.getStyleClass().add("NotEditable");}
        if (!T130.getText().isEmpty()) { T130.setEditable(false);T130.getStyleClass().add("NotEditable");}
        if (!T131.getText().isEmpty()) { T131.setEditable(false);T131.getStyleClass().add("NotEditable");}
        if (!T132.getText().isEmpty()) { T132.setEditable(false);T132.getStyleClass().add("NotEditable");}
        if (!T133.getText().isEmpty()) { T133.setEditable(false);T133.getStyleClass().add("NotEditable");}
        if (!T134.getText().isEmpty()) { T134.setEditable(false);T134.getStyleClass().add("NotEditable");}
        if (!T135.getText().isEmpty()) { T135.setEditable(false);T135.getStyleClass().add("NotEditable");}
        if (!T136.getText().isEmpty()) { T136.setEditable(false);T136.getStyleClass().add("NotEditable");}
        if (!T137.getText().isEmpty()) { T137.setEditable(false);T137.getStyleClass().add("NotEditable");}
        if (!T138.getText().isEmpty()) { T138.setEditable(false);T138.getStyleClass().add("NotEditable");}
        if (!T139.getText().isEmpty()) { T139.setEditable(false);T139.getStyleClass().add("NotEditable");}
        if (!T140.getText().isEmpty()) { T140.setEditable(false);T140.getStyleClass().add("NotEditable");}
        if (!T141.getText().isEmpty()) { T141.setEditable(false);T141.getStyleClass().add("NotEditable");}
        if (!T142.getText().isEmpty()) { T142.setEditable(false);T142.getStyleClass().add("NotEditable");}
        if (!T143.getText().isEmpty()) { T143.setEditable(false);T143.getStyleClass().add("NotEditable");}
        if (!T144.getText().isEmpty()) { T144.setEditable(false);T144.getStyleClass().add("NotEditable");}
        if (!T145.getText().isEmpty()) { T145.setEditable(false);T145.getStyleClass().add("NotEditable");}
        if (!T146.getText().isEmpty()) { T146.setEditable(false);T146.getStyleClass().add("NotEditable");}
        if (!T147.getText().isEmpty()) { T147.setEditable(false);T147.getStyleClass().add("NotEditable");}
        if (!T148.getText().isEmpty()) { T148.setEditable(false);T148.getStyleClass().add("NotEditable");}
        if (!T149.getText().isEmpty()) { T149.setEditable(false);T149.getStyleClass().add("NotEditable");}
        if (!T150.getText().isEmpty()) { T150.setEditable(false);T150.getStyleClass().add("NotEditable");}
        if (!T151.getText().isEmpty()) { T151.setEditable(false);T151.getStyleClass().add("NotEditable");}
        if (!T152.getText().isEmpty()) { T152.setEditable(false);T152.getStyleClass().add("NotEditable");}
        if (!T153.getText().isEmpty()) { T153.setEditable(false);T153.getStyleClass().add("NotEditable");}
        if (!T154.getText().isEmpty()) { T154.setEditable(false);T154.getStyleClass().add("NotEditable");}
        if (!T155.getText().isEmpty()) { T155.setEditable(false);T155.getStyleClass().add("NotEditable");}
        if (!T156.getText().isEmpty()) { T156.setEditable(false);T156.getStyleClass().add("NotEditable");}
        if (!T157.getText().isEmpty()) { T157.setEditable(false);T157.getStyleClass().add("NotEditable");}
        if (!T158.getText().isEmpty()) { T158.setEditable(false);T158.getStyleClass().add("NotEditable");}
        if (!T159.getText().isEmpty()) { T159.setEditable(false);T159.getStyleClass().add("NotEditable");}
        if (!T160.getText().isEmpty()) { T160.setEditable(false);T160.getStyleClass().add("NotEditable");}
        if (!T161.getText().isEmpty()) { T161.setEditable(false);T161.getStyleClass().add("NotEditable");}
        if (!T162.getText().isEmpty()) { T162.setEditable(false);T162.getStyleClass().add("NotEditable");}
        if (!T163.getText().isEmpty()) { T163.setEditable(false);T163.getStyleClass().add("NotEditable");}
        if (!T164.getText().isEmpty()) { T164.setEditable(false);T164.getStyleClass().add("NotEditable");}
        if (!T165.getText().isEmpty()) { T165.setEditable(false);T165.getStyleClass().add("NotEditable");}
        if (!T166.getText().isEmpty()) { T166.setEditable(false);T166.getStyleClass().add("NotEditable");}
        if (!T167.getText().isEmpty()) { T167.setEditable(false);T167.getStyleClass().add("NotEditable");}
        if (!T168.getText().isEmpty()) { T168.setEditable(false);T168.getStyleClass().add("NotEditable");}
        if (!T169.getText().isEmpty()) { T169.setEditable(false);T169.getStyleClass().add("NotEditable");}
        if (!T170.getText().isEmpty()) { T170.setEditable(false);T170.getStyleClass().add("NotEditable");}
        if (!T171.getText().isEmpty()) { T171.setEditable(false);T171.getStyleClass().add("NotEditable");}
        if (!T172.getText().isEmpty()) { T172.setEditable(false);T172.getStyleClass().add("NotEditable");}
        if (!T173.getText().isEmpty()) { T173.setEditable(false);T173.getStyleClass().add("NotEditable");}
        if (!T174.getText().isEmpty()) { T174.setEditable(false);T174.getStyleClass().add("NotEditable");}
        if (!T175.getText().isEmpty()) { T175.setEditable(false);T175.getStyleClass().add("NotEditable");}
        if (!T176.getText().isEmpty()) { T176.setEditable(false);T176.getStyleClass().add("NotEditable");}
        if (!T177.getText().isEmpty()) { T177.setEditable(false);T177.getStyleClass().add("NotEditable");}
        if (!T178.getText().isEmpty()) { T178.setEditable(false);T178.getStyleClass().add("NotEditable");}
        if (!T179.getText().isEmpty()) { T179.setEditable(false);T179.getStyleClass().add("NotEditable");}
        if (!T180.getText().isEmpty()) { T180.setEditable(false);T180.getStyleClass().add("NotEditable");}
        if (!T181.getText().isEmpty()) { T181.setEditable(false);T181.getStyleClass().add("NotEditable");}
        if (!T182.getText().isEmpty()) { T182.setEditable(false);T182.getStyleClass().add("NotEditable");}
        if (!T183.getText().isEmpty()) { T183.setEditable(false);T183.getStyleClass().add("NotEditable");}
        if (!T184.getText().isEmpty()) { T184.setEditable(false);T184.getStyleClass().add("NotEditable");}
        if (!T185.getText().isEmpty()) { T185.setEditable(false);T185.getStyleClass().add("NotEditable");}
        if (!T186.getText().isEmpty()) { T186.setEditable(false);T186.getStyleClass().add("NotEditable");}
        if (!T187.getText().isEmpty()) { T187.setEditable(false);T187.getStyleClass().add("NotEditable");}
        if (!T188.getText().isEmpty()) { T188.setEditable(false);T188.getStyleClass().add("NotEditable");}
        if (!T189.getText().isEmpty()) { T189.setEditable(false);T189.getStyleClass().add("NotEditable");}
        if (!T190.getText().isEmpty()) { T190.setEditable(false);T190.getStyleClass().add("NotEditable");}
        if (!T191.getText().isEmpty()) { T191.setEditable(false);T191.getStyleClass().add("NotEditable");}
        if (!T192.getText().isEmpty()) { T192.setEditable(false);T192.getStyleClass().add("NotEditable");}
        if (!T193.getText().isEmpty()) { T193.setEditable(false);T193.getStyleClass().add("NotEditable");}
        if (!T194.getText().isEmpty()) { T194.setEditable(false);T194.getStyleClass().add("NotEditable");}
        if (!T195.getText().isEmpty()) { T195.setEditable(false);T195.getStyleClass().add("NotEditable");}
        if (!T196.getText().isEmpty()) { T196.setEditable(false);T196.getStyleClass().add("NotEditable");}
        if (!T197.getText().isEmpty()) { T197.setEditable(false);T197.getStyleClass().add("NotEditable");}
        if (!T198.getText().isEmpty()) { T198.setEditable(false);T198.getStyleClass().add("NotEditable");}
        if (!T199.getText().isEmpty()) { T199.setEditable(false);T199.getStyleClass().add("NotEditable");}
        if (!T200.getText().isEmpty()) { T200.setEditable(false);T200.getStyleClass().add("NotEditable");}
        if (!T201.getText().isEmpty()) { T201.setEditable(false);T201.getStyleClass().add("NotEditable");}
        if (!T202.getText().isEmpty()) { T202.setEditable(false);T202.getStyleClass().add("NotEditable");}
        if (!T203.getText().isEmpty()) { T203.setEditable(false);T203.getStyleClass().add("NotEditable");}
        if (!T204.getText().isEmpty()) { T204.setEditable(false);T204.getStyleClass().add("NotEditable");}
        if (!T205.getText().isEmpty()) { T205.setEditable(false);T205.getStyleClass().add("NotEditable");}
        if (!T206.getText().isEmpty()) { T206.setEditable(false);T206.getStyleClass().add("NotEditable");}
        if (!T207.getText().isEmpty()) { T207.setEditable(false);T207.getStyleClass().add("NotEditable");}
        if (!T208.getText().isEmpty()) { T208.setEditable(false);T208.getStyleClass().add("NotEditable");}
        if (!T209.getText().isEmpty()) { T209.setEditable(false);T209.getStyleClass().add("NotEditable");}
        if (!T210.getText().isEmpty()) { T210.setEditable(false);T210.getStyleClass().add("NotEditable");}
        if (!T211.getText().isEmpty()) { T211.setEditable(false);T211.getStyleClass().add("NotEditable");}
        if (!T212.getText().isEmpty()) { T212.setEditable(false);T212.getStyleClass().add("NotEditable");}
        if (!T213.getText().isEmpty()) { T213.setEditable(false);T213.getStyleClass().add("NotEditable");}
        if (!T214.getText().isEmpty()) { T214.setEditable(false);T214.getStyleClass().add("NotEditable");}
        if (!T215.getText().isEmpty()) { T215.setEditable(false);T215.getStyleClass().add("NotEditable");}
        if (!T216.getText().isEmpty()) { T216.setEditable(false);T216.getStyleClass().add("NotEditable");}
        if (!T217.getText().isEmpty()) { T217.setEditable(false);T217.getStyleClass().add("NotEditable");}
        if (!T218.getText().isEmpty()) { T218.setEditable(false);T218.getStyleClass().add("NotEditable");}
        if (!T219.getText().isEmpty()) { T219.setEditable(false);T219.getStyleClass().add("NotEditable");}
        if (!T220.getText().isEmpty()) { T220.setEditable(false);T220.getStyleClass().add("NotEditable");}
        if (!T221.getText().isEmpty()) { T221.setEditable(false);T221.getStyleClass().add("NotEditable");}
        if (!T222.getText().isEmpty()) { T222.setEditable(false);T222.getStyleClass().add("NotEditable");}
        if (!T223.getText().isEmpty()) { T223.setEditable(false);T223.getStyleClass().add("NotEditable");}
        if (!T224.getText().isEmpty()) { T224.setEditable(false);T224.getStyleClass().add("NotEditable");}
        if (!T225.getText().isEmpty()) { T225.setEditable(false);T225.getStyleClass().add("NotEditable");}
        if (!T226.getText().isEmpty()) { T226.setEditable(false);T226.getStyleClass().add("NotEditable");}
        if (!T227.getText().isEmpty()) { T227.setEditable(false);T227.getStyleClass().add("NotEditable");}
        if (!T228.getText().isEmpty()) { T228.setEditable(false);T228.getStyleClass().add("NotEditable");}
        if (!T229.getText().isEmpty()) { T229.setEditable(false);T229.getStyleClass().add("NotEditable");}
        if (!T230.getText().isEmpty()) { T230.setEditable(false);T230.getStyleClass().add("NotEditable");}
        if (!T231.getText().isEmpty()) { T231.setEditable(false);T231.getStyleClass().add("NotEditable");}
        if (!T232.getText().isEmpty()) { T232.setEditable(false);T232.getStyleClass().add("NotEditable");}
        if (!T233.getText().isEmpty()) { T233.setEditable(false);T233.getStyleClass().add("NotEditable");}
        if (!T234.getText().isEmpty()) { T234.setEditable(false);T234.getStyleClass().add("NotEditable");}
        if (!T235.getText().isEmpty()) { T235.setEditable(false);T235.getStyleClass().add("NotEditable");}
        if (!T236.getText().isEmpty()) { T236.setEditable(false);T236.getStyleClass().add("NotEditable");}
        if (!T237.getText().isEmpty()) { T237.setEditable(false);T237.getStyleClass().add("NotEditable");}
        if (!T238.getText().isEmpty()) { T238.setEditable(false);T238.getStyleClass().add("NotEditable");}
        if (!T239.getText().isEmpty()) { T239.setEditable(false);T239.getStyleClass().add("NotEditable");}
        if (!T240.getText().isEmpty()) { T240.setEditable(false);T240.getStyleClass().add("NotEditable");}
        if (!T241.getText().isEmpty()) { T241.setEditable(false);T241.getStyleClass().add("NotEditable");}
        if (!T242.getText().isEmpty()) { T242.setEditable(false);T242.getStyleClass().add("NotEditable");}
        if (!T243.getText().isEmpty()) { T243.setEditable(false);T243.getStyleClass().add("NotEditable");}
        if (!T244.getText().isEmpty()) { T244.setEditable(false);T244.getStyleClass().add("NotEditable");}
        if (!T245.getText().isEmpty()) { T245.setEditable(false);T245.getStyleClass().add("NotEditable");}
        if (!T246.getText().isEmpty()) { T246.setEditable(false);T246.getStyleClass().add("NotEditable");}
        if (!T247.getText().isEmpty()) { T247.setEditable(false);T247.getStyleClass().add("NotEditable");}
        if (!T248.getText().isEmpty()) { T248.setEditable(false);T248.getStyleClass().add("NotEditable");}
        if (!T249.getText().isEmpty()) { T249.setEditable(false);T249.getStyleClass().add("NotEditable");}
        if (!T250.getText().isEmpty()) { T250.setEditable(false);T250.getStyleClass().add("NotEditable");}
        if (!T251.getText().isEmpty()) { T251.setEditable(false);T251.getStyleClass().add("NotEditable");}
        if (!T252.getText().isEmpty()) { T252.setEditable(false);T252.getStyleClass().add("NotEditable");}
        if (!T253.getText().isEmpty()) { T253.setEditable(false);T253.getStyleClass().add("NotEditable");}
        if (!T254.getText().isEmpty()) { T254.setEditable(false);T254.getStyleClass().add("NotEditable");}
        if (!T255.getText().isEmpty()) { T255.setEditable(false);T255.getStyleClass().add("NotEditable");}
        if (!T256.getText().isEmpty()) { T256.setEditable(false);T256.getStyleClass().add("NotEditable");}
        if (!T257.getText().isEmpty()) { T257.setEditable(false);T257.getStyleClass().add("NotEditable");}
        if (!T258.getText().isEmpty()) { T258.setEditable(false);T258.getStyleClass().add("NotEditable");}
        if (!T259.getText().isEmpty()) { T259.setEditable(false);T259.getStyleClass().add("NotEditable");}
        if (!T260.getText().isEmpty()) { T260.setEditable(false);T260.getStyleClass().add("NotEditable");}
        if (!T261.getText().isEmpty()) { T261.setEditable(false);T261.getStyleClass().add("NotEditable");}
        if (!T262.getText().isEmpty()) { T262.setEditable(false);T262.getStyleClass().add("NotEditable");}
        if (!T263.getText().isEmpty()) { T263.setEditable(false);T263.getStyleClass().add("NotEditable");}
        if (!T264.getText().isEmpty()) { T264.setEditable(false);T264.getStyleClass().add("NotEditable");}
        if (!T265.getText().isEmpty()) { T265.setEditable(false);T265.getStyleClass().add("NotEditable");}
        if (!T266.getText().isEmpty()) { T266.setEditable(false);T266.getStyleClass().add("NotEditable");}
        if (!T267.getText().isEmpty()) { T267.setEditable(false);T267.getStyleClass().add("NotEditable");}
        if (!T268.getText().isEmpty()) { T268.setEditable(false);T268.getStyleClass().add("NotEditable");}
        if (!T269.getText().isEmpty()) { T269.setEditable(false);T269.getStyleClass().add("NotEditable");}
        if (!T270.getText().isEmpty()) { T270.setEditable(false);T270.getStyleClass().add("NotEditable");}
        if (!T271.getText().isEmpty()) { T271.setEditable(false);T271.getStyleClass().add("NotEditable");}
        if (!T272.getText().isEmpty()) { T272.setEditable(false);T272.getStyleClass().add("NotEditable");}
        if (!T273.getText().isEmpty()) { T273.setEditable(false);T273.getStyleClass().add("NotEditable");}
        if (!T274.getText().isEmpty()) { T274.setEditable(false);T274.getStyleClass().add("NotEditable");}
        if (!T275.getText().isEmpty()) { T275.setEditable(false);T275.getStyleClass().add("NotEditable");}
        if (!T276.getText().isEmpty()) { T276.setEditable(false);T276.getStyleClass().add("NotEditable");}
        if (!T277.getText().isEmpty()) { T277.setEditable(false);T277.getStyleClass().add("NotEditable");}
        if (!T278.getText().isEmpty()) { T278.setEditable(false);T278.getStyleClass().add("NotEditable");}
        if (!T279.getText().isEmpty()) { T279.setEditable(false);T279.getStyleClass().add("NotEditable");}
        if (!T280.getText().isEmpty()) { T280.setEditable(false);T280.getStyleClass().add("NotEditable");}
        if (!T281.getText().isEmpty()) { T281.setEditable(false);T281.getStyleClass().add("NotEditable");}
        if (!T282.getText().isEmpty()) { T282.setEditable(false);T282.getStyleClass().add("NotEditable");}
        if (!T283.getText().isEmpty()) { T283.setEditable(false);T283.getStyleClass().add("NotEditable");}
        if (!T284.getText().isEmpty()) { T284.setEditable(false);T284.getStyleClass().add("NotEditable");}
        if (!T285.getText().isEmpty()) { T285.setEditable(false);T285.getStyleClass().add("NotEditable");}
        if (!T286.getText().isEmpty()) { T286.setEditable(false);T286.getStyleClass().add("NotEditable");}
        if (!T287.getText().isEmpty()) { T287.setEditable(false);T287.getStyleClass().add("NotEditable");}
        if (!T288.getText().isEmpty()) { T288.setEditable(false);T288.getStyleClass().add("NotEditable");}
        if (!T289.getText().isEmpty()) { T289.setEditable(false);T289.getStyleClass().add("NotEditable");}
        if (!T290.getText().isEmpty()) { T290.setEditable(false);T290.getStyleClass().add("NotEditable");}
        if (!T291.getText().isEmpty()) { T291.setEditable(false);T291.getStyleClass().add("NotEditable");}
        if (!T292.getText().isEmpty()) { T292.setEditable(false);T292.getStyleClass().add("NotEditable");}
        if (!T293.getText().isEmpty()) { T293.setEditable(false);T293.getStyleClass().add("NotEditable");}
        if (!T294.getText().isEmpty()) { T294.setEditable(false);T294.getStyleClass().add("NotEditable");}
        if (!T295.getText().isEmpty()) { T295.setEditable(false);T295.getStyleClass().add("NotEditable");}
        if (!T296.getText().isEmpty()) { T296.setEditable(false);T296.getStyleClass().add("NotEditable");}
        if (!T297.getText().isEmpty()) { T297.setEditable(false);T297.getStyleClass().add("NotEditable");}
        if (!T298.getText().isEmpty()) { T298.setEditable(false);T298.getStyleClass().add("NotEditable");}
        if (!T299.getText().isEmpty()) { T299.setEditable(false);T299.getStyleClass().add("NotEditable");}
        if (!T300.getText().isEmpty()) { T300.setEditable(false);T300.getStyleClass().add("NotEditable");}
        if (!T301.getText().isEmpty()) { T301.setEditable(false);T301.getStyleClass().add("NotEditable");}
        if (!T302.getText().isEmpty()) { T302.setEditable(false);T302.getStyleClass().add("NotEditable");}
        if (!T303.getText().isEmpty()) { T303.setEditable(false);T303.getStyleClass().add("NotEditable");}
        if (!T304.getText().isEmpty()) { T304.setEditable(false);T304.getStyleClass().add("NotEditable");}
        if (!T305.getText().isEmpty()) { T305.setEditable(false);T305.getStyleClass().add("NotEditable");}
        if (!T306.getText().isEmpty()) { T306.setEditable(false);T306.getStyleClass().add("NotEditable");}
        if (!T307.getText().isEmpty()) { T307.setEditable(false);T307.getStyleClass().add("NotEditable");}
        if (!T308.getText().isEmpty()) { T308.setEditable(false);T308.getStyleClass().add("NotEditable");}
        if (!T309.getText().isEmpty()) { T309.setEditable(false);T309.getStyleClass().add("NotEditable");}
        if (!T310.getText().isEmpty()) { T310.setEditable(false);T310.getStyleClass().add("NotEditable");}
        if (!T311.getText().isEmpty()) { T311.setEditable(false);T311.getStyleClass().add("NotEditable");}
        if (!T312.getText().isEmpty()) { T312.setEditable(false);T312.getStyleClass().add("NotEditable");}
        if (!T313.getText().isEmpty()) { T313.setEditable(false);T313.getStyleClass().add("NotEditable");}
        if (!T314.getText().isEmpty()) { T314.setEditable(false);T314.getStyleClass().add("NotEditable");}
        if (!T315.getText().isEmpty()) { T315.setEditable(false);T315.getStyleClass().add("NotEditable");}
        if (!T316.getText().isEmpty()) { T316.setEditable(false);T316.getStyleClass().add("NotEditable");}
        if (!T317.getText().isEmpty()) { T317.setEditable(false);T317.getStyleClass().add("NotEditable");}
        if (!T318.getText().isEmpty()) { T318.setEditable(false);T318.getStyleClass().add("NotEditable");}
        if (!T319.getText().isEmpty()) { T319.setEditable(false);T319.getStyleClass().add("NotEditable");}
        if (!T320.getText().isEmpty()) { T320.setEditable(false);T320.getStyleClass().add("NotEditable");}
        if (!T321.getText().isEmpty()) { T321.setEditable(false);T321.getStyleClass().add("NotEditable");}
        if (!T322.getText().isEmpty()) { T322.setEditable(false);T322.getStyleClass().add("NotEditable");}
        if (!T323.getText().isEmpty()) { T323.setEditable(false);T323.getStyleClass().add("NotEditable");}
        if (!T324.getText().isEmpty()) { T324.setEditable(false);T324.getStyleClass().add("NotEditable");}
        if (!T325.getText().isEmpty()) { T325.setEditable(false);T325.getStyleClass().add("NotEditable");}
        if (!T326.getText().isEmpty()) { T326.setEditable(false);T326.getStyleClass().add("NotEditable");}
        if (!T327.getText().isEmpty()) { T327.setEditable(false);T327.getStyleClass().add("NotEditable");}
        if (!T328.getText().isEmpty()) { T328.setEditable(false);T328.getStyleClass().add("NotEditable");}
        if (!T329.getText().isEmpty()) { T329.setEditable(false);T329.getStyleClass().add("NotEditable");}
        if (!T330.getText().isEmpty()) { T330.setEditable(false);T330.getStyleClass().add("NotEditable");}
        if (!T331.getText().isEmpty()) { T331.setEditable(false);T331.getStyleClass().add("NotEditable");}
        if (!T332.getText().isEmpty()) { T332.setEditable(false);T332.getStyleClass().add("NotEditable");}
        if (!T333.getText().isEmpty()) { T333.setEditable(false);T333.getStyleClass().add("NotEditable");}
        if (!T334.getText().isEmpty()) { T334.setEditable(false);T334.getStyleClass().add("NotEditable");}
        if (!T335.getText().isEmpty()) { T335.setEditable(false);T335.getStyleClass().add("NotEditable");}
        if (!T336.getText().isEmpty()) { T336.setEditable(false);T336.getStyleClass().add("NotEditable");}
        if (!T337.getText().isEmpty()) { T337.setEditable(false);T337.getStyleClass().add("NotEditable");}
        if (!T338.getText().isEmpty()) { T338.setEditable(false);T338.getStyleClass().add("NotEditable");}
        if (!T339.getText().isEmpty()) { T339.setEditable(false);T339.getStyleClass().add("NotEditable");}
        if (!T340.getText().isEmpty()) { T340.setEditable(false);T340.getStyleClass().add("NotEditable");}
        if (!T341.getText().isEmpty()) { T341.setEditable(false);T341.getStyleClass().add("NotEditable");}
        if (!T342.getText().isEmpty()) { T342.setEditable(false);T342.getStyleClass().add("NotEditable");}
        if (!T343.getText().isEmpty()) { T343.setEditable(false);T343.getStyleClass().add("NotEditable");}
        if (!T344.getText().isEmpty()) { T344.setEditable(false);T344.getStyleClass().add("NotEditable");}
        if (!T345.getText().isEmpty()) { T345.setEditable(false);T345.getStyleClass().add("NotEditable");}
        if (!T346.getText().isEmpty()) { T346.setEditable(false);T346.getStyleClass().add("NotEditable");}
        if (!T347.getText().isEmpty()) { T347.setEditable(false);T347.getStyleClass().add("NotEditable");}
        if (!T348.getText().isEmpty()) { T348.setEditable(false);T348.getStyleClass().add("NotEditable");}
        if (!T349.getText().isEmpty()) { T349.setEditable(false);T349.getStyleClass().add("NotEditable");}
        if (!T350.getText().isEmpty()) { T350.setEditable(false);T350.getStyleClass().add("NotEditable");}
        if (!T351.getText().isEmpty()) { T351.setEditable(false);T351.getStyleClass().add("NotEditable");}
        if (!T352.getText().isEmpty()) { T352.setEditable(false);T352.getStyleClass().add("NotEditable");}
        if (!T353.getText().isEmpty()) { T353.setEditable(false);T353.getStyleClass().add("NotEditable");}
        if (!T354.getText().isEmpty()) { T354.setEditable(false);T354.getStyleClass().add("NotEditable");}
        if (!T355.getText().isEmpty()) { T355.setEditable(false);T355.getStyleClass().add("NotEditable");}
        if (!T356.getText().isEmpty()) { T356.setEditable(false);T356.getStyleClass().add("NotEditable");}
        if (!T357.getText().isEmpty()) { T357.setEditable(false);T357.getStyleClass().add("NotEditable");}
        if (!T358.getText().isEmpty()) { T358.setEditable(false);T358.getStyleClass().add("NotEditable");}
        if (!T359.getText().isEmpty()) { T359.setEditable(false);T359.getStyleClass().add("NotEditable");}
        if (!T360.getText().isEmpty()) { T360.setEditable(false);T360.getStyleClass().add("NotEditable");}
        if (!T361.getText().isEmpty()) { T361.setEditable(false);T361.getStyleClass().add("NotEditable");}
        if (!T362.getText().isEmpty()) { T362.setEditable(false);T362.getStyleClass().add("NotEditable");}
        if (!T363.getText().isEmpty()) { T363.setEditable(false);T363.getStyleClass().add("NotEditable");}
        if (!T364.getText().isEmpty()) { T364.setEditable(false);T364.getStyleClass().add("NotEditable");}
        if (!T365.getText().isEmpty()) { T365.setEditable(false);T365.getStyleClass().add("NotEditable");}
        if (!T366.getText().isEmpty()) { T366.setEditable(false);T366.getStyleClass().add("NotEditable");}
        if (!T367.getText().isEmpty()) { T367.setEditable(false);T367.getStyleClass().add("NotEditable");}
        if (!T368.getText().isEmpty()) { T368.setEditable(false);T368.getStyleClass().add("NotEditable");}
        if (!T369.getText().isEmpty()) { T369.setEditable(false);T369.getStyleClass().add("NotEditable");}
        if (!T370.getText().isEmpty()) { T370.setEditable(false);T370.getStyleClass().add("NotEditable");}
        if (!T371.getText().isEmpty()) { T371.setEditable(false);T371.getStyleClass().add("NotEditable");}
        if (!T372.getText().isEmpty()) { T372.setEditable(false);T372.getStyleClass().add("NotEditable");}
        if (!T373.getText().isEmpty()) { T373.setEditable(false);T373.getStyleClass().add("NotEditable");}
        if (!T374.getText().isEmpty()) { T374.setEditable(false);T374.getStyleClass().add("NotEditable");}
        if (!T375.getText().isEmpty()) { T375.setEditable(false);T375.getStyleClass().add("NotEditable");}
        if (!T376.getText().isEmpty()) { T376.setEditable(false);T376.getStyleClass().add("NotEditable");}
        if (!T377.getText().isEmpty()) { T377.setEditable(false);T377.getStyleClass().add("NotEditable");}
        if (!T378.getText().isEmpty()) { T378.setEditable(false);T378.getStyleClass().add("NotEditable");}
        if (!T379.getText().isEmpty()) { T379.setEditable(false);T379.getStyleClass().add("NotEditable");}
        if (!T380.getText().isEmpty()) { T380.setEditable(false);T380.getStyleClass().add("NotEditable");}
        if (!T381.getText().isEmpty()) { T381.setEditable(false);T381.getStyleClass().add("NotEditable");}
        if (!T382.getText().isEmpty()) { T382.setEditable(false);T382.getStyleClass().add("NotEditable");}
        if (!T383.getText().isEmpty()) { T383.setEditable(false);T383.getStyleClass().add("NotEditable");}
        if (!T384.getText().isEmpty()) { T384.setEditable(false);T384.getStyleClass().add("NotEditable");}
        if (!T385.getText().isEmpty()) { T385.setEditable(false);T385.getStyleClass().add("NotEditable");}
        if (!T386.getText().isEmpty()) { T386.setEditable(false);T386.getStyleClass().add("NotEditable");}
        if (!T387.getText().isEmpty()) { T387.setEditable(false);T387.getStyleClass().add("NotEditable");}
        if (!T388.getText().isEmpty()) { T388.setEditable(false);T388.getStyleClass().add("NotEditable");}
        if (!T389.getText().isEmpty()) { T389.setEditable(false);T389.getStyleClass().add("NotEditable");}
        if (!T390.getText().isEmpty()) { T390.setEditable(false);T390.getStyleClass().add("NotEditable");}
        if (!T391.getText().isEmpty()) { T391.setEditable(false);T391.getStyleClass().add("NotEditable");}
        if (!T392.getText().isEmpty()) { T392.setEditable(false);T392.getStyleClass().add("NotEditable");}
        if (!T393.getText().isEmpty()) { T393.setEditable(false);T393.getStyleClass().add("NotEditable");}
        if (!T394.getText().isEmpty()) { T394.setEditable(false);T394.getStyleClass().add("NotEditable");}
        if (!T395.getText().isEmpty()) { T395.setEditable(false);T395.getStyleClass().add("NotEditable");}
        if (!T396.getText().isEmpty()) { T396.setEditable(false);T396.getStyleClass().add("NotEditable");}
        if (!T397.getText().isEmpty()) { T397.setEditable(false);T397.getStyleClass().add("NotEditable");}
        if (!T398.getText().isEmpty()) { T398.setEditable(false);T398.getStyleClass().add("NotEditable");}
        if (!T399.getText().isEmpty()) { T399.setEditable(false);T399.getStyleClass().add("NotEditable");}
    }

    public void setBoard(String[] board){
        this.board = board;
        T0.setText(board[0]);
        T1.setText(board[1]);
        T2.setText(board[2]);
        T3.setText(board[3]);
        T4.setText(board[4]);
        T5.setText(board[5]);
        T6.setText(board[6]);
        T7.setText(board[7]);
        T8.setText(board[8]);
        T9.setText(board[9]);
        T10.setText(board[10]);
        T11.setText(board[11]);
        T12.setText(board[12]);
        T13.setText(board[13]);
        T14.setText(board[14]);
        T15.setText(board[15]);
        T16.setText(board[16]);
        T17.setText(board[17]);
        T18.setText(board[18]);
        T19.setText(board[19]);
        T20.setText(board[20]);
        T21.setText(board[21]);
        T22.setText(board[22]);
        T23.setText(board[23]);
        T24.setText(board[24]);
        T25.setText(board[25]);
        T26.setText(board[26]);
        T27.setText(board[27]);
        T28.setText(board[28]);
        T29.setText(board[29]);
        T30.setText(board[30]);
        T31.setText(board[31]);
        T32.setText(board[32]);
        T33.setText(board[33]);
        T34.setText(board[34]);
        T35.setText(board[35]);
        T36.setText(board[36]);
        T37.setText(board[37]);
        T38.setText(board[38]);
        T39.setText(board[39]);
        T40.setText(board[40]);
        T41.setText(board[41]);
        T42.setText(board[42]);
        T43.setText(board[43]);
        T44.setText(board[44]);
        T45.setText(board[45]);
        T46.setText(board[46]);
        T47.setText(board[47]);
        T48.setText(board[48]);
        T49.setText(board[49]);
        T50.setText(board[50]);
        T51.setText(board[51]);
        T52.setText(board[52]);
        T53.setText(board[53]);
        T54.setText(board[54]);
        T55.setText(board[55]);
        T56.setText(board[56]);
        T57.setText(board[57]);
        T58.setText(board[58]);
        T59.setText(board[59]);
        T60.setText(board[60]);
        T61.setText(board[61]);
        T62.setText(board[62]);
        T63.setText(board[63]);
        T64.setText(board[64]);
        T65.setText(board[65]);
        T66.setText(board[66]);
        T67.setText(board[67]);
        T68.setText(board[68]);
        T69.setText(board[69]);
        T70.setText(board[70]);
        T71.setText(board[71]);
        T72.setText(board[72]);
        T73.setText(board[73]);
        T74.setText(board[74]);
        T75.setText(board[75]);
        T76.setText(board[76]);
        T77.setText(board[77]);
        T78.setText(board[78]);
        T79.setText(board[79]);
        T80.setText(board[80]);
        T81.setText(board[81]);
        T82.setText(board[82]);
        T83.setText(board[83]);
        T84.setText(board[84]);
        T85.setText(board[85]);
        T86.setText(board[86]);
        T87.setText(board[87]);
        T88.setText(board[88]);
        T89.setText(board[89]);
        T90.setText(board[90]);
        T91.setText(board[91]);
        T92.setText(board[92]);
        T93.setText(board[93]);
        T94.setText(board[94]);
        T95.setText(board[95]);
        T96.setText(board[96]);
        T97.setText(board[97]);
        T98.setText(board[98]);
        T99.setText(board[99]);
        T100.setText(board[100]);
        T101.setText(board[101]);
        T102.setText(board[102]);
        T103.setText(board[103]);
        T104.setText(board[104]);
        T105.setText(board[105]);
        T106.setText(board[106]);
        T107.setText(board[107]);
        T108.setText(board[108]);
        T109.setText(board[109]);
        T110.setText(board[110]);
        T111.setText(board[111]);
        T112.setText(board[112]);
        T113.setText(board[113]);
        T114.setText(board[114]);
        T115.setText(board[115]);
        T116.setText(board[116]);
        T117.setText(board[117]);
        T118.setText(board[118]);
        T119.setText(board[119]);
        T120.setText(board[120]);
        T121.setText(board[121]);
        T122.setText(board[122]);
        T123.setText(board[123]);
        T124.setText(board[124]);
        T125.setText(board[125]);
        T126.setText(board[126]);
        T127.setText(board[127]);
        T128.setText(board[128]);
        T129.setText(board[129]);
        T130.setText(board[130]);
        T131.setText(board[131]);
        T132.setText(board[132]);
        T133.setText(board[133]);
        T134.setText(board[134]);
        T135.setText(board[135]);
        T136.setText(board[136]);
        T137.setText(board[137]);
        T138.setText(board[138]);
        T139.setText(board[139]);
        T140.setText(board[140]);
        T141.setText(board[141]);
        T142.setText(board[142]);
        T143.setText(board[143]);
        T144.setText(board[144]);
        T145.setText(board[145]);
        T146.setText(board[146]);
        T147.setText(board[147]);
        T148.setText(board[148]);
        T149.setText(board[149]);
        T150.setText(board[150]);
        T151.setText(board[151]);
        T152.setText(board[152]);
        T153.setText(board[153]);
        T154.setText(board[154]);
        T155.setText(board[155]);
        T156.setText(board[156]);
        T157.setText(board[157]);
        T158.setText(board[158]);
        T159.setText(board[159]);
        T160.setText(board[160]);
        T161.setText(board[161]);
        T162.setText(board[162]);
        T163.setText(board[163]);
        T164.setText(board[164]);
        T165.setText(board[165]);
        T166.setText(board[166]);
        T167.setText(board[167]);
        T168.setText(board[168]);
        T169.setText(board[169]);
        T170.setText(board[170]);
        T171.setText(board[171]);
        T172.setText(board[172]);
        T173.setText(board[173]);
        T174.setText(board[174]);
        T175.setText(board[175]);
        T176.setText(board[176]);
        T177.setText(board[177]);
        T178.setText(board[178]);
        T179.setText(board[179]);
        T180.setText(board[180]);
        T181.setText(board[181]);
        T182.setText(board[182]);
        T183.setText(board[183]);
        T184.setText(board[184]);
        T185.setText(board[185]);
        T186.setText(board[186]);
        T187.setText(board[187]);
        T188.setText(board[188]);
        T189.setText(board[189]);
        T190.setText(board[190]);
        T191.setText(board[191]);
        T192.setText(board[192]);
        T193.setText(board[193]);
        T194.setText(board[194]);
        T195.setText(board[195]);
        T196.setText(board[196]);
        T197.setText(board[197]);
        T198.setText(board[198]);
        T199.setText(board[199]);
        T200.setText(board[200]);
        T201.setText(board[201]);
        T202.setText(board[202]);
        T203.setText(board[203]);
        T204.setText(board[204]);
        T205.setText(board[205]);
        T206.setText(board[206]);
        T207.setText(board[207]);
        T208.setText(board[208]);
        T209.setText(board[209]);
        T210.setText(board[210]);
        T211.setText(board[211]);
        T212.setText(board[212]);
        T213.setText(board[213]);
        T214.setText(board[214]);
        T215.setText(board[215]);
        T216.setText(board[216]);
        T217.setText(board[217]);
        T218.setText(board[218]);
        T219.setText(board[219]);
        T220.setText(board[220]);
        T221.setText(board[221]);
        T222.setText(board[222]);
        T223.setText(board[223]);
        T224.setText(board[224]);
        T225.setText(board[225]);
        T226.setText(board[226]);
        T227.setText(board[227]);
        T228.setText(board[228]);
        T229.setText(board[229]);
        T230.setText(board[230]);
        T231.setText(board[231]);
        T232.setText(board[232]);
        T233.setText(board[233]);
        T234.setText(board[234]);
        T235.setText(board[235]);
        T236.setText(board[236]);
        T237.setText(board[237]);
        T238.setText(board[238]);
        T239.setText(board[239]);
        T240.setText(board[240]);
        T241.setText(board[241]);
        T242.setText(board[242]);
        T243.setText(board[243]);
        T244.setText(board[244]);
        T245.setText(board[245]);
        T246.setText(board[246]);
        T247.setText(board[247]);
        T248.setText(board[248]);
        T249.setText(board[249]);
        T250.setText(board[250]);
        T251.setText(board[251]);
        T252.setText(board[252]);
        T253.setText(board[253]);
        T254.setText(board[254]);
        T255.setText(board[255]);
        T256.setText(board[256]);
        T257.setText(board[257]);
        T258.setText(board[258]);
        T259.setText(board[259]);
        T260.setText(board[260]);
        T261.setText(board[261]);
        T262.setText(board[262]);
        T263.setText(board[263]);
        T264.setText(board[264]);
        T265.setText(board[265]);
        T266.setText(board[266]);
        T267.setText(board[267]);
        T268.setText(board[268]);
        T269.setText(board[269]);
        T270.setText(board[270]);
        T271.setText(board[271]);
        T272.setText(board[272]);
        T273.setText(board[273]);
        T274.setText(board[274]);
        T275.setText(board[275]);
        T276.setText(board[276]);
        T277.setText(board[277]);
        T278.setText(board[278]);
        T279.setText(board[279]);
        T280.setText(board[280]);
        T281.setText(board[281]);
        T282.setText(board[282]);
        T283.setText(board[283]);
        T284.setText(board[284]);
        T285.setText(board[285]);
        T286.setText(board[286]);
        T287.setText(board[287]);
        T288.setText(board[288]);
        T289.setText(board[289]);
        T290.setText(board[290]);
        T291.setText(board[291]);
        T292.setText(board[292]);
        T293.setText(board[293]);
        T294.setText(board[294]);
        T295.setText(board[295]);
        T296.setText(board[296]);
        T297.setText(board[297]);
        T298.setText(board[298]);
        T299.setText(board[299]);
        T300.setText(board[300]);
        T301.setText(board[301]);
        T302.setText(board[302]);
        T303.setText(board[303]);
        T304.setText(board[304]);
        T305.setText(board[305]);
        T306.setText(board[306]);
        T307.setText(board[307]);
        T308.setText(board[308]);
        T309.setText(board[309]);
        T310.setText(board[310]);
        T311.setText(board[311]);
        T312.setText(board[312]);
        T313.setText(board[313]);
        T314.setText(board[314]);
        T315.setText(board[315]);
        T316.setText(board[316]);
        T317.setText(board[317]);
        T318.setText(board[318]);
        T319.setText(board[319]);
        T320.setText(board[320]);
        T321.setText(board[321]);
        T322.setText(board[322]);
        T323.setText(board[323]);
        T324.setText(board[324]);
        T325.setText(board[325]);
        T326.setText(board[326]);
        T327.setText(board[327]);
        T328.setText(board[328]);
        T329.setText(board[329]);
        T330.setText(board[330]);
        T331.setText(board[331]);
        T332.setText(board[332]);
        T333.setText(board[333]);
        T334.setText(board[334]);
        T335.setText(board[335]);
        T336.setText(board[336]);
        T337.setText(board[337]);
        T338.setText(board[338]);
        T339.setText(board[339]);
        T340.setText(board[340]);
        T341.setText(board[341]);
        T342.setText(board[342]);
        T343.setText(board[343]);
        T344.setText(board[344]);
        T345.setText(board[345]);
        T346.setText(board[346]);
        T347.setText(board[347]);
        T348.setText(board[348]);
        T349.setText(board[349]);
        T350.setText(board[350]);
        T351.setText(board[351]);
        T352.setText(board[352]);
        T353.setText(board[353]);
        T354.setText(board[354]);
        T355.setText(board[355]);
        T356.setText(board[356]);
        T357.setText(board[357]);
        T358.setText(board[358]);
        T359.setText(board[359]);
        T360.setText(board[360]);
        T361.setText(board[361]);
        T362.setText(board[362]);
        T363.setText(board[363]);
        T364.setText(board[364]);
        T365.setText(board[365]);
        T366.setText(board[366]);
        T367.setText(board[367]);
        T368.setText(board[368]);
        T369.setText(board[369]);
        T370.setText(board[370]);
        T371.setText(board[371]);
        T372.setText(board[372]);
        T373.setText(board[373]);
        T374.setText(board[374]);
        T375.setText(board[375]);
        T376.setText(board[376]);
        T377.setText(board[377]);
        T378.setText(board[378]);
        T379.setText(board[379]);
        T380.setText(board[380]);
        T381.setText(board[381]);
        T382.setText(board[382]);
        T383.setText(board[383]);
        T384.setText(board[384]);
        T385.setText(board[385]);
        T386.setText(board[386]);
        T387.setText(board[387]);
        T388.setText(board[388]);
        T389.setText(board[389]);
        T390.setText(board[390]);
        T391.setText(board[391]);
        T392.setText(board[392]);
        T393.setText(board[393]);
        T394.setText(board[394]);
        T395.setText(board[395]);
        T396.setText(board[396]);
        T397.setText(board[397]);
        T398.setText(board[398]);
        T399.setText(board[399]);
        this.setEditable();
    }

    public String[] getBoard(){
        String[] gameBoard = new String[400];
        gameBoard[0] = T0.getText();
        gameBoard[1] = T1.getText();
        gameBoard[2] = T2.getText();
        gameBoard[3] = T3.getText();
        gameBoard[4] = T4.getText();
        gameBoard[5] = T5.getText();
        gameBoard[6] = T6.getText();
        gameBoard[7] = T7.getText();
        gameBoard[8] = T8.getText();
        gameBoard[9] = T9.getText();
        gameBoard[10] = T10.getText();
        gameBoard[11] = T11.getText();
        gameBoard[12] = T12.getText();
        gameBoard[13] = T13.getText();
        gameBoard[14] = T14.getText();
        gameBoard[15] = T15.getText();
        gameBoard[16] = T16.getText();
        gameBoard[17] = T17.getText();
        gameBoard[18] = T18.getText();
        gameBoard[19] = T19.getText();
        gameBoard[20] = T20.getText();
        gameBoard[21] = T21.getText();
        gameBoard[22] = T22.getText();
        gameBoard[23] = T23.getText();
        gameBoard[24] = T24.getText();
        gameBoard[25] = T25.getText();
        gameBoard[26] = T26.getText();
        gameBoard[27] = T27.getText();
        gameBoard[28] = T28.getText();
        gameBoard[29] = T29.getText();
        gameBoard[30] = T30.getText();
        gameBoard[31] = T31.getText();
        gameBoard[32] = T32.getText();
        gameBoard[33] = T33.getText();
        gameBoard[34] = T34.getText();
        gameBoard[35] = T35.getText();
        gameBoard[36] = T36.getText();
        gameBoard[37] = T37.getText();
        gameBoard[38] = T38.getText();
        gameBoard[39] = T39.getText();
        gameBoard[40] = T40.getText();
        gameBoard[41] = T41.getText();
        gameBoard[42] = T42.getText();
        gameBoard[43] = T43.getText();
        gameBoard[44] = T44.getText();
        gameBoard[45] = T45.getText();
        gameBoard[46] = T46.getText();
        gameBoard[47] = T47.getText();
        gameBoard[48] = T48.getText();
        gameBoard[49] = T49.getText();
        gameBoard[50] = T50.getText();
        gameBoard[51] = T51.getText();
        gameBoard[52] = T52.getText();
        gameBoard[53] = T53.getText();
        gameBoard[54] = T54.getText();
        gameBoard[55] = T55.getText();
        gameBoard[56] = T56.getText();
        gameBoard[57] = T57.getText();
        gameBoard[58] = T58.getText();
        gameBoard[59] = T59.getText();
        gameBoard[60] = T60.getText();
        gameBoard[61] = T61.getText();
        gameBoard[62] = T62.getText();
        gameBoard[63] = T63.getText();
        gameBoard[64] = T64.getText();
        gameBoard[65] = T65.getText();
        gameBoard[66] = T66.getText();
        gameBoard[67] = T67.getText();
        gameBoard[68] = T68.getText();
        gameBoard[69] = T69.getText();
        gameBoard[70] = T70.getText();
        gameBoard[71] = T71.getText();
        gameBoard[72] = T72.getText();
        gameBoard[73] = T73.getText();
        gameBoard[74] = T74.getText();
        gameBoard[75] = T75.getText();
        gameBoard[76] = T76.getText();
        gameBoard[77] = T77.getText();
        gameBoard[78] = T78.getText();
        gameBoard[79] = T79.getText();
        gameBoard[80] = T80.getText();
        gameBoard[81] = T81.getText();
        gameBoard[82] = T82.getText();
        gameBoard[83] = T83.getText();
        gameBoard[84] = T84.getText();
        gameBoard[85] = T85.getText();
        gameBoard[86] = T86.getText();
        gameBoard[87] = T87.getText();
        gameBoard[88] = T88.getText();
        gameBoard[89] = T89.getText();
        gameBoard[90] = T90.getText();
        gameBoard[91] = T91.getText();
        gameBoard[92] = T92.getText();
        gameBoard[93] = T93.getText();
        gameBoard[94] = T94.getText();
        gameBoard[95] = T95.getText();
        gameBoard[96] = T96.getText();
        gameBoard[97] = T97.getText();
        gameBoard[98] = T98.getText();
        gameBoard[99] = T99.getText();
        gameBoard[100] = T100.getText();
        gameBoard[101] = T101.getText();
        gameBoard[102] = T102.getText();
        gameBoard[103] = T103.getText();
        gameBoard[104] = T104.getText();
        gameBoard[105] = T105.getText();
        gameBoard[106] = T106.getText();
        gameBoard[107] = T107.getText();
        gameBoard[108] = T108.getText();
        gameBoard[109] = T109.getText();
        gameBoard[110] = T110.getText();
        gameBoard[111] = T111.getText();
        gameBoard[112] = T112.getText();
        gameBoard[113] = T113.getText();
        gameBoard[114] = T114.getText();
        gameBoard[115] = T115.getText();
        gameBoard[116] = T116.getText();
        gameBoard[117] = T117.getText();
        gameBoard[118] = T118.getText();
        gameBoard[119] = T119.getText();
        gameBoard[120] = T120.getText();
        gameBoard[121] = T121.getText();
        gameBoard[122] = T122.getText();
        gameBoard[123] = T123.getText();
        gameBoard[124] = T124.getText();
        gameBoard[125] = T125.getText();
        gameBoard[126] = T126.getText();
        gameBoard[127] = T127.getText();
        gameBoard[128] = T128.getText();
        gameBoard[129] = T129.getText();
        gameBoard[130] = T130.getText();
        gameBoard[131] = T131.getText();
        gameBoard[132] = T132.getText();
        gameBoard[133] = T133.getText();
        gameBoard[134] = T134.getText();
        gameBoard[135] = T135.getText();
        gameBoard[136] = T136.getText();
        gameBoard[137] = T137.getText();
        gameBoard[138] = T138.getText();
        gameBoard[139] = T139.getText();
        gameBoard[140] = T140.getText();
        gameBoard[141] = T141.getText();
        gameBoard[142] = T142.getText();
        gameBoard[143] = T143.getText();
        gameBoard[144] = T144.getText();
        gameBoard[145] = T145.getText();
        gameBoard[146] = T146.getText();
        gameBoard[147] = T147.getText();
        gameBoard[148] = T148.getText();
        gameBoard[149] = T149.getText();
        gameBoard[150] = T150.getText();
        gameBoard[151] = T151.getText();
        gameBoard[152] = T152.getText();
        gameBoard[153] = T153.getText();
        gameBoard[154] = T154.getText();
        gameBoard[155] = T155.getText();
        gameBoard[156] = T156.getText();
        gameBoard[157] = T157.getText();
        gameBoard[158] = T158.getText();
        gameBoard[159] = T159.getText();
        gameBoard[160] = T160.getText();
        gameBoard[161] = T161.getText();
        gameBoard[162] = T162.getText();
        gameBoard[163] = T163.getText();
        gameBoard[164] = T164.getText();
        gameBoard[165] = T165.getText();
        gameBoard[166] = T166.getText();
        gameBoard[167] = T167.getText();
        gameBoard[168] = T168.getText();
        gameBoard[169] = T169.getText();
        gameBoard[170] = T170.getText();
        gameBoard[171] = T171.getText();
        gameBoard[172] = T172.getText();
        gameBoard[173] = T173.getText();
        gameBoard[174] = T174.getText();
        gameBoard[175] = T175.getText();
        gameBoard[176] = T176.getText();
        gameBoard[177] = T177.getText();
        gameBoard[178] = T178.getText();
        gameBoard[179] = T179.getText();
        gameBoard[180] = T180.getText();
        gameBoard[181] = T181.getText();
        gameBoard[182] = T182.getText();
        gameBoard[183] = T183.getText();
        gameBoard[184] = T184.getText();
        gameBoard[185] = T185.getText();
        gameBoard[186] = T186.getText();
        gameBoard[187] = T187.getText();
        gameBoard[188] = T188.getText();
        gameBoard[189] = T189.getText();
        gameBoard[190] = T190.getText();
        gameBoard[191] = T191.getText();
        gameBoard[192] = T192.getText();
        gameBoard[193] = T193.getText();
        gameBoard[194] = T194.getText();
        gameBoard[195] = T195.getText();
        gameBoard[196] = T196.getText();
        gameBoard[197] = T197.getText();
        gameBoard[198] = T198.getText();
        gameBoard[199] = T199.getText();
        gameBoard[200] = T200.getText();
        gameBoard[201] = T201.getText();
        gameBoard[202] = T202.getText();
        gameBoard[203] = T203.getText();
        gameBoard[204] = T204.getText();
        gameBoard[205] = T205.getText();
        gameBoard[206] = T206.getText();
        gameBoard[207] = T207.getText();
        gameBoard[208] = T208.getText();
        gameBoard[209] = T209.getText();
        gameBoard[210] = T210.getText();
        gameBoard[211] = T211.getText();
        gameBoard[212] = T212.getText();
        gameBoard[213] = T213.getText();
        gameBoard[214] = T214.getText();
        gameBoard[215] = T215.getText();
        gameBoard[216] = T216.getText();
        gameBoard[217] = T217.getText();
        gameBoard[218] = T218.getText();
        gameBoard[219] = T219.getText();
        gameBoard[220] = T220.getText();
        gameBoard[221] = T221.getText();
        gameBoard[222] = T222.getText();
        gameBoard[223] = T223.getText();
        gameBoard[224] = T224.getText();
        gameBoard[225] = T225.getText();
        gameBoard[226] = T226.getText();
        gameBoard[227] = T227.getText();
        gameBoard[228] = T228.getText();
        gameBoard[229] = T229.getText();
        gameBoard[230] = T230.getText();
        gameBoard[231] = T231.getText();
        gameBoard[232] = T232.getText();
        gameBoard[233] = T233.getText();
        gameBoard[234] = T234.getText();
        gameBoard[235] = T235.getText();
        gameBoard[236] = T236.getText();
        gameBoard[237] = T237.getText();
        gameBoard[238] = T238.getText();
        gameBoard[239] = T239.getText();
        gameBoard[240] = T240.getText();
        gameBoard[241] = T241.getText();
        gameBoard[242] = T242.getText();
        gameBoard[243] = T243.getText();
        gameBoard[244] = T244.getText();
        gameBoard[245] = T245.getText();
        gameBoard[246] = T246.getText();
        gameBoard[247] = T247.getText();
        gameBoard[248] = T248.getText();
        gameBoard[249] = T249.getText();
        gameBoard[250] = T250.getText();
        gameBoard[251] = T251.getText();
        gameBoard[252] = T252.getText();
        gameBoard[253] = T253.getText();
        gameBoard[254] = T254.getText();
        gameBoard[255] = T255.getText();
        gameBoard[256] = T256.getText();
        gameBoard[257] = T257.getText();
        gameBoard[258] = T258.getText();
        gameBoard[259] = T259.getText();
        gameBoard[260] = T260.getText();
        gameBoard[261] = T261.getText();
        gameBoard[262] = T262.getText();
        gameBoard[263] = T263.getText();
        gameBoard[264] = T264.getText();
        gameBoard[265] = T265.getText();
        gameBoard[266] = T266.getText();
        gameBoard[267] = T267.getText();
        gameBoard[268] = T268.getText();
        gameBoard[269] = T269.getText();
        gameBoard[270] = T270.getText();
        gameBoard[271] = T271.getText();
        gameBoard[272] = T272.getText();
        gameBoard[273] = T273.getText();
        gameBoard[274] = T274.getText();
        gameBoard[275] = T275.getText();
        gameBoard[276] = T276.getText();
        gameBoard[277] = T277.getText();
        gameBoard[278] = T278.getText();
        gameBoard[279] = T279.getText();
        gameBoard[280] = T280.getText();
        gameBoard[281] = T281.getText();
        gameBoard[282] = T282.getText();
        gameBoard[283] = T283.getText();
        gameBoard[284] = T284.getText();
        gameBoard[285] = T285.getText();
        gameBoard[286] = T286.getText();
        gameBoard[287] = T287.getText();
        gameBoard[288] = T288.getText();
        gameBoard[289] = T289.getText();
        gameBoard[290] = T290.getText();
        gameBoard[291] = T291.getText();
        gameBoard[292] = T292.getText();
        gameBoard[293] = T293.getText();
        gameBoard[294] = T294.getText();
        gameBoard[295] = T295.getText();
        gameBoard[296] = T296.getText();
        gameBoard[297] = T297.getText();
        gameBoard[298] = T298.getText();
        gameBoard[299] = T299.getText();
        gameBoard[300] = T300.getText();
        gameBoard[301] = T301.getText();
        gameBoard[302] = T302.getText();
        gameBoard[303] = T303.getText();
        gameBoard[304] = T304.getText();
        gameBoard[305] = T305.getText();
        gameBoard[306] = T306.getText();
        gameBoard[307] = T307.getText();
        gameBoard[308] = T308.getText();
        gameBoard[309] = T309.getText();
        gameBoard[310] = T310.getText();
        gameBoard[311] = T311.getText();
        gameBoard[312] = T312.getText();
        gameBoard[313] = T313.getText();
        gameBoard[314] = T314.getText();
        gameBoard[315] = T315.getText();
        gameBoard[316] = T316.getText();
        gameBoard[317] = T317.getText();
        gameBoard[318] = T318.getText();
        gameBoard[319] = T319.getText();
        gameBoard[320] = T320.getText();
        gameBoard[321] = T321.getText();
        gameBoard[322] = T322.getText();
        gameBoard[323] = T323.getText();
        gameBoard[324] = T324.getText();
        gameBoard[325] = T325.getText();
        gameBoard[326] = T326.getText();
        gameBoard[327] = T327.getText();
        gameBoard[328] = T328.getText();
        gameBoard[329] = T329.getText();
        gameBoard[330] = T330.getText();
        gameBoard[331] = T331.getText();
        gameBoard[332] = T332.getText();
        gameBoard[333] = T333.getText();
        gameBoard[334] = T334.getText();
        gameBoard[335] = T335.getText();
        gameBoard[336] = T336.getText();
        gameBoard[337] = T337.getText();
        gameBoard[338] = T338.getText();
        gameBoard[339] = T39.getText();
        gameBoard[340] = T340.getText();
        gameBoard[341] = T341.getText();
        gameBoard[342] = T342.getText();
        gameBoard[343] = T343.getText();
        gameBoard[344] = T344.getText();
        gameBoard[345] = T345.getText();
        gameBoard[346] = T346.getText();
        gameBoard[347] = T347.getText();
        gameBoard[348] = T348.getText();
        gameBoard[349] = T349.getText();
        gameBoard[350] = T350.getText();
        gameBoard[351] = T351.getText();
        gameBoard[352] = T352.getText();
        gameBoard[353] = T353.getText();
        gameBoard[354] = T354.getText();
        gameBoard[355] = T355.getText();
        gameBoard[356] = T356.getText();
        gameBoard[357] = T357.getText();
        gameBoard[358] = T358.getText();
        gameBoard[359] = T359.getText();
        gameBoard[360] = T360.getText();
        gameBoard[361] = T361.getText();
        gameBoard[362] = T362.getText();
        gameBoard[363] = T363.getText();
        gameBoard[364] = T364.getText();
        gameBoard[365] = T365.getText();
        gameBoard[366] = T366.getText();
        gameBoard[367] = T367.getText();
        gameBoard[368] = T368.getText();
        gameBoard[369] = T369.getText();
        gameBoard[370] = T370.getText();
        gameBoard[371] = T371.getText();
        gameBoard[372] = T372.getText();
        gameBoard[373] = T373.getText();
        gameBoard[374] = T374.getText();
        gameBoard[375] = T375.getText();
        gameBoard[376] = T376.getText();
        gameBoard[377] = T377.getText();
        gameBoard[378] = T378.getText();
        gameBoard[379] = T379.getText();
        gameBoard[380] = T380.getText();
        gameBoard[381] = T381.getText();
        gameBoard[382] = T382.getText();
        gameBoard[383] = T383.getText();
        gameBoard[384] = T384.getText();
        gameBoard[385] = T385.getText();
        gameBoard[386] = T386.getText();
        gameBoard[387] = T387.getText();
        gameBoard[388] = T388.getText();
        gameBoard[389] = T389.getText();
        gameBoard[390] = T390.getText();
        gameBoard[391] = T391.getText();
        gameBoard[392] = T392.getText();
        gameBoard[393] = T393.getText();
        gameBoard[394] = T394.getText();
        gameBoard[395] = T395.getText();
        gameBoard[396] = T396.getText();
        gameBoard[397] = T397.getText();
        gameBoard[398] = T398.getText();
        gameBoard[399] = T399.getText();
        return gameBoard;
    }

    public boolean compare(){
        String[] newBoard = this.getBoard();
        int number = 0;
        for (int i =0;i<400;i++){
            if (!newBoard[i].equals(board[i])) {
                number++;
                index = i;
            }
        }
        if (number != 1){
            setBoard(board);
            Platform.runLater(()->{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Input Error!");
                alert.setContentText("You can only enter one letter during your turn.");
                alert.showAndWait();
            });
            return false;
        }
        else{
            board = getBoard();
            return true;
        }
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

    public void setAllReady(){
        Platform.runLater(()->{
            player1Ready.setVisible(false);
            player2Ready.setVisible(false);
            player3Ready.setVisible(false);
            player4Ready.setVisible(false);
        });
    }

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
    /*Liping score here
   Getting vertical score selection bug
     */
    private void confirm() {
        if (Game.turn){
            Platform.runLater(()->{
                Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
                alert.setTitle("Confirmation");
                alert.setHeaderText("Please choose a direction for the word to be submitted:");
                alert.setContentText("Choose your option.");
                ButtonType buttonTypeH = new ButtonType("Horizontal");
                ButtonType buttonTypeV = new ButtonType("Vertical");
                ButtonType buttonTypeCancel = new ButtonType("Let me think", ButtonBar.ButtonData.CANCEL_CLOSE);
                alert.getButtonTypes().setAll(buttonTypeH, buttonTypeV, buttonTypeCancel);
                Optional<ButtonType> result = alert.showAndWait();
                String word =null;
                String inputRegex = "^[a-zA-Z]{1}$";
                // user chose "Horizontal"
                if (result.get() == buttonTypeH){
                    if (compare() == true){
                        if (!getBoard()[index].matches(inputRegex)){
                            Platform.runLater(()->{
                                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                alert1.setHeaderText("Input Error!");
                                alert1.setContentText("You can only enter one letter in the box.");
                                alert1.showAndWait();
                            });
                        } else {
                            word = Game.horizontal(index,getBoard());
                            Game.sendCharacter(index,getBoard()[index].toUpperCase(),word);
                        }
                    }
                    // user chose "Vertical"
                    //check error here
                } else if (result.get() == buttonTypeV) {
                    if (compare() == true){
                        if (!getBoard()[index].matches(inputRegex)){
                            Platform.runLater(()->{
                                Alert alert1 = new Alert(Alert.AlertType.ERROR);
                                alert1.setHeaderText("Input Error!");
                                alert1.setContentText("You can only enter one letter in the box.");
                                alert1.showAndWait();
                            });
                        } else {
                            word = Game.vertical(index,getBoard());
                            Game.sendCharacter(index,getBoard()[index].toUpperCase(),word);
                        }
                        //word = Game.vertical(index,getBoard());
                        //Game.sendCharacter(index,getBoard()[index].toUpperCase(),word);
                    }
                }
            });
        } else {
            Platform.runLater(()->{
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
            Game.pass();
        }else {
            Platform.runLater(()->{
                Alert alert = new Alert(Alert.AlertType.ERROR);
                alert.setHeaderText("Not your turn!");
                alert.setContentText("Please wait for others...");
                alert.showAndWait();
            });
        }
    }

    public void voting(String name,String word){
        Platform.runLater(()->{
            Alert alert1 = new Alert(Alert.AlertType.CONFIRMATION);
            alert1.setTitle("Voting Confirmation");
            alert1.setHeaderText("Do you want to vote for this word ?");
            alert1.setContentText("Do you think this is a word ?");
            ButtonType buttonyes = new ButtonType("Yes");
            ButtonType buttonno = new ButtonType("No");
            alert1.getButtonTypes().setAll(buttonyes,buttonno);
            Optional<ButtonType> result1 = alert1.showAndWait();
            //add condition to not allow self voting by the player
            if(result1.get()==buttonyes) {
                Game.voting(true,name,word);
            }
            else if(result1.get()==buttonno) {
                Game.voting(false,name,word);
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

}



