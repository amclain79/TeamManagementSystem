package interactor;

import boundary.IManager;
import entity.*;
import gateway.IGateway;
import model.TeamTaskRequest;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ManagerInteractorTest {
    private class FakeProjectStateManager implements IGateway {
        @Override
        public Profile getProfile(String e) {return null;}

        @Override
        public boolean isFirstProfile() {return false;}

        @Override
        public void saveProfile(Profile p) {}

        @Override
        public void saveTeam(Team t) {}

        @Override
        public boolean isUniqueTeamName(String n) {return false;}

        @Override
        public int getNumTeams() {return 0;}

        @Override
        public List<Team> getOpenTeams() {return null;}

        @Override
        public List<Profile> getProfiles(Team t) {return null;}

        @Override
        public MemberTask getMemberTask(String e) {
            return null;
        }

        @Override
        public void saveMemberTask(MemberTask task) {

        }

        @Override
        public TeamTask getTeamTask(String e) {return null;}

        @Override
        public void saveTeamTask(TeamTask tt) { teamTask = tt;}

        @Override
        public ConcurrentHashMap<String, TeamFeedback> getTeamFeedbacks() {
            return new ConcurrentHashMap<>();
        }

        @Override
        public void saveTeamFeedback(TeamFeedback teamFeedback) {

        }

        @Override
        public boolean isValidTeamName(String teamName) {
            return true;
        }

        @Override
        public boolean isValidLeadEmail(String e) {
            return true;
        }

        @Override
        public List<Team> getTeamsWithLeads() {
            List<Team> l = new ArrayList<>();
            return l;
        }
    }

    private ManagerInteractor managerInteractor;
    private static TeamTask teamTask;

    @Before
    public void setup(){
        managerInteractor = new ManagerInteractor(new FakeProjectStateManager());
    }

    @Test
    public void implementsIManager(){
        assertTrue(managerInteractor instanceof IManager);
    }

    @Test
    public void hasIGateway(){
        assertNotNull(managerInteractor.gateway);
    }

    @Test
    public void getTeamFeedbacks(){
        ConcurrentHashMap<String, TeamFeedback> teamFeedbacks = managerInteractor.viewTeamFeedbacks();
        assertNotNull(teamFeedbacks);
    }

    @Test
    public void assignTeamTask(){
        String description = "description";
        String teamName = "teamName";
        LocalDate dueDate = LocalDate.now();
        String leadEmail = "lead@email.com";
        TeamTaskRequest expected = new TeamTaskRequest(description, teamName, dueDate, leadEmail);
        managerInteractor.assignTeamTask(expected);
        assertEquals(expected.leadEmail, teamTask.teamLeadEmail);
    }

    @Test
    public void isValidTeamName(){
        assertTrue(managerInteractor.isValidTeamName("validTeamName"));
    }

    @Test
    public void isValidLeadEmail(){
        assertTrue(managerInteractor.isValidLeadEmail("validLead@email.com"));
    }

    @Test
    public void getTeamsWithLeads(){
        assertNotNull(managerInteractor.getTeamsWithLeads());
    }
}
