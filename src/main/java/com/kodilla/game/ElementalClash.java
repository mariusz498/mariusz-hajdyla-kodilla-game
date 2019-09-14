package com.kodilla.game;
import com.kodilla.game.creatures.*;
import com.kodilla.game.engine.*;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import java.net.URL;

public class ElementalClash extends Application {

    private static void sleeper(int millis){
        try {
            Thread.sleep(millis);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
    }

    private static ImageView generateBuildingImage(String source){
        URL url = ElementalClash.class.getClassLoader().getResource(source);
        Image image = new Image(String.valueOf(url));
        ImageView imgView = new ImageView(image);
        imgView.setFitHeight(170);
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

    public static OccupationChecker playerChecker = new OccupationChecker();
    public static OccupationChecker AIChecker = new OccupationChecker();

    public static void createCreature(Player player, int place, Creature creature, FlowPane pane, FlowPane statsPane, OccupationChecker checker) {
        ImageView creatureImg = generateCreatureImage(creature.getSource());
        pane.getChildren().add(place, creatureImg);
        pane.getChildren().remove(place+1);
        checker.occupy(place);
        creature.payManaCost(player);
        player.addCreature(place, creature);
        statsPane.getChildren().add(place, creature.getLabel());
        statsPane.getChildren().remove(place+1);

        System.out.println("Umieszczono stwora: " + creature.getName());
    }

    private static void killCreature(Player player, int place, Creature creature, FlowPane pane, FlowPane statsPane, OccupationChecker checker) {
        ImageView placeholderImg = generateCreatureImage("pics/creaturePlaceholder.png");
        pane.getChildren().add(place, placeholderImg);
        pane.getChildren().remove(place+1);
        checker.remove(place);
        player.removeCreature(place);
        System.out.println(creature.getName() + " poległ(-a)!");
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Arial", 20));
        label.setTextFill(Color.web("#FFF"));
        label.setText(" ");
        label.setPrefWidth(110.0);
        statsPane.getChildren().add(place, label);
        statsPane.getChildren().remove(place+1);
    }

    public static void processMyAttacks(Player attacking, Player opponent) {
        statusLabel.setText("Faza ataku");
        int i = 0;
        for (attacking.checkCreature(i); i < 6; i++) {
            if (attacking.checkCreature(i) != null) {
                if (opponent.checkCreature(i) != null) {
                    opponent.checkCreature(i).setCurrentHealth(opponent.checkCreature(i).getCurrentHealth() - attacking.checkCreature(i).getPower());
                    AICreaturesStats.getChildren().add(i, opponent.checkCreature(i).getLabel());
                    AICreaturesStats.getChildren().remove(i+1);
                    if (opponent.checkCreature(i).getCurrentHealth() <= 0) {
                        ElementalClash.killCreature(opponent, i, opponent.checkCreature(i), AIBattlefield, AICreaturesStats, AIChecker);
                    }
                } else {
                    opponent.setCurrentLife(opponent.getCurrentLife() - attacking.checkCreature(i).getPower());
                    opponent.lifeLbl.setText("Życie: " + opponent.getCurrentLife());
                }
                System.out.println(attacking.checkCreature(i).getName() + " zaatakował(-a)!");
                if (opponent.getCurrentLife() <= 0) {
                    statusLabel.setText("Wygrałeś!");
                }
                sleeper(1000);
            }
        }
        statusLabel.setText("Tura komputera");
    }

    private void processAIAttacks(Player attacking, Player opponent) {
        statusLabel.setText("Komputer atakuje");
        int i = 0;
        for (attacking.checkCreature(i); i < 6; i++) {
            if (attacking.checkCreature(i) != null) {
                if (opponent.checkCreature(i) != null) {
                    opponent.checkCreature(i).setCurrentHealth(opponent.checkCreature(i).getCurrentHealth() - attacking.checkCreature(i).getPower());
                    myCreaturesStats.getChildren().add(i, opponent.checkCreature(i).getLabel());
                    myCreaturesStats.getChildren().remove(i+1);
                    if (opponent.checkCreature(i).getCurrentHealth() <= 0) {
                        ElementalClash.killCreature(opponent, i, opponent.checkCreature(i), myBattlefield, myCreaturesStats, playerChecker);
                    }
                } else {
                    opponent.setCurrentLife(opponent.getCurrentLife() - attacking.checkCreature(i).getPower());
                    opponent.lifeLbl.setText("Życie: " + opponent.getCurrentLife());
                }
            }
            if (opponent.getCurrentLife() <= 0) {
                statusLabel.setText("Przegrałeś!");
            }
            sleeper(500);
        }
    }

    private URL backgroundUrl = ElementalClash.class.getClassLoader().getResource("pics/background.jpg");

    private Image backgroundImg = new Image(String.valueOf(backgroundUrl));
    private FlowPane computerStats = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane playerStats = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane myBuildings = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane AIBuildings = new FlowPane(Orientation.HORIZONTAL);
    private static FlowPane AICreaturesStats = new FlowPane(Orientation.HORIZONTAL);
    private static FlowPane myCreaturesStats = new FlowPane(Orientation.HORIZONTAL);
    private static FlowPane AIBattlefield = new FlowPane(Orientation.HORIZONTAL);
    private static FlowPane myBattlefield = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane putButtons = new FlowPane(Orientation.HORIZONTAL);
    private FlowPane buyButtons = new FlowPane(Orientation.HORIZONTAL);

    private int boardWidth = 900;

    private Label computerLbl = new Label();
    private Label computerManaLbl = new Label();
    private static Label statusLabel = new Label();

    private Label playerLbl = new Label();
    private Label playerManaLbl = new Label();

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

        computerStats.setBackground(new Background(new BackgroundFill(new Color(0.66,0.66,0.66,0.8), CornerRadii.EMPTY, Insets.EMPTY)));
        playerStats.setBackground(new Background(new BackgroundFill(new Color(0.66,0.66,0.66,0.8), CornerRadii.EMPTY, Insets.EMPTY)));

        ImageView lavaGroundImg = generateBuildingImage("pics/lavaGround.jpg");
        ImageView fireCaveImg = generateBuildingImage("pics/fireCave.jpg");
        ImageView fireTreeImg = generateBuildingImage("pics/fireTree2.png");
        ImageView geyserImg = generateBuildingImage("pics/geyser.jpg");
        ImageView roughSeaImg = generateBuildingImage("pics/roughSea.jpg");
        ImageView underwaterCaveImg = generateBuildingImage("pics/underwaterCave.jpg");
        ImageView underwaterKingdomImg = generateBuildingImage("pics/underwaterKingdom.jpg");
        ImageView volcanoImg = generateBuildingImage("pics/volcano.jpg");

        myBuildings.setHgap(10);
        myBuildings.setPrefWrapLength(boardWidth);
        myBuildings.setAlignment(Pos.BOTTOM_CENTER);
        myBuildings.getChildren().add(lavaGroundImg);
        myBuildings.getChildren().add(fireTreeImg);
        myBuildings.getChildren().add(volcanoImg);
        myBuildings.getChildren().add(fireCaveImg);

        AIBuildings.setHgap(10);
        AIBuildings.setPrefWrapLength(boardWidth);
        AIBuildings.setAlignment(Pos.TOP_CENTER);
        AIBuildings.getChildren().add(roughSeaImg);
        AIBuildings.getChildren().add(geyserImg);
        AIBuildings.getChildren().add(underwaterKingdomImg);
        AIBuildings.getChildren().add(underwaterCaveImg);

        AIBattlefield.setHgap(10);
        AIBattlefield.setPrefWrapLength(boardWidth);
        AIBattlefield.setAlignment(Pos.CENTER);
        for(int i = 0; i < 6; i++) {
            AIBattlefield.getChildren().add(generateCreatureImage("pics/creaturePlaceholder.png"));
        }

        myBattlefield.setHgap(10);
        myBattlefield.setPrefWrapLength(boardWidth);
        myBattlefield.setAlignment(Pos.CENTER);
        for(int i = 0; i < 6; i++) {
            myBattlefield.getChildren().add(generateCreatureImage("pics/creaturePlaceholder.png"));
        }

        Player player = new Player("gracz");
        Player computer = new Player ("komputer");

        computerLbl.setText("Komputer");
        computerLbl.setFont(new Font("Arial", 24));
        computerLbl.setTextFill(Color.web("#FFF"));

        computer.lifeLbl.setText("Życie: " + computer.getCurrentLife());
        computer.lifeLbl.setFont(new Font("Arial", 24));
        computer.lifeLbl.setTextFill(Color.GREEN);

        computerManaLbl.setText("Mana: " + computer.getCurrentMana());
        computerManaLbl.setFont(new Font("Arial", 24));
        computerManaLbl.setTextFill(Color.BLUE);

        computerStats.setPrefWrapLength(120);
        computerStats.setAlignment(Pos.CENTER);
        computerStats.setHgap(10);
        computerStats.getChildren().add(computerLbl);
        computerStats.getChildren().add(computer.lifeLbl);
        computerStats.getChildren().add(computerManaLbl);

        playerLbl.setText("Gracz");
        playerLbl.setFont(new Font("Arial", 24));
        playerLbl.setTextFill(Color.web("#FFF"));

        player.lifeLbl.setText("Życie: " + player.getCurrentLife());
        player.lifeLbl.setFont(new Font("Arial", 24));
        player.lifeLbl.setTextFill(Color.GREEN);

        playerManaLbl.setText("Mana: " + player.getCurrentMana());
        playerManaLbl.setFont(new Font("Arial", 24));
        playerManaLbl.setTextFill(Color.ORANGERED);

        statusLabel.setText("Twoja tura");
        statusLabel.setFont(new Font("Arial", 24));
        statusLabel.setTextFill(Color.web("#FFF"));

        playerStats.setPrefWrapLength(120);
        playerStats.setAlignment(Pos.CENTER);
        playerStats.setHgap(10);
        playerStats.getChildren().add(playerLbl);
        playerStats.getChildren().add(player.lifeLbl);
        playerStats.getChildren().add(playerManaLbl);

        Button buy0 = new Button();
        buy0.setPrefWidth(170.0);
        buy0.setText("Kup: (1/8)\nKoszt: 2 many");
        buy0.setOnAction((e) -> {
                    if (player.getCurrentMana()>=2) {
                        chosenCreature = chooseCreature(new WallOfFire());
                        statusLabel.setText("Umieść stwora");
                    }
                    else{
                        System.out.println("Nie masz wystarczającej ilości many!");
                    }
                }
        );

        Button buy1 = new Button();
        buy1.setPrefWidth(170.0);
        buy1.setText("Kup: (7/4)\nKoszt: 3 many");
        buy1.setOnAction((e) -> {
            if (player.getCurrentMana()>=3) {
                chosenCreature = chooseCreature(new FireWolf());
                statusLabel.setText("Umieść stwora");
            }
            else{
                System.out.println("Nie masz wystarczającej ilości many!");
            }
        }
        );

        Button buy2 = new Button();
        buy2.setPrefWidth(170.0);
        buy2.setText("Kup: (5/14)\nKoszt: 5 many");
        buy2.setOnAction((e) -> {
                    if (player.getCurrentMana()>=5) {
                        chosenCreature = chooseCreature(new Phoenix());
                        statusLabel.setText("Umieść stwora");
                    }
                    else{
                        System.out.println("Nie masz wystarczającej ilości many!");
                    }
                }
        );

        Button buy3 = new Button();
        buy3.setPrefWidth(170.0);
        buy3.setText("Kup: (15/20)\nKoszt: 10 many");
        buy3.setOnAction((e) -> {
                    if (player.getCurrentMana()>=10) {
                        chosenCreature = chooseCreature(new BlackDragon());
                        statusLabel.setText("Umieść stwora");
                    }
                    else{
                        System.out.println("Nie masz wystarczającej ilości many!");
                    }
                }
        );

        buyButtons.setPrefWrapLength(boardWidth);
        buyButtons.setAlignment(Pos.CENTER);
        buyButtons.setHgap(10);
        buyButtons.getChildren().add(buy0);
        buyButtons.getChildren().add(buy1);
        buyButtons.getChildren().add(buy2);
        buyButtons.getChildren().add(buy3);

        Button put1 = new Button();
        put1.setPrefWidth(110.0);
        put1.setText("Umieść");
        put1.setOnAction((e) -> {
            if (!playerChecker.isOccupied(0)) {
                if (chosenCreature != null) {
                    createCreature(player, 0, chosenCreature, myBattlefield, myCreaturesStats, playerChecker);
                    playerManaLbl.setText("Mana: " + player.getCurrentMana());
                    chosenCreature = null;
                    statusLabel.setText("Faza ataku");
                    sleeper(1000);
                  /*  Platform.runLater(() -> {
                            AttackTask<Void> attackTask = new AttackTask<>(player, computer);
                            new Thread(attackTask).start();
                           // });*/
                    processMyAttacks(player, computer);
                }
             else {
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
                    createCreature(player,1, chosenCreature, myBattlefield, myCreaturesStats, playerChecker);
                    playerManaLbl.setText("Mana: " + player.getCurrentMana());
                    chosenCreature = null;
                    statusLabel.setText("Faza ataku");
                    sleeper(1000);
                    Platform.runLater(() -> {
                        processMyAttacks(player, computer);
                    });
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
                    createCreature(player, 2, chosenCreature, myBattlefield, myCreaturesStats, playerChecker);
                    playerManaLbl.setText("Mana: " + player.getCurrentMana());
                    statusLabel.setText("Faza ataku");
                    sleeper(1000);
                    Platform.runLater(() -> {
                        processMyAttacks(player, computer);
                    });
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
                    createCreature(player,3, chosenCreature, myBattlefield, myCreaturesStats, playerChecker);
                    playerManaLbl.setText("Mana: " + player.getCurrentMana());
                    chosenCreature = null;
                    statusLabel.setText("Faza ataku");
                    sleeper(1000);
                    Platform.runLater(() -> {
                        processMyAttacks(player, computer);
                    });
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
                    createCreature(player,4, chosenCreature, myBattlefield, myCreaturesStats, playerChecker);
                    playerManaLbl.setText("Mana: " + player.getCurrentMana());
                    chosenCreature = null;
                    statusLabel.setText("Faza ataku");
                    sleeper(1000);
                    Platform.runLater(() -> {
                        processMyAttacks(player, computer);
                    });
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
                    createCreature(player,5, chosenCreature, myBattlefield, myCreaturesStats, playerChecker);
                    playerManaLbl.setText("Mana: " + player.getCurrentMana());
                    chosenCreature = null;
                    statusLabel.setText("Faza ataku");
                    sleeper(1000);
                    Platform.runLater(() -> {
                        processMyAttacks(player, computer);
                    });
                } else {
                    System.out.println("Nie wybrano stwora!");
                }
            }
            else{
                System.out.println("Miejsce zajęte!");
            }
        });

        Button saveMana = new Button();
        saveMana.setPrefWidth(160.0);
        saveMana.setText("Gromadź manę (+2)");
        saveMana.setOnAction((e) -> {
            player.setCurrentMana(player.getCurrentMana() + 2);
            playerManaLbl.setText("Mana: " + player.getCurrentMana());
            System.out.println("Mana gracza: " + player.getCurrentMana());
            sleeper(1000);
            Platform.runLater(() -> {
                processMyAttacks(player, computer);
            });
                }
        );

        putButtons.setPrefWrapLength(boardWidth);
        putButtons.setAlignment(Pos.CENTER);
        putButtons.setHgap(10);
        putButtons.getChildren().add(put1);
        putButtons.getChildren().add(put2);
        putButtons.getChildren().add(put3);
        putButtons.getChildren().add(put4);
        putButtons.getChildren().add(put5);
        putButtons.getChildren().add(put6);

        AICreaturesStats.setPrefWrapLength(boardWidth);
        AICreaturesStats.setAlignment(Pos.CENTER);
        AICreaturesStats.setHgap(10);
        AICreaturesStats.setPrefHeight(20);

        myCreaturesStats.setPrefWrapLength(boardWidth);
        myCreaturesStats.setAlignment(Pos.CENTER);
        myCreaturesStats.setHgap(10);
        myCreaturesStats.setPrefHeight(20);
        for(int i = 0; i < 6; i++){
            Label label = new Label();
            label.setAlignment(Pos.CENTER);
            label.setFont(new Font("Arial", 20));
            label.setTextFill(Color.web("#FFF"));
            label.setText(" ");
            label.setPrefWidth(110.0);
        myCreaturesStats.getChildren().add(label);
        }

        grid.add(computerStats,0,2,1,1);
        grid.add(playerStats,0,3,1,1);
        grid.add(statusLabel, 0, 6, 1, 2);
        grid.add(saveMana, 0, 7, 1,1);
        grid.add(AIBuildings, 1,0,1,1);
        grid.add(AICreaturesStats, 1, 1, 1, 1);
        grid.add(AIBattlefield, 1,2,1,1);
        grid.add(myBattlefield,1,3,1,1);
        grid.add(myCreaturesStats, 1, 4, 1, 1);
        grid.add(putButtons,1,5,1,1);
        grid.add(myBuildings, 1, 6, 1, 1);
        grid.add(buyButtons,1,7,1,1);

        Scene scene = new Scene(grid, 1600, 900, Color.BLACK);

        primaryStage.setTitle("ElementalClash");
        primaryStage.setScene(scene);
        primaryStage.show();
    }
}
