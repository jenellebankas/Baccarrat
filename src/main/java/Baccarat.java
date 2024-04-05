import java.util.Scanner;

public class Baccarat {
  public Baccarat() {
  }

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

  }

  private static void checkNatural(BaccaratHand punto, BaccaratHand banco) {

    if(punto.isNatural()) {
      System.out.print("Player has a Natural\n");
    }

    if(banco.isNatural()) {
      System.out.print("Banker has a Natural\n");
    }
  }

  private static void finalResult(int numRounds, int playerWins, int bankerWins, int numTies) {
    System.out.printf("%d rounds played\n", numRounds);
    System.out.printf("%d player wins\n", playerWins);
    System.out.printf("%d banker wins\n", bankerWins);
    System.out.printf("%d ties\n", numTies);
  }

  private static void rules(BaccaratHand punto, BaccaratHand banco, Shoe shoe) {

    if (punto.value() <= 5) {
      initialDeal(punto, shoe);
      System.out.println("Dealing third card to player...");

      if (banco.value() <= 2) {
        initialDeal(banco, shoe);
      } else if (banco.value() == 3 && punto.cards.getLast().value() == 8) {
        initialDeal(banco, shoe);
      } else if (banco.value() == 4 && punto.cards.getLast().value() == 2
        || punto.cards.getLast().value() == 3 || punto.cards.getLast().value() == 4
        || punto.cards.getLast().value() == 5 || punto.cards.getLast().value() == 6
        || punto.cards.getLast().value() == 7) {
        initialDeal(banco, shoe);
      } else if (banco.value() == 5 && punto.cards.getLast().value() == 4
        || punto.cards.getLast().value() == 5 || punto.cards.getLast().value() == 6
        || punto.cards.getLast().value() == 7) {
        initialDeal(banco, shoe);
      } else if (banco.value() == 6 && punto.cards.getLast().value() == 6 ||
        punto.cards.getLast().value() == 7) {
        initialDeal(banco, shoe);
      }
    }
  }

  private static int checkWins(BaccaratHand punto, BaccaratHand banco) {

    int puntoDifference = 9 - punto.value();
    int bancoDifference = 9 - banco.value();

    if (puntoDifference == bancoDifference) {
      System.out.println("Tie");
      return 1;
    } else if (puntoDifference < bancoDifference) {
      System.out.println("Player win!");
      return 2;
    } else {
      System.out.println("Banker win!");
      return 3;
    }
  }
  public static void main(String[] args) {

    int numRounds = 1;
    int bankerWins = 0;
    int playerWins = 0;
    int numTies = 0;

    char userInput = 'y';


    try {
      BaccaratHand punto = makeHand();
      BaccaratHand banco = makeHand();

      Shoe shoe = makeShoe();

      shoe.shuffle();

      initialDeal(punto, shoe);
      initialDeal(banco, shoe);
      initialDeal(punto, shoe);
      initialDeal(banco, shoe);

      if (args.length == 0) {
        while (shoe.size() >= 6) {

          System.out.printf("Round %d\n", numRounds);

          printResults(punto, banco);
          rules(punto, banco, shoe);
          checkNatural(punto, banco);
          if (checkWins(punto, banco) == 1) {
            numTies++;
          } else if (checkWins(punto, banco) == 2) {
            playerWins++;
          } else {
            bankerWins++;
          }
          numRounds++;
        }
      } else if (args.length == 1) {
        if (args[0].equals("-i") || args[0].equals("--interactive")) {

          while (userInput == 'y' || userInput == 'Y') {

            System.out.printf("Round %d\n", numRounds);

            printResults(punto, banco);
            rules(punto, banco, shoe);
            checkNatural(punto, banco);
            if (checkWins(punto, banco) == 1) {
              numTies++;
            } else if (checkWins(punto, banco) == 2) {
              playerWins++;
            } else {
              bankerWins++;
            }
            numRounds++;

            System.out.print("Another round? (y/n): ");

            Scanner scanner = new Scanner(System.in);
            userInput = scanner.next().charAt(0);

            if (userInput == 'n') {
              break;
            }
          }
        }
      }

      finalResult(numRounds, playerWins, bankerWins, numTies);
      System.exit(0);

    } catch (CardException cards) {
      System.err.printf("CardException: %s%n", cards);
      System.exit(1);
    }
  }
}
