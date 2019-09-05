package com.kodilla.game.creatures;

public interface Creature {
    int placement = 0;
    int health = 0;
    int power = 0;

    void putCreature(int placement);
    void removeCreature(int placement);
    void attack(int power);
}
