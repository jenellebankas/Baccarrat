
public class BaccaratHand extends CardCollection {


  public BaccaratHand() {

  }

  public int size() {
    return super.size();
  }

  public void add(Card card) {
    super.add(card);
  }

  @Override public int value() {

    int total = 0;

    for(Card card: cards) {
      total += card.value();
    }

    if (total > 10) {
      total = total % 10;
    }

    return total;

  }

  public boolean isNatural() {
    return size() == 2 && (value() == 8 || value() == 9);
  }

  @Override
  public String toString() {

    int i = 1;

    StringBuilder builder = new StringBuilder();

    for(Card card: cards) {

      i++;

      builder.append(card.toString());

      if (i == cards.size()) {
        builder.append(' ');
      }
    }

    return String.format("%s", builder);
  }
}