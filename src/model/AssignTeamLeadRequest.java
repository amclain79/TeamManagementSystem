package model;

import entity.Profile;

public class AssignTeamLeadRequest {
    public Profile nominee;
    public String teamName;
    public AssignTeamLeadRequest(Profile n, String tn){
        nominee = n;
        teamName = tn;
    }
}
