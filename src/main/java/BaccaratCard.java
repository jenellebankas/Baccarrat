

public class BaccaratCard extends Card {

  private final Rank rank;
  private final Suit suit;

  public BaccaratCard(Rank r, Suit s) {
   super(r, s);
   this.rank = r;
   this.suit = s;
  }

  public Rank getRank() {
    return rank;
  }

  public Suit getSuit() {
    return suit;
  }

  @Override public String toString() {
    return String.format("%s%s", rank, suit);
  }

  public boolean equals(Object other) {
    return false;
  }

  public int compareTo(Card other) {
    return 0;
  }

  public int value() {
    return 0;
  }
}