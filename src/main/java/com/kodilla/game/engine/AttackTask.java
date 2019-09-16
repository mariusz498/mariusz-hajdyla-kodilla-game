package com.kodilla.game.engine;

import com.kodilla.game.ElementalClash;
import javafx.concurrent.Task;

public class AttackTask<Void> extends Task<Void> {
    public Player attacking;
    public Player opponent;

    public AttackTask(Player attacking, Player opponent){
        this.attacking = attacking;
        this.opponent = opponent;
    }

    @Override
    protected Void call() throws Exception {
        ElementalClash.processMyAttacks(attacking, opponent);
        return null;
    }
}
