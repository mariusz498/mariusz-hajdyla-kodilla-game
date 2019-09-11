package com.kodilla.game.engine;

import com.kodilla.game.creatures.Creature;

public class Player {
    private int currentLife = 30;
    private int currentMana = 5;
    private String name;
    private Creature creaturesArray[] = new Creature[6];

    public Player(String name) {
        this.name = name;
    }

    public int getCurrentLife() {
        return currentLife;
    }

    public void setCurrentLife(int currentLife) {
        this.currentLife = currentLife;
    }

    public int getCurrentMana() {
        return currentMana;
    }

    public void setCurrentMana(int currentMana) {
        this.currentMana = currentMana;
    }

    public String getName() {
        return name;
    }

    public void addCreature(int place, Creature creature){
        creaturesArray[place] = creature;
        System.out.println("Dodano stwora: " + creature.getName() + "na miejscu " + place+1);
    }

    public void removeCreature(int place){
        creaturesArray[place] = null;
        System.out.println("Usunięto stwora z miejsca " + place+1);
    }

    public Creature checkCreature(int place){
        return creaturesArray[place];
    }

}
