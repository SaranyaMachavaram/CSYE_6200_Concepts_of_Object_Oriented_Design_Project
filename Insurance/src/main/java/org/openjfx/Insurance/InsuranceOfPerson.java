package org.openjfx.Insurance;

public class InsuranceOfPerson {
    public int ins_id;
    public String email;
    public String ins_type;
    public String ins_name;
    public int premium;
    public int duration;
    public String agent;

    public InsuranceOfPerson() {
    }

    public InsuranceOfPerson(int ins_id, String email, String ins_type, String ins_name, int premium, int duration, String agent) {
        this.ins_id = ins_id;
        this.email = email;
        this.ins_type = ins_type;
        this.ins_name = ins_name;
        this.premium = premium;
        this.duration = duration;
        this.agent=agent;
    }

    public int getIns_Id() {
        return ins_id;
    }

    public String getEmail() {
        return email;
    }

    public String getIns_type() {
        return ins_type;
    }

    public String getIns_name() {
        return ins_name;
    }

    public int getPremium() {
        return premium;
    }

    public int getDuration() {
        return duration;
    }
    
    public String getAgent() {
    	return agent;
    }

    public void setIns_Id(int ins_id) {
        this.ins_id = ins_id;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setIns_type(String ins_type) {
        this.ins_type = ins_type;
    }

    public void setIns_name(String ins_name) {
        this.ins_name = ins_name;
    }

    public void setpremium(int premium) {
        this.premium = premium;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }
    
    public void setAgent(String agent) {
        this.agent = agent;
    }
    
}
