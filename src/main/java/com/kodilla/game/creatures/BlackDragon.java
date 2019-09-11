package com.kodilla.game.creatures;

import com.kodilla.game.engine.Player;

public class BlackDragon implements Creature {
    private int currentHealth = 20;
    private int power = 15;
    private int manaCost = 10;
    private String name = "Czarny smok";
    private String source = "pics/blackDragon.jpg";

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

    public void attack(Player player) {
    }

    public void payManaCost(Player player){
        player.setCurrentMana(player.getCurrentMana() - manaCost);
    }
}
