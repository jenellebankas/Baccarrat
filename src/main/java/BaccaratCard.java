
public class BaccaratCard extends Card {

  public BaccaratCard(Rank r, Suit s) {
   super(r, s);
  }

  public Rank getRank() {
    return super.getRank();
  }

  public Suit getSuit() {
    return super.getSuit();
  }

  @Override public String toString() {
    return String.format("%s", super.toString());
  }

  @Override public boolean equals(Object other) {
    return super.equals(other);
  }

  @Override public int compareTo(Card other) {
    return super.compareTo(other);

  }

    public int value() {

    int tempValue = super.value();
    if (tempValue >= 10) {
      tempValue = tempValue % 10;
    }

    return tempValue;

  }
}