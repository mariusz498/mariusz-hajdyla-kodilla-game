package com.kodilla.game;
import com.kodilla.game.creatures.*;
import com.kodilla.game.engine.*;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.net.URL;

public class ElementalClash extends Application {

    private static ImageView generateBuildingImage(String source){
        URL url = ElementalClash.class.getClassLoader().getResource(source);
        Image image = new Image(String.valueOf(url));
        ImageView imgView = new ImageView(image);
        imgView.setFitHeight(180);
        imgView.setPreserveRatio(true);
        return imgView;
    }

    private static ImageView generateCreatureImage(String source){
        URL url = ElementalClash.class.getClassLoader().getResource(source);
        Image image = new Image(String.valueOf(url));
        ImageView imgView = new ImageView(image);
        imgView.setFitHeight(130);
        imgView.setPreserveRatio(true);
        return imgView;
    }

    private Creature chosenCreature;

    private static Creature chooseCreature(Creature creature){
        System.out.println("Wybrano stwora: " + creature.getName());
        return creature;
    }

    private static void createCreature(int place, Creature creature, FlowPane pane, OccupationChecker checker) {
        ImageView creatureImg = generateCreatureImage(creature.getSource());
        pane.getChildren().add(place, creatureImg);
        pane.getChildren().remove(place+1);
        checker.occupy(place);

        System.out.println("Umieszczono stwora: " + creature.getName());
    }

    private URL backgroundUrl = ElementalClash.class.getClassLoader().getResource("pics/background.jpg");

    private Image backgroundImg = new Image(String.valueOf(backgroundUrl));
    private FlowPane myBuildings = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane AIBuildings = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane AIBattlefield = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane myBattlefield = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane putButtons = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane buyButtons = new FlowPane(Orientation.HORIZONTAL);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(1.0, 1.0, true, true, false, false);
        BackgroundImage backgroundImage = new BackgroundImage(backgroundImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_CENTER);
        grid.setPadding(new Insets(12.0, 12.0, 12.0, 12.0));
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setBackground(background);

        ImageView lavaGroundImg = generateBuildingImage("pics/lavaGround.jpg");
        ImageView fireCaveImg = generateBuildingImage("pics/fireCave.jpg");
        ImageView fireTreeImg = generateBuildingImage("pics/fireTree2.png");
        ImageView geyserImg = generateBuildingImage("pics/geyser.jpg");
        ImageView roughSeaImg = generateBuildingImage("pics/roughSea.jpg");
        ImageView underwaterCaveImg = generateBuildingImage("pics/underwaterCave.jpg");
        ImageView underwaterKingdomImg = generateBuildingImage("pics/underwaterKingdom.jpg");
        ImageView volcanoImg = generateBuildingImage("pics/volcano.jpg");

        myBuildings.setHgap(10);
        myBuildings.setPrefWrapLength(1366);
        myBuildings.setAlignment(Pos.BOTTOM_CENTER);
        myBuildings.getChildren().add(lavaGroundImg);
        myBuildings.getChildren().add(fireTreeImg);
        myBuildings.getChildren().add(volcanoImg);
        myBuildings.getChildren().add(fireCaveImg);


        AIBuildings.setHgap(10);
        AIBuildings.setPrefWrapLength(1366);
        AIBuildings.setAlignment(Pos.TOP_CENTER);
        AIBuildings.getChildren().add(roughSeaImg);
        AIBuildings.getChildren().add(geyserImg);
        AIBuildings.getChildren().add(underwaterKingdomImg);
        AIBuildings.getChildren().add(underwaterCaveImg);

        AIBattlefield.setHgap(10);
        AIBattlefield.setPrefWrapLength(1366);
        AIBattlefield.setAlignment(Pos.CENTER);
        for(int i = 0; i < 6; i++) {
            AIBattlefield.getChildren().add(generateCreatureImage("pics/creaturePlaceholder.png"));
        }

        myBattlefield.setHgap(10);
        myBattlefield.setPrefWrapLength(1366);
        myBattlefield.setAlignment(Pos.CENTER);
        for(int i = 0; i < 6; i++) {
            myBattlefield.getChildren().add(generateCreatureImage("pics/creaturePlaceholder.png"));
        }

        OccupationChecker playerChecker = new OccupationChecker();
        OccupationChecker AIChecker = new OccupationChecker();

        //TODO dodanie pozostałych przycisków kupowania

