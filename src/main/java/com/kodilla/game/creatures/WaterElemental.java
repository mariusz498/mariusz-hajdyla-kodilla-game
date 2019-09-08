package com.kodilla.game.creatures;

public class WaterElemental implements Creature {
    private int currentHealth = 8;
    private int power = 3;
    private String name = "Żywiołak wody";
    private String source = "pics/waterElemental.jpg";

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
