package com.kodilla.game.creatures;

import com.kodilla.game.engine.Player;

public class SeaSerpent implements Creature {
    private int currentHealth = 17;
    private int power = 18;
    private int manaCost = 10;
    private String name = "Potwór z głębin";
    private String source = "pics/seaSerpent.jpg";

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
