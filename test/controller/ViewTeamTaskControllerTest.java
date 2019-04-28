package controller;

import boundary.ILead;
import entity.MemberFeedback;
import entity.Profile;
import entity.TeamTask;
import model.AssignMemberTaskRequest;
import model.CreateTeamFeedbackRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ViewTeamTaskControllerTest {
    private class FakeLeadInteractor implements ILead {
        @Override
        public TeamTask viewTeamTask(String e) {
            TeamTask teamTask = new TeamTask();
            return teamTask;
        }

        @Override
        public void createTeamFeedback(CreateTeamFeedbackRequest cfr) {

        }

        @Override
        public void assignMemberTask(AssignMemberTaskRequest mtr) {

        }

        @Override
        public ConcurrentHashMap<String, Profile> getMemberProfiles(String e) {
            return null;
        }

        @Override
        public ConcurrentHashMap<String, MemberFeedback> viewMemberFeedback(String e) {
            return null;
        }
    }

    private ViewTeamTaskController viewTeamTaskController;

    @Before
    public void setup() {
        viewTeamTaskController = new ViewTeamTaskController(new FakeLeadInteractor());
    }

    @Test
    public void hasILead() {
        assertNotNull(viewTeamTaskController.lead);
    }

    @Test
    public void viewTeamTask() {
        String leadEmail = "lead@email.com";
        TeamTask teamTask = viewTeamTaskController.viewTeamTask(leadEmail);
    }
}
