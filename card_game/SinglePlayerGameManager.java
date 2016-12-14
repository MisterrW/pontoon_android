package card_game;
import java.util.*;    

public class SinglePlayerGameManager {
  private ArrayList<CardPlayer> allPlayers;
  private ArrayList<CardPlayer> initialPlayers;
  private Dealer dealer;
  private Gambler player;
  private WinCheck winCheck;

  public SinglePlayerGameManager(ArrayList<CardPlayer> allPlayers, ArrayList<CardPlayer> initialPlayers, Dealer dealer, Gambler player1){
    this.dealer = dealer;
    this.player = player1;
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
    this.winCheck.showHand(this.player);
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
    String choice = new String();
    if (this.allPlayers.size() == 2) 
    {
      
        System.out.println("~* You're up, " + this.player.getName() + ". Stick or twist? *~");
        choice = System.console().readLine().toLowerCase();
        System.out.println("You  " + choice + "!");
        if(choice.equals("twist")) 
        {
          Card card = deal(this.player);
          System.out.println("You receive the " + card.getName() + ".");
          System.out.println(this.player.getName() + ", your hand is now worth " + this.winCheck.calcScore(this.player) + ".");
          System.out.println("*~*~*~*~*");
          this.winCheck.bustCheck(this.player);
        } 
        else 
        {
          System.out.println(this.player.getName() + ", your hand is worth " + this.winCheck.calcScore(this.player) + ".");
        }
      }

      if (choice.equals("stick")) {
       System.out.println("You stick! Dealer's round!");
       dealersRound();
      } else {
       turnHandler();     
      }
    }

  public void dealersRound() {
    if (this.winCheck.calcScore(this.dealer) <= 16) {
      System.out.println("Des takes a card!");
      deal(this.dealer);
      this.winCheck.bustCheck(this.dealer);
      dealersRound();
    }
    System.out.println("Des sticks! Show!");
    winCheck();
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