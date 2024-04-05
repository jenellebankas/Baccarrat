import java.util.Scanner;

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

  public static void showResults() {

  }


  public static void main(String[] args) {

    int numRounds = 0;
    int bancoWins = 0;
    int playerWins = 0;
    int numTies = 0;

    BaccaratHand punto = makeHand();
    BaccaratHand banco = makeHand();

    Shoe shoe = makeShoe();

    shoe.shuffle();

    initialDeal(punto, shoe);
    initialDeal(banco, shoe);
    initialDeal(punto, shoe);
    initialDeal(banco, shoe);

    printResults(punto, banco);

    if (args.length == 0) {
      while(shoe.size() >= 6) {
        numRounds++;
      }
    }

    if(args[1] == "-i" || args[1] == "--interactive") {
      System.out.println("Another round?");
      Scanner scanner = new Scanner(System.in);
      String userInput = scanner.nextLine();
      while (userInput.equals('y') || userInput.equals('Y')) {

        numRounds++;
      }
    }
  }
}
