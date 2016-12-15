import static org.junit.Assert.*;
import org.junit.*;
import card_game.*;

public class DeckTest {

  @Test
  public void checkDeckLength() {
    Deck deck = new Deck();
    deck.fillDeck();
    assertEquals(52, deck.getCards().size());
  }

  @Test
  public void checkCanDealCard(){
    Deck deck = new Deck();
    deck.fillDeck();
    Card testCard = new Card(CardSuit.HEARTS, CardValue.THREE);
    Card card = deck.dealCard();
    assertEquals(card.getClass(), testCard.getClass());
  }

  @Test
  public void checkDealingEmptiesDeck() {
    Deck deck = new Deck();
    deck.fillDeck();
    deck.dealCard();
    assertEquals(51, deck.getCards().size());
  }

  @Test
  public void checkReturningCards() {
    Deck deck = new Deck();
    deck.fillDeck();
    Card card = deck.dealCard();
    deck.returnCardToBackOfDeck(card);
    assertEquals(52, deck.getCards().size());
  }

}
