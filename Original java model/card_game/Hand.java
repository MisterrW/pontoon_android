package card_game;
import java.util.*;

public class Hand {
  private ArrayList<Card> cards;

 public Hand(){
  this.cards = new ArrayList<Card>();
  }

 public void receiveCard(Card card){
  cards.add(cards.size(), card);
 }

 public ArrayList<Card> showHand(){
  return cards;
 }

 public Card returnCard(){
  if(cards.size() > 0) {
    Card card = cards.remove(cards.size() - 1);
    return card;
  }
  return null;
 }

}