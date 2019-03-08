package controller;

import boundary.IUser;
import entity.Profile;
import entity.Team;
import java.util.List;

public class JoinTeamController {
    public IUser userBoundary;

    public JoinTeamController(IUser u) {
        userBoundary = u;
    }

    public List<Team> getOpenTeams() {
        return userBoundary.getOpenTeams();
    }

    public List<Profile> getProfiles(Team chosenTeam) {
        return userBoundary.getProfiles(chosenTeam);
    }

    public Profile getProfile(Profile chosenProfile) {
        return null;
    }
}
