import static org.junit.Assert.*;
import org.junit.*;
import card_game.*;

public class CardTest {

  @Test
  public void checkCardHasSuit() {
    Card card = new Card(CardSuit.HEARTS, CardValue.THREE);  
    assertEquals(CardSuit.HEARTS, card.getSuit());
  }

  @Test
  public void checkCardHasValue() {
    Card card = new Card(CardSuit.HEARTS, CardValue.THREE);
    assertEquals(CardValue.THREE, card.getValue());
  }

}