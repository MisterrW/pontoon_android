package com.example.user.androidpontoon;

import java.util.*;

public class GameState {
    private String mainText;

    private ArrayList<Card> playerHand;
    private ArrayList<Card> dealersHand;
    private String handValue;

    private String playerName;
    private String toastText;

    private SinglePlayerGameManager game;

    public GameState(){
        this.mainText = "Welcome to Pontoon";
        printText(mainText);
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
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public void showHand(CardPlayer player) {
        Gambler player1 = (Gambler)player;
        printText(showAnyHand(player));
        // getPlayerChoice();
    }

    public void showHoleCard(Dealer dealer) {
        Card card = dealer.showHand().get(0);
        printText("Dealer's hole card:");
        printText(" - " + card.getName());
    }

    public void showDealerHand(CardPlayer dealer) {
        String dealerHand = showAnyHand(dealer);
        updateDealerHand(dealerHand);
        printText(dealerHand);
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
        printText("Type stick or twist.");
        setPlayerChoice(System.console().readLine().toLowerCase());
    }

    public void setPlayerChoice(String choice) {
        game.turnHandler(choice);
        getPlayerChoice();
    }

    public void printText(String text) {
        System.out.println(text);
    }

    public void updateDealerHand(String text) {
        // setup.setDealerHand(text);
    }

    public void endGame(CardPlayer player, CardPlayer dealer){
        showHand(player);
        showDealerHand(dealer);
        printText("Game over!");
        System.exit(0);
        // setup.endGameScreen(player, dealer);
    }

}