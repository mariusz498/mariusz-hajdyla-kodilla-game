package com.kodilla.game.engine;

import com.kodilla.game.ElementalClash;


public class NewGameStarter {

    public static void startNewGame(){

        ElementalClash.player.setCurrentLife(30);
        ElementalClash.player.setCurrentMana(5);
        ElementalClash.player.lifeLbl.setText("Życie: " + ElementalClash.player.getCurrentLife());
        ElementalClash.playerManaLbl.setText("Mana: " + ElementalClash.player.getCurrentMana());
        for(int i = 0; i < 6; i++){
            if(ElementalClash.playerChecker.isOccupied(i)){
                ElementalClash.killCreature(ElementalClash.player, i, ElementalClash.player.checkCreature(i), ElementalClash.myBattlefield, ElementalClash.myCreaturesStats, ElementalClash.playerChecker);
            }
        }

        ElementalClash.computer.setCurrentLife(30);
        ElementalClash.computer.setCurrentMana(5);
        ElementalClash.computer.lifeLbl.setText("Życie: " + ElementalClash.computer.getCurrentLife());
        ElementalClash.computerManaLbl.setText("Mana: " + ElementalClash.computer.getCurrentMana());
        for(int i = 0; i < 6; i++){
            if(ElementalClash.aiChecker.isOccupied(i)){
                ElementalClash.killCreature(ElementalClash.computer, i, ElementalClash.computer.checkCreature(i), ElementalClash.aiBattlefield, ElementalClash.aiCreaturesStats, ElementalClash.aiChecker);
            }
        }

        for(int i = 0; i < 4; i ++)
        {
            ElementalClash.buyButtons.getChildren().get(i).setVisible(true);
        }
        for(int i = 0; i < 6; i ++)
        {
            ElementalClash.putButtons.getChildren().get(i).setVisible(false);
        }
        ElementalClash.saveMana.setVisible(true);
        ElementalClash.statusLabel.setText("Twoja tura");
    }

}
