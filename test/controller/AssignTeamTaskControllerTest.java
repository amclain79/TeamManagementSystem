package controller;

import boundary.IManager;
import entity.Profile;
import entity.Team;
import entity.TeamFeedback;
import entity.TeamTask;
import model.AssignTeamLeadRequest;
import model.TeamTaskRequest;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.Assert.assertNotNull;

public class AssignTeamTaskControllerTest {
    private class FakeManagerInteractor implements IManager {
        @Override
        public ConcurrentHashMap<String, TeamFeedback> viewTeamFeedbacks() {
            return null;
        }

        @Override
        public void assignTeamTask(TeamTaskRequest ttr) {
            teamTask = new TeamTask(ttr);
        }

        @Override
        public boolean isValidTeamName(String n) {
            if("invalid".equals(n))
                return false;
            else
                return true;
        }

        @Override
        public boolean isValidLeadEmail(String e) {
            if("invalidLead@email.com".equals(e))
                return false;
            else
                return true;
        }

        @Override
        public List<Team> getTeamsWithLeads() {
            List<Team> l = new ArrayList<>();
            return l;
        }

        @Override
        public ConcurrentHashMap<String, List<Profile>> getNomineeProfilesByTeam() {
            return null;
        }

        @Override
        public void assignTeamLead(AssignTeamLeadRequest atlr) {

        }
    }

    private AssignTeamTaskController assignTeamTaskController;
    private TeamTaskRequest teamTaskRequest;
    private static TeamTask teamTask;

    @Before
    public void setup(){
        teamTaskRequest = new TeamTaskRequest(
                "Description", "TeamName", LocalDate.now()
        );
        assignTeamTaskController = new AssignTeamTaskController(new FakeManagerInteractor());
    }

    @Test
    public void hasBoundary(){
        assertNotNull(assignTeamTaskController.manager);
    }

    @Test
    public void assignTeamTask(){
        assignTeamTaskController.assignTeamTask(teamTaskRequest);
        assertNotNull(teamTask);
    }

    @Test (expected = AssignTeamTaskController.InvalidDescription.class)
    public void validTeamTaskRequest_invalidDescription(){
        teamTaskRequest.description = "";
        assignTeamTaskController.isValidTeamTaskRequest(teamTaskRequest);
    }

    @Test (expected = AssignTeamTaskController.InvalidTeamName.class)
    public void validTeamTaskRequest_invalidTeamName(){
        teamTaskRequest.teamName = "invalid";
        assignTeamTaskController.isValidTeamTaskRequest(teamTaskRequest);
    }

    @Test (expected = AssignTeamTaskController.InvalidDueDate.class)
    public void validTeamTaskRequest_invalidDueDate(){
        teamTaskRequest.dueDate = null;
        assignTeamTaskController.isValidTeamTaskRequest(teamTaskRequest);
    }

    @Test
    public void getTeamsWithLeads(){
        List<Team> teamsWithLeads = assignTeamTaskController.getTeamsWithLeads();
        assertNotNull(teamsWithLeads);
    }
}
