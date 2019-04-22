package controller;

import boundary.IManager;
import entity.TeamFeedback;
import entity.TeamLeadNominations;
import model.AssignTeamLeadRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertNotNull;

public class ViewTeamFeedbacksControllerTest {
    private class FakeManagerInteractor implements IManager{
        @Override
        public ConcurrentHashMap<String, TeamFeedback> viewTeamFeedbacks() {
            return new ConcurrentHashMap<>();
        }

        @Override
        public ConcurrentHashMap<String, TeamLeadNominations> viewTeamLeadNominations() {
            return null;
        }

        @Override
        public void assignTeamLead(AssignTeamLeadRequest a) {

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
