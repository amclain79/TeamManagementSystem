package interactor;

import boundary.IMember;
import entity.*;
import gateway.IGateway;
import model.NominationRequest;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MemberInteractorTest {

    private String email;
    private MemberInteractor memberInteractor;

    private class FakeProjectStateManager implements IGateway {
        @Override
        public Profile getProfile(String e) { 
            Profile p = new Profile();
            p.email = e;
            return p;
        }
        @Override
        public boolean isFirstProfile(){ return false; }
        @Override
        public void saveProfile(Profile p){}
        @Override
        public void saveTeam(Team t){}
        @Override
        public boolean isUniqueTeamName(String n){ return true; }
        @Override
        public int getNumTeams(){return 1;}
        @Override
        public List<Team> getOpenTeams() { return null; }
        @Override
        public List<Profile> getProfiles(Team t) { return null; }
        @Override
        public MemberTask getMemberTask(String e) { return null; }

        @Override
        public void saveMemberTask(MemberTask task) {}

        @Override
        public TeamTask getTeamTask(String e) {
            return null;
        }

        @Override
        public void saveTeamTask(TeamTask tt) {

        }

        @Override
        public ConcurrentHashMap<String, TeamFeedback> getTeamFeedbacks() {
            return null;
        }

        @Override
        public void saveTeamFeedback(TeamFeedback teamFeedback) {

        }

        @Override
        public boolean isValidTeamName(String teamName) {
            return false;
        }

        @Override
        public boolean isValidLeadEmail(String e) {
            return false;
        }

        @Override
        public List<Team> getTeamsWithLeads() {
            return null;
        }

        @Override
        public void saveNomination(Nomination n) {
            nomination = n;
        }

        @Override
        public ConcurrentHashMap<String, List<Nomination>> getNominations() {
            return null;
        }

        @Override
        public Team getTeam(String e) {
            return new Team();
        }

        @Override
        public List<Profile> getCandidateProfiles(String e) {
            return new ArrayList<>();
        }

    }

    private static Nomination nomination;

    @Before
    public void setup() {
        memberInteractor = new MemberInteractor(new FakeProjectStateManager());
        email = "test@gmail.com";
    }

    @Test
    public void instanceOfIMember(){
        assertTrue( memberInteractor instanceof IMember);
    }

    @Test
    public void hasGateway() {
        assertNotNull(memberInteractor.gateway);
    }

    @Test
    public void viewProfileTest() {
        Profile profile = memberInteractor.viewProfile("test@gmail.com");
        assertEquals(email, profile.email);
    }

    @Test
    public void nominateLead(){
        String nominee = "nominee@email.com";
        String teamName = "teamName";
        String nominator = "nominator@email.com";
        NominationRequest nominationRequest =
                new NominationRequest(nominee, teamName, nominator);
        memberInteractor.nominateLead(nominationRequest);
        assertNotNull(nomination);
    }

    @Test
    public void getTeam(){
        assertNotNull(memberInteractor.getTeam("member@email.com"));
    }

    @Test
    public void getCandidates(){
        assertNotNull(memberInteractor.getCandidateProfiles("member@email.com"));
    }
}
