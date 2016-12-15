package card_game;
import java.util.*;

public class Deck {
 private ArrayList<Card> cards;

 public Deck(){
  this.cards = new ArrayList<Card>();
  }

  public void fillDeck() {
  CardSuit[] suits = CardSuit.values();
  CardValue[] values = CardValue.values();
  for (CardSuit suit : suits) {
    for (CardValue value : values) {
      cards.add(new Card(suit, value));
      }
    }
  }

  public void shuffleCards() {
    Collections.shuffle(cards);
  }

  public ArrayList<Card> getCards() {
    return cards;
  }

  public Card dealCard() {
    Card card = cards.remove(cards.size() - 1);
    return card;
  }

  public void returnCardToBackOfDeck(Card card) {
    cards.add(0, card);
  }
}

