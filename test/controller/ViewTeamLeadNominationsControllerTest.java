package controller;

import boundary.IManager;
import entity.TeamFeedback;
import entity.TeamLeadNominations;
import model.AssignTeamLeadRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertNotNull;

public class ViewTeamLeadNominationsControllerTest {

    ViewTeamLeadNominationsController viewTeamLeadNominationsController;

    private class FakeManagerInteractor implements IManager {
        @Override
        public ConcurrentHashMap<String, TeamFeedback> viewTeamFeedbacks() {
            return null;
        }

        @Override
        public ConcurrentHashMap<String, TeamLeadNominations> viewTeamLeadNominations() {
            return new ConcurrentHashMap<>();
        }

        @Override
        public void assignTeamLead(AssignTeamLeadRequest a) {

        }
    }

    @Before
    public void setup(){
        viewTeamLeadNominationsController = new ViewTeamLeadNominationsController(new ViewTeamLeadNominationsControllerTest.FakeManagerInteractor());
    }

    @Test
    public void hasIManagerBoundary(){
        assertNotNull(viewTeamLeadNominationsController.managerBoundary);
    }

    @Test
    public void viewTeamLeadNominations(){
        ConcurrentHashMap<String, TeamLeadNominations> teamLeamNominations = viewTeamLeadNominationsController.viewTeamLeadNominations();
        assertNotNull(teamLeamNominations);
    }

}
