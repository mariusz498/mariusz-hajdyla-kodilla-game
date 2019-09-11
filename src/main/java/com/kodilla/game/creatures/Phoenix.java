package com.kodilla.game.creatures;

import com.kodilla.game.engine.Player;

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

    public void attack(Player player) {
    }

    public void payManaCost(Player player){
        player.setCurrentMana(player.getCurrentMana() - manaCost);
    }
}
