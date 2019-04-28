package controller;

import boundary.ILead;
import entity.Profile;
import entity.TeamTask;
import model.AssignMemberTaskRequest;
import model.CreateTeamFeedbackRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertNotNull;

public class CreateTeamFeedbackControllerTest {
    private class FakeLeadInteractor implements ILead{

        @Override
        public TeamTask viewTeamTask(String e) {
            return null;
        }

        @Override
        public void createTeamFeedback(CreateTeamFeedbackRequest cfr) {
            createTeamFeedbackRequest = cfr;
        }

        @Override
        public void assignMemberTask(AssignMemberTaskRequest mtr) {

        }

        @Override
        public ConcurrentHashMap<String, Profile> getMemberProfiles(String e) {
            return null;
        }
    }

    private CreateTeamFeedbackController controller;
    private static CreateTeamFeedbackRequest createTeamFeedbackRequest;

    @Before
    public void setup(){
        controller = new CreateTeamFeedbackController(new FakeLeadInteractor());
    }

    @Test
    public void createTeamFeedback(){
        controller.createTeamFeedback(
                new CreateTeamFeedbackRequest(
                        "lead@email.com", "feedback"
                )
        );
        assertNotNull(createTeamFeedbackRequest);
    }

    @Test (expected = CreateTeamFeedbackController.InvalidFeedback.class)
    public void createTeamFeedback_InvalidFeedback(){
        controller.createTeamFeedback(
                new CreateTeamFeedbackRequest(
                        "lead@email.com", ""
                )
        );
    }
}
