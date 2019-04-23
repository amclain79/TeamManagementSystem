package model;

public class AssignTeamLeadRequest {
    public String teamName;
    public String memberEmail;

    public AssignTeamLeadRequest() {}

    public AssignTeamLeadRequest(String t, String m) {
        teamName = t;
        memberEmail = m;
    }
}
