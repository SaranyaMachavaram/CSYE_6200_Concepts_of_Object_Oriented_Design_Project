package org.openjfx.Insurance;
import static org.openjfx.Insurance.MessageLog.pushlog;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

import org.openjfx.Insurance.InsuranceOfPerson;

import javafx.fxml.FXML;
public class DB {

    private static final String QUERY_OF_INSERTION= "INSERT INTO user_registration (name, email, pass) VALUES (?, ?, ?)";
    private static final String QUERY_OF_SELECTION= "SELECT id,email,ins_type,ins_name,premium,duration,agent FROM insurance_plan WHERE email=?";
    private static final String QUERY_OF_SEARCH = "SELECT * FROM insurance_plan WHERE email like ? ";
    private static final String QUERY_OF_SEARCHING = "SELECT id FROM user_registration WHERE email = ? and pass = ?";
    private static final String QUERY_OF_DELETION = "DELETE FROM insurance_plan WHERE email = ?";
    @FXML public List<InsuranceOfPerson> getPlansForCustomer() throws SQLException, IOException {
        
        List<InsuranceOfPerson> insuranceplans = new ArrayList<>();
        DB db = new DB();
        UserSession usersession = UserSession.getsessionInstance();
        insuranceplans = db.getRecord(usersession.getsessionUserName());
        return insuranceplans;
    }
    private static final String QUERY_OF_ADDITION = "INSERT INTO insurance_plan (email, ins_type, ins_name,premium,duration,agent)VALUES (?, ?, ?, ?, ?, ?)";
    private static final String QUERY_SET = "UPDATE insurance_plan set ins_type = ?,ins_name=?,premium = ?,duration= ?,agent=? WHERE email= ?";
    public void insertRecord(String Name, String email, String pwd) throws SQLException {

        try (
                
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood_project?allowPublicKeyRetrieval=true&useSSL=false","root","Mysql@1");
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_OF_INSERTION) ){
            preparedStatement.setString(1, Name);
            preparedStatement.setString(2, email);
            preparedStatement.setString(3, pwd);
            System.out.println(preparedStatement);
        
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
          
            printSQLException(e);
        }
    }
    
    public void addRecord(String email, String ins_type, String ins_name, 
            int premium, int duration, String agent) throws SQLException {

        try (
                
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood_project?allowPublicKeyRetrieval=true&useSSL=false","root","Mysql@1");
            
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_OF_ADDITION) )
            {
                preparedStatement.setString(1, email);
                preparedStatement.setString(2, ins_type);
                preparedStatement.setString(3, ins_name);
                preparedStatement.setInt(4, premium);
                preparedStatement.setInt(5, duration);
                preparedStatement.setString(6, agent);
                System.out.println(preparedStatement);
               
                pushlog("Add Insurance plan for user: "+email);
                preparedStatement.executeUpdate();
            } catch (SQLException e) {
            
            printSQLException(e);
        }
    }
    
    public void setRecord(String email, String ins_type, String ins_name, 
            int premium, int duration, String agent) throws SQLException
    {
        List<InsuranceOfPerson> plans = new ArrayList<>();
        try(
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood_project?allowPublicKeyRetrieval=true&useSSL=false","root","Mysql@1");
           
        	PreparedStatement preparedStatement = connection.prepareStatement(QUERY_SET)) 
        {
        	System.out.println(QUERY_SET);
            preparedStatement.setString(1,ins_type);
            preparedStatement.setString(2,ins_name);
            preparedStatement.setInt(3,premium);
            preparedStatement.setInt(4, duration);
            preparedStatement.setString(5,agent);
            preparedStatement.setString(6,email);
            System.out.println(preparedStatement);
            pushlog("Update Insurance plan for user: "+email+" with "+" monthly premium: "+premium+" and tenure: "+duration);
            int row = preparedStatement.executeUpdate();
        }catch(SQLException e){
        
            printSQLException(e);     
        }
        return;
    }
    
    public List<InsuranceOfPerson> getRecord(String emailId) throws SQLException
    {
        List<InsuranceOfPerson> plans = new ArrayList<>();
        try(
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood_project?allowPublicKeyRetrieval=true&useSSL=false","root","Mysql@1");
            PreparedStatement preparedStatement = connection.prepareStatement(QUERY_OF_SELECTION);) 
        {
            preparedStatement.setString(1,emailId);
            System.out.println(preparedStatement);
            ResultSet rs = preparedStatement.executeQuery();
            while(rs.next())
            {
               InsuranceOfPerson insure = new InsuranceOfPerson();
               insure.setIns_Id(rs.getInt("id"));
               System.out.println(insure.getIns_Id());
               insure.setEmail(rs.getString("email"));
               System.out.println(insure.getEmail());
               insure.setIns_type(rs.getString("ins_type"));
               System.out.println(insure.getIns_type());
               insure.setIns_name(rs.getString("ins_name"));
               System.out.println(insure.getIns_name());
               insure.setpremium(rs.getInt("premium"));
               System.out.println(insure.getPremium());
               insure.setDuration(rs.getInt("duration"));
               System.out.println(insure.getDuration());
               insure.setAgent(rs.getString("agent"));
               System.out.println(insure.getAgent());
               plans.add(insure);
            }
            pushlog("Print the insurance plan for user from records: "+ emailId);
            return plans;
        }catch(SQLException e){
        
            printSQLException(e);     
        }
        return plans;
    }
    public List<InsuranceOfPerson> searchRecord(String email) throws SQLException
    {
        List<InsuranceOfPerson> plans = new ArrayList<>();
        try(
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood_project?allowPublicKeyRetrieval=true&useSSL=false","root","Mysql@1");
            PreparedStatement Statement = connection.prepareStatement(QUERY_OF_SEARCH);) 
        {
        	Statement.setString(1,"%"+email+"%");
            System.out.println(Statement);
            ResultSet rs = Statement.executeQuery();
            System.out.println(rs);
            while(rs.next())
            {
               InsuranceOfPerson insure = new InsuranceOfPerson();
               insure.setIns_Id(rs.getInt("id"));
               System.out.println(insure.getIns_Id());
               insure.setEmail(rs.getString("email"));
               System.out.println(insure.getEmail());
               insure.setIns_type(rs.getString("ins_type"));
               System.out.println(insure.getIns_type());
               insure.setIns_name(rs.getString("ins_name"));
               System.out.println(insure.getIns_name());
               insure.setpremium(rs.getInt("premium"));
               System.out.println(insure.getPremium());
               insure.setDuration(rs.getInt("duration"));
               System.out.println(insure.getDuration());
               insure.setAgent(rs.getString("agent"));
               System.out.println(insure.getAgent());
               plans.add(insure);
            }
            return plans;
        }catch(SQLException e){
        
            printSQLException(e);     
        }
        return plans;
    }
        
    public boolean searchRecord(String email, String pwd) throws SQLException
    {
        List<InsuranceOfPerson> plans = new ArrayList<>();
        try(
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood_project?allowPublicKeyRetrieval=true&useSSL=false","root","Mysql@1");
            PreparedStatement Statement = connection.prepareStatement(QUERY_OF_SEARCHING);) 
        {
        	Statement.setString(1,email);
        	Statement.setString(2,pwd);
            System.out.println(Statement);
            ResultSet rs = Statement.executeQuery();
            if(rs.next())
            {
                return true;
            }     
        }catch(SQLException e){
        
            printSQLException(e);     
        }
        return false;
    }
    
    public boolean searchAdminRecord(String email, String pwd) 
    {
        if(email.equals("sysadmin") && pwd.equals("sysadmin1234"))
        {
            return true;
        }
        return false;
    }
    public void deleteRecord(String email) {
        try(
            Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/ood_project?allowPublicKeyRetrieval=true&useSSL=false","root","Mysql@1");
            PreparedStatement Statement = connection.prepareStatement(QUERY_OF_DELETION	);) 
        {
        	Statement.setString(1,email);
            pushlog("Delete the insurance plan for user: "+ email);
            int row = Statement.executeUpdate();  
        }catch(SQLException e){
        
            printSQLException(e);     
        }
    }

    public static void printSQLException(SQLException exception) {
        for (Throwable exe: exception) {
            if (exe instanceof SQLException) {
                exe.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) exe).getSQLState());
                System.err.println("Error Code: " + ((SQLException) exe).getErrorCode());
                System.err.println("Message: " + exe.getMessage());
                Throwable t = exception.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }


    
}
