// TODO: Implement the Shoe class in this file

public class Shoe extends CardCollection{

  public Shoe(int decks) throws CardException {
    if (decks != 6 || decks != 8) {
      throw new CardException("Incorrect Value");
    }
  }

  public int size() {
    return 0;
  }

  public void shuffle() {

  }

  public Card deal() {
    return null;
  }
}