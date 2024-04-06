import java.util.Scanner;

public class Baccarat {

  private int numRounds = 1;
  private int bankerWins = 0;
  private int playerWins = 0;
  private int numTies = 0;

  public final BaccaratHand punto = new BaccaratHand();
  public final BaccaratHand banco = new BaccaratHand();
  public final Shoe shoe = new Shoe(6);
  public Baccarat() {

  }

  private void firstHand() {

    shoe.shuffle();

    for (int i = 0; i < 2; i++) {
      punto.cards.add(shoe.deal());
      banco.cards.add(shoe.deal());
    }
  }

  private void addToHand(BaccaratHand currentHand) {
    currentHand.cards.add(shoe.deal());
  }

  private  void printResults() {

    System.out.printf("Player: %s = %d\n", punto.cards.toString(), punto.value());
    System.out.printf("Banker: %s = %d\n", banco.cards.toString(), banco.value());

  }

  private int checkNatural() {

    if(punto.isNatural()) {
      System.out.println("Player has a Natural");
      System.out.println("Player win!");
      playerWins++;
      return 1;
    } else if(banco.isNatural()) {
      System.out.println("Banker has a Natural");
      System.out.println("Banker win!");
      bankerWins++;
      return 1;
    } else if(punto.isNatural() && banco.isNatural()){
      System.out.println("Tie");
      numTies++;
      return 1;
    } else {
      return 0;
    }
  }

  private void finalResult() {

    System.out.printf("\n%d rounds played\n", numRounds - 1);
    System.out.printf("%d player wins\n", playerWins);
    System.out.printf("%d banker wins\n", bankerWins);
    System.out.printf("%d ties\n", numTies);

  }

  private void rules() {

    if (punto.value() <= 5) {

      addToHand(punto);
      System.out.println("Dealing third card to player...");

      if (banco.value() <= 2) {
        addToHand(banco);
        System.out.println("Dealing third card to banker...");
      } else if (banco.value() == 3 && punto.cards.getLast().value() == 8) {
        addToHand(banco);
        System.out.println("Dealing third card to banker...");
      } else if (banco.value() == 4 && punto.cards.getLast().value() == 2
        || punto.cards.getLast().value() == 3 || punto.cards.getLast().value() == 4
        || punto.cards.getLast().value() == 5 || punto.cards.getLast().value() == 6
        || punto.cards.getLast().value() == 7) {
        addToHand(banco);
        System.out.println("Dealing third card to banker...");
      } else if (banco.value() == 5 && punto.cards.getLast().value() == 4
        || punto.cards.getLast().value() == 5 || punto.cards.getLast().value() == 6
        || punto.cards.getLast().value() == 7) {
        addToHand(banco);
        System.out.println("Dealing third card to banker...");
      } else if (banco.value() == 6 && punto.cards.getLast().value() == 6 ||
        punto.cards.getLast().value() == 7) {
        addToHand(banco);
        System.out.println("Dealing third card to banker...");
      }
      printResults();

    }
  }

  private void gamePlay() {

    System.out.printf("\nRound %d\n", numRounds);

    firstHand();
    printResults();
    if(checkNatural() == 0) {

      rules();

      int check = checkWins();
      if (check == 1) {
        numTies++;
      } else if (check == 2) {
        playerWins++;
      } else {
        bankerWins++;
      }

    }

    numRounds++;
    clearGame();

  }

  private void clearGame() {
    punto.discard();
    banco.discard();
  }
  private int checkWins() {

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

    Baccarat baccaratGame = new Baccarat();
    char userInput = 'y';

    try {

      if (args.length == 0) {
        while (baccaratGame.shoe.size() > 6) {
          baccaratGame.gamePlay();
        }
      } else if (args.length == 1) {
        if (args[0].equals("-i") || args[0].equals("--interactive")) {

          while (userInput == 'y' || userInput == 'Y') {

            baccaratGame.gamePlay();

            System.out.print("Another round? (y/n): ");
            Scanner input = new Scanner(System.in);
            userInput = input.next().charAt(0);

            if (userInput == 'n') {
              break;
            }
          }
        }
      }

      baccaratGame.finalResult();
      System.exit(0);

    } catch (CardException cards) {
      System.err.printf("CardException: %s%n", cards);
      System.exit(1);
    }
  }
}
