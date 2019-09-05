package com.kodilla.game;
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

        ImageView placeholder = generateCreatureImage("pics/creaturePlaceholder.png");
        ImageView placeholder2 = generateCreatureImage("pics/creaturePlaceholder.png");
        ImageView fireWolfImg = generateCreatureImage("pics/fireWolf.jpg");

        AIBattlefield.setHgap(10);
        AIBattlefield.setPrefWrapLength(1366);
        AIBattlefield.setAlignment(Pos.CENTER);
        AIBattlefield.getChildren().add(placeholder);

        myBattlefield.setHgap(10);
        myBattlefield.setPrefWrapLength(1366);
        myBattlefield.setAlignment(Pos.CENTER);
        myBattlefield.getChildren().add(placeholder2);

        Button put1 = new Button();
        put1.setText("Umieść");
        put1.setOnAction((e) -> {
            if (true) {
                myBattlefield.getChildren().remove(placeholder2);
                myBattlefield.getChildren().add(fireWolfImg);
                System.out.println("umieszczono stwora");
            }
        });
        Button put2 = new Button();
        put2.setText("Umieść");
        put2.setOnAction((e) -> {
            if (true) {
                System.out.println("umieszczono stwora");
            }
        });
        Button put3 = new Button();
        put3.setText("Umieść");
        put3.setOnAction((e) -> {
            if (true) {
                System.out.println("umieszczono stwora");
            }
        });
        Button put4 = new Button();
        put4.setText("Umieść");
        put4.setOnAction((e) -> {
            if (true) {
                System.out.println("umieszczono stwora");
            }
        });
        Button put5 = new Button();
        put5.setText("Umieść");
        put5.setOnAction((e) -> {
            if (true) {
                System.out.println("umieszczono stwora");
            }
        });
        Button put6 = new Button();
        put6.setText("Umieść");
        put6.setOnAction((e) -> {
            if (true) {
                System.out.println("umieszczono stwora");
            }
        });

        putButtons.setPrefWrapLength(1366);
        putButtons.setAlignment(Pos.CENTER);
        putButtons.setHgap(50);
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
