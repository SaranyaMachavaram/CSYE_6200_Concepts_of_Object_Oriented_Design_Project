package org.openjfx.Insurance;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;

import javafx.fxml.FXML;


public class Primary {

    @FXML
    private void switchToSecondary() throws IOException {
        App.setRoot("secondaryview");
    }
}
