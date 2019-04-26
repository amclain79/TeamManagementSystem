package model;

import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class TeamTaskRequestTest {
    private TeamTaskRequest teamTaskRequest;

    @Test
    public void canCreate(){
        String description = "description";
        String teamName = "TeamName";
        LocalDate dueDate = LocalDate.now();
        String leadEmail = "lead@email.com";

        teamTaskRequest = new TeamTaskRequest(
                description, teamName, dueDate, leadEmail
        );
        assertTrue(description.equals(teamTaskRequest.description));
        assertTrue(teamName.equals(teamTaskRequest.teamName));
        assertTrue(dueDate.toString().equals(teamTaskRequest.dueDate.toString()));
        assertTrue(leadEmail.equals(teamTaskRequest.leadEmail));
    }
}
