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

    public static void makeDecision(Player player, OccupationChecker checker, FlowPane pane, FlowPane statsPane){
        if (player.getCurrentMana() < 2){
            player.setCurrentMana(player.getCurrentMana() + 2);
        }
        else {
            Random random = new Random();
            int i = random.nextInt(2);
            switch (i) {
                case 0: {
                    player.setCurrentMana(player.getCurrentMana() + 2);
                    creatureCreated = true;
                    break;
                }
                case 1: {
                    for (int k = 0; k < 6; k++) {
                        while (!checker.isOccupied(k) && creatureCreated == false) {
                            int j = random.nextInt(6);
                            if (!checker.isOccupied(j)) {
                                ElementalClash.createCreature(player, j, AIdecidionsMaker.creaturePicker(player), pane, statsPane, checker);
                                creatureCreated = true;
                                break;
                            }
                        }
                    }
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
