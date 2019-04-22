package controller;

import boundary.IUser;
import model.CreateTeamRequest;

public class CreateTeamController {
    public class InvalidTeamName extends RuntimeException{}

    public IUser userBoundary;

    public CreateTeamController(IUser interactor) {
        userBoundary = interactor;
    }

    public void createTeam(CreateTeamRequest r) {
        isValidCreateTeamRequest(r);
        userBoundary.createTeam(r);
    }

    public void getTeam(String teamName) {
        userBoundary.getTeam(teamName);
    }

    public void isValidCreateTeamRequest(CreateTeamRequest r) {
        if(r.teamName == null || r.teamName.equals(""))
            throw new InvalidTeamName();

        if(!isUniqueTeamName(r.teamName))
            throw new InvalidTeamName();
    }

    public boolean isUniqueTeamName(String n) {
        return userBoundary.isUniqueTeamName(n);
    }
}
