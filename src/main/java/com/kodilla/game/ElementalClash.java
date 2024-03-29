package com.kodilla.game;
import com.kodilla.game.creatures.BlackDragon;
import com.kodilla.game.creatures.Creature;
import com.kodilla.game.creatures.FireWolf;
import com.kodilla.game.creatures.Phoenix;
import com.kodilla.game.creatures.WallOfFire;
import com.kodilla.game.engine.OccupationChecker;
import com.kodilla.game.engine.NewGameStarter;
import com.kodilla.game.engine.AIdecidionsMaker;
import com.kodilla.game.engine.Player;
import javafx.application.Application;
import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundSize;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.CornerRadii;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;
import java.net.URL;

public class ElementalClash extends Application {

    private static void sleeper(int millis){
        try {
            Thread.sleep(millis);
        }
        catch (InterruptedException ex) {
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
    public static Player player = new Player("gracz");
    public static Player computer = new Player ("komputer");

    private static Creature chooseCreature(Creature creature){
        System.out.println("Wybrano stwora: " + creature.getName());
        return creature;
    }

    public static OccupationChecker playerChecker = new OccupationChecker();
    public static OccupationChecker aiChecker = new OccupationChecker();
    public static Button saveMana = new Button();
    public static Button newGame = new Button();

    public static boolean endOfGame = false;

    public static void createCreature(Player player, int place, Creature creature, FlowPane pane, FlowPane statsPane, OccupationChecker checker) {
        ImageView creatureImg = generateCreatureImage(creature.getSource());
        pane.getChildren().add(place, creatureImg);
        pane.getChildren().remove(place+1);
        checker.occupy(place);
        creature.payManaCost(player);
        player.addCreature(place, creature);
        player.checkCreature(place).setCurrentHealth(player.checkCreature(place).getStartingHealth());
        statsPane.getChildren().add(place, creature.getLabel());
        statsPane.getChildren().remove(place+1);
        for(int i = 0; i < 6; i ++) {
            putButtons.getChildren().get(i).setVisible(false);
        }
        for(int i = 0; i < 4; i ++) {
            buyButtons.getChildren().get(i).setVisible(false);
        }
        saveMana.setVisible(false);
        System.out.println("Umieszczono stwora: " + creature.getName());
    }

    public static void killCreature(Player player, int place, Creature creature, FlowPane pane, FlowPane statsPane, OccupationChecker checker) {
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
        int i;
        for(i = 0; i < 4; i ++) {
            buyButtons.getChildren().get(i).setVisible(false);
        }
        for(i = 0; i < 6; i ++) {
            putButtons.getChildren().get(i).setVisible(false);
        }
        saveMana.setVisible(false);
        i = 0;
        for (attacking.checkCreature(i); i < 6; i++) {
            if (attacking.checkCreature(i) != null) {
                if (opponent.checkCreature(i) != null) {
                    opponent.checkCreature(i).setCurrentHealth(opponent.checkCreature(i).getCurrentHealth() - attacking.checkCreature(i).getPower());
                    aiCreaturesStats.getChildren().add(i, opponent.checkCreature(i).getLabel());
                    aiCreaturesStats.getChildren().remove(i+1);
                    if (opponent.checkCreature(i).getCurrentHealth() <= 0) {
                        ElementalClash.killCreature(opponent, i, opponent.checkCreature(i), aiBattlefield, aiCreaturesStats, aiChecker);
                    }
                }
                else {
                    opponent.setCurrentLife(opponent.getCurrentLife() - attacking.checkCreature(i).getPower());
                    opponent.lifeLbl.setText("Życie: " + opponent.getCurrentLife());
                }
                System.out.println(attacking.checkCreature(i).getName() + " zaatakował(-a)!");
                if (opponent.getCurrentLife() <= 0) {
                    statusLabel.setText("Wygrałeś!");
                    endOfGame = true;
                    break;
                }
            }
        }
        if (!endOfGame) {
            statusLabel.setText("Tura komputera");
        }
    }

    private void processAIAttacks(Player attacking, Player opponent) {
        statusLabel.setText("Komputer atakuje");
        int i = 0;
        for (attacking.checkCreature(i); i < 6; i++) {
            if (attacking.checkCreature(i) != null) {
                if (opponent.checkCreature(i) != null) {
                    opponent.checkCreature(i).setCurrentHealth(opponent.checkCreature(i).getCurrentHealth() - attacking.checkCreature(i).getPower());
                    myCreaturesStats.getChildren().add(i, opponent.checkCreature(i).getLabel());
                    myCreaturesStats.getChildren().remove(i + 1);
                    if (opponent.checkCreature(i).getCurrentHealth() <= 0) {
                        ElementalClash.killCreature(opponent, i, opponent.checkCreature(i), myBattlefield, myCreaturesStats, playerChecker);
                    }
                }
                else {
                    opponent.setCurrentLife(opponent.getCurrentLife() - attacking.checkCreature(i).getPower());
                    opponent.lifeLbl.setText("Życie: " + opponent.getCurrentLife());
                }
                System.out.println(attacking.checkCreature(i).getName() + " zaatakował(-a)!");
            }
            if (opponent.getCurrentLife() <= 0) {
                statusLabel.setText("Przegrałeś!");
                endOfGame = true;
                break;
            }
        }
        if (!endOfGame) {
            statusLabel.setText("Twoja tura");
            for(i = 0; i < 4; i ++) {
                buyButtons.getChildren().get(i).setVisible(true);
            }
            saveMana.setVisible(true);
        }
    }

    private URL backgroundUrl = ElementalClash.class.getClassLoader().getResource("pics/background.jpg");
    public Image backgroundImg = new Image(String.valueOf(backgroundUrl));
    public FlowPane computerStats = new FlowPane(Orientation.HORIZONTAL);
    public FlowPane playerStats = new FlowPane(Orientation.HORIZONTAL);
    public FlowPane myBuildings = new FlowPane(Orientation.HORIZONTAL);
    public FlowPane aiBuildings = new FlowPane(Orientation.HORIZONTAL);
    public static FlowPane aiCreaturesStats = new FlowPane(Orientation.HORIZONTAL);
    public static FlowPane myCreaturesStats = new FlowPane(Orientation.HORIZONTAL);
    public static FlowPane aiBattlefield = new FlowPane(Orientation.HORIZONTAL);
    public static FlowPane myBattlefield = new FlowPane(Orientation.HORIZONTAL);
    public static FlowPane putButtons = new FlowPane(Orientation.HORIZONTAL);
    public static FlowPane buyButtons = new FlowPane(Orientation.HORIZONTAL);

    private int boardWidth = 900;

    public static Label computerLbl = new Label();
    public static Label computerManaLbl = new Label();
    public static Label statusLabel = new Label();
    public static Label playerLbl = new Label();
    public static Label playerManaLbl = new Label();

    private void generatePutButton(Button button, int place){
        button.setVisible(false);
        button.setPrefWidth(110.0);
        button.setText("Umieść");
        button.setOnAction((e) -> {
            if (!playerChecker.isOccupied(place)) {
                if (chosenCreature != null) {
                    createCreature(player, place, chosenCreature, myBattlefield, myCreaturesStats, playerChecker);
                    playerManaLbl.setText("Mana: " + player.getCurrentMana());
                    chosenCreature = null;
                    statusLabel.setText("Faza ataku");
                    sleeper(1000);
                    processMyAttacks(player, computer);
                    if (!endOfGame) {
                        AIdecidionsMaker.makeDecision(computer, aiChecker, playerChecker, aiBattlefield, aiCreaturesStats);
                        processAIAttacks(computer, player);
                    }
                }
                else {
                    System.out.println("Nie wybrano stwora!");
                }
            }
            else {
                System.out.println("Miejsce zajęte!");
            }
        });
    }

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

        computerStats.setBackground(new Background(new BackgroundFill(new Color(0.66,0.66,0.66,0.8), new CornerRadii(10), Insets.EMPTY)));
        playerStats.setBackground(new Background(new BackgroundFill(new Color(0.66,0.66,0.66,0.8), new CornerRadii(10), Insets.EMPTY)));

        ImageView lavaGroundImg = generateBuildingImage("pics/lavaGround.jpg");
        ImageView fireCaveImg = generateBuildingImage("pics/fireCave.jpg");
        ImageView fireTreeImg = generateBuildingImage("pics/fireTree2.png");
        ImageView geyserImg = generateBuildingImage("pics/geyser.jpg");
        ImageView roughSeaImg = generateBuildingImage("pics/roughSea.jpg");
        ImageView underwaterCaveImg = generateBuildingImage("pics/underwaterCave.jpg");
        ImageView underwaterKingdomImg = generateBuildingImage("pics/underwaterKingdom.jpg");
        ImageView volcanoImg = generateBuildingImage("pics/volcano.jpg");

        Tooltip lavaGroundTip = new Tooltip();
        lavaGroundTip.setText("Płonące pola\nProdukują:\nŚciana ognia (1/8)");
        Tooltip.install(lavaGroundImg,lavaGroundTip);

        Tooltip fireCaveTip = new Tooltip();
        fireCaveTip.setText("Ognista jaskinia\nProdukuje:\nCzarny smok (15/20)");
        Tooltip.install(fireCaveImg,fireCaveTip);

        Tooltip fireTreeTip = new Tooltip();
        fireTreeTip.setText("Las ognia\nProdukuje:\nOgnisty wilk (4/5)");
        Tooltip.install(fireTreeImg,fireTreeTip);

        Tooltip geyserTip = new Tooltip();
        geyserTip.setText("Gejzer\nProdukuje:\nŻywiołak wody (10/9)");
        Tooltip.install(geyserImg,geyserTip);

        Tooltip roughSeaTip = new Tooltip();
        roughSeaTip.setText("Wzburzone morze\nProdukuje:\nFala tsunami (1/12)");
        Tooltip.install(roughSeaImg,roughSeaTip);

        Tooltip underwaterCaveTip = new Tooltip();
        underwaterCaveTip.setText("Podwodna jaskinia\nProdukuje:\nPotwór z głębin (17/18)");
        Tooltip.install(underwaterCaveImg,underwaterCaveTip);

        Tooltip underwaterKingdomTip = new Tooltip();
        underwaterKingdomTip.setText("Morskie królestwo\nProdukuje:\nMerfolk (3/8)");
        Tooltip.install(underwaterKingdomImg,underwaterKingdomTip);

        Tooltip volcanoTip = new Tooltip();
        volcanoTip.setText("Wulkan\nProdukuje:\nFeniks (5/14)");
        Tooltip.install(volcanoImg,volcanoTip);

        myBuildings.setHgap(10);
        myBuildings.setPrefWrapLength(boardWidth);
        myBuildings.setAlignment(Pos.BOTTOM_CENTER);
        myBuildings.getChildren().add(lavaGroundImg);
        myBuildings.getChildren().add(fireTreeImg);
        myBuildings.getChildren().add(volcanoImg);
        myBuildings.getChildren().add(fireCaveImg);

        aiBuildings.setHgap(10);
        aiBuildings.setPrefWrapLength(boardWidth);
        aiBuildings.setAlignment(Pos.TOP_CENTER);
        aiBuildings.getChildren().add(roughSeaImg);
        aiBuildings.getChildren().add(underwaterKingdomImg);
        aiBuildings.getChildren().add(geyserImg);
        aiBuildings.getChildren().add(underwaterCaveImg);

        aiBattlefield.setHgap(10);
        aiBattlefield.setPrefWrapLength(boardWidth);
        aiBattlefield.setAlignment(Pos.CENTER);
        for(int i = 0; i < 6; i++) {
            aiBattlefield.getChildren().add(generateCreatureImage("pics/creaturePlaceholder.png"));
        }

        myBattlefield.setHgap(10);
        myBattlefield.setPrefWrapLength(boardWidth);
        myBattlefield.setAlignment(Pos.CENTER);
        for(int i = 0; i < 6; i++) {
            myBattlefield.getChildren().add(generateCreatureImage("pics/creaturePlaceholder.png"));
        }

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
        statusLabel.setMaxWidth(160);
        statusLabel.setWrapText(true);
        statusLabel.setTextFill(Color.web("#FFF"));
        statusLabel.setTextAlignment(TextAlignment.CENTER);

        playerStats.setPrefWrapLength(110);
        playerStats.setAlignment(Pos.CENTER);
        playerStats.setHgap(10);
        playerStats.getChildren().add(playerLbl);
        playerStats.getChildren().add(player.lifeLbl);
        playerStats.getChildren().add(playerManaLbl);

        Button buy0 = new Button();
        buy0.setPrefWidth(170.0);
        buy0.setText("Kup: (1/8)\nKoszt: 2 many");
        buy0.setOnAction((e) -> {
            if (player.getCurrentMana() >= 2) {
                chosenCreature = chooseCreature(new WallOfFire());
                statusLabel.setText("Umieść stwora");
                for(int i = 0; i < 6; i ++) {
                    putButtons.getChildren().get(i).setVisible(true);
                }
            }
            else {
                statusLabel.setText("Nie masz wystarczającej ilości many!");
            }
        });

        Button buy1 = new Button();
        buy1.setPrefWidth(170.0);
        buy1.setText("Kup: (4/5)\nKoszt: 3 many");
        buy1.setOnAction((e) -> {
            if (player.getCurrentMana() >= 3) {
                chosenCreature = chooseCreature(new FireWolf());
                statusLabel.setText("Umieść stwora");
                for(int i = 0; i < 6; i ++) {
                    putButtons.getChildren().get(i).setVisible(true);
                }
            }
            else {
                statusLabel.setText("Nie masz wystarczającej ilości many!");
            }
        });

        Button buy2 = new Button();
        buy2.setPrefWidth(170.0);
        buy2.setText("Kup: (5/14)\nKoszt: 5 many");
        buy2.setOnAction((e) -> {
            if (player.getCurrentMana() >= 5) {
                chosenCreature = chooseCreature(new Phoenix());
                statusLabel.setText("Umieść stwora");
                for(int i = 0; i < 6; i ++) {
                    putButtons.getChildren().get(i).setVisible(true);
                }
            }
            else {
                statusLabel.setText("Nie masz wystarczającej ilości many!");
            }
        });

        Button buy3 = new Button();
        buy3.setPrefWidth(170.0);
        buy3.setText("Kup: (15/20)\nKoszt: 10 many");
        buy3.setOnAction((e) -> {
            if (player.getCurrentMana() >= 10) {
                chosenCreature = chooseCreature(new BlackDragon());
                statusLabel.setText("Umieść stwora");
                for (int i = 0; i < 6; i++) {
                    putButtons.getChildren().get(i).setVisible(true);
                }
            }
            else {
                statusLabel.setText("Nie masz wystarczającej ilości many!");
            }
        });

        buyButtons.setPrefWrapLength(boardWidth);
        buyButtons.setAlignment(Pos.CENTER);
        buyButtons.setHgap(10);
        buyButtons.getChildren().add(buy0);
        buyButtons.getChildren().add(buy1);
        buyButtons.getChildren().add(buy2);
        buyButtons.getChildren().add(buy3);

        Button put1 = new Button();
        Button put2 = new Button();
        Button put3 = new Button();
        Button put4 = new Button();
        Button put5 = new Button();
        Button put6 = new Button();
        generatePutButton(put1, 0);
        generatePutButton(put2, 1);
        generatePutButton(put3, 2);
        generatePutButton(put4, 3);
        generatePutButton(put5, 4);
        generatePutButton(put6, 5);

        saveMana.setPrefWidth(160.0);
        saveMana.setText("Gromadź manę (+2)");
        saveMana.setOnAction((e) -> {
            player.setCurrentMana(player.getCurrentMana() + 2);
            playerManaLbl.setText("Mana: " + player.getCurrentMana());
            System.out.println("Mana gracza: " + player.getCurrentMana());
            sleeper(1000);
            processMyAttacks(player, computer);
            if(!endOfGame) {
                AIdecidionsMaker.makeDecision(computer, aiChecker, playerChecker, aiBattlefield, aiCreaturesStats);
                processAIAttacks(computer, player);
            }
                }
        );

        newGame.setPrefWidth(160.0);
        newGame.setText("Nowa gra");
        newGame.setOnAction((e) -> {
            endOfGame = false;
            chosenCreature = null;
            NewGameStarter.startNewGame();
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

        aiCreaturesStats.setPrefWrapLength(boardWidth);
        aiCreaturesStats.setAlignment(Pos.CENTER);
        aiCreaturesStats.setHgap(10);
        aiCreaturesStats.setPrefHeight(20);
        for(int i = 0; i < 6; i++) {
            Label label = new Label();
            label.setAlignment(Pos.CENTER);
            label.setFont(new Font("Arial", 20));
            label.setTextFill(Color.web("#FFF"));
            label.setText(" ");
            label.setPrefWidth(110.0);
            aiCreaturesStats.getChildren().add(label);
        }

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

        grid.add(newGame, 0 , 0, 1, 1);
        grid.add(computerStats,0,2,1,1);
        grid.add(playerStats,0,3,1,1);
        grid.add(statusLabel, 0, 6, 1, 1);
        grid.add(saveMana, 0, 7, 1,1);
        grid.add(aiBuildings, 1,0,1,1);
        grid.add(aiCreaturesStats, 1, 1, 1, 1);
        grid.add(aiBattlefield, 1,2,1,1);
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
