package card_game;
import java.util.*;

class GameState {
  private String mainText;

  public void setMainText(String text) {
    mainText = text;
    printText(mainText);
  }

  public void printText(String text) {
    System.out.println(text);
  }

}

