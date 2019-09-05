package com.kodilla.game.creatures;

public interface Creature {
    int health = 0;
    int power = 0;
    String source = null;
    String name = null;

    void attack(int power);
    String getSource();
    String getName();
}
