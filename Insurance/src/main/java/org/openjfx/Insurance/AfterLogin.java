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

import org.openjfx.Insurance.InsuranceOfPerson;

import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window;

public class AfterLogin implements Initializable {
    
    @FXML
    private Label valueofhead;
    
    @FXML
    private Label email;

    @FXML
    private Label duration;
   
    @FXML
    private Label ins_premium;
    
    @FXML
    private Label ins_name;
    
    @FXML
    private Label ins_type;
    
    @FXML
    private Label agent;
    
    @FXML
    private Button plan_add;
    
    @FXML
    private Button plan_update;
    
    @FXML
    private Button plan_delete;
    
    @FXML
    private Button Claim;
    
    @FXML
    private void ChangePlanlist() throws IOException {
        App.setRoot("plansForPeople");
    }

    @FXML public List<InsuranceOfPerson> getPlansForCustomer() throws SQLException, IOException {
        
        List<InsuranceOfPerson> insurance_plans = new ArrayList<>();
        DB db = new DB();
        UserSession usersession = UserSession.getsessionInstance();
        insurance_plans = db.getRecord(usersession.getsessionUserName());
        return insurance_plans;
    }

    @Override
    public void initialize(URL url, ResourceBundle rb) {
        
        //Insurance insure = new Insurance();
    	UserSession usersession = UserSession.getsessionInstance();
        valueofhead.setText("Hi "+usersession.getsessionUserName());
        List<InsuranceOfPerson> insurance_plans = new ArrayList<>();
        try {
        	insurance_plans = getPlansForCustomer();
            Iterator i = insurance_plans.iterator();
            if(i.hasNext())
            {
                plan_add.setVisible(false);

            }
            else
            {
                plan_update.setVisible(false);
                plan_delete.setVisible(false);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(AfterLogin.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(AfterLogin.class.getName()).log(Level.SEVERE, null, ex);
        }
        //insurance_plan.setText("Insurance values");
        
        Iterator<InsuranceOfPerson> iterateplans = insurance_plans.iterator();
        String email_id = "";
        while(iterateplans.hasNext())
        {   
        	    InsuranceOfPerson value = new InsuranceOfPerson();
                value = iterateplans.next();
                ins_premium.setText("Premium for Monthly is: "+value.premium+""+"Dollars");
                duration.setText("Duration to pay the money: "+ value.duration+""+"months");
                ins_name.setText("Name of the Insurance: "+value.ins_name);
                email.setText("Email: "+value.email);
                email_id = value.email;
                ins_type.setText("Insurance type: "+value.ins_type);
                agent.setText("Agent: "+value.agent);
                System.out.println(value.ins_type);
        }
        plan_add.setOnAction(new BtHandler());
        plan_update.setOnAction(new BtHandler());
        plan_delete.setOnAction(new BtHandler2());
      
        return;
    }
    
    class BtHandler2 implements EventHandler   
    {       
        @Override
        public void handle(Event t) {
            System.out.println("event---- Button Handler");
            DB db = new DB();
            UserSession userinstance = UserSession.getsessionInstance();
            
            String email_id = userinstance.getsessionUserName();
            db.deleteRecord(email_id);
            try {
                App.setRoot("AfterLogin");
            } catch (IOException ex) {
                Logger.getLogger(AfterLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
    
    class BtHandler implements EventHandler   
    {       
        @Override
        public void handle(Event t) {
            try {
                System.out.println("event---- button handler");
                App.setRoot("planadd");
            } catch (IOException ex) {
                Logger.getLogger(AfterLogin.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }


    @FXML
    private void switchToLogin() throws IOException{
        App.setRoot("Logininto");
    }
    
    
    @FXML
    private void switchToLog() throws IOException{
        App.setRoot("MessageLog");
    }
}

