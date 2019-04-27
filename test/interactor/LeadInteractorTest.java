package interactor;

import boundary.ILead;
import entity.*;
import gateway.IGateway;
import model.CreateTeamFeedbackRequest;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LeadInteractorTest {
    private class FakeProjectStateManager implements IGateway {

        @Override
        public ConcurrentHashMap<String, Profile> getProfiles() {
            return null;
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
            ConcurrentHashMap<String, TeamTask> teamTasks = new ConcurrentHashMap<>();
            teamTasks.put(teamTask.teamName, teamTask);
            return teamTasks;
        }

        @Override
        public void saveTeamTask(TeamTask tt) {

        }

        @Override
        public ConcurrentHashMap<String, TeamFeedback> getTeamFeedbacks() {
            return null;
        }

        @Override
        public void saveTeamFeedback(TeamFeedback tfb) {
            teamFeedback = tfb;
        }

        @Override
        public ConcurrentHashMap<String, Nomination> getNominations() {
            return null;
        }

        @Override
        public void saveNomination(Nomination n) {

        }

        @Override
        public void saveNominations(ConcurrentHashMap<String, Nomination> n) {

        }
    }

    private LeadInteractor leadInteractor;
    private static TeamFeedback teamFeedback;
    private static Profile leadProfile = new Profile("lead", "lead@email.com", "edu", "exp");
    private static Team team = new Team("teamName", leadProfile.email);
    private static TeamTask teamTask = new TeamTask("description", team.teamName, LocalDate.now());

    @Before
    public void setup(){
        leadInteractor = new LeadInteractor(new FakeProjectStateManager());
        team.assignTeamLead(leadProfile.email);
    }

    @Test
    public void implementsILead(){
        assertTrue(leadInteractor instanceof ILead);
    }

    @Test
    public void hasGateway(){
        assertNotNull(leadInteractor.gateway);
    }

    @Test
    public void viewTeamTask(){
        TeamTask tt = leadInteractor.viewTeamTask(leadProfile.email);
        assertTrue(team.teamName.equals(tt.teamName));
    }

    @Test
    public void createTeamFeedback(){
        leadInteractor.createTeamFeedback(
                new CreateTeamFeedbackRequest(
                        leadProfile.email, "feedback"
                )
        );
        assertNotNull(teamFeedback);
        assertTrue(team.teamName.equals(teamFeedback.teamName));
        assertTrue("feedback".equals(teamFeedback.feedback));
    }
}
