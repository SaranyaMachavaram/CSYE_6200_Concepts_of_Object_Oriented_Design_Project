
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
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Window;

public class PlanAdd implements Initializable {
    
    @FXML
    private Label greetings;
    @FXML
    private ComboBox insuranceType;
    @FXML
    private ComboBox insuranceName;
    @FXML
    private ComboBox tenure;
    @FXML
    private ComboBox monthlyPayment;
    @FXML
    private ComboBox agent;
    @FXML
    private Button btnPlan;
    @FXML 
    private Button btnUpdate;
    @FXML 
    private Button btnClaim;
    @FXML
    private String strInstype;
    @FXML
    private String strInsname;
    @FXML
    private String strTenureval;
    @FXML
    private int payment;
    @FXML
    private String stragent;
    
    private final ObservableList<String> listInsuranceType = FXCollections.observableArrayList("Bronze","Silver","Gold","Platinum");
    private final ObservableList<String> listInsuranceName = FXCollections.observableArrayList("Health Insurance","Home Insurance","Life Insurance", "Auto Inusurance");
    private final ObservableList<Integer> listMonths = FXCollections.observableArrayList(250,350,450,100);
    private final ObservableList<String> listTenure = FXCollections.observableArrayList("36 months", "48 months", "96 months");
    private final ObservableList<String> listAgent = FXCollections.observableArrayList("Saranya", "Ananth", "Rucha","ABCD");

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        
        insuranceType.setItems(listInsuranceType);
        insuranceName.setItems(listInsuranceName);
        tenure.setItems(listTenure);
        monthlyPayment.setItems(listMonths);
        agent.setItems(listAgent);
        
