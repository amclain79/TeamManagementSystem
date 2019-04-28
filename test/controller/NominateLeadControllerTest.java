package controller;

import boundary.IMember;
import entity.MemberTask;
import entity.Nomination;
import entity.Profile;
import entity.Team;
import model.MemberFeedbackRequest;
import model.NominationRequest;
import org.junit.Before;
import org.junit.Test;
import java.util.ArrayList;
import java.util.List;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class NominateLeadControllerTest {
    private class FakeMemberInteractor implements IMember {
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
            nomination = new Nomination(nr);
        }

        @Override
        public Team getTeam(String e) {
            return new Team();
        }

        @Override
        public List<Profile> getCandidateProfiles(String e) {
            return new ArrayList<>();
        }

        @Override
        public void createMemberFeedback(MemberFeedbackRequest mfr) {

        }
    }

    private NominateLeadController nominateLeadController;
    private static Nomination nomination;

    @Before
    public void setup(){
        nominateLeadController = new NominateLeadController(new FakeMemberInteractor());
    }

    @Test
    public void hasMemberBoundary(){
        assertNotNull(nominateLeadController.member);
    }

    @Test
    public void nominateLead(){
        nominateLeadController.nominateLead(new NominationRequest(
                "nominee@email.com", "teamName", "nominator@email.com"
        ));
        assertNotNull(nomination);
    }

    @Test
    public void getTeam(){
        assertNotNull(nominateLeadController.getTeam("member@email.com"));
    }

    @Test
    public void getCandidateProfiles(){
        assertNotNull(nominateLeadController.getCandidateProfiles("member@email.com"));
    }
}
