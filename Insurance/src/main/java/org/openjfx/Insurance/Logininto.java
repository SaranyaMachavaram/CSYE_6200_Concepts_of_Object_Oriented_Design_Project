package org.openjfx.Insurance;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.HashSet;
import java.util.ResourceBundle;

import org.openjfx.Insurance.MessageLog;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.Window;


public class Logininto implements Initializable {

    /**
     * Initializes the controller class.
     */
    @FXML
    private TextField emailId;

    @FXML
    private PasswordField password;

    @FXML
    private Button submit;
    
    @FXML
    public void login(ActionEvent event) throws SQLException, IOException {

        Window owner = submit.getScene().getWindow();

        System.out.println(emailId.getText());
        System.out.println(password.getText());

        if (emailId.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please mention your email id");
            return;
        }
        if (password.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please mention a password");
            return;
        }

        String emailId1 = emailId.getText();
        String password1 = password.getText();
        MessageLog.stack1.clear();
        MessageLog.queue1.clear();
        DB db = new DB();
        boolean check1 = db.searchAdminRecord(emailId1, password1);
        if(check1)
        {
            
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Successfully Logged into Admin Account!","Welcome back");
            UserSession.setsessionInstance();
            
            HashSet<String> privileges = new HashSet<>();          
            UserSession.getsessionInstance(emailId1, privileges);
            switchToAdminLogin();
            return;
        }
        
        
        boolean check = db.searchRecord(emailId1, password1);
        if(check)
        {
            showAlert(Alert.AlertType.CONFIRMATION, owner, "Login Successful!","Welcome ");
            HashSet<String> privileges = new HashSet<>(); 
            MessageLog.stack1.clear();
            MessageLog.queue1.clear();
            UserSession.setsessionInstance();
            UserSession.getsessionInstance(emailId1, privileges);
            switchToPostLogin();
            return;
        }
        showAlert(Alert.AlertType.ERROR, owner, "Login is unsuccessful","Please Try again");
        
    }

    private static void showAlert(Alert.AlertType Type, Window ownername, String titles, String messages) {
        Alert alert = new Alert(Type);
        alert.setTitle(titles);
        alert.setHeaderText(null);
        alert.setContentText(messages);
        alert.initOwner(ownername);
        alert.show();
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
    }    
        
    @FXML
    private void switchToPostLogin() throws IOException{
        App.setRoot("AfterLogin");
    }
    
    private void switchToAdminLogin() throws IOException{
        App.setRoot("Searchview");
    }
    
    @FXML
    private void switchToHome() throws IOException{
        App.setRoot("Home");
    }
}
