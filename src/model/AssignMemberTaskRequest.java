package model;

public class AssignMemberTaskRequest {
    public int daysToComplete;
    public String memberEmail;
    public String description;

    public AssignMemberTaskRequest(int d, String e, String de){
        daysToComplete = d;
        memberEmail = e;
        description = de;
    }
}
