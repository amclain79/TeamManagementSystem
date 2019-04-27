package gateway;

import entity.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ProjectStateManagerTest {
    private ProjectStateManager projectStateManager = ProjectStateManager.getInstance();
    @Before
    public void setup(){
        projectStateManager.clear();
    }

    @Test
    public void saveProfile(){
        projectStateManager.saveProfile(
                new Profile("name", "person@email.com", "edu", "exp"));
        assertEquals(1, projectStateManager.getProfiles().size());
    }

    @Test
    public void saveTeam(){
        projectStateManager.saveTeam(
                new Team("teamName", "person@email.com"));
        assertEquals(1, projectStateManager.getTeams().size());
    }

    @Test
    public void saveMemberTask(){
        projectStateManager.saveMemberTask(
                new MemberTask("description", LocalDate.now(), "member@email.com")
        );
        assertEquals(1, projectStateManager.getMemberTasks().size());
    }

    @Test
    public void saveTeamTask() {
        projectStateManager.saveTeamTask(
                new TeamTask("description", "teamName", LocalDate.now())
        );
        assertEquals(1, projectStateManager.getTeamTasks().size());
    }

    @Test
    public void saveTeamFeedback() {
        projectStateManager.saveTeamFeedback(
                new TeamFeedback("teamName", "feedback", LocalDate.now())
        );
        assertEquals(1, projectStateManager.getTeamFeedbacks().size());
    }

    @Test
    public void saveNomination() {
        projectStateManager.saveNomination(
                new Nomination("nominee@email.com", "teamName", "nominator@email.com")
        );
        assertEquals(1, projectStateManager.getNominations().size());
    }

    @Test
    public void saveNominations(){
        projectStateManager.clear();
        projectStateManager.saveNominations(
                new ConcurrentHashMap<String, Nomination>()
        );
        assertNotNull(projectStateManager.nominations);
    }
}
