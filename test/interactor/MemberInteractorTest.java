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
        public ConcurrentHashMap<String, Profile> getProfiles() {
            ConcurrentHashMap<String, Profile> profiles = new ConcurrentHashMap<>();
            profiles.put(candidateProfile.email, candidateProfile);
            profiles.put(nominatorProfile.email, nominatorProfile);
            return profiles;
        }

        @Override
        public void saveProfile(Profile p) {

        }

        @Override
        public ConcurrentHashMap<String, Team> getTeams() {
            ConcurrentHashMap<String, Team> teams = new ConcurrentHashMap<>();
            teams.put(candidateTeam.teamName, candidateTeam);
            return teams;
        }

        @Override
        public void saveTeam(Team t) {

        }

        @Override
        public ConcurrentHashMap<String, MemberTask> getMemberTasks() {
            return null;
        }

        @Override
        public void saveMemberTask(MemberTask mt) {

        }

        @Override
        public ConcurrentHashMap<String, TeamTask> getTeamTasks() {
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
        public void saveTeamFeedback(TeamFeedback tfb) {

        }

        @Override
        public ConcurrentHashMap<String, Nomination> getNominations() {
            return null;
        }

        @Override
        public void saveNomination(Nomination n) {
            nomination = n;
        }

        @Override
        public void saveNominations(ConcurrentHashMap<String, Nomination> n) {

        }
    }

    private static Nomination nomination;
    private static Profile candidateProfile = new Profile("candidate", "candidate@email.com", "edu", "exp");
    private static Profile nominatorProfile = new Profile("nominator", "nominator@email.com", "edu", "exp");
    private static Team candidateTeam = new Team("candidateTeam", nominatorProfile.email);

    @Before
    public void setup() {
        memberInteractor = new MemberInteractor(new FakeProjectStateManager());
        email = "test@gmail.com";
        candidateTeam.addMember(candidateProfile.email);
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
        Profile profile = memberInteractor.viewProfile(nominatorProfile.email);
        assertTrue(profile.email.equals(nominatorProfile.email));
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
        Team t = memberInteractor.getTeam(nominatorProfile.email);
        assertTrue(t.teamMembers.contains(nominatorProfile.email));
    }

    @Test
    public void getCandidates(){
        List<Profile> candidates = memberInteractor.getCandidateProfiles(nominatorProfile.email);
        assertTrue(candidateProfile.email.equals(candidates.get(0).email));
    }
}
