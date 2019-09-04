package com.kodilla.game;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

public class ElementalClash extends Application {

    private Image backgroundImg = new Image("file:D:\\Coding\\mariusz-hajdyla-kodilla-game\\src\\main\\resources\\pics\\background.jpg");
    private Image lavaGround = new Image("file:D:\\Coding\\mariusz-hajdyla-kodilla-game\\src\\main\\resources\\pics\\lavaGround.jpg");
    private FlowPane objects = new FlowPane(Orientation.HORIZONTAL);

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {

        BackgroundSize backgroundSize = new BackgroundSize(100 , 100, true, true, true, false);
        BackgroundImage backgroundImage = new BackgroundImage(backgroundImg, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.CENTER, backgroundSize);
        Background background = new Background(backgroundImage);

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER);
        grid.setPadding(new Insets(11.5, 12.5, 13.5, 14.5));
        grid.setHgap(5.5);
        grid.setVgap(5.5);
        grid.setBackground(background);

        ImageView img = new ImageView(lavaGround);
        objects.getChildren().add(img);

        grid.add(objects, 0, 0, 3, 1);

        Scene scene = new Scene(grid, 1920, 1080, Color.BLACK);

        primaryStage.setTitle("ElementalClash");
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
