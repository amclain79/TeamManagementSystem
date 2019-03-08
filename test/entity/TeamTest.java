package entity;

import model.CreateTeamRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

public class TeamTest {
    private Team team1;
    private Team team2;
    String teamName;
    String memberEmail1;
    String memberEmail2;

    @Before
    public void setup(){
        teamName = "teamName";
        memberEmail1 = "email1@gmail.com";
        memberEmail2 = "email2@gmail.com";

        team1 = new Team(new CreateTeamRequest(
                teamName,
                memberEmail1
        ));

        team2 = new Team();
    }

    @Test
    public void addMember(){
        team1.addMember("email2@gmail.com");
        assertEquals(2, team1.teamMembers.size());
    }

    @Test
    public void setTeamAttributes(){
        team2.teamName = teamName;
        team2.addMember(memberEmail2);
        assertEquals(1, team2.teamMembers.size());
    }

    @Test
    public void isOpen(){
        Project.getInstance().maxMembers = 1;
        Team openTeam = new Team();
        Team closedTeam = new Team("n","e");
        assertTrue(openTeam.isOpen());
        assertFalse(closedTeam.isOpen());
    }

    @Test
    public void compareTo(){
        Team team1 = new Team("Team1", "e");
        Team team2 = new Team("Team2", "e");
        int result1 = team1.compareTo(team2);
        int result2 = team2.compareTo(team1);
        assertTrue(result1 < 0);
        assertTrue(result2 > 0);
    }
}
