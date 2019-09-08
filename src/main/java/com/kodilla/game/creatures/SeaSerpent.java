package com.kodilla.game.creatures;

public class SeaSerpent implements Creature {
    private int currentHealth = 17;
    private int power = 18;
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

    public void attack() {

    }
}
