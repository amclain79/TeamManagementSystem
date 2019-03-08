package model;

public class CreateTeamRequest {
    public String teamName;
    public String memberEmail;

    public CreateTeamRequest() {}

    public CreateTeamRequest(String n, String e) {
        teamName = n;
        memberEmail = e;
    }
}
