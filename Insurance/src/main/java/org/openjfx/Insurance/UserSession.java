
package org.openjfx.Insurance;
import java.util.HashSet;
import java.util.Set;
import javafx.fxml.FXML;

public final class UserSession {

    @FXML
    private static UserSession sessionInstance;

    @FXML
    private String sessionUserName;
    
    @FXML
    private Set<String> sessionPrivileges;

    
    private UserSession(String sessionUserName, Set<String> sessionPrivileges) {
        this.sessionUserName = sessionUserName;
        this.sessionPrivileges = sessionPrivileges;
    }
    
    @FXML
    public static UserSession getsessionInstance()
    {
        return sessionInstance;
    }
    
    @FXML
    public static UserSession getsessionInstance(String sessionUserName, Set<String> sessionPrivileges) {
        if(sessionInstance == null) {
            sessionInstance = new UserSession(sessionUserName, sessionPrivileges);
        }
        return sessionInstance;
    }
    
    @FXML
    public static void setsessionInstance() {
        sessionInstance = null;
    }

    @FXML
    public String getsessionUserName() {
        return sessionUserName;
    }

    @FXML
    public Set<String> getsessionPrivileges() {
        return sessionPrivileges;
    }

    @FXML
    public void cleanUserSession() {
        sessionUserName = "";// or null
        sessionPrivileges = new HashSet<>();// or null
    }

    @Override
    public String toString() {
        return "UserSession{" +
                "sessionUserName='" + sessionUserName + '\'' +
                ", sessionPrivileges=" + sessionPrivileges +
                '}';
    }
}