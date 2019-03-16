package model;

import entity.Team;

public class JoinTeamRequest {
    public String email;
    public Team team;

    public JoinTeamRequest() {}

    public JoinTeamRequest(Team t, String e) {
        team = t;
        email = e;
    }
}
