package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class AssignTeamLeadRequestTest {
    private AssignTeamLeadRequest atr;
    private String teamName;
    private String memberEmail;

    @Test
    public void setup(){
        teamName = "Team 1";
        memberEmail = "member1@email.com";
    }

    @Test
    public void initialize(){
        atr = new AssignTeamLeadRequest(
                teamName,
                memberEmail
        );

        assertEquals(teamName, atr.teamName);
        assertEquals(memberEmail, atr.memberEmail);
    }

    @Test
    public void setValues(){
        atr = new AssignTeamLeadRequest();
        atr.teamName = teamName;
        atr.memberEmail = memberEmail;
        assertEquals(teamName, atr.teamName);
        assertEquals(memberEmail, atr.memberEmail);
    }

}
