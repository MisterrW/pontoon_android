package com.example.user.androidpontoon;
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

    public void singleplayerSetup() {
        ArrayList<CardPlayer> allPlayers = new ArrayList<CardPlayer>();
        setMainText("You will be playing against Des the Slightly Dim Dealer.");

        printText("Type your name.");

//    String gamblerName = setup.getUserInput();
        String gamblerName = "player";

        Dealer dealer = new Dealer("Des the Dealer", new Hand(), new Deck());
        Gambler player1 = new Gambler(gamblerName, new Hand());
        setPlayerName(gamblerName);

        allPlayers.add(allPlayers.size(), player1);
        allPlayers.add(allPlayers.size(), dealer);

        game = new SinglePlayerGameManager(this, allPlayers, dealer, player1);
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
        setup.showToast(toastText);
    }

    public void setPlayerName(String playerName){
        this.playerName = playerName;
    }

    public void setPlayerHand(ArrayList<Card> cards){
        this.playerHand = new ArrayList<Card>(cards);
    }

    public void showHand(CardPlayer player) {
        String hand = showAnyHand(player);
        updateHand(hand);
    }

    public void showDealerHand(CardPlayer dealer) {
        String dealerHand = showAnyHand(dealer);
        updateDealerHand(dealerHand);
    }

    public String showAnyHand(CardPlayer player){
        String hand = String.format(player.getName() + " has these cards: ");
        ArrayList<Card> cards = player.showHand();
        for (Card card : cards) {
            String tempString = String.format("- " + card.getName());
            hand = String.format(hand + tempString);
        }
        return hand;
    }

//  public String getPlayerInput() {
////    return System.console().readLine().toLowerCase();
//      String input = setup.getUserInput();
//      return input;
//  }

    public void getPlayerChoice() {
//    return System.console().readLine().toLowerCase();
        setup.getPlayerChoice();
    }

    public void setPlayerChoice(String choice) {
        game.turnHandler(choice);
    }

    public void printText(String text) {
        setup.setMainText(text);
    }

    public void updateHand(String text) {
        setup.setPlayerHand(text);
    }

    public void updateDealerHand(String text) {
        setup.setDealerHand(text);
    }

    public void endGame(){
        setup.endGameScreen();
    }

}