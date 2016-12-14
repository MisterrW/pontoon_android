import static org.junit.Assert.*;
import org.junit.*;
import card_game.*;
import java.util.*;

public class WinCheckTest {
  Gambler player;
  Dealer dealer;
  ArrayList<CardPlayer> allPlayers;
  WinCheck winCheck;

  @Before
  public void initialize() {
    player = new Gambler("Will", new Hand());
    dealer = new Dealer("Dealer", new Hand(), new Deck());
    allPlayers = new ArrayList<CardPlayer>();
    allPlayers.add(player);
    winCheck = new WinCheck(allPlayers);
  }

  @Test
  public void checkNormalScore(){
    Card card1 = new Card(CardSuit.DIAMONDS, CardValue.THREE);
    Card card2 = new Card(CardSuit.HEARTS, CardValue.SIX);
    player.receiveCard(card1);
    player.receiveCard(card2);
    
    winCheck.calcScore(player);
    assertEquals(9, player.getScore());
  }

  @Test
  public void testAcesLowIfOver21(){
    Card card1 = new Card(CardSuit.DIAMONDS, CardValue.NINE);
    Card card2 = new Card(CardSuit.HEARTS, CardValue.ACE);
    Card card3 = new Card(CardSuit.HEARTS, CardValue.EIGHT);
    player.receiveCard(card1);
    player.receiveCard(card2);
    player.receiveCard(card3);
    
    winCheck.calcScore(player);
    assertEquals(18, player.getScore());
  }

  @Test
  public void testPontoonStatement(){
    Card card1 = new Card(CardSuit.DIAMONDS, CardValue.JACK);
    Card card2 = new Card(CardSuit.HEARTS, CardValue.ACE);
    player.receiveCard(card1);
    player.receiveCard(card2);
    
    winCheck.calcScore(player);
    assertEquals("pontoon", player.getSpecialScore());
  }

  @Test
  public void test5CardTrickStatement(){
    Card card1 = new Card(CardSuit.HEARTS, CardValue.TWO);
    Card card2 = new Card(CardSuit.DIAMONDS, CardValue.TWO);
    Card card3 = new Card(CardSuit.CLUBS, CardValue.TWO);
    Card card4 = new Card(CardSuit.SPADES, CardValue.TWO);
    Card card5 = new Card(CardSuit.HEARTS, CardValue.THREE);
    player.receiveCard(card1);
    player.receiveCard(card2);
    player.receiveCard(card3);
    player.receiveCard(card4);
    player.receiveCard(card5);
    
    winCheck.calcScore(player);
    assertEquals("5 card trick", player.getSpecialScore());
  }

  @Test
  public void testNormalWin(){
    allPlayers.add(dealer);
    Card card1 = new Card(CardSuit.DIAMONDS, CardValue.NINE);
    Card card2 = new Card(CardSuit.HEARTS, CardValue.ACE);
    Card card3 = new Card(CardSuit.HEARTS, CardValue.EIGHT);
    Card card4 = new Card(CardSuit.DIAMONDS, CardValue.EIGHT);
    player.receiveCard(card1);
    player.receiveCard(card2);
    dealer.receiveCard(card3);
    dealer.receiveCard(card4);
    
    assertEquals("Will wins with 20!", winCheck.fakeWinCheck());
  }

  @Test
  public void testNormalDraw(){
    allPlayers.add(dealer);
    Card card1 = new Card(CardSuit.DIAMONDS, CardValue.NINE);
    Card card2 = new Card(CardSuit.HEARTS, CardValue.ACE);
    player.receiveCard(card1);
    player.receiveCard(card2);
    dealer.receiveCard(card1);
    dealer.receiveCard(card2);
    
    assertEquals("It's a draw!", winCheck.fakeWinCheck());
  }

  @Test
  public void testBustWin(){
    allPlayers.add(dealer);
    Card card1 = new Card(CardSuit.DIAMONDS, CardValue.NINE);
    Card card2 = new Card(CardSuit.HEARTS, CardValue.TEN);
    dealer.receiveCard(card1);
    dealer.receiveCard(card2);
    
    assertEquals("Dealer wins with 19!", winCheck.fakeWinCheck());
  }

