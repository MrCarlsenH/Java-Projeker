package playdice.pigsgame;

import java.util.Scanner;

public class Pig { //Lavet af Thor, Lucas og Emil

    //Alle ANSI Strings er til at give farve til tekst.
    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    //Globale variabler til at kunne anvende, hvor vi vil.
    private static int roundsPlayer1 = 0;
    private static int roundsPlayer2 = 0;
    private static int Rolls = 0;
    private static int sum;
    private static int score;
    private static double rollsPlayer2;
    private static double rollsPlayer1;

    public static void main(String[] args) {
        System.out.println("Welcome to the game of Pig");
        printRules();
        System.out.println();

        playPigs();

        System.out.println();
        System.out.println("The average amount of rolls for " +ANSI_PURPLE + "Player1" + ANSI_RESET + " each round: " + rollsPlayer1/(double) roundsPlayer1);
        System.out.println("The average amount of rolls for " +ANSI_BLUE + "Player2" + ANSI_RESET + " each round: " + rollsPlayer2/(double) roundsPlayer2);
        System.out.println("");
        System.out.println("Thank you for playing Pig");
    }

    private static void printRules() {
        System.out.println(ANSI_BLUE + "================================================================================");
        System.out.println("Rules of Pig:");
        System.out.println("The first player throws a dice until they get 1 eye or decide to stop");
        System.out.println("If the player gets 1 eye they lose their round and the points earned that round");
        System.out.println("If they decide to stop before getting 1 eye, the points earned that round");
        System.out.println("will be added to their total points.");
        System.out.println("The first player to reach the decided amount of points wins.");
        System.out.println("================================================================================" + ANSI_RESET);
    }

    private static void playPigs() {
        int player1Points = 0;
        int player2Points = 0;
        int currentPlayer = 0;

        Scanner scanner = new Scanner(System.in);

        System.out.println("Choose amount of points needed to win.");
        score = scanner.nextInt();

        while (player1Points < score && player2Points < score) {
            Scanner scanner1 = new Scanner(System.in);
            if (currentPlayer % 2 == 0) {
                System.out.print(ANSI_PURPLE + "Player 1" + ANSI_RESET + " ready?");
                String answer = scanner1.nextLine();
                playRound(player1Points);
                player1Points = player1Points + sum;

                System.out.println("Current points: ");
                System.out.println("---------------");
                System.out.println(ANSI_PURPLE + "Player 1: " + ANSI_RESET + player1Points);
                System.out.println(ANSI_BLUE + "Player 2: " + ANSI_RESET + player2Points);
                System.out.println("---------------");
                rollsPlayer1 = rollsPlayer1 + Rolls;
                System.out.println("Current Total rolls for player1: " + rollsPlayer1);
                roundsPlayer1++;
                Rolls = 0;
                sum = 0;
                currentPlayer++;
            } else {
                System.out.print(ANSI_BLUE + "Player 2: " + ANSI_RESET + "ready?");
                String answer = scanner1.nextLine();
                playRound(player2Points);
                player2Points = player2Points + sum;

                System.out.println("Current points: ");
                System.out.println("---------------");
                System.out.println(ANSI_PURPLE + "Player 1: " + ANSI_RESET + player1Points);
                System.out.println(ANSI_BLUE + "Player 2: " + ANSI_RESET + player2Points);
                System.out.println("---------------");
                rollsPlayer2 = rollsPlayer2 + Rolls;
                System.out.println("Current Total rolls for player2: " + rollsPlayer2);
                roundsPlayer2++;
                Rolls = 0;
                sum = 0;
                currentPlayer++;

            }
        }
    }

    private static int playRound(int currentPlayerPoints) {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Roll? ('-' stops) ");
        String answer = scanner.nextLine();

        while (!answer.equals("-")) {
            int face = rollDie();

            if (face == 1) {
                sum = 0;
                System.out.println(ANSI_RED + "You rolled 1, and lost your points" + ANSI_RESET);
                Rolls++;
                break;

            }

            sum = sum + face;
            System.out.println("You rolled: " + face);
            System.out.println("Current round points: " + sum);
            Rolls++;
            System.out.println("Current rolls: " + Rolls);

            if ((currentPlayerPoints + sum) >= score) {
                System.out.println(ANSI_GREEN + "You won" + ANSI_RESET);
                break;
            }

            System.out.print("Roll? ('-' stops) ");
            answer = scanner.nextLine();

        }
        return sum + Rolls;
    }

    private static int rollDie() {
        return (int) (Math.random() * 6 + 1);
    }

}
