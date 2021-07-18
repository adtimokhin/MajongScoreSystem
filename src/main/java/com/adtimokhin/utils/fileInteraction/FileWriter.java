package com.adtimokhin.utils.fileInteraction;

import com.adtimokhin.entity.Player;
import com.adtimokhin.entity.Record;

import java.io.BufferedWriter;
import java.io.IOException;

public class FileWriter implements FileIntegrator {

    private BufferedWriter writer;


    @Override
    public void openConnection(String filename) throws IOException {
        writer = new BufferedWriter(new java.io.FileWriter(filename));
    }

    public void savePlayer(Player player) throws IOException {
        writer.write("Player:{");
        writer.write("name:\"" + player.getName() + "\";");
        writer.write("id:\"" + player.getId() + "\";");
        writer.write("windDirection:\"" + player.getWindDirection() + "\";");
        writer.write("score:\"" + player.getScore() + "\"");
        writer.write("}");
        writer.flush();
    }

    public void saveRecord(Record record) throws IOException {
        writer.write("Record:{");
        writer.write("numberOfRound:\"" + record.getNumberOfRound() + "\";");
        writer.write("leadingWind:\"" + record.getLeadingWind() + "\";");
        StringBuilder stringBuilder = new StringBuilder("[");
        for (int i = 0; i < record.getScores().length; i++) {
            if (i != record.getScores().length - 1) {
                stringBuilder.append(record.getScores()[i] + ",");
            } else {
                stringBuilder.append(record.getScores()[i] + "]");
            }
        }
        writer.write("scores:\"" + stringBuilder.toString() + "\";");
        writer.write("winnerPlayerId:\"" + record.getWinnerPlayerId() + "\";");
        writer.write("leadingPlayerId:\"" + record.getLeadingPlayerId() + "\"");
        writer.write("}");
        writer.flush();


    }

    public void saveNumberOfFullRoundsPlayed(int fullRoundsPlayed) throws IOException {
        writer.write("fullRoundsPlayed:\"" + fullRoundsPlayed + "\"");
        writer.flush();

    }

    public void addSeperator() throws IOException {
        writer.write(",");
        writer.flush();
    }

    @Override
    public void closeConnection() throws IOException {
        writer.close();
    }
}
