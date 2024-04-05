import java.util.Collections;

public class Shoe extends CardCollection {


  public Shoe(int decks) throws CardException {

    for (int i = 0; i < decks; i ++) {
      for (BaccaratCard.Suit suit : BaccaratCard.Suit.values()) {
        for (Card.Rank rank : Card.Rank.values()) {
          BaccaratCard tempCard = new BaccaratCard(rank, suit);
          cards.add(tempCard);
        }
      }
    }

    if (!(decks == 6 || decks == 8)) {
      throw new CardException("Incorrect Value");
    }
  }

  public int size() {
    return super.size();
  }

  public void shuffle() {
    Collections.shuffle(cards);
  }

  public Card deal() {

    if (cards.isEmpty()) {
      throw new CardException("No cards in shoe.");
    } else {
      return cards.removeFirst();
    }
  }
}