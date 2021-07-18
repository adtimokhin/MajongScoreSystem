package com.adtimokhin;

import com.adtimokhin.entity.Player;
import com.adtimokhin.entity.Record;
import com.adtimokhin.utils.gameSaves.PropertyRecover;
import com.adtimokhin.utils.gameSaves.PropertySaver;
import com.adtimokhin.utils.input.InputValidator;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

    public static final int PLAYER_COUNT = 4;
    public static char[] winds = {'E', 'S', 'W', 'N'};

    private static BufferedReader reader = new BufferedReader(
            new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException {

        System.out.println("Enter a name of already existing save, or create a new one!");
        String filename = reader.readLine();
        File file = new File(filename);
        if (file.exists()) {
            PropertyRecover recover = new PropertyRecover();
            recover.loadAllInfo(filename);
            int numberOfFullRounds = recover.getFullRoundsPlayed();
            Player[] players = recover.getPlayers();
            Record[] records = recover.getRecords();
            startAGame(numberOfFullRounds, players, records, filename);
        } else {
            startAGame(0, null, null, filename);
        }

//        ScoreCounter.calculateScores(initializeNewGame(), 1, true);
    }


    private static Player[] initializeNewGame() {
        Player[] players = new Player[PLAYER_COUNT];

        // Get players' names and winds
        for (int i = 0; i < PLAYER_COUNT; i++) {
            Player player = new Player();
            player.setWindDirection(winds[i]);
            player.setName(InputValidator.untilGetString("Enter a name for a player with the wind direction " + winds[i]));
            player.setScore(0);
            players[i] = player;
        }

        return players;

    }

    private static void displayInformationAtTheEndOfTheRound(Player[] players, Record record) {

        System.out.println("-------------- The end of the round information --------------");
        System.out.println("Round number: " + record.getNumberOfRound());
        System.out.println("Leading wind: " + record.getLeadingWind());
        System.out.println("Leading player: " + players[record.getLeadingPlayerId() - 1].getName());
        System.out.println("Winner player: " + players[record.getWinnerPlayerId() - 1].getName());
        System.out.println("----------------------------------------------");
        System.out.println("Points scored:\n");
        for (int i = 0; i < players.length; i++) {
            System.out.println(players[i].getName());
            System.out.println(record.getScores()[i] + "\n");
        }

        System.out.println("Point status of each player:");
        for (int i = 0; i < players.length; i++) {
            Player player = players[i];
            System.out.println(player.getName() + ": " + player.getScore());
        }
    }

    private static void startAGame(int startingRound, Player[] players, Record[] records, String filename) throws IOException {
        PropertySaver propertySaver = new PropertySaver();
        if (startingRound == 0) {
            players = initializeNewGame();
            records = new Record[PLAYER_COUNT * 4];
        }

        for (int i = startingRound; i < records.length; i++) {
            Record record;
            if (i == 0) {
                record = new Record((i + 1), null);
            } else {
                record = new Record((i + 1), records[i - 1]);
            }
            record.printRoundInfo(players[record.getLeadingPlayerId() - 1]);
            System.out.println("When you finish the round, please press Enter");
            reader.read();
            //identifying the player who won the round
            StringBuilder stringBuilderMSG = new StringBuilder();

            stringBuilderMSG.append("Who won the round?\n");
            for (int j = 0; j < players.length; j++) {
                stringBuilderMSG
                        .append(j + 1)
                        .append(") ")
                        .append(players[j].getName())
                        .append("\n");
            }
            stringBuilderMSG.append("-1) No one won");

            int winnerId = InputValidator.untilGotIntInRange(stringBuilderMSG.toString(), -1, PLAYER_COUNT);
            record.setWinnerPlayerId(winnerId);
            if (winnerId == -1) {
                records[i] = record;
                continue;
            }

            //Getting scores for the round
            record.setScores(ScoreCounter.calculateScores(players, winnerId, winnerId == record.getLeadingPlayerId()));
            records[i] = record;

            //updating score of every player
            for (int j = 0; j < players.length; j++) {
                if ((j + 1) == winnerId) {
                    continue;
                }
                for (int k = 0; k < players.length; k++) {
                    if (j == k) {
                        continue;
                    }
                    if ((k + 1) == winnerId) {
                        if ((j + 1) == record.getLeadingPlayerId()) {
                            players[j].setScore(players[j].getScore() - (record.getScores()[k] * 2));
                            players[k].setScore(players[k].getScore() + (record.getScores()[k] * 2));
                        } else {
                            players[j].setScore(players[j].getScore() - (record.getScores()[k]));
                            players[k].setScore(players[k].getScore() + (record.getScores()[k]));
                        }
                    }else if (record.getScores()[j] > record.getScores()[k]) {
                        players[k].setScore(players[k].getScore() - (record.getScores()[j]));
                        players[j].setScore(players[j].getScore() + (record.getScores()[j]));
                    }
                }
            }

            // displaying information at the end of the round
            displayInformationAtTheEndOfTheRound(players, record);

            //saving information.
            propertySaver.saveGameDetails(players, records, filename);
        }

        reader.close();


    }
}
