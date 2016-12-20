package com.example.user.androidpontoon;

import java.util.*;

public abstract class Player {
  private String name;
  private Hand hand;
  private int score;
  private int winCount;
  private String specialScore;
  private Boolean winner;
  private int funds;

  public Player(String name, Hand hand) {
    this.hand = hand;
    this.name = name;
    this.score = 0;
    this.winCount = 0;
    this.specialScore = "";
    this.winner = false;
    this.funds = 0;
  }

  public ArrayList<Card> showHand() {
    return this.hand.showHand();
  }

  public void receiveCard(Card card) {
    this.hand.receiveCard(card);
  }

  public Boolean getWinner() {
    return winner;
  }

  public void setWinner(Boolean winner) {
    this.winner = winner;
  }

  public String getName() {
    return this.name;
  }

  public void setScore(int score) {
    this.score = score;
  }

  public int getScore() {
    return this.score;
  }

  public void setFunds(int funds) {
    this.funds = funds;
  }

  public int getFunds() {
    return this.funds;
  }

  public void setSpecialScore(String specialScore) {
    this.specialScore = specialScore;
  }

  public String getSpecialScore() {
    return this.specialScore;
  }

  public int getWinCount() {
    return winCount;
  }

  public void setWinCount(int winCount) {
    this.winCount = winCount;
  }

  public Card returnCard(){
    return hand.returnCard();
  }
}