package com.kodilla.game.engine;

import com.kodilla.game.ElementalClash;
import com.kodilla.game.creatures.*;
import javafx.scene.layout.FlowPane;

import java.util.Random;

public class AIdecidionsMaker {

    private static Creature[] availableCreatures = {new WaterWave(), new Merfolk(), new WaterElemental(), new SeaSerpent()};
    private static boolean creatureCreated = false;


    private static Creature creaturePicker(Player player){
        Random random = new Random();
        int i = 0;
        do {
        i = random.nextInt(4);
        }
        while(player.getCurrentMana() < availableCreatures[i].getManaCost());
        return availableCreatures[i];
    }

    public static void makeDecision(Player player, OccupationChecker checker, OccupationChecker opponentChecker, FlowPane pane, FlowPane statsPane){
        if (player.getCurrentMana() < 2){
            player.setCurrentMana(player.getCurrentMana() + 2);
        }
        else {
            Random random = new Random();
            int i = random.nextInt(100);
            int j = i%3;
            switch (j) {
                case 0: {
                    player.setCurrentMana(player.getCurrentMana() + 2);
                    creatureCreated = true;
                    break;
                }
                case 1: {
                    for (int k = 0; k < 6; k++) {
                        while (!checker.isOccupied(k) && creatureCreated == false) {
                            int l = random.nextInt(6);
                            if (!checker.isOccupied(l)) {
                                ElementalClash.createCreature(player, l, AIdecidionsMaker.creaturePicker(player), pane, statsPane, checker);
                                creatureCreated = true;
                                break;
                            }
                        }
                    }
                    break;
                }
                case 2: {
                    for (int k = 0; k < 6; k++) {
                        while (!checker.isOccupied(k) && opponentChecker.isOccupied(k)) {
                                ElementalClash.createCreature(player, k, AIdecidionsMaker.creaturePicker(player), pane, statsPane, checker);
                                creatureCreated = true;
                                break;
                        }
                    }
                    break;
                }
            }
            if (!creatureCreated) {
                player.setCurrentMana(player.getCurrentMana() + 2);
            }
                creatureCreated = false;
        }
        ElementalClash.computerManaLbl.setText("Mana: " + player.getCurrentMana());
    }
}
