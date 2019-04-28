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

import java.time.LocalDate;
import java.util.Date;
import java.util.List;

public class ViewMemberTaskControllerTest {

    private ViewMemberTaskController controller;

    @Before
    public void setup(){ 
        controller = new ViewMemberTaskController(new FakeMemberInteractor() );
    }

    @Test
    public void hasBoundary() {
        Assert.assertNotNull(controller.boundary);
    }

    @Test
    public void viewMemberTask(){
        String email = "email@email.com";
        MemberTask task = controller.viewMemberTask(email);
        Assert.assertEquals(email, task.memberEmail);
    }

    private class FakeMemberInteractor implements IMember {
        @Override
        public MemberTask viewMemberTask(String email){
            LocalDate date = LocalDate.now();
            MemberTask memberTask = new MemberTask("Complete member description controller", date, "email@email.com");
            return memberTask;
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

        }

        @Override
        public Profile viewProfile(String email){
            return null;
        }
    }

}
