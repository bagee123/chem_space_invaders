package com.example.chem_project;


import javafx.animation.Animation;
import javafx.animation.AnimationTimer;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import javafx.util.Duration;


import java.io.*;
import java.net.URISyntaxException;
import java.util.*;


public class game extends Application {
    private Stage theStage;
    private Pane parent;
    private Rectangle player;
    private List<Rectangle> enemies;
    private boolean movingRight = true;
    private Label timerLabel;
    private Timeline timeline;
    private int milliSecondsElapsed;
    boolean shouldCreateEnemy = true;
    long lastCircleTime = 0;
    private List<String> dataList;


    int playerSpeed = 0;
    int oppSpeed = 0;


    String playerSpeedSetting = "";


    String oppSpeedSetting = "";


    int setting = 0;
    final long delay = 500_000_000;
    private long lastTime = 0;


    private AnimationTimer timer;


    @Override
    public void start(Stage stage) throws Exception {
        theStage = stage;
        settingScreen();
    }


    private void restartGame() throws URISyntaxException {
        if(setting == 1){
            player.setTranslateX(0);
        }else if(setting == 2) {
            player.setTranslateX(0);
        }else if(setting == 3) {
            player.setTranslateX(0);
        }else if(setting == 4) {
            player.setTranslateX(730);
        }else if(setting == 5) {
            player.setTranslateX(480);
        }


        if (Objects.equals(playerSpeedSetting, "slow")) {
            playerSpeed = 3;
        }else if (Objects.equals(playerSpeedSetting, "mid")){
            playerSpeed = 6;
        }else if (Objects.equals(playerSpeedSetting, "fast")){
            playerSpeed = 9;
        }


        if(Objects.equals(oppSpeedSetting, "slow")){
            oppSpeed = 3;
        }else if(Objects.equals(oppSpeedSetting, "mid")){
            oppSpeed = 6;
        }else if(Objects.equals(oppSpeedSetting, "fast")) {
            oppSpeed = 9;
        }
        //System.out.println(Objects.requireNonNull(getClass().getResource("database1.txt")).toURI().toString());
        //File file = new File("C:\\Users\\z_may\\IdeaProjects\\Chem project\\src\\main\\resources\\com\\example\\chem_project\\database1.txt");
        //System.out.println(file.exists());
        player.setTranslateY(805);
        movingRight = true;
        milliSecondsElapsed = 0;
        timerLabel.setText("00:00:000");
        enemies.forEach(enemy -> parent.getChildren().remove(enemy));
        enemies.clear();
    }




