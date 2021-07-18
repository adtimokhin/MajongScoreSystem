package com.adtimokhin;

import com.adtimokhin.entity.Player;
import com.adtimokhin.utils.input.InputValidator;

public class ScoreCounter {

    private static final String OPEN_THREE_NOT_SPECIAL_IN_A_ROW_SYMBOL = "OTA";
    private static final String OPEN_THREE_ONE_OR_NINE_IN_A_ROW_SYMBOL = "OT1";
    private static final String OPEN_THREE_DRAGONS_SYMBOL = "OTD";
    private static final String OPEN_THREE_WINDS_SYMBOL = "OTW";


    private static final String OPEN_FOUR_NOT_SPECIAL_IN_A_ROW_SYMBOL = "OFA";
    private static final String OPEN_FOUR_ONE_OR_NINE_IN_A_ROW_SYMBOL = "OF1";
    private static final String OPEN_FOUR_DRAGONS_SYMBOL = "OFD";
    private static final String OPEN_FOUR_WINDS_SYMBOL = "OFW";

    private static final String CLOSE_THREE_NOT_SPECIAL_IN_A_ROW_SYMBOL = "CTA";
    private static final String CLOSE_THREE_ONE_OR_NINE_IN_A_ROW_SYMBOL = "CT1";
    private static final String CLOSE_THREE_DRAGONS_SYMBOL = "CTD";
    private static final String CLOSE_THREE_WINDS_SYMBOL = "CTW";


    private static final String CLOSE_FOUR_NOT_SPECIAL_IN_A_ROW_SYMBOL = "CFA";
    private static final String CLOSE_FOUR_ONE_OR_NINE_IN_A_ROW_SYMBOL = "CF1";
    private static final String CLOSE_FOUR_DRAGONS_SYMBOL = "CFD";
    private static final String CLOSE_FOUR_WINDS_SYMBOL = "CFW";

    private static final String SEQUENCE_NOT_SPEACIAL_SYMBOL = "S";

    private static final String PAIR_DRAGONS_SYMBOL = "PD";
    private static final String PAIR_OWN_WINDS_SYMBOL = "OW";
    private static final String PAIR_ANY_WINDS_SYMBOL = "PW";

    private static final String STOP_SYMBOL = "-";
    private static final String INFO_SYMBOL = "!";

    private static final int TWO_POINTS = 2;
    private static final int FOUR_POINTS = 4;
    private static final int EIGHT_POINTS = 4;
    private static final int SIXTEEN_POINTS = 16;
    private static final int TWENTY_POINTS = 20;
    private static final int THIRTYTWO_POINTS = 32;


