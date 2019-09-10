package com.kodilla.game.creatures;

import com.kodilla.game.engine.Player;

public class WaterWave implements Creature {
    private int currentHealth = 12;
    private int power = 0;
    private int manaCost = 2;
    private String name = "Fala tsunami";
    private String source = "pics/waterWave.jpg";

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

    public void attack(Player player) {
    }

    public void payManaCost(Player player){
        player.setCurrentMana(player.getCurrentMana() - manaCost);
    }
}
