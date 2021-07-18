package com.adtimokhin.utils.gameSaves;

import com.adtimokhin.entity.Player;
import com.adtimokhin.entity.Record;
import com.adtimokhin.utils.fileInteraction.FileReader;

import java.io.IOException;
import java.util.ArrayList;

public class PropertyRecover {

    private ArrayList<Player> players = new ArrayList<>();
    private ArrayList<Record> records = new ArrayList<>();
    private int fullRoundsPlayed;

    public void loadAllInfo(String filename) {
        FileReader reader = new FileReader();
        try {
            reader.openConnection(filename);
            String[] properties = reader.readline().split("},");
            for (int i = 0; i < properties.length; i++) {
                if (properties[i].startsWith("Player")) {
                    players.add(getPLayer(properties[i]));
                } else if (properties[i].startsWith("Record")) {
                    records.add(getRecord(properties[i]));
                } else if (properties[i].startsWith("fullRoundsPlayed")) {
                    String[] parts = properties[i].split(":");
                    fullRoundsPlayed = findNumber("fullRoundsPlayed", parts);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public Player[] getPlayers() {
        Player[] pl = new Player[players.size()];
        for (int i = 0; i < pl.length; i++) {
            pl[i] = players.get(i);
        }
        return pl;
    }

    public Record[] getRecords() {
        Record[] records1 = new Record[16];
        for (int i = 0; i < records.size(); i++) {
            records1[i] = records.get(i);
        }
        return records1;
    }

    public int getFullRoundsPlayed() {
        return fullRoundsPlayed;
    }


    private int parseInt(String str) {
        try {
            return Integer.parseInt(str);
        } catch (NumberFormatException e) {
            return -1;
        }
    }

    private int findNumber(String propertyName, String... parts) {
        if (parts.length == 2) {
            if (parts[0].equals(propertyName)) {
                String number = parts[1].substring(1, (parts[1].length() - 1));
                return parseInt(number);
            }
        }
        return -1;
    }

    private String findString(String propertyName, String... parts) {
        if (parts.length == 2) {
            if (parts[0].equals(propertyName)) {
                // todo: There are no checks if this string is valid.
                return parts[1].substring(1, (parts[1].length() - 1));
            }
        }
        return "NO DATA FOUND";
    }

    private Record getRecord(String property) {
        String listOfProperties = property.substring("Record:{".length(), (property.length()));
        String[] properties = listOfProperties.split(";");

        Record record = new Record(0, null);

        for (String p :
                properties) {
            String[] parts = p.split(":");
            String propertyName = parts[0];
            switch (propertyName) {
                case "numberOfRound":
                    record.setNumberOfRound(findNumber("numberOfRound", parts));
                    break;

                case "leadingWind":
                    String windDirection = findString("leadingWind", parts);
                    if (windDirection.length() == 1) {
                        record.setLeadingWind(windDirection.toCharArray()[0]);
                    } else {
                        record.setLeadingWind('A');
                    }
                    break;

                case "winnerPlayerId":
                    record.setWinnerPlayerId(findNumber("winnerPlayerId", parts));
                    break;

                case "leadingPlayerId":
                    record.setLeadingPlayerId(findNumber("leadingPlayerId", parts));
                    break;

                case "scores":
                    record.setScores(findIntArray("scores", parts));
            }
        }

        return record;
    }

    private int[] findIntArray(String propertyName, String[] parts) {

        String arrayInString = findString(propertyName, parts);
        String[] numbers = arrayInString.split(",");
        int[] ints = new int[numbers.length];
        for (int i = 0; i < numbers.length; i++) {
            if (i == 0) {
                ints[i] = parseInt(numbers[i].substring(1));
            }else if(i == (numbers.length-1)){
                ints[i] = parseInt(numbers[i].substring(0,1));
            }else {
                ints[i] = parseInt(numbers[i]);
            }
        }
        return ints;
    }

    private Player getPLayer(String property) {
        String listOfProperties = property.substring("Player:{".length(), (property.length()));
        String[] properties = listOfProperties.split(";");

        Player player = new Player(0);

        for (String property1 : properties) {
            String[] parts = property1.split(":");
            String propertyName = parts[0];
            switch (propertyName) {
                case "name":
                    player.setName(findString("name", parts));
                    break;
                case "id":
                    player.setId(findNumber("id", parts));

                    break;
                case "score":
                    player.setScore(findNumber("score", parts));
                    break;
                case "windDirection":
                    String windDirection = findString("windDirection", parts);
                    if (windDirection.length() == 1) {
                        player.setWindDirection(windDirection.toCharArray()[0]);
                    } else {
                        player.setWindDirection('A');
                    }
                    break;
            }
        }


        return player;

    }

}
