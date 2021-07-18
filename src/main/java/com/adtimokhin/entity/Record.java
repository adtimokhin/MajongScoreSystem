package com.adtimokhin.entity;

import com.adtimokhin.Main;

public class Record {

    private static final int PLAYER_COUNT = Main.PLAYER_COUNT;

    // store information about a single round when it finishes

    private int numberOfRound;
    private char leadingWind;

    private int[] scores;

    private int winnerPlayerId;
    private int leadingPlayerId;

    public Record(int numberOfRound, Record pastRound) {
        this.numberOfRound = numberOfRound;
        leadingWind = Main.winds[(numberOfRound - 1) / PLAYER_COUNT];
        scores = new int[PLAYER_COUNT];

        for (int i = 0; i < scores.length; i++) {
            scores[i] = 0;
        }

        // identifying leading player
        if (pastRound == null) {
            leadingPlayerId = 1;
        } else {
            leadingPlayerId = pastRound.getNewLeadingPlayerId();
        }

    }

    public void printRoundInfo(Player leadingPlayer) {
        System.out.println("Information about this round:");
        System.out.println("Round number: " + this.getNumberOfRound());
        System.out.println("Leading wind: " + this.getLeadingWind());
        System.out.println("Leading player: " + leadingPlayer.getName());
    }


    public void setNumberOfRound(int numberOfRound) {
        this.numberOfRound = numberOfRound;
    }

    public void setLeadingWind(char leadingWind) {
        this.leadingWind = leadingWind;
    }

    private int getNewLeadingPlayerId() {
        if (winnerPlayerId == leadingPlayerId) {
            return winnerPlayerId;
        }
        int e = (leadingPlayerId + 1) % PLAYER_COUNT;
        if(e == 0){
            return PLAYER_COUNT;
        }
        return e;
    }

    public int getNumberOfRound() {
        return numberOfRound;
    }

    public char getLeadingWind() {
        return leadingWind;
    }

    public int[] getScores() {
        return scores;
    }

    public int getWinnerPlayerId() {
        return winnerPlayerId;
    }

    public int getLeadingPlayerId() {
        return leadingPlayerId;
    }

    public void setWinnerPlayerId(int winnerPlayerId) {
        this.winnerPlayerId = winnerPlayerId;
    }

    public void setLeadingPlayerId(int leadingPlayerId) {
        this.leadingPlayerId = leadingPlayerId;
    }

    public void setScores(int[] scores) {
        this.scores = scores;
    }
}
