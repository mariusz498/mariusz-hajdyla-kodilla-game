package com.kodilla.game.creatures;

import com.kodilla.game.engine.Player;
import javafx.scene.control.Label;

public interface Creature {
    int health = 0;
    int power = 0;
    int manaCost = 0;
    String source = null;
    String name = null;

    void payManaCost(Player player);
    String getSource();
    String getName();
    int getManaCost();
    int getPower();
    void setCurrentHealth(int currentHealth);
    int getCurrentHealth();
    Label getLabel();
}
