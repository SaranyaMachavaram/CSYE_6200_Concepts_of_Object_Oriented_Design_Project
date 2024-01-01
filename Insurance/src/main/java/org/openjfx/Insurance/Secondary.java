package org.openjfx.Insurance;

import java.io.IOException;
import javafx.fxml.FXML;

public class Secondary {

    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primaryview");
    }
}