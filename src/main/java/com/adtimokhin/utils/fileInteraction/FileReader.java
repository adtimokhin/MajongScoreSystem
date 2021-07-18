package com.adtimokhin.utils.fileInteraction;

import java.io.BufferedReader;
import java.io.IOException;

public class FileReader implements FileIntegrator {
    private BufferedReader bufferedReader;

    @Override
    public void openConnection(String filename) throws IOException {
        bufferedReader = new BufferedReader(new java.io.FileReader(filename));
    }

    public String readline() throws IOException {
        return bufferedReader.readLine();
    }

    @Override
    public void closeConnection() throws IOException {
        bufferedReader.close();
    }
}