        UserSession user = UserSession.getsessionInstance();
        String emailId = (String) user.getsessionUserName();
        List<InsuranceOfPerson> plans = new ArrayList<InsuranceOfPerson>();
        try {
            plans = getPlans(emailId);
            Iterator i = plans.iterator();
        } catch (SQLException | IOException ex) {
            Logger.getLogger(PlanAdd.class.getName()).log(Level.SEVERE, null, ex);
        }
        Iterator i = plans.iterator();
        if(i.hasNext())
        {
           greetings.setText("Upgrade/Update your existing plan");
           InsuranceOfPerson planinsurance = new InsuranceOfPerson();
           planinsurance = plans.get(0);
           insuranceType.setValue(planinsurance.getIns_type());
           insuranceName.setValue(planinsurance.getIns_name());
           monthlyPayment.setValue(planinsurance.getPremium());
           tenure.setValue(planinsurance.getDuration()+""+"months");
           agent.setValue(planinsurance.getAgent());
           btnUpdate.setVisible(true);
           btnPlan.setVisible(false);
           btnClaim.setVisible(true);
        }
        else
        {
           greetings.setText("Add New plan");
           btnPlan.setVisible(true);
           btnUpdate.setVisible(false); 
           btnClaim.setVisible(false);
        }
    }
    
    @FXML
    public void updateplan(ActionEvent event) throws SQLException, IOException {

        Window owner = btnUpdate.getScene().getWindow();
        
        int tenval;
        String tenure_value;
        tenure_value = (String) tenure.getValue();
        tenure_value = tenure_value.substring(0,2);
        strInstype = (String) insuranceType.getValue();
        strInsname = (String) insuranceName.getValue();
        payment = (int) monthlyPayment.getValue();
        tenval = Integer.parseInt(tenure_value);
        stragent = (String) agent.getValue();
        
         
            UserSession user = UserSession.getsessionInstance();
            String emailId = (String) user.getsessionUserName();
            List<InsuranceOfPerson> plans = new ArrayList<>();
            try {
                plans = getPlans(emailId);
            } catch (SQLException | IOException ex) {
                Logger.getLogger(PlanAdd.class.getName()).log(Level.SEVERE, null, ex);
            }
            if(plans!=null)
            {
            	InsuranceOfPerson plan = new InsuranceOfPerson();
                plan = plans.get(0);
                DB jdbcDao = new DB();
                jdbcDao.setRecord(plan.email, strInstype, strInsname,
                        payment, tenval,stragent);
                showAlert(Alert.AlertType.CONFIRMATION, owner, "Plan updated Successful!",
                     "Updated plan "+strInsname+" with "+ strInstype +""+" "+payment+"with"+stragent);
            }
        switchToPostLogin();
    }
    
    
    @FXML
    public void addplan(ActionEvent event) throws SQLException, IOException {

        Window owner = btnPlan.getScene().getWindow();
        
        if (insuranceName.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add Insurance name");
            return;
        }
        if(insuranceType.getValue() == null)
        {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add insurance type");
        }
        
        if(monthlyPayment.getValue() == null)
        {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add payment value");
        }
        if(tenure.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add tenure");
            return;
        }
        if(agent.getValue() == null) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add agent");
            return;
        }
        
        int tenval;
        strTenureval = (String) tenure.getValue();
        strTenureval = strTenureval.substring(0,2);
        strInstype = (String) insuranceType.getValue();
        strInsname = (String) insuranceName.getValue();
        payment = (int) monthlyPayment.getValue();
        tenval = Integer.parseInt(strTenureval);
        stragent = (String) agent.getValue();
        
        if (strInstype.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add Insurance name");
            return;
        }

        if (strInsname.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add insurance type");
            return;
        }
        if (payment==0) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add tenure");
            return;
        }
        if(strTenureval.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add payment value");
            return;
        }
        if(stragent.isEmpty()) {
            showAlert(Alert.AlertType.ERROR, owner, "Form Error!",
                "Please add agent");
            return;
        }
        
        System.out.println(strInstype);
        System.out.println(strInsname);
        System.out.println(payment);
        System.out.println(tenval);
        //sSystem.out.println(passwordField.getText());
        
        //get emailId of User
        UserSession user = UserSession.getsessionInstance();
        String emailId = (String) user.getsessionUserName();
        List<InsuranceOfPerson> plans = new ArrayList<>();
        DB jdbcDao = new DB();

        jdbcDao.addRecord(emailId, strInstype, strInsname , payment, tenval,stragent);
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Plan added Successful!",
                 "Added plan "+strInsname+" with "+ strInstype +""+" "+payment+"with"+stragent);
        switchToPostLogin();
    }
    
    
    public void Claim(ActionEvent event) throws SQLException, IOException {

        Window owner = btnClaim.getScene().getWindow();
        int tenval;
        strTenureval = (String) tenure.getValue();
        strTenureval = strTenureval.substring(0,2);
        strInstype = (String) insuranceType.getValue();
        strInsname = (String) insuranceName.getValue();
        payment = (int) monthlyPayment.getValue();
        tenval = Integer.parseInt(strTenureval);
        stragent = (String) agent.getValue();
        showAlert(Alert.AlertType.CONFIRMATION, owner, "Claim has been requested successfully!",
                 " "+strInsname+" with "+ strInstype +" "+" "+payment+" with"+stragent);
        switchtoAddPlan();
    }
    
    
    @FXML
    private void switchToPostLogin() throws IOException {
        App.setRoot("AfterLogin");
    }
    
    @FXML
    private void switchToTerms() throws IOException {
        App.setRoot("termsandcondition");
    }
    
    @FXML
    private void switchtoAddPlan() throws IOException {
        App.setRoot("planadd");
    }
    
    @FXML public List<InsuranceOfPerson> getPlans(String emailid) throws SQLException, IOException {
        
        List<InsuranceOfPerson> plans = new ArrayList<>();
        DB jdbcDao = new DB();
        plans = jdbcDao.getRecord(emailid);
        return plans;
    }
    
    
    private static void showAlert(Alert.AlertType alertType, Window owner, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        alert.initOwner(owner);
        alert.show();
    }
}
