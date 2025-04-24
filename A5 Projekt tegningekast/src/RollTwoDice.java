import java.util.Arrays;
import java.util.Scanner;

public class RollTwoDice { //Lavet af Thor, Lucas og Emil

    private static int rollCount = 0;
    private static int sum = 0;
    private static int pairs = 0;

    private static int max = 0;

    private static int[] eyeFreqeuncy = new int[6];

    public static void main(String[] args) {
        System.out.println("Welcome to the game of RollTwoDice");
        printRules();
        System.out.println();

        playTwoDice();

        System.out.println();
        System.out.println("Thank you for playing RollTwoDice");
    }

    private static void printRules() {
        System.out.println("=====================================================");
        System.out.println("Rules of RollTwoDice:");
        System.out.println("The player throws Two dice as long as he/she wants.");
        System.out.println("=====================================================");
    }

    private static void playTwoDice() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Roll? ('no' stops) ");
        String answer = scanner.nextLine();
        while (!answer.equals("no")) {
            int[] face = rollDice();
            System.out.println("Eyes: " + Arrays.toString(face));
            System.out.println();

            updateStatistics(face);

            System.out.print("Roll? ('no' stops) ");
            answer = scanner.nextLine();
        }

        printStatistics();
        scanner.close();
    }

    private static int[] rollDice() {
        int[] diceRolls = new int[2];
        int roll1 = (int) (Math.random() * 6 + 1);
        int roll2 = (int) (Math.random() * 6 + 1);

        diceRolls[0] = roll1;
        diceRolls[1] = roll2;

        return diceRolls;
    }

    private static void updateStatistics(int[] face) {
        //Ligger antal gange der bliver kastet sammen.
        rollCount++;
        //Tager den totale værdi af øjnene og ligger dem sammen til en total værdi.
        sum = sum + face[0] + face[1];
        //Et par øjne.
        if (face[0] == face[1]){
            pairs++;
        }
        //Opdatere hvis rollSum er højere end det tidligere.
        int rollSum = face[0] + face[1];
        if (rollSum > max) {
            max = rollSum;
        }
//        int[] eyeFreqeuncy = new int[6];
        for (int i = 0; i < face.length; i++) {
            if (face[i] == 1) {
                eyeFreqeuncy[0]++;
            }
            if (face[i] == 2) {
                eyeFreqeuncy[1]++;
            }
            if (face[i] == 3) {
                eyeFreqeuncy[2]++;
            }
            if (face[i] == 4) {
                eyeFreqeuncy[3]++;
            }
            if (face[i] == 5) {
                eyeFreqeuncy[4]++;
            }
            if (face[i] == 6) {
                eyeFreqeuncy[5]++;
            }
            //Optimale måde, hvor kun en linje behøves at skrives.
            // for (int i = 0; i < face.length; i++){
            //eyeFreqeuncy(face[i] - 1)++;}
        }
    }

    private static void printStatistics() {
        System.out.println("\nResults:");
        //Udskriver antal kast spillere har slået.
        System.out.println("-------");
        System.out.printf("%17s %4d\n", "Roll count:", rollCount);
        //Udskriver den totale sum af antal øjne der bliver slået.
        System.out.println("-------");
        System.out.printf("%17s %4d\n", "Sum of rolls:", sum);
        //Udskriver antal par slået.
        System.out.println("-------");
        System.out.printf("%17s %4d\n", "Pairs count:", pairs);
        //Udskrivers højeste værdi
        System.out.println("-------");
        System.out.printf("%17s %4d\n", "Highest value:", max);
        //Udskriver de forskellige antal øjne
        System.out.println("-------");
//        System.out.println("Antallet samme øjne: " + Arrays.toString(eyeFreqeuncy));
        System.out.println("Number of timse rolled 1: " + (eyeFreqeuncy[0]));
        System.out.println("Number of timse rolled 2: " + (eyeFreqeuncy[1]));
        System.out.println("Number of timse rolled 3: " + (eyeFreqeuncy[2]));
        System.out.println("Number of timse rolled 4: " + (eyeFreqeuncy[3]));
        System.out.println("Number of timse rolled 5: " + (eyeFreqeuncy[4]));
        System.out.println("Number of timse rolled 6: " + (eyeFreqeuncy[5]));
    }
}
