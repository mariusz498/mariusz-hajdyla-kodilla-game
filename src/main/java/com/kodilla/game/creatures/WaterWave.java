package com.kodilla.game.creatures;

public class WaterWave implements Creature {
    private int currentHealth = 12;
    private int power = 0;
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

    public void attack() {

    }
}
