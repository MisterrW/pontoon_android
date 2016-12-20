package com.example.user.androidpontoon;
import java.util.*;

public class SinglePlayerGameManager {
  private GameState gameState;
  private ArrayList<CardPlayer> allPlayers;
  private Dealer dealer;
  private Gambler player;
  private WinCheck winCheck;

  public SinglePlayerGameManager(GameState gameState, ArrayList<CardPlayer> allPlayers, Dealer dealer, Gambler player1){
    this.gameState = gameState;
    this.dealer = dealer;
    this.player = player1;
    this.allPlayers = allPlayers;
    this.winCheck = new WinCheck(this, gameState, this.allPlayers);
  }

  public void play(){
    initialDeal();
    twistCheck();
  }

  public void setup(){
  }

  public void initialDeal(){
    gameState.setToastText("Initial deal! All players receive two cards!");
    for(int i=1; i<=2; i++) {
      dealAll();
    }
    gameState.showHand(this.player);
    gameState.showHoleCard(this.dealer);
  }

  public void dealAll(){
    for (CardPlayer player : this.allPlayers) {
      deal(player);
    }
  }

  public Card deal(CardPlayer player){
    Card card = this.dealer.dealCard();
    player.receiveCard(card);
    return card;
  }

  public void twistCheck(){
    if (this.allPlayers.size() == 2) {
      gameState.setMainText("You're up, " + this.player.getName() + ". Stick or twist?");
      gameState.getPlayerChoice();
    }
  }

  public void turnHandler(String choice){
    gameState.setMainText("You  " + choice + "!");
    if(choice.equals("twist")) {
      Card card = deal(this.player);
      gameState.setToastText("You receive the " + card.getName() + ".");
        gameState.showHand(this.player);
      gameState.setToastText(this.player.getName() + ", your hand is now worth " + this.winCheck.calcScore(this.player) + ".");
      this.winCheck.bustCheck(this.player);
    }

    if (choice.equals("stick")) {
      gameState.setMainText("You stick! Dealer's round!");
      dealersRound();
    }
  }

  public void dealersRound() {
    if (this.winCheck.calcScore(this.dealer) <= 16) {
      gameState.setToastText("Des takes a card!");
      deal(this.dealer);
      this.winCheck.bustCheck(this.dealer);
      dealersRound();
    }
    gameState.setToastText("Des sticks! Show!");
    winCheck();
  }

  public void winCheck(){
    this.winCheck.showAllHands();
    this.winCheck.winCheck();
  }

}