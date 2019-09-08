package com.kodilla.game.creatures;

public class WallOfFire implements Creature {
    private int currentHealth = 8;
    private int power = 1;
    private String name = "Ściana ognia";
    private String source = "pics/wallOfFire.jpg";

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

    public void attack() {

    }
}
