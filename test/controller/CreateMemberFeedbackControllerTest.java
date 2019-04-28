package controller;

import boundary.IMember;
import entity.MemberTask;
import entity.Profile;
import entity.Team;
import model.MemberFeedbackRequest;
import model.NominationRequest;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class CreateMemberFeedbackControllerTest {
    private class FakeMemberInteractor implements IMember{

        @Override
        public Profile viewProfile(String email) {
            return null;
        }

        @Override
        public MemberTask viewMemberTask(String email) {
            return null;
        }

        @Override
        public void nominateLead(NominationRequest nr) {

        }

        @Override
        public Team getTeam(String e) {
            return null;
        }

        @Override
        public List<Profile> getCandidateProfiles(String e) {
            return null;
        }

        @Override
        public void createMemberFeedback(MemberFeedbackRequest mfr) {
            memberFeedbackRequest = mfr;
        }
    }

    private CreateMemberFeedbackController controller;
    private static MemberFeedbackRequest memberFeedbackRequest;

    @Before
    public void setup(){
        controller = new CreateMemberFeedbackController(new FakeMemberInteractor());
    }

    @Test
    public void createMemberFeedback(){
        controller.createMemberFeedback(
                new MemberFeedbackRequest(
                        "member@email.com", "feedback"
                )
        );
        assertNotNull(memberFeedbackRequest);
    }

    @Test (expected = CreateMemberFeedbackController.InvalidFeedback.class)
    public void createMemberFeedback_InvalidFeedback(){
        controller.createMemberFeedback(
                new MemberFeedbackRequest(
                        "member@email.com", ""
                )
        );
    }
}
