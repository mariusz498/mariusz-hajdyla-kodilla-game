package com.kodilla.game.creatures;

import com.kodilla.game.engine.Player;

public class FireWolf implements Creature {
    private int currentHealth = 4;
    private int power = 7;
    private int manaCost = 3;
    private String name = "Ognisty wilk";
    private String source = "pics/fireWolf.jpg";

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


    public void payManaCost(Player player){
        player.setCurrentMana(player.getCurrentMana() - manaCost);
    }
}
