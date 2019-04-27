package interactor;

import boundary.IManager;
import entity.*;
import gateway.IGateway;
import model.ProjectTypes.*;
import model.TeamTaskRequest;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ManagerInteractorTest {
    private class FakeProjectStateManager implements IGateway {

        @Override
        public ConcurrentHashMap<String, Profile> getProfiles() {
            ConcurrentHashMap<String, Profile> profiles = new ConcurrentHashMap<>();
            profiles.put(leadProfile.email, leadProfile);
            return profiles;
        }

        @Override
        public void saveProfile(Profile p) {

        }

        @Override
        public ConcurrentHashMap<String, Team> getTeams() {
            ConcurrentHashMap<String, Team> teams = new ConcurrentHashMap<>();
            teams.put(team.teamName, team);
            return teams;
        }

        @Override
        public void saveTeam(Team t) {

        }

        @Override
        public ConcurrentHashMap<String, MemberTask> getMemberTasks() {
            return null;
        }

        @Override
        public void saveMemberTask(MemberTask mt) {

        }

        @Override
        public ConcurrentHashMap<String, TeamTask> getTeamTasks() {
            return null;
        }

        @Override
        public void saveTeamTask(TeamTask tt) {

        }

        @Override
        public ConcurrentHashMap<String, TeamFeedback> getTeamFeedbacks() {
            return new ConcurrentHashMap<String, TeamFeedback>();
        }

        @Override
        public void saveTeamFeedback(TeamFeedback tfb) {

        }

        @Override
        public ConcurrentHashMap<String, Nomination> getNominations() {
            return null;
        }

        @Override
        public void saveNomination(Nomination n) {

        }
    }

    private ManagerInteractor managerInteractor;
    private static TeamTask teamTask;
    private static Profile leadProfile = new Profile("lead", "lead@email.com", "edu", "exp");
    private static Team team = new Team("teamName", "member@member.com");

    @Before
    public void setup(){
        managerInteractor = new ManagerInteractor(new FakeProjectStateManager());
        leadProfile.role = Role.LEAD;
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
        TeamTaskRequest expected = new TeamTaskRequest(description, teamName, dueDate);
        managerInteractor.assignTeamTask(expected);
    }

    @Test
    public void isValidTeamName(){
        assertTrue(managerInteractor.isValidTeamName(team.teamName));
    }

    @Test
    public void isValidLeadEmail(){
        assertTrue(managerInteractor.isValidLeadEmail(leadProfile.email));
    }

    @Test
    public void getTeamsWithLeads(){
        assertNotNull(managerInteractor.getTeamsWithLeads());
    }
}