    private void settingScreen(){
        VBox vBox = new VBox();
        vBox.setPrefWidth(800);
        vBox.setPrefHeight(800);
        Scene scene = new Scene(vBox);
        vBox.setAlignment(Pos.CENTER);
        vBox.setSpacing(5);
        ToggleGroup toggleGroup = new ToggleGroup();
        ToggleGroup playerSpeedToggle = new ToggleGroup();
        ToggleGroup oppSpeedToggle = new ToggleGroup();
        Label gameName = new Label("Chem Project");
        gameName.setStyle("-fx-font-size: 60px");
        Label settings = new Label("Settings");
        settings.setStyle("-fx-font-size: 40px");
        Label speed = new Label("Speed");
        speed.setStyle("-fx-font-size: 40px");
        RadioButton rightQuarterRadio = new RadioButton("Right Quarter Screen");
        RadioButton rightHalfRadio = new RadioButton("Right Half Screen");
        RadioButton quarterRadio = new RadioButton("Left Quarter Screen");
        RadioButton halfRadio = new RadioButton("Left Half Screen");
        RadioButton fullRadio = new RadioButton("Full Screen");

        RadioButton playerSlow = new RadioButton("Player Slow");
        RadioButton playerMid = new RadioButton("Player Mid");
        RadioButton playerFast = new RadioButton("Player Fast");

        RadioButton oppSlow = new RadioButton("Opp Slow");
        RadioButton oppMid = new RadioButton("Opp Mid");
        RadioButton oppFast = new RadioButton("Opp Fast");


        quarterRadio.setToggleGroup(toggleGroup);
        halfRadio.setToggleGroup(toggleGroup);
        fullRadio.setToggleGroup(toggleGroup);
        rightQuarterRadio.setToggleGroup(toggleGroup);
        rightHalfRadio.setToggleGroup(toggleGroup);

        playerSlow.setToggleGroup(playerSpeedToggle);
        playerMid.setToggleGroup(playerSpeedToggle);
        playerFast.setToggleGroup(playerSpeedToggle);

        oppSlow.setToggleGroup(oppSpeedToggle);
        oppMid.setToggleGroup(oppSpeedToggle);
        oppFast.setToggleGroup(oppSpeedToggle);

        quarterRadio.setPadding(new Insets(0,0,0,17));
        Button startBtn = new Button("Start Game");


        startBtn.setOnAction( e->{
            if(!(quarterRadio.isSelected() || halfRadio.isSelected() || fullRadio.isSelected() || rightQuarterRadio.isSelected() || rightHalfRadio.isSelected())){
                System.out.println("select a setting");
            }else if(quarterRadio.isSelected()) {
                setting = 1;
                startGame();
            }else if(halfRadio.isSelected()) {
                setting = 2;
                startGame();
            }else if(fullRadio.isSelected()) {
                setting = 3;
                startGame();
            }else if(rightQuarterRadio.isSelected()) {
                setting = 4;
                startGame();
            }else if(rightHalfRadio.isSelected()) {
                setting = 5;
                startGame();
            }


            if(playerSlow.isSelected()){
                playerSpeedSetting = "slow";
                playerSpeed = 3;
            }else if(playerMid.isSelected()){
                playerSpeedSetting = "mid";
                playerSpeed = 6;
            }else if(playerFast.isSelected()){
                playerSpeedSetting = "fast";
                playerSpeed = 9;
            }


            if(oppSlow.isSelected()){
                oppSpeedSetting = "slow";
                oppSpeed = 3;
            }else if(oppMid.isSelected()){
                oppSpeedSetting = "mid";
                oppSpeed = 6;
            }else if(oppFast.isSelected()){
                oppSpeedSetting = "fast";
                oppSpeed = 9;
            }




        });


        vBox.getChildren().addAll(gameName,settings,quarterRadio,halfRadio,
                rightQuarterRadio,rightHalfRadio,fullRadio,speed,playerSlow,playerMid,playerFast,
                oppSlow,oppMid,oppFast,startBtn);


        theStage.setScene(scene);
        theStage.show();
    }


    private void stopGame() {
        if (timeline != null) {
            timeline.stop();
        }


        if (timer != null) {
            timer.stop();
        }
        // Stop other timers if any
        // Clear game state
        milliSecondsElapsed = 0;
        player.setTranslateX(0);
        player.setTranslateY(805);
        movingRight = true;
        enemies.forEach(enemy -> parent.getChildren().remove(enemy));
        enemies.clear();
    }




