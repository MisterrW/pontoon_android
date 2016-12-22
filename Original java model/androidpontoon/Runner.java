package com.example.user.androidpontoon;

public class Runner{
  public static void main(String[] args) {

    Dealer dealer = new Dealer("Dealer", new Hand(), new Deck());
    Gambler gambler = new Gambler("Player", new Hand());

    gambler.setFunds(200);
    dealer.setFunds(200);

    dealer.fillDeck();
    dealer.shuffleCards();
    
    GameState gameState = new GameState();
    gameState.singleplayerContinue(gambler, dealer);
  }
}