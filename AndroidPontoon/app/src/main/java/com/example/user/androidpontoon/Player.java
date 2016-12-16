package com.example.user.androidpontoon;
import java.util.*;

public abstract class Player{
  private String name;
  private Hand hand;
  private int score;
  private int winCount;
  private String specialScore;

  public Player(String name, Hand hand) {
    this.hand = hand;
    this.name = name;
    this.score = 0;
    this.winCount = 0;
    this.specialScore = "";
  }

  public ArrayList<Card> showHand() {
    return this.hand.showHand();
  }

  public void receiveCard(Card card) {
    this.hand.receiveCard(card);
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
}