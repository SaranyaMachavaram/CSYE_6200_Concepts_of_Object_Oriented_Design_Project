/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.Insurance;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.openjfx.Insurance.AfterLogin.BtHandler;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class Search implements Initializable {

    @FXML
    private Label email_id;
    @FXML
    private Label insurance_plan;
    
    @FXML 
    private Label insurance_name;
    
    @FXML
    private Label monthly_payment;
    
    @FXML 
    private Label tenure;
    
    @FXML 
    private Label agent;
    
    @FXML
    private TextField search_bar;
    
    @FXML
    private Button searchButton;
    
    @FXML
    private Button update_button;
    
    @FXML
    private Button delete_button;
    
    private static Scene scene;
    
    
    @FXML public List<InsuranceOfPerson> getPlans(String emailid) throws SQLException, IOException {
        
        List<InsuranceOfPerson> plans = new ArrayList<>();
        DB jdbcDao = new DB();
        UserSession user = UserSession.getsessionInstance();
        plans = jdbcDao.searchRecord(emailid);
        return plans;
    }
    
    @Override
    public void initialize(URL url, ResourceBundle rb) {
  
        return;
    }
    
    @FXML
    public void searchfun(ActionEvent event) throws SQLException, IOException {

        Window owner = searchButton.getScene().getWindow();

        List<InsuranceOfPerson> plans = new ArrayList<>();
        
        if (search_bar.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Search 404",
                "Enter a username email to search");
            return;
        }
        System.out.println(search_bar.getText());
        
        if (search_bar.getText().isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Search 404",
                "Enter a username email to search");
            return;
        }

        String searchid = search_bar.getText();
        
        try {
            plans = getPlans(searchid);
        } catch (SQLException | IOException ex) {
            Logger.getLogger(PlanAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        Iterator itr = plans.iterator();
        if(itr.hasNext())
        {
        	InsuranceOfPerson plan = new InsuranceOfPerson();
            plan = plans.get(0);
            insurance_name.setText(plan.ins_name);
            insurance_plan.setText(plan.ins_type);
            monthly_payment.setText(plan.premium+"");
            tenure.setText(plan.duration+"");
            email_id.setText(plan.email);
            agent.setText(plan.agent);
        }
        else
        {
            insurance_name.setText("No such customer with active plans and given email id");
            insurance_plan.setText("");
            monthly_payment.setText("");
            email_id.setText("");
            tenure.setText("");
            agent.setText("");
        }
    }


    @FXML
    public void deletefun(ActionEvent event) throws SQLException, IOException {

        Window ownername = delete_button.getScene().getWindow();
        
        String email = email_id.getText();
        DB db = new DB();
        db.deleteRecord(email);
        ReloadSearch();
    }
    private static void showAlert(Alert.AlertType Type, Window ownername, String titles, String messages) {
        Alert alert = new Alert(Type);
        alert.setTitle(titles);
        alert.setHeaderText(null);
        alert.setContentText(messages);
        alert.initOwner(ownername);
        alert.show();
    }

    
    @FXML
    private void ReloadSearch() throws IOException {
        App.setRoot("Searchview");
    }
    
    @FXML
    private void switchToLogin() throws IOException {
        App.setRoot("Logininto");
    }
}