    // todo: test this method.
    public static int[] calculateScores(Player[] players, int winnerId, boolean leaderIsWinner) {
        System.out.println("If you\'re unsure of what symbols to use, enter \"" + INFO_SYMBOL + "\" symbol.");

        int[] scores = new int[players.length];

        for (int i = 0; i < players.length; i++) {
            System.out.println("COUNTING SCORE FOR PLAYER " + players[i].getId() + ". " + players[i].getName());

            int score = 0;
            int numberOfSuits = 0;
            boolean combinationsHaveOnlyOnesAndNines = true;
            int numberOfDragonAndWindsCombinations = 0;
            int numberOfAnyWinds = 0;

            //some variables for calculating number of extra doubles for winning ma-johng.
            boolean noSequences = true;
            int numberOfDragonGroups = 0;
            int numberOfWindGroups = 0;
            boolean isTherePairOfDragons = false;
            boolean isTherePairOfWinds = false;

            String scoreSymbol = InputValidator.untilGetString("Enter category").toUpperCase();
            while (!scoreSymbol.equals(STOP_SYMBOL)) {

                if (scoreSymbol.equals(INFO_SYMBOL)){
                    printInfo();
                    scoreSymbol = InputValidator.untilGetString("Enter category");
                    continue;
                }

                //checking score for combinations with suits
                if (scoreSymbol.endsWith("A") || scoreSymbol.endsWith("1") || scoreSymbol.endsWith("S")) {

                    if (numberOfSuits == 0) {
                        if (InputValidator.yesOrNo("Do you have more than one suit in your combination?")) {
                            numberOfSuits = 2;
                        } else {
                            numberOfSuits = 1;
                        }
                    }

                    if (!scoreSymbol.endsWith("S")) {
                        int combinationScore = TWO_POINTS;
                        if (scoreSymbol.endsWith("1")) {
                            combinationScore *= 2;
                        } else {
                            combinationsHaveOnlyOnesAndNines = false;
                        }
                        if (scoreSymbol.contains("F")) {
                            combinationScore *= 4;
                        }
                        if (scoreSymbol.startsWith("C")) {
                            combinationScore *= 2;
                        }

                        score += combinationScore * InputValidator.untilGotIntInRange("How many of those do you have?", 1, 4);
                    } else {
                        noSequences = false;
                        combinationsHaveOnlyOnesAndNines = false;
                    }
                    //checking score for combinations with dragons/winds made out of 3 or 4 cards
                } else if ((scoreSymbol.endsWith("D") || scoreSymbol.endsWith("W")) && scoreSymbol.length() != 2) {
                    int combinationScore = FOUR_POINTS;
                    int num = InputValidator.untilGotIntInRange("How many of those do you have?", 1, 4);
                    if (scoreSymbol.endsWith("W")) {
                        if (InputValidator.yesOrNo("Are those winds yours or leading?")) {
                            numberOfDragonAndWindsCombinations += num;
                        }else {
                            numberOfAnyWinds += num;
                        }
                        numberOfWindGroups += num;
                    } else {
                        numberOfDragonGroups += num;
                        numberOfDragonAndWindsCombinations += num;

                    }
                    if (scoreSymbol.contains("F")) {
                        combinationScore *= 4;
                    }
                    if (scoreSymbol.startsWith("C")) {
                        combinationScore *= 2;
                    }
                    score += combinationScore * num;
                } else if (scoreSymbol.equals(PAIR_DRAGONS_SYMBOL)) {
                    score += TWO_POINTS;
                    isTherePairOfDragons = true;
                } else if (scoreSymbol.equals(PAIR_OWN_WINDS_SYMBOL)) {
                    score += TWO_POINTS;
                    isTherePairOfWinds = true;
                } else if (scoreSymbol.equals(PAIR_ANY_WINDS_SYMBOL)) {
                    isTherePairOfWinds = true;
                } else {
                    System.out.println("We couldn\'t recognise the symbol you\'ve used.");
                }

                scoreSymbol = InputValidator.untilGetString("Enter category");
            }

            // calculating number of doubles

            int doubles = 0;
            doubles += numberOfDragonAndWindsCombinations;
            if (numberOfSuits == 1) {
                if (numberOfDragonAndWindsCombinations != 0 || numberOfAnyWinds != 0) {
                    doubles++;
                } else {
                    doubles += 3;
                }
            } else if (numberOfSuits == 0) {
                if (numberOfDragonAndWindsCombinations != 0 || numberOfAnyWinds != 0) {
                    doubles += 3;
                }
            }

            if (combinationsHaveOnlyOnesAndNines && score > 0) {
                doubles++;
            }

            //extra doubles for the winner

            if(players[i].getId() == winnerId){
                if(noSequences && score  > 0){
                    doubles++;
                }
                if (score == 0){
                    doubles ++;
                }
                if(numberOfWindGroups == 3 && isTherePairOfWinds){
                    doubles ++;
                }
                if (numberOfDragonGroups == 2 && isTherePairOfDragons){
                    doubles++;
                }
                if(leaderIsWinner){
                    doubles++;
                }

                doubles += InputValidator.untilGotIntInRange("How many are there other doubles you should get?" , 0 ,4);
                score += TWENTY_POINTS;
            }

            // finding the final score;
            for (int j = 0; j < doubles; j++) {
                score *= 2;
            }


            scores[i] = score;
        }

        return scores;

    }


    private static void printInfo(){
        System.out.println("Sorry, I was a bit too lazy to do this section. Better luck next time :)");
    }
}
