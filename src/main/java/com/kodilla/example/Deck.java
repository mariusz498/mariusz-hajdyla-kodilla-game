package com.kodilla.example;

public class Deck{
    int i;
    Deck(int i){
        this.i = i;
    }

    public Card dealCard() {
        return new Card();
    }

    public void restoreDeck() {
    }

    public void shuffle() {
    }

    public int getNumberOfCardsRemaining() {
        return 50;
    }

    public int getSizeOfDeck() {
        return 50;
    }
}
