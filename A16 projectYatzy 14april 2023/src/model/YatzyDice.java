package model;

import java.util.Random;

public class YatzyDice {
    // Face values of the 5 dice.
    // 1 <= values[i] <= 6 for i in [0..4]
    private int[] values = new int[5];

    // Number of times the 5 dice have been thrown.
    // 0 <= throwCount <= 3.
    private int throwCount = 0;

    // Random number generator.
    private final Random random = new Random();

    /**
     * Return the 5 face values of the dice.
     */
    public int[] getValues() {
        return values;
    }

    /**
     * Set the 5 face values of the dice.<br/>
     * Pre: 1 <= values[i] <= 6 for i in [0..4].<br/>
     * Note: This method is only to be used in tests.
     */
    void setValues(int[] values) {
        this.values = values;
    }

    /**
     * Return the number of times the 5 dice has been thrown.
     */
    public int getThrowCount() {
        return throwCount + 1;
    }

    /**
     * Reset the throw count.
     */
    public void resetThrowCount() {
        throwCount = 0;
    }

    /**
     * Roll the 5 dice. Only roll dice that are not hold.<br/>
     * Note: holdStatus[index] is true, if die no. index is hold (for index in [0..4]).
     */
    public void throwDice(boolean[] holdStatus) {
        for (int i = 0; i < values.length; i++) {
            if (!holdStatus[i]) {
                values[i] = random.nextInt(1, 6);
            }
        }
        throwCount++;
    }

    // -------------------------------------------------------------------------

    /**
     * Return all results possible with the current face values.<br/>
     * The order of the results is the same as on the score board.<br/>
     * Note: This is an optional method. Comment this method out,<br/>
     * if you don't want use it.
     */
    public int[] getResults() {
        int[] results = new int[15];
        for (int i = 0; i <= 5; i++) {
            results[i] = this.sameValuePoints(i + 1);
        }
        results[6] = this.onePairPoints();
        results[7] = this.twoPairPoints();
        results[8] = this.threeSamePoints();
        results[9] = this.fourSamePoints();
        results[10] = this.fullHousePoints();
        results[11] = this.smallStraightPoints();
        results[12] = this.largeStraightPoints();
        results[13] = this.chancePoints();
        results[14] = this.yatzyPoints();

        return results;
    }

    // -------------------------------------------------------------------------

    // Return an int[7] containing the frequency of face values.
    // Frequency at index v is the number of dice with the face value v, 1 <= v <= 6.
    // Index 0 is not used.
    // Note: This method can be used in several of the following methods.
    private int[] frequency() {
        int[] frequency = new int[7];
        for (int i = 0; i < values.length; i++) {
            frequency[values[i]]++;
        }
        return frequency;
    }

    /**
     * Return same-value points for the given face value.<br/>
     * Returns 0, if no dice has the given face value.<br/>
     * Pre: 1 <= value <= 6;
     */
    public int sameValuePoints(int value) {
        int valuePoints = 0;
        valuePoints = frequency()[value] * value;
        return valuePoints;
    }

    /**
     * Return points for one pair (for the face value giving the highest points).<br/>
     * Return 0, if there aren't 2 dice with the same face value.
     */
    public int onePairPoints() {
        int pairPoints = 0;
        for (int i = 1; i < frequency().length; i++) {
            if (frequency()[i] >= 2) {
                if (pairPoints < i * 2) {
                    pairPoints = i * 2;
                }
            }
        }
        return pairPoints;
    }

    /**
     * Return points for two pairs<br/>
     * (for the 2 face values giving the highest points).<br/>
     * Return 0, if there aren't 2 dice with the same face value<br/>
     * and 2 other dice with the same but different face value.
     */
    public int twoPairPoints() {
        int twoPairPoints = 0;
        int pair = 0;
        int count = 0;
        for (int i = 1; i < frequency().length; i++) {
            if (frequency()[i] >= 2) {
                pair += i * 2;
                count++;
                }
            }
        if(count == 2){
            twoPairPoints = pair;
        }
        return twoPairPoints;
    }

    /**
     * Return points for 3 of a kind.<br/>
     * Return 0, if there aren't 3 dice with the same face value.
     */
    public int threeSamePoints() {
        int threeSame = 0;
        for (int i = 1; i < frequency().length; i++) {
            if (frequency()[i] >= 3) {
                threeSame = i * 3;
            }
        }
        return threeSame;
    }

    /**
     * Return points for 4 of a kind.<br/>
     * Return 0, if there aren't 4 dice with the same face value.
     */
    public int fourSamePoints() {
        int fourSame = 0;
        for (int i = 1; i < frequency().length; i++) {
            if (frequency()[i] >= 4) {
                fourSame = i * 4;
            }
        }
        return fourSame;
    }

    /**
     * Return points for full house.<br/>
     * Return 0, if there aren't 3 dice with the same face value<br/>
     * and 2 other dice with the same but different face value.
     */
    public int fullHousePoints() {
        int fHouse3 = 0;
        int fHouse2 = 0;
        int fHouse = 0;
        for (int i = 1; i < frequency().length; i++) {
            if (frequency()[i] == 3) {
                fHouse3 = i * 3;
            }
        }
        for (int j = 0; j < frequency().length; j++) {
            if (frequency()[j] == 2) {
                fHouse2 = j * 2;
            }
        }
        if (fHouse3 > 0 && fHouse2 > 0) {
            fHouse = fHouse3 + fHouse2;
        }
        return fHouse;
    }

    /**
     * Return points for small straight.<br/>
     * Return 0, if the dice aren't showing 1,2,3,4,5.
     */
    public int smallStraightPoints() {

        int lStraight = 0;
        int noget = 0;
        for (int i = 0; i < frequency().length; i++) {
            if(frequency()[i] == 1) {
                for (int j = 0; j < values.length; j++) {
                    if (values[j] <= 5 && values[j] != 6) {
                        noget += values[j];
                    }
                }
            }
            if(noget == 15){
                lStraight = 15;
            }
        }
        return lStraight;
    }


    /**
     * Return points for large straight.<br/>
     * Return 0, if the dice aren't showing 2,3,4,5,6.
     */
    public int largeStraightPoints() {
        int bStraight = 0;
        int noget = 0;
        for (int i = 0; i < frequency().length; i++) {
            if(frequency()[i] == 1) {
                for (int j = 0; j < values.length; j++) {
                    if (values[j] <= 6 && values[j] != 1) {
                        noget += values[j];
                    }
                }
            }
            if(noget == 20){
                bStraight = 20;
            }
        }
        return bStraight;
    }


    /**
     * Return points for chance (the sum of face values).
     */
    public int chancePoints() {
        int chance = 0;
        for (int i = 0; i < values.length; i++) {
            chance += values[i];
        }
        return chance;
    }

    /**
     * Return points for yatzy (50 points).<br/>
     * Return 0, if there aren't 5 dice with the same face value.
     */
    public int yatzyPoints() {
        int yatzy = 0;
        for (int i = 1; i < frequency().length; i++) {
            if(frequency()[i] == 5){
                yatzy = 50;
            }
        }
        return yatzy;
    }
}
