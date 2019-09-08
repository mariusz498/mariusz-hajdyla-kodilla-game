package com.kodilla.game.creatures;

public class Merfolk implements Creature {
    private int currentHealth = 8;
    private int power = 3;
    private String name = "Merfolk";
    private String source = "pics/merfolk.jpg";

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

    public void attack(){
    }
}
