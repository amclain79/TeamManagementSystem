package entity;

import model.AssignMemberTaskRequest;

import java.time.LocalDate;

public class MemberTask {
    public String memberEmail;
    public String description;
    public LocalDate dueDate;

    public MemberTask(){}

    public MemberTask(String de, LocalDate d, String e){
        description = de;
        dueDate = d;
        memberEmail = e;
    }

    public MemberTask(AssignMemberTaskRequest mtr) {
        description = mtr.description;
        dueDate = LocalDate.now().plusDays(mtr.daysToComplete);
        memberEmail = mtr.memberEmail;
    }

    @Override
    public String toString(){
        return String.format(
                "Due Date: %s\nEmail: %s\nTask: %s",
                dueDate, memberEmail, description);
    }
}
