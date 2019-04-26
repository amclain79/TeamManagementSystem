package interactor;

import boundary.ILead;
import entity.*;
import gateway.IGateway;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LeadInteractorTest {
    private class FakeProjectStateManager implements IGateway {
        @Override
        public Profile getProfile(String e) {
            return null;
        }

        @Override
        public boolean isFirstProfile() {
            return false;
        }

        @Override
        public void saveProfile(Profile p) {

        }

        @Override
        public void saveTeam(Team t) {

        }

        @Override
        public boolean isUniqueTeamName(String n) {
            return false;
        }

        @Override
        public int getNumTeams() {
            return 0;
        }

        @Override
        public List<Team> getOpenTeams() {
            return null;
        }

        @Override
        public List<Profile> getProfiles(Team t) {
            return null;
        }

        @Override
        public MemberTask getMemberTask(String e) {
            return null;
        }

        @Override
        public void saveMemberTask(MemberTask task) {

        }

        @Override
        public TeamTask getTeamTask(String e) {
            TeamTask teamTask = new TeamTask();
            teamTask.teamLeadEmail = e;
            return teamTask;
        }

        @Override
        public void saveTeamTask(TeamTask tt) {

        }

        @Override
        public ConcurrentHashMap<String, TeamFeedback> getTeamFeedbacks() {
            return null;
        }

        @Override
        public void saveTeamFeedback(TeamFeedback teamFeedback) {

        }

        @Override
        public boolean isValidTeamName(String teamName) {
            return false;
        }

        @Override
        public boolean isValidLeadEmail(String e) {
            return false;
        }

        @Override
        public List<Team> getTeamsWithLeads() {
            return null;
        }
    }

    private LeadInteractor leadInteractor;

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
        String leadEmail = "lead@email.com";
        TeamTask teamTask = leadInteractor.viewTeamTask(leadEmail);
        assertEquals(leadEmail, teamTask.teamLeadEmail);
    }
}
