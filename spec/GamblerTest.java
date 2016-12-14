import static org.junit.Assert.*;
import org.junit.*;
import card_game.*;
import java.util.*;

public class GamblerTest {
  Gambler player;

  @Before
  public void initialize() {
    player = new Gambler("Will", new Hand());
    Card card1 = new Card(CardSuit.DIAMONDS, CardValue.THREE);
    Card card2 = new Card(CardSuit.HEARTS, CardValue.SIX);
    player.receiveCard(card1);
    player.receiveCard(card2);
  }

  @Test
  public void testHand(){
    assertEquals(2, player.showHand().size());
    assertEquals(CardValue.SIX, player.showHand().get(1).getValue());
  }

  @Test
  public void testRecieveCard(){
    player.receiveCard(new Card(CardSuit.HEARTS, CardValue.ACE));
    assertEquals(CardValue.ACE, player.showHand().get(2).getValue());
  }

  @Test
  public void testName(){
    assertEquals("Will", player.getName());
  }

  @Test
  public void testNormalScore(){
    player.setScore(9);
    assertEquals(9, player.getScore());
  }

  @Test
  public void testSpecialScore(){
    player.setSpecialScore("pontoon");
    assertEquals("pontoon", player.getSpecialScore());
  }
}
