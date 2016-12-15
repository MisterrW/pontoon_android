import java.util.*;
import static org.junit.Assert.*;
import org.junit.*;
import card_game.*;

public class HandTest {

  @Test
  public void canReceiveCard() {
    Hand hand = new Hand();
    Card card = new Card(CardSuit.HEARTS, CardValue.THREE);
    hand.receiveCard(card);
    assertEquals(1, hand.showHand().size());
  }

  @Test
  public void canShowHand() {
    Card card = new Card(CardSuit.HEARTS, CardValue.THREE);
    Hand hand = new Hand();
    ArrayList<Card> testCards = new ArrayList<Card>();
    testCards.add(0, card);
    hand.receiveCard(card);
    assertEquals(testCards, hand.showHand());
  }

}