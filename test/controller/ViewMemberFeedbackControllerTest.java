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

import static org.junit.Assert.assertNotNull;

public class ViewMemberFeedbackControllerTest {
    private class FakeLeadInteractor implements ILead{

        @Override
        public TeamTask viewTeamTask(String e) {
            return null;
        }

        @Override
        public void createTeamFeedback(CreateTeamFeedbackRequest cfr) {

        }

        @Override
        public void assignMemberTask(AssignMemberTaskRequest mtr) {

        }

        @Override
        public ConcurrentHashMap<String, Profile> getMemberProfiles(String e) {
            return new ConcurrentHashMap<>();
        }

        @Override
        public ConcurrentHashMap<String, MemberFeedback> viewMemberFeedback(String e) {
            return new ConcurrentHashMap<>();
        }
    }

    private ViewMemberFeedbackController controller;

    @Before
    public void setup(){
        controller = new ViewMemberFeedbackController(new FakeLeadInteractor());
    }

    @Test
    public void hasLead(){
        assertNotNull(controller.lead);
    }

    @Test
    public void viewMemberFeedback(){
        assertNotNull(controller.viewMemberFeedback("lead@email.com"));
    }

    @Test
    public void getMemberProfiles(){
        assertNotNull(controller.getMemberProfiles("lead@email.com"));
    }
}
