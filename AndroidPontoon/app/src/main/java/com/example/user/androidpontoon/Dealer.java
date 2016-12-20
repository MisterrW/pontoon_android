package com.example.user.androidpontoon;
import java.util.*;

public class Dealer extends Player implements CardPlayer{
  private Deck deck;

  public Dealer(String name, Hand hand, Deck deck) {
    super(name, hand);
    this.deck = deck;
  }

  public int getDeckSize(){
    return deck.getDeckSize();
  }

  public void fillDeck() {
    this.deck.fillDeck();
  }

  public void shuffleCards(){
    this.deck.shuffleCards();
  }

  public Card dealCard(){
    return this.deck.dealCard();
  }

  public void returnCardToBackOfDeck(Card card) {
    deck.returnCardToBackOfDeck(card);
  }
}