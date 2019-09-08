package com.kodilla.game.engine;

import java.util.LinkedList;

public class OccupationChecker {
    private LinkedList<Boolean> occupationList = new LinkedList<>();

    public OccupationChecker(){
        for (int i=0; i<6; i++){
            occupationList.add(i, false);
        }
    }

    public boolean isOccupied(int place) {
        return occupationList.get(place);
    }

    public void occupy(int place) {
        occupationList.set(place, true);
    }
}
