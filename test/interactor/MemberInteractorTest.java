package interactor;

import boundary.IMember;
import entity.*;
import gateway.IGateway;
import model.MemberFeedbackRequest;
import model.NominationRequest;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class MemberInteractorTest {

    private String email;
    private MemberInteractor member;

    private class FakeProjectStateManager implements IGateway {
        @Override
        public ConcurrentHashMap<String, Profile> getProfiles() {
            ConcurrentHashMap<String, Profile> profiles = new ConcurrentHashMap<>();
            profiles.put(member0.email, member0);
            profiles.put(member1.email, member1);
            return profiles;
        }

        @Override
        public void saveProfile(Profile p) {

        }

        @Override
        public ConcurrentHashMap<String, Team> getTeams() {
            ConcurrentHashMap<String, Team> teams = new ConcurrentHashMap<>();
            teams.put(team.teamName, team);
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
        public ConcurrentHashMap<String, MemberFeedback> getMemberFeedbacks() {
            return null;
        }

        @Override
        public void saveMemberFeedback(MemberFeedback mfb) {
            memberFeedback = mfb;
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
    private static Profile member0 = new Profile("candidate", "candidate@email.com", "edu", "exp");
    private static Profile member1 = new Profile("nominator", "nominator@email.com", "edu", "exp");
    private static Team team = new Team("teamName", member1.email);
    private static MemberFeedback memberFeedback;

    @Before
    public void setup() {
        member = new MemberInteractor(new FakeProjectStateManager());
        email = "test@gmail.com";
        team.addMember(member0.email);
    }

    @Test
    public void instanceOfIMember(){
        assertTrue( member instanceof IMember);
    }

    @Test
    public void hasGateway() {
        assertNotNull(member.gateway);
    }

    @Test
    public void viewProfileTest() {
        Profile profile = member.viewProfile(member1.email);
        assertTrue(profile.email.equals(member1.email));
    }

    @Test
    public void nominateLead(){
        String nominee = "nominee@email.com";
        String teamName = "teamName";
        String nominator = "nominator@email.com";
        NominationRequest nominationRequest =
                new NominationRequest(nominee, teamName, nominator);
        member.nominateLead(nominationRequest);
        assertNotNull(nomination);
    }

    @Test
    public void getTeam(){
        Team t = member.getTeam(member1.email);
        assertTrue(t.teamMembers.contains(member1.email));
    }

    @Test
    public void getCandidates(){
        List<Profile> candidates = member.getCandidateProfiles(member1.email);
        assertTrue(member0.email.equals(candidates.get(0).email));
    }

    @Test
    public void createMemberFeedback(){
        member.createMemberFeedback(
                new MemberFeedbackRequest(
                        member0.email, "feedback"
                )
        );
        assertNotNull(memberFeedback);
    }
}
