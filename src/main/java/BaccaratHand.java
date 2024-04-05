// TODO: Implement the BaccaratHand class in the file

import java.util.LinkedList;
import java.util.List;



public class BaccaratHand extends CardCollection {

  private final List<BaccaratCard> hand;
  public BaccaratHand() {
    hand = new LinkedList<>();
  }

  public int size() {
    return hand.size();
  }

  public void add(BaccaratCard card) {
    hand.add(card);
  }

  public int value() {
    return 0;
  }

  public boolean isNatural() {
    return false;
  }

  @Override
  public String toString() {
    return String.format("%s%s %s%s",
      hand.get(0).getRank(),
      hand.get(0).getSuit(),
      hand.get(1).getRank(),
      hand.get(1).getSuit());
  }
}