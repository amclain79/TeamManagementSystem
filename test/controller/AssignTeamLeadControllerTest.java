package controller;

import boundary.IManager;
import entity.TeamFeedback;
import entity.TeamLeadNominations;
import model.AssignTeamLeadRequest;

import java.util.concurrent.ConcurrentHashMap;

public class AssignTeamLeadControllerTest {

    private AssignTeamLeadController assignTeamLeadController;


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
}