        Button buy1 = new Button();
        buy1.setPrefWidth(180.0);
        buy1.setText("Kup: (5/3)\nKoszt: 3 many");
        buy1.setOnAction((e) -> {
            //TODO: sprawdzanie, czy można kupić stwora w if().
            if (true) {
                chosenCreature = chooseCreature(new FireWolf());
            }
        }
        );

        buyButtons.setPrefWrapLength(1366);
        buyButtons.setAlignment(Pos.CENTER);
        buyButtons.setHgap(50);
        buyButtons.getChildren().add(buy1);

        Button put1 = new Button();
        put1.setPrefWidth(110.0);
        put1.setText("Umieść");
        put1.setOnAction((e) -> {
            if(!playerChecker.isOccupied(0)) {
                if (chosenCreature != null) {
                    createCreature(0, chosenCreature, myBattlefield, playerChecker);
                    chosenCreature = null;
                } else {
                    System.out.println("Nie wybrano stwora!");
                }
            }
            else{
                System.out.println("Miejsce zajęte!");
            }
        });


        Button put2 = new Button();
        put2.setPrefWidth(110.0);
        put2.setText("Umieść");
        put2.setOnAction((e) -> {
            if(!playerChecker.isOccupied(1)) {
                if (chosenCreature != null) {
                    createCreature(1, chosenCreature, myBattlefield, playerChecker);
                    chosenCreature = null;
                } else {
                    System.out.println("Nie wybrano stwora!");
                }
            }
            else{
                System.out.println("Miejsce zajęte!");
            }
        });

        Button put3 = new Button();
        put3.setPrefWidth(110.0);
        put3.setText("Umieść");
        put3.setOnAction((e) -> {
            if(!playerChecker.isOccupied(2)) {
                if (chosenCreature != null) {
                    createCreature(2, chosenCreature, myBattlefield, playerChecker);
                    chosenCreature = null;
                } else {
                    System.out.println("Nie wybrano stwora!");
                }
            }
            else{
                System.out.println("Miejsce zajęte!");
            }
        });

        Button put4 = new Button();
        put4.setPrefWidth(110.0);
        put4.setText("Umieść");
        put4.setOnAction((e) -> {
            if(!playerChecker.isOccupied(3)) {
                if (chosenCreature != null) {
                    createCreature(3, chosenCreature, myBattlefield, playerChecker);
                    chosenCreature = null;
                } else {
                    System.out.println("Nie wybrano stwora!");
                }
            }
            else{
                System.out.println("Miejsce zajęte!");
            }
        });

        Button put5 = new Button();
        put5.setPrefWidth(110.0);
        put5.setText("Umieść");
        put5.setOnAction((e) -> {
            if(!playerChecker.isOccupied(4)) {
                if (chosenCreature != null) {
                    createCreature(4, chosenCreature, myBattlefield, playerChecker);
                    chosenCreature = null;
                } else {
                    System.out.println("Nie wybrano stwora!");
                }
            }
            else{
                System.out.println("Miejsce zajęte!");
            }
        });

        Button put6 = new Button();
        put6.setPrefWidth(110.0);
        put6.setText("Umieść");
        put6.setOnAction((e) -> {
            if(!playerChecker.isOccupied(5)) {
                if (chosenCreature != null) {
                    createCreature(5, chosenCreature, myBattlefield, playerChecker);
                    chosenCreature = null;
                } else {
                    System.out.println("Nie wybrano stwora!");
                }
            }
            else{
                System.out.println("Miejsce zajęte!");
            }
        });

        putButtons.setPrefWrapLength(1366);
        putButtons.setAlignment(Pos.CENTER);
        putButtons.setHgap(10);
        putButtons.getChildren().add(put1);
        putButtons.getChildren().add(put2);
        putButtons.getChildren().add(put3);
        putButtons.getChildren().add(put4);
        putButtons.getChildren().add(put5);
        putButtons.getChildren().add(put6);

        grid.add(AIBuildings, 0,0,1,1);
        grid.add(AIBattlefield, 0,1,1,1);
        grid.add(myBattlefield,0,2,1,1);
        grid.add(putButtons,0,3,1,1);
        grid.add(myBuildings, 0, 4, 1, 1);
        grid.add(buyButtons,0,5,1,1);


        Scene scene = new Scene(grid, 1366, 768, Color.BLACK);

        primaryStage.setTitle("ElementalClash");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
