package entity;

import org.junit.Before;
import org.junit.Test;
import java.util.Date;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class TeamTaskTest {
    private TeamTask teamTask;
    private String description;
    private String teamName;
    private Date dueDate;
    private String teamLeadEmail;

    @Before
    public void setup(){
        description = "description";
        teamName = "teamName";
        dueDate = new Date();
        teamLeadEmail = "teamLead@email.com";
        teamTask = new TeamTask();
        teamTask.description = description;
        teamTask.teamName = teamName;
        teamTask.dueDate = dueDate;
        teamTask.teamLeadEmail = teamLeadEmail;
    }

    @Test
    public void createTeamTask(){
        assertNotNull(teamTask);
    }

    @Test
    public void createTeamTaskWithAttributes(){
        assertEquals(description, teamTask.description);
        assertEquals(teamName, teamTask.teamName);
        assertTrue(dueDate.toString().equals(teamTask.dueDate.toString()));
        assertTrue(teamLeadEmail.equals(teamTask.teamLeadEmail));
    }

    @Test
    public void toStringTest(){
        String teamTaskFormat = String.format(
                "Date: %s\nTeamLeadEmail: %s\nTeamName: %s\nDescription: %s\n",
                new Date().toString(),
                "teamLead@email.com",
                "teamName",
                "description");
        assertTrue(teamTaskFormat.equals(teamTask.toString()));
    }
}
