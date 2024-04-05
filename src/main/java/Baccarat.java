public class Baccarat {

  private static void initialDeal(BaccaratHand current, Shoe currentShoe) {

    current.cards.add(currentShoe.deal());

  }

  private static BaccaratHand makeHand() {

    return new BaccaratHand();
  }

  private static Shoe makeShoe() {

    return new Shoe(6);
  }

  private static void printResults(BaccaratHand punto, BaccaratHand banco) {

    System.out.printf("Player: %s = %d\n", punto.cards.toString(), punto.value());
    System.out.printf("Banker: %s = %d\n", banco.cards.toString(), banco.value());

    if(punto.isNatural()) {
      System.out.print("Player has a Natural\n");
    }

    if(banco.isNatural()) {
      System.out.print("Banker has a Natural\n");
    }
  }


  public static void main(String[] args) {

    BaccaratHand punto = makeHand();
    BaccaratHand banco = makeHand();

    Shoe shoe = makeShoe();

    shoe.shuffle();

    initialDeal(punto, shoe);
    initialDeal(banco, shoe);
    initialDeal(punto, shoe);
    initialDeal(banco, shoe);

    printResults(punto, banco);

    //if(args[1] == "-i" || args[1] == "--interactive") {

    //}

  }
}
