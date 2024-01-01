/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.Insurance;

import java.io.IOException;
import java.sql.SQLException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class Home {
    
    @FXML
    private void switchToPrimary() throws IOException {
        App.setRoot("primaryview");
    }
    
    @FXML
    private void switchToLogin() throws IOException{
        App.setRoot("Logininto");
    }
    
    @FXML
    private void switchToTeam() throws IOException{
        App.setRoot("teaminfo");
    }
    
    
}
