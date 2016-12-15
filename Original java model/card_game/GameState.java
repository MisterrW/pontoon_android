package card_game;
import java.util.*;

public class GameState {
  private String mainText;
  private ArrayList<Card> playerHand;
  private String handValue;
  private ArrayList<Card> dealersHand;
  private String playerName;
  private String toastText;

  public GameState(){
    this.mainText = "Welcome";
  }

  public void singleplayerSetup() {
    ArrayList<CardPlayer> allPlayers = new ArrayList<CardPlayer>();
    setMainText("You will be playing against Des the Slightly Dim Dealer.");

    printText("Type your name.");
    String gamblerName = System.console().readLine();

    Dealer dealer = new Dealer("Des the Dealer", new Hand(), new Deck());
    Gambler player1 = new Gambler(gamblerName, new Hand());
    setPlayerName(gamblerName);

    allPlayers.add(allPlayers.size(), player1);
    allPlayers.add(allPlayers.size(), dealer);

    SinglePlayerGameManager game = new SinglePlayerGameManager(this, allPlayers, dealer, player1);
    game.play();
  }

  public void setMainText(String text) {
    mainText = text;
    printText(mainText);
  }

  public void setToastText(String text) {
    toastText = text;
    printText(toastText);
  }

  public void setPlayerName(String playerName){
    this.playerName = playerName;
  }

  public void setPlayerHand(ArrayList<Card> cards){
    this.playerHand = new ArrayList<Card>(cards);
  }

  public void showHand(CardPlayer player){
    String hand = String.format(player.getName() + " has these cards: ");
    ArrayList<Card> cards = player.showHand();
    for (Card card : cards) {
      String tempString = String.format("- " + card.getName());
      hand = String.format(hand + tempString);
    }
    printText(hand);
  }

  public String getPlayerInput() {
    return System.console().readLine().toLowerCase();
  }

  public void printText(String text) {
    System.out.println(text);
  }

}