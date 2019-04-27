package entity;

import java.time.LocalDate;

public class MemberTask {
    public String memberEmail;
    public String description;
    public LocalDate date;

    public MemberTask(){
        this.memberEmail = "";
        this.description = "";
        this.date = LocalDate.now();
    }

    public MemberTask(String description, LocalDate date, String email){
        this.memberEmail = email;
        this.description = description;
        this.date = date;
    }

    public String toString(){
        return String.format(
                "Due Date: %s\nEmail: %s\nTask: %s",
                date.toString(), memberEmail, description);
    }
}
