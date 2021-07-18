package com.adtimokhin.utils.gameSaves;

import com.adtimokhin.entity.Player;
import com.adtimokhin.entity.Record;
import com.adtimokhin.utils.fileInteraction.FileWriter;

import java.io.IOException;

public class PropertySaver {

    public void saveGameDetails(Player[] players, Record[] records, String filename) {
        int fullRoundsPlayed = 0;
        if (filename == null) {
            filename = "Sample.txt";
        }

        FileWriter fileWriter = new FileWriter();
        try {
            fileWriter.openConnection(filename);
            for (Player player :
                    players) {
                fileWriter.savePlayer(player);
                fileWriter.addSeperator();

            }
            for (int i = 0; i < records.length; i++) {
                if(records[i] == null){
                    continue;
                }else {
                    fullRoundsPlayed++;
                    fileWriter.saveRecord(records[i]);
                    fileWriter.addSeperator();
                }
            }

            fileWriter.saveNumberOfFullRoundsPlayed(fullRoundsPlayed);

            fileWriter.closeConnection();

        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
