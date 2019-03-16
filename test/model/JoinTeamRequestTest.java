package model;

import entity.Team;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class JoinTeamRequestTest {
    private JoinTeamRequest jtr;
    private Team team;
    private String email;

    @Test
    public void setup(){
        team = new Team();
        email = "email@email.com";
    }

    @Test
    public void initialize(){
        jtr = new JoinTeamRequest(
                team,
                email
        );
        assertEquals(team, jtr.team);
        assertEquals(email, jtr.email);
    }

    @Test
    public void setValues(){
        jtr = new JoinTeamRequest();
        jtr.email = email;
        jtr.team = team;
        assertEquals(team, jtr.team);
        assertEquals(email, jtr.email);
    }
}
