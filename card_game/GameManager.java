package card_game;
import java.util.*;    

public class GameManager {
  private ArrayList<CardPlayer> allPlayers;
  private ArrayList<CardPlayer> initialPlayers;
  private Dealer dealer;
  private WinCheck winCheck;

  public GameManager(ArrayList<CardPlayer> allPlayers, ArrayList<CardPlayer> initialPlayers, Dealer dealer){
    this.dealer = dealer;
    this.allPlayers = allPlayers;
    this.initialPlayers = initialPlayers;
    this.winCheck = new WinCheck(this.allPlayers);
  }

  public void play(){
    setup();
    initialDeal();
    turnHandler();
  }

  public void setup(){
    System.out.println("~* Filling the Deck! *~"); 
    dealer.fillDeck();
    System.out.println("~* Shuffling the Deck! *~"); 
    dealer.shuffleCards();
  }

  public ArrayList<CardPlayer> checkInitialPlayers(){
    return this.initialPlayers;
  }

  public void initialDeal(){
    System.out.println("~* Initial deal! All players receive two cards! *~"); 
    for(int i=1; i<=2; i++) {
      dealAll();
    }
    this.winCheck.showAllHands();
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

  public void turnHandler(){
    if (this.allPlayers.size() == 2) 
    {
      ArrayList<String> choices = new ArrayList<String>();
      for (CardPlayer player : this.allPlayers){
        System.out.println("~* You're up, " + player.getName() + ". Stick or twist? *~");
        String choice = System.console().readLine().toLowerCase();
        choices.add(choices.size(), choice);
        System.out.println("You  " + choice + "!");
        if(choice.equals("twist")) 
        {
          Card card = deal(player);
          System.out.println("You receive the " + card.getName() + ".");
          System.out.println(player.getName() + ", your hand is now worth " + this.winCheck.calcScore(player) + ".");
          System.out.println("*~*~*~*~*");
          this.winCheck.bustCheck(player);
        } 
        else 
        {
          System.out.println(player.getName() + ", your hand is worth " + this.winCheck.calcScore(player) + ".");
        }
      }
      String choice1 = choices.get(0);
      String choice2 = choices.get(1);

      

      if( choice1.equals("stick") && choice2.equals("stick") ) {
       winCheck();
      } else {
       turnHandler();     
      }
    }
  }

  public void winCheck(){
    this.winCheck.showAllHands();
    this.winCheck.winCheck();
  }

  public static void endGame(){
    System.out.println("~* Thanks for playing! *~");
    System.out.println("~* Type P to play again, or anything else to quit. *~");
    String choice = System.console().readLine().toLowerCase();
    if (choice.equals("p")) {
      Runner.setup();
    } else {
      System.exit(0);
    }
  }

}