package interactor;

import boundary.ILead;
import entity.*;
import gateway.IGateway;
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
            Team team = new Team(teamName, leadEmail);
            team.assignTeamLead(leadEmail);
            ConcurrentHashMap<String, Team> teams = new ConcurrentHashMap<>();
            teams.put(teamName, team);
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
            teamTasks.put(teamName, teamTask);
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
    private static TeamTask teamTask = new TeamTask("description", "teamName", LocalDate.now());
    private static String teamName = teamTask.teamName;
    private static String leadEmail = "lead@email.com";

    @Before
    public void setup(){
        leadInteractor = new LeadInteractor(new FakeProjectStateManager());
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
        TeamTask tt = leadInteractor.viewTeamTask(leadEmail);
        assertTrue(teamName.equals(tt.teamName));
    }
}
