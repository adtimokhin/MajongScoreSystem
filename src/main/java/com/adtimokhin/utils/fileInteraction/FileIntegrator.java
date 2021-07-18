package com.adtimokhin.utils.fileInteraction;

import java.io.IOException;

public interface FileIntegrator {

     void openConnection(String filename) throws IOException;
     void closeConnection() throws IOException;

}
