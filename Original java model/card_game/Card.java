package card_game;

public class Card {
  private CardSuit suit;
  private CardValue value;

  public Card(CardSuit suit, CardValue value) {
    this.suit = suit;
    this.value = value;
  }

  public CardSuit getSuit() {
    return this.suit;
  }

  public CardValue getValue() {
    return this.value;
  }

  public String getName() {
      String value = getValue().toString().toLowerCase();
      String valueCaps = value.substring(0, 1).toUpperCase() + value.substring(1);
      String suit = getSuit().toString().toLowerCase();
      String suitCaps = suit.substring(0, 1).toUpperCase() + suit.substring(1);
      return String.format(valueCaps + " of " + suitCaps);
  }

}