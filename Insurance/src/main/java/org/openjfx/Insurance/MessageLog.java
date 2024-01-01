package org.openjfx.Insurance;

import java.io.IOException;
import java.net.URL;
import java.sql.SQLException;
import java.util.ArrayDeque;
import java.util.Deque;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Window; 
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import org.openjfx.Insurance.InsuranceOfPerson;

import javafx.scene.control.TextArea;

public class MessageLog {
    
    @FXML 
    private TextArea logfield;

    @FXML
    private Button showfieldlog;

    public static Stack<String> stack1 = new Stack();
    public static Deque<String> queue1 = new ArrayDeque<>();
    
    @FXML
    public static void pushlog(String value)
    {
        stack1.push(value);
        System.out.println("Log message inserted into stack");
    }
    
    @FXML
    public void logprint(ActionEvent event) throws SQLException, IOException {

        Window owner = showfieldlog.getScene().getWindow();
        if(!(stack1.empty()))
        {
            logfield.setText(" "+stack1.peek());
            queue1.addFirst(stack1.peek());
            stack1.pop();
        }
        while(!(stack1.empty()))
        {
            logfield.setText(logfield.getText()+"\n"+stack1.peek());
            queue1.addFirst(stack1.peek());
            stack1.pop();
        } 
    }  

    @FXML
    private void switchLogin() throws IOException{
        App.setRoot("Logininto");

    }
    
    @FXML
    private void switchAfterPostLogin() throws IOException{     
        while(!(queue1.isEmpty()))
        {
            stack1.push(queue1.remove());   
        }
        App.setRoot("AfterLogin");
    }
}
