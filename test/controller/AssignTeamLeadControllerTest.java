package controller;

import boundary.IManager;
import entity.Profile;
import entity.Team;
import entity.TeamFeedback;
import model.AssignTeamLeadRequest;
import model.TeamTaskRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertNotNull;

public class AssignTeamLeadControllerTest {
    private class FakeManagerInteractor implements IManager {

        @Override
        public ConcurrentHashMap<String, TeamFeedback> viewTeamFeedbacks() {
            return null;
        }

        @Override
        public void assignTeamTask(TeamTaskRequest ttr) {

        }

        @Override
        public boolean isValidTeamName(String n) {
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

        @Override
        public ConcurrentHashMap<String, List<Profile>> getNomineeProfilesByTeam() {
            return new ConcurrentHashMap<>();
        }

        @Override
        public void assignTeamLead(AssignTeamLeadRequest atlr) {
            assignTeamLeadRequest = atlr;
        }
    }

    private AssignTeamLeadController assignTeamLeadController;
    private static AssignTeamLeadRequest assignTeamLeadRequest;

    @Before
    public void setup(){
        assignTeamLeadController = new AssignTeamLeadController(new FakeManagerInteractor());
    }

    @Test
    public void hasManager(){
        assertNotNull(assignTeamLeadController.manager);
    }

    @Test
    public void getNomineeProfilesByTeam(){
        assertNotNull(assignTeamLeadController.getNomineeProfilesByTeam());
    }

    @Test
    public void assignTeamLead(){
        assignTeamLeadController.assignTeamLead(
                new AssignTeamLeadRequest(
                        new Profile(), "teamName"
                )
        );
        assertNotNull(assignTeamLeadRequest);
    }
}
