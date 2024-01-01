/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package org.openjfx.Insurance;

public class UserInfo {
    
    private int user_id;
    private String user_name;
    private String user_email;

    public UserInfo(int user_id, String user_name, String user_email) {
        this.user_id = user_id;
        this.user_name = user_name;
        this.user_email = user_email;
    }

    public UserInfo() {
    }

    public int getId() {
        return user_id;
    }

    public String getFull_name() {
        return user_name;
    }

    public String getuser_email() {
        return user_email;
    }

    public void setId(int user_id) {
        this.user_id = user_id;
    }

    public void setFull_name(String user_name) {
        this.user_name = user_name;
    }

    public void setuser_email(String user_email) {
        this.user_email = user_email;
    }
    
    
}
