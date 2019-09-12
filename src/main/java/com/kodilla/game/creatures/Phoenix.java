package com.kodilla.game.creatures;

import com.kodilla.game.engine.Player;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class Phoenix implements Creature {
    private int currentHealth = 14;
    private int power = 5;
    private int manaCost = 5;
    private String name = "Feniks";
    private String source = "pics/phoenix.jpg";

    public String getSource() {
        return source;
    }

    public void setCurrentHealth(int currentHealth) {
        this.currentHealth = currentHealth;
    }

    public int getCurrentHealth() {
        return currentHealth;
    }

    public int getPower() {
        return power;
    }

    public String getName() {
        return name;
    }

    public int getManaCost(){ return manaCost;}

    public Label getLabel(){
        Label label = new Label();
        label.setAlignment(Pos.CENTER);
        label.setFont(new Font("Arial", 20));
        label.setTextFill(Color.web("#FFF"));
        label.setText(this.getPower() + "/" + this.getCurrentHealth());
        label.setPrefWidth(110.0);
        return label;
    }

    public void payManaCost(Player player){
        player.setCurrentMana(player.getCurrentMana() - manaCost);
    }
}
