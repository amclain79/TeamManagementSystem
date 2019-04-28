package controller;

import boundary.IMember;
import entity.MemberTask;
import entity.Profile;
import entity.Team;
import model.MemberFeedbackRequest;
import model.NominationRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class ViewProfileControllerTest {

    private ViewProfileController controller;

    @Before
    public void setup(){
        controller = new ViewProfileController(new FakeMemberInteractor() );
    }

    @Test
    public void hasBoundary(){
       Assert.assertNotNull(controller.memberBoundary);

    }

    @Test
    public void viewProfile(){
        String email = "email@email.com";
        Profile profile = controller.viewProfile(email);
        Assert.assertEquals(email, profile.email);
    }

    private class FakeMemberInteractor implements IMember {
        @Override
        public Profile viewProfile(String email) {
            Profile p = new Profile();
            p.email = email;
            return p;
        }

        @Override
        public MemberTask viewMemberTask(String email) { return null; }

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

        }
    }
}