    private void startGame(){
        parent = new Pane();
        player = new Rectangle(20, 20);
        enemies = new ArrayList<>();
        //dataList = new ArrayList<>();
        timerLabel = new Label("00:00:000");
        timerLabel.setStyle("-fx-font-size: 40px");


        timeline = new Timeline(new KeyFrame(Duration.seconds(.01), e -> {
            milliSecondsElapsed++;
            int minutes = (milliSecondsElapsed / 6000) % 60;
            int seconds = (milliSecondsElapsed / 100) % 60;
            int hundredths = milliSecondsElapsed % 100;
            timerLabel.setText(String.format("%02d:%02d:%02d", minutes, seconds, hundredths));
        }));


        timeline.setCycleCount(Animation.INDEFINITE);
        timeline.play();
        timer = new AnimationTimer() {
            @Override
            public void handle(long l) {


                if (l - lastCircleTime > delay) {
                    shouldCreateEnemy = true;
                    lastCircleTime = l;
                }
                if(setting == 1){
                    if (movingRight) {
                        player.setTranslateX(player.getTranslateX() + playerSpeed);
                        if (player.getTranslateX() >= 245) {
                            player.setTranslateX(245);
                            movingRight = false;
                        }
                    } else {
                        player.setTranslateX(player.getTranslateX() - playerSpeed);
                        if (player.getTranslateX() <= 0) {
                            player.setTranslateX(0);
                            movingRight = true;
                        }
                    }
                }else if(setting == 2){
                    if (movingRight) {
                        player.setTranslateX(player.getTranslateX() + playerSpeed);
                        if (player.getTranslateX() >= 490) {
                            player.setTranslateX(490);
                            movingRight = false;
                        }
                    } else {
                        player.setTranslateX(player.getTranslateX() - playerSpeed);
                        if (player.getTranslateX() <= 0) {
                            player.setTranslateX(0);
                            movingRight = true;
                        }
                    }
                }else if(setting == 3){
                    if (movingRight) {
                        player.setTranslateX(player.getTranslateX() + playerSpeed);
                        if (player.getTranslateX() >= 980) {
                            player.setTranslateX(980);
                            movingRight = false;
                        }
                    } else {
                        player.setTranslateX(player.getTranslateX() - playerSpeed);
                        if (player.getTranslateX() <= 0) {
                            player.setTranslateX(0);
                            movingRight = true;
                        }
                    }
                }else if(setting == 4){
                    if (movingRight) {
                        player.setTranslateX(player.getTranslateX() + playerSpeed);
                        if (player.getTranslateX() >= 980) {
                            player.setTranslateX(980);
                            movingRight = false;
                        }
                    } else {
                        player.setTranslateX(player.getTranslateX() - playerSpeed);
                        if (player.getTranslateX() <= 730) {
                            player.setTranslateX(730);
                            movingRight = true;
                        }
                    }
                }else if(setting == 5){
                    if (movingRight) {
                        player.setTranslateX(player.getTranslateX() + playerSpeed);
                        if (player.getTranslateX() >= 980) {
                            player.setTranslateX(980);
                            movingRight = false;
                        }
                    } else {
                        player.setTranslateX(player.getTranslateX() - playerSpeed);
                        if (player.getTranslateX() <= 480) {
                            player.setTranslateX(480);
                            movingRight = true;
                        }
                    }
                }


                // Handle falling enemies
                if (shouldCreateEnemy && enemies.size() < 20) {
                    // Adjust the range of random values for x-coordinate
                    Rectangle enemy = new Rectangle(new Random().nextInt(1001), 0, 20, 20);
                    enemy.setFill(Color.RED);
                    enemy.setUserData(oppSpeed);
                    enemies.add(enemy);
                    parent.getChildren().add(enemy);
                    shouldCreateEnemy = false;


                }
                try {
                    Iterator<Rectangle> iterator = enemies.iterator();
                    while (iterator.hasNext()) {
                        Rectangle enemy = iterator.next();
                        int speed = (int) enemy.getUserData(); // Get the speed for this circle
                        enemy.setTranslateY(enemy.getTranslateY() + speed);


                        if (enemy.getTranslateY() > 1000) {
                            iterator.remove();
                            parent.getChildren().remove(enemy);
                        } else if (enemy.getBoundsInParent().intersects(player.getBoundsInParent())) {


                           /*if (setting == 1){
                               System.out.println(timerLabel.getText());
                               appendToLogFile(timerLabel.getText(),"C:\\Users\\z_may\\IdeaProjects\\Chem project\\src\\main\\resources\\com\\example\\chem_project\\leftQuarter.txt");
                               //dataList.add(timerLabel.getText());
                           } else if (setting == 2) {
                               System.out.println(timerLabel.getText());
                               appendToLogFile(timerLabel.getText(),FILEPATH);
                           } else if (setting == 3) {
                               System.out.println(timerLabel.getText());
                               appendToLogFile(timerLabel.getText(),FILEPATH);
                           }else if (setting == 4) {
                               System.out.println(timerLabel.getText());
                               appendToLogFile(timerLabel.getText(),FILEPATH);
                           }else if (setting == 5) {
                               System.out.println(timerLabel.getText());
                               appendToLogFile(timerLabel.getText(), FILEPATH);
                           }


                            */


                            if(Objects.equals(playerSpeedSetting, "slow")){
                                if(Objects.equals(oppSpeedSetting, "slow")){
                                    System.out.println(timerLabel.getText());
                                    //appendToLogFile(timerLabel.getText(), FILEPATH);
                                }else if(Objects.equals(oppSpeedSetting, "mid")){
                                    System.out.println(timerLabel.getText());
                                    // appendToLogFile(timerLabel.getText(), FILEPATH );
                                }else if(Objects.equals(oppSpeedSetting, "fast")){
                                    System.out.println(timerLabel.getText());
                                    // appendToLogFile(timerLabel.getText(), FILEPATH );
                                }
                            }else if(Objects.equals(playerSpeedSetting, "mid")){
                                if(Objects.equals(oppSpeedSetting, "slow")){
                                    System.out.println(timerLabel.getText());
                                    // appendToLogFile(timerLabel.getText(), FILEPATH );
                                }else if(Objects.equals(oppSpeedSetting, "mid")){
                                    System.out.println(timerLabel.getText());
                                    // appendToLogFile(timerLabel.getText(), FILEPATH );
                                }else if(Objects.equals(oppSpeedSetting, "fast")){
                                    System.out.println(timerLabel.getText());
                                    //appendToLogFile(timerLabel.getText(), FILEPATH );
                                }
                            }else if(Objects.equals(playerSpeedSetting, "fast")){
                                if(Objects.equals(oppSpeedSetting, "slow")){
                                    System.out.println(timerLabel.getText());
                                    // appendToLogFile(timerLabel.getText(), FILEPATH );
                                }else if(Objects.equals(oppSpeedSetting, "mid")){
                                    System.out.println(timerLabel.getText());
                                    // appendToLogFile(timerLabel.getText(), FILEPATH );
                                }else if(Objects.equals(oppSpeedSetting, "fast")){
                                    System.out.println(timerLabel.getText());
                                    // appendToLogFile(timerLabel.getText(), FILEPATH );
                                }
                            }
                            restartGame();
                        }
                    }
                }catch (Exception ex){


                }
            }
        };


        timer.start();
        parent.setPrefWidth(1000);
        parent.setPrefHeight(1000);
        parent.getChildren().add(player);
        parent.getChildren().add(timerLabel);
        if(setting == 1){
            player.setTranslateX(0);
        }else if(setting == 2) {
            player.setTranslateX(0);
        }else if(setting == 3) {
            player.setTranslateX(0);
        }else if(setting == 4) {
            player.setTranslateX(730);
        }else if(setting == 5) {
            player.setTranslateX(480);
        }


        if (Objects.equals(playerSpeedSetting, "slow")) {
            playerSpeed = 3;
        }else if (Objects.equals(playerSpeedSetting, "mid")){
            playerSpeed = 6;
        }else if (Objects.equals(playerSpeedSetting, "fast")){
            playerSpeed = 9;
        }


        if(Objects.equals(oppSpeedSetting, "slow")){
            oppSpeed = 3;
        }else if(Objects.equals(oppSpeedSetting, "mid")){
            oppSpeed = 6;
        }else if(Objects.equals(oppSpeedSetting, "fast")) {
            oppSpeed = 9;
        }
        player.setTranslateY(805);
        timerLabel.setTranslateX(430);
        timerLabel.setTranslateY(50);
        Scene scene = new Scene(parent);
        scene.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent keyEvent) {
                if (keyEvent.getCode() == KeyCode.B){
                    stopGame();
                    settingScreen();
                }
            }
        });
        theStage.setTitle("Chem");
        theStage.setScene(scene);
        theStage.show();
    }
    private void appendToLogFile(String message,String filename) {
        try {
            FileWriter fw = new FileWriter(filename, true);
            fw.write(message);
            fw.write("\n");
            fw.close();
        }
        catch(IOException e) {
        }
    }
    public static void main(String[] args) {
        launch();
    }
}

