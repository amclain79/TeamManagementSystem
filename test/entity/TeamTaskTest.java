package entity;

import model.TeamTaskRequest;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class TeamTaskTest {
    private TeamTask teamTask;
    private String description;
    private String teamName;
    private LocalDate dueDate;

    @Before
    public void setup(){
        description = "description";
        teamName = "teamName";
        dueDate = LocalDate.now();
        teamTask = new TeamTask();
        teamTask.description = description;
        teamTask.teamName = teamName;
        teamTask.dueDate = dueDate;
    }

    @Test
    public void createTeamTaskWithAttributesViaConstructor(){
        TeamTask tt = new TeamTask(description,teamName,dueDate);
        assertEquals(description, tt.description);
        assertEquals(teamName, tt.teamName);
        assertEquals(dueDate, tt.dueDate);
    }

    @Test
    public void createTeamTaskWithAttributes(){
        assertEquals(description, teamTask.description);
        assertEquals(teamName, teamTask.teamName);
        assertTrue(dueDate.toString().equals(teamTask.dueDate.toString()));
    }

    @Test
    public void toStringTest(){
        String teamTaskFormat = String.format(
                "Due: %s\nTeamName: %s\nDescription: %s\n",
                dueDate.toString(),
                teamName,
                description);
        assertTrue(teamTaskFormat.equals(teamTask.toString()));
    }

    @Test
    public void createTeamTaskFromTeamTaskRequest(){
        TeamTask tt = new TeamTask(
                new TeamTaskRequest(
                        description, teamName, dueDate
                )
        );
        assertEquals(description, tt.description);
        assertEquals(teamName, tt.teamName);
        assertTrue(dueDate.toString().equals(tt.dueDate.toString()));
    }
}
