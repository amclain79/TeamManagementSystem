package entity;

import java.util.Date;

public class MemberTask {
    public String memberEmail;
    public String description;
    public Date date;

    public MemberTask(){
        this.owner = "";
        this.description = "";
        this.date = new Date();
    }

    public MemberTask(String description, Date date, String email){
        this.memberEmail = email;
        this.description = description;
        this.date = date;
    }

}
