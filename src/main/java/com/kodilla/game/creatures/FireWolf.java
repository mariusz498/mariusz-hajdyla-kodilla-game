package com.kodilla.game.creatures;

public class FireWolf implements Creature {
    private int placement = 0;
    private int currentHealth = 4;
    private int power = 7;

    public FireWolf(int placement){
        this.placement = placement;
    }

    public void putCreature(int placement) {}

    public void removeCreature(int placement) {}

    public void attack(int power) {
        this.power = power;
    }
}
