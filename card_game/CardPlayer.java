package card_game;
import java.util.*;

public interface CardPlayer {

  public ArrayList<Card> showHand();
  public void receiveCard(Card card);
  public String getName();
  public void setScore(int score);
  public int getScore();
  public void setSpecialScore(String specialScore);
  public String getSpecialScore();
}