  @Test
  public void testPontoonWin(){
    allPlayers.add(dealer);
    Card card1 = new Card(CardSuit.DIAMONDS, CardValue.JACK);
    Card card2 = new Card(CardSuit.HEARTS, CardValue.ACE);
    Card card3 = new Card(CardSuit.HEARTS, CardValue.EIGHT);
    Card card4 = new Card(CardSuit.DIAMONDS, CardValue.EIGHT);
    player.receiveCard(card1);
    player.receiveCard(card2);
    dealer.receiveCard(card3);
    dealer.receiveCard(card4);
    
    assertEquals("Will wins with pontoon!", winCheck.fakeWinCheck());
  }

  @Test
  public void testPontoonDraw(){
    allPlayers.add(dealer);
    Card card1 = new Card(CardSuit.DIAMONDS, CardValue.JACK);
    Card card2 = new Card(CardSuit.HEARTS, CardValue.ACE);
    Card card3 = new Card(CardSuit.CLUBS, CardValue.JACK);
    Card card4 = new Card(CardSuit.SPADES, CardValue.ACE);
    player.receiveCard(card1);
    player.receiveCard(card2);
    dealer.receiveCard(card3);
    dealer.receiveCard(card4);
    
    assertEquals("Wow, two pontoons! Draw!", winCheck.fakeWinCheck());
  }

  @Test
  public void test5CardWin(){
    allPlayers.add(dealer);
    Card card1 = new Card(CardSuit.DIAMONDS, CardValue.TWO);
    Card card2 = new Card(CardSuit.HEARTS, CardValue.ACE);
    Card card3 = new Card(CardSuit.HEARTS, CardValue.TWO);
    Card card4 = new Card(CardSuit.DIAMONDS, CardValue.THREE);
    Card card5 = new Card(CardSuit.DIAMONDS, CardValue.THREE);
    Card card6 = new Card(CardSuit.DIAMONDS, CardValue.JACK);
    player.receiveCard(card1);
    player.receiveCard(card2);
    player.receiveCard(card3);
    player.receiveCard(card4);
    player.receiveCard(card5);
    dealer.receiveCard(card6);
    
    assertEquals("Will wins with 5 card trick!", winCheck.fakeWinCheck());
  }

  @Test
  public void test5CardDraw(){
    allPlayers.add(dealer);
    Card card1 = new Card(CardSuit.DIAMONDS, CardValue.TWO);
    Card card2 = new Card(CardSuit.HEARTS, CardValue.ACE);
    Card card3 = new Card(CardSuit.HEARTS, CardValue.TWO);
    Card card4 = new Card(CardSuit.DIAMONDS, CardValue.THREE);
    Card card5 = new Card(CardSuit.DIAMONDS, CardValue.THREE);
    player.receiveCard(card1);
    player.receiveCard(card2);
    player.receiveCard(card3);
    player.receiveCard(card4);
    player.receiveCard(card5);
    dealer.receiveCard(card1);
    dealer.receiveCard(card2);
    dealer.receiveCard(card3);
    dealer.receiveCard(card4);
    dealer.receiveCard(card5);
    
    assertEquals("Wow, two 5 card tricks! Draw!", winCheck.fakeWinCheck());
  }

  // @Test
  // public void testBustCheck(){
  //   Card card1 = new Card(CardSuit.DIAMONDS, CardValue.JACK);
  //   Card card2 = new Card(CardSuit.HEARTS, CardValue.TEN);
  //   Card card3 = new Card(CardSuit.HEARTS, CardValue.EIGHT);
  //   player.receiveCard(card1);
  //   player.receiveCard(card2);
  //   player.receiveCard(card3);
    
  //   winCheck.bustCheck(player);
  //   assertEquals(0, allPlayers.size());
  // }


  
}
