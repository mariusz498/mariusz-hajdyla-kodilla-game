package com.kodilla.game.creatures;

public class FireWolf implements Creature {
    private int currentHealth = 4;
    private int power = 7;
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

    public void attack(int power) {
        this.power = power;
    }
}
