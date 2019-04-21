package entity;

import java.util.Date;

public class MemberTask {
    public String memberEmail;
    public String description;
    public Date date;

    public MemberTask(){
        this.memberEmail = "";
        this.description = "";
        this.date = new Date();
    }

    public MemberTask(String description, Date date, String email){
        this.memberEmail = email;
        this.description = description;
        this.date = date;
    }

    public String toString(){
        return String.format(
                "Date: %s\nEmail: %s\nTask: %s",
                date.toString(), memberEmail, description);
    }
}
