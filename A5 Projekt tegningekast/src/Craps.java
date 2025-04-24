package playdice.crapsgame;

import java.util.Arrays;
import java.util.Scanner;

public class Craps { //Lavet af Thor, Lucas og Emil

    private static int point = 0;

    public static void main(String[] args) {
        System.out.println("Welcome to the game of Craps");
        printRules();
        System.out.println();

        playCraps();

        System.out.println();
        System.out.println("Thank you for playing Craps");
    }

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_RESET = "\u001B[0m";
    public static final String ANSI_BLUE = "\u001B[34m";

    private static void printRules() {
        System.out.println(ANSI_BLUE + "==========================================================================================================");
        System.out.println("Rules of Craps:");
        System.out.println("The player throws Two dice. If the sum of the eyes is equal to 7 or 11 the player wins in the first round.");
        System.out.println("If the player gets the sum of eyes equel to 2,3 or 12 the player lose in the first round.");
        System.out.println("If the player gets 4,5,6,8,9 or 10 in the first round.");
        System.out.println("The first sum will be the winner number and 7 will now be the losing number to get.");
        System.out.println("The player keep rolling until the first sum is rolled or 7.");
        System.out.println("==========================================================================================================" + ANSI_RESET);
    }

    private static void playCraps() {
        Scanner scanner = new Scanner(System.in);

        // Initiates the winning point
        System.out.print("Press Enter to Roll");
        String answer = scanner.nextLine();
        point = rollDice();


        if (point == 7 || point == 11) {
            System.out.println(ANSI_GREEN + "You won by rolling " + point + ANSI_RESET);
            scanner.close();
        } else if (point == 2 || point == 3 || point == 12) {
            System.out.println(ANSI_RED + "You lost by rolling " + point + ANSI_RESET);
            scanner.close();
        } else {
            System.out.println("The current winning number is " + point);

            if (rollForPoint() == true) {
                System.out.println(ANSI_GREEN + "You won by rolling the same number again!" + ANSI_RESET);

            } else {
                System.out.println(ANSI_RED + "You lost by rolling 7" + ANSI_RESET);

            }
        }
        scanner.close();
    }

    private static int rollDice() {
        int roll1 = (int) (Math.random() * 6 + 1);
        int roll2 = (int) (Math.random() * 6 + 1);

        int rollSum = roll1 + roll2;

        return rollSum;
    }

    private static boolean rollForPoint() {
        Scanner scanner = new Scanner(System.in);

        System.out.print("Roll again? ");
        scanner.nextLine();
        int newRoll = rollDice();
        System.out.println();
        System.out.println("The winning number is " + point);
        System.out.println("You just rolled " + newRoll);

        while (newRoll != 7) {
            System.out.print("Roll again? ");
            scanner.nextLine();
            newRoll = rollDice();
            System.out.println();
            System.out.println("The winning number is " + point);
            System.out.println("You just rolled " + newRoll);

        if (newRoll == point) {
            return true;
        }
            else if (newRoll == 7) {
                return false;

            }
        }
        return true;
    }
}
