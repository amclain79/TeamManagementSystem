package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertTrue;

public class CreateTeamRequestTest {
    String teamName;
    String memberEmail;

    @Before
    public void setup(){
        teamName = "teamName";
        memberEmail = "email@gmail.com";
    }

    @Test
    public void createTeamRequest(){
        CreateTeamRequest r = new CreateTeamRequest();
        r.teamName = teamName;
        r.memberEmail = memberEmail;
        assertTrue(r.teamName.equals(teamName));
        assertTrue(r.memberEmail.equals(memberEmail));
    }

    @Test
    public void createTeamRequest_constructor(){
        CreateTeamRequest r = new CreateTeamRequest(
                teamName, memberEmail
        );
        assertTrue(r.teamName.equals(teamName));
        assertTrue(r.memberEmail.equals(memberEmail));
    }
}
