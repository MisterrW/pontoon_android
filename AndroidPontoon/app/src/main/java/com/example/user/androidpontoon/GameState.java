package com.example.user.androidpontoon;
import android.widget.ImageView;

import java.util.*;

public class GameState {

    private Setup setup;

    private String mainText;

    private ArrayList<Card> playerHand;
    private ArrayList<Card> dealersHand;
    private String handValue;

    private String playerName;
    private String toastText;


    private SinglePlayerGameManager game;

    public GameState(Setup setup){
        this.setup = setup;
        this.mainText = "Welcome to Pontoon";
    }

    public GameState(Setup setup, Gambler gambler, Dealer dealer){
        this.setup = setup;
        this.mainText = "Welcome to Pontoon";
    }

    public void singleplayerContinue(Gambler gambler, Dealer dealer) {
        ArrayList<CardPlayer> allPlayers = new ArrayList<CardPlayer>();

        allPlayers.add(allPlayers.size(), gambler);
        allPlayers.add(allPlayers.size(), dealer);

        game = new SinglePlayerGameManager(this, allPlayers, dealer, gambler);
        game.play();
    }

    public void singleplayerSetup() {
        ArrayList<CardPlayer> allPlayers = new ArrayList<CardPlayer>();

        printText("Type your name.");

        String gamblerName = "Player";

        Dealer dealer = new Dealer("Des the Dealer", new Hand(), new Deck());
        Gambler gambler = new Gambler(gamblerName, new Hand());
        setPlayerName(gamblerName);

        allPlayers.add(allPlayers.size(), gambler);
        allPlayers.add(allPlayers.size(), dealer);

        game = new SinglePlayerGameManager(this, allPlayers, dealer, gambler);
        game.play();
    }

    public void setMainText(String text) {
        mainText = text;
        printText(mainText);
    }

    public String getMainText(){
        return mainText;
    }

    public void setToastText(String text) {
        toastText = text;
//        setup.showToast(toastText);
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public void showHand(CardPlayer player) {
        Gambler player1 = (Gambler)player;
        setup.setPlayerHandImages(player1.showHand());
    }

    public void showDealerHand(CardPlayer dealer) {
        String dealerHand = showAnyHand(dealer);
        updateDealerHand(dealerHand);
    }

    public String showAnyHand(CardPlayer player){
        String hand = String.format(player.getName() + ":");
        ArrayList<Card> cards = player.showHand();
        for (Card card : cards) {
            String tempString = String.format("\n - " + card.getName());
            hand = String.format(hand + tempString);
        }
        return hand;
    }

    public void getPlayerChoice() {
        setup.getPlayerChoice();
    }

    public void setPlayerChoice(String choice) {
        game.turnHandler(choice);
    }

    public void printText(String text) {
        setup.setMainText(text);
    }

    public void updateDealerHand(String text) {
        setup.setDealerHand(text);
    }

    public void endGame(CardPlayer player, CardPlayer dealer){
        setup.endGameScreen(player, dealer);
    }

}