package org.openjfx.Insurance;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ResourceBundle;

import org.openjfx.Insurance.AfterLogin;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.text.Text;
import javafx.stage.Stage;
import javafx.stage.Window;

public class Registeration{

    @FXML
    private TextField Name;

    @FXML
    private TextField EmailId;

    @FXML
    private PasswordField pass;

    @FXML
    private Button submitButton;
    
    @FXML
    private Label insurance_plan;
    
    @FXML
    private AnchorPane anchor;
    
    @FXML
    private Scene scene;

    @FXML
    public void register(ActionEvent event) throws SQLException, IOException {

        Window owner = submitButton.getScene().getWindow();
        System.out.println(Name.getText());
        System.out.println(EmailId.getText());
        System.out.println(pass.getText());
        if (Name.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your name");
            return;
        }

        if (EmailId.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter your email id");
            return;
        }
        if (pass.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please enter a password");
            return;
        }

        String fullName = Name.getText();
        String emailId = EmailId.getText();
        String password = pass.getText();

        if(emailId.endsWith("@northeastern.edu"))
        {
            DB jdbcDao = new DB();
            jdbcDao.insertRecord(fullName, emailId, password);
        
            showAlert(Alert.AlertType.CONFIRMATION, owner, "User is registered successfully!",
            "Welcome " + Name.getText());
       
            switchToLogin();
        }
        else
        {
            showAlert(Alert.AlertType.ERROR, owner, "Registration Unuccessful, Please try again!",
            "Email must end with @northeastern.edu ");
            
        }
    }

    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
    
    @FXML
    private void switchToHome() throws IOException {
        App.setRoot("Home");
    }
    
    @FXML
    private void switchToLogin() throws IOException {
        
        
        App.setRoot("Logininto");
    }
    
    @FXML
    private void switchToRegister() throws IOException {
        
        
        App.setRoot("primaryview");
    }

    

}
