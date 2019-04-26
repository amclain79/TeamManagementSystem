package controller;

import boundary.IManager;
import entity.Team;
import entity.TeamFeedback;
import model.TeamTaskRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.Assert.assertNotNull;

public class ViewTeamFeedbacksControllerTest {
    private class FakeManagerInteractor implements IManager{
        @Override
        public ConcurrentHashMap<String, TeamFeedback> viewTeamFeedbacks() {
            return new ConcurrentHashMap<>();
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
    }

    ViewTeamFeedbacksController viewTeamFeedbacksController;

    @Before
    public void setup(){
        viewTeamFeedbacksController = new ViewTeamFeedbacksController(new FakeManagerInteractor());
    }

    @Test
    public void hasIManagerBoundary(){
        assertNotNull(viewTeamFeedbacksController.managerBoundary);
    }

    @Test
    public void viewTeamFeedbacks(){
        ConcurrentHashMap<String, TeamFeedback> teamFeedbacks = viewTeamFeedbacksController.viewTeamFeedbacks();
        assertNotNull(teamFeedbacks);
    }
}
