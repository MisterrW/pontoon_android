package card_game;
import java.util.*;

class Runner {

  public static void main(String[] args) {
    setup();
  }

  public static void setup() {
    System.out.println("Welcome to Will's (Sort Of) Pontoon!");
    System.out.println("You can play 2-player multiplayer, or one person play against a (moronic) robot dealer.");
    System.out.println("Type S for singleplayer, or anything else for multiplayer.");
    
    String choice = System.console().readLine().toLowerCase();
    if (choice.equals("s")) {
      singleplayerSetup();
    } else {
      multiplayerSetup();
    }

  }

  public static void multiplayerSetup() {
    ArrayList<CardPlayer> allPlayers = new ArrayList<CardPlayer>();
    ArrayList<CardPlayer> initialPlayers = new ArrayList<CardPlayer>();
    System.out.println("In this variant, all cards are visible to all players. It's a game for two players: a dealer and a gambler.");
    
    System.out.println("Choose a name for the dealer.");
    String dealerName = System.console().readLine();

    System.out.println("And a name for the gambler.");
    String gamblerName = System.console().readLine();

    Dealer dealer = new Dealer(dealerName, new Hand(), new Deck());
    Gambler player1 = new Gambler(gamblerName, new Hand());

    allPlayers.add(allPlayers.size(), player1);
    allPlayers.add(allPlayers.size(), dealer);
    initialPlayers.add(initialPlayers.size(), player1);
    initialPlayers.add(initialPlayers.size(), dealer);

    GameManager game = new GameManager(allPlayers, initialPlayers, dealer);
    game.play();
  }

  public static void singleplayerSetup() {
    ArrayList<CardPlayer> allPlayers = new ArrayList<CardPlayer>();
    ArrayList<CardPlayer> initialPlayers = new ArrayList<CardPlayer>();
    System.out.println("You will be playing against Des the Slightly Dim Dealer.");

    System.out.println("Type your name.");
    String gamblerName = System.console().readLine();

    Dealer dealer = new Dealer("Des the Dealer", new Hand(), new Deck());
    Gambler player1 = new Gambler(gamblerName, new Hand());

    allPlayers.add(allPlayers.size(), player1);
    allPlayers.add(allPlayers.size(), dealer);
    initialPlayers.add(initialPlayers.size(), player1);
    initialPlayers.add(initialPlayers.size(), dealer);

    SinglePlayerGameManager game = new SinglePlayerGameManager(allPlayers, initialPlayers, dealer, player1);
    game.play();
  }
}