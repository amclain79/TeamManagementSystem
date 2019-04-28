package interactor;

import boundary.ILead;
import entity.*;
import gateway.IGateway;
import model.AssignMemberTaskRequest;
import model.CreateTeamFeedbackRequest;
import model.MemberFeedbackRequest;
import model.ProjectTypes.*;
import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class LeadInteractorTest {
    private class FakeProjectStateManager implements IGateway {

        @Override
        public ConcurrentHashMap<String, Profile> getProfiles() {
            ConcurrentHashMap<String, Profile> profiles = new ConcurrentHashMap<>();
            profiles.put(leadProfile.email, leadProfile);
            profiles.put(memberProfile.email, memberProfile);
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
            memberTask = mt;
        }

        @Override
        public ConcurrentHashMap<String, TeamTask> getTeamTasks() {
            ConcurrentHashMap<String, TeamTask> teamTasks = new ConcurrentHashMap<>();
            teamTasks.put(teamTask.teamName, teamTask);
            return teamTasks;
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
            teamFeedback = tfb;
        }

        @Override
        public ConcurrentHashMap<String, MemberFeedback> getMemberFeedbacks() {
            ConcurrentHashMap<String, MemberFeedback> memberFeedbacks = new ConcurrentHashMap<>();
            memberFeedbacks.put(memberFeedback.memberEmail, memberFeedback);
            return memberFeedbacks;
        }

        @Override
        public void saveMemberFeedback(MemberFeedback mfb) {

        }

        @Override
        public ConcurrentHashMap<String, Nomination> getNominations() {
            return null;
        }

        @Override
        public void saveNomination(Nomination n) {

        }

        @Override
        public void saveNominations(ConcurrentHashMap<String, Nomination> n) {

        }
    }

    private LeadInteractor lead;
    private static TeamFeedback teamFeedback;
    private static Profile leadProfile = new Profile("lead", "lead@email.com", "edu", "exp");
    private static Team team = new Team("teamName", leadProfile.email);
    private static TeamTask teamTask = new TeamTask("description", team.teamName, LocalDate.now());
    private static MemberTask memberTask;
    private static Profile memberProfile = new Profile("member", "member@email.com", "edu", "exp");
    private static MemberFeedback memberFeedback =  new MemberFeedback(
                                                        new MemberFeedbackRequest(
                                                                memberProfile.email, "feedback"
                                                        )
                                                    );

    @Before
    public void setup(){
        lead = new LeadInteractor(new FakeProjectStateManager());
        team.assignTeamLead(leadProfile.email);
        team.addMember(memberProfile.email);
        memberProfile.role = Role.MEMBER;
    }

    @Test
    public void implementsILead(){
        assertTrue(lead instanceof ILead);
    }

    @Test
    public void hasGateway(){
        assertNotNull(lead.gateway);
    }

    @Test
    public void viewTeamTask(){
        TeamTask tt = lead.viewTeamTask(leadProfile.email);
        assertTrue(team.teamName.equals(tt.teamName));
    }

    @Test
    public void createTeamFeedback(){
        lead.createTeamFeedback(
                new CreateTeamFeedbackRequest(
                        leadProfile.email, "feedback"
                )
        );
        assertNotNull(teamFeedback);
        assertTrue(team.teamName.equals(teamFeedback.teamName));
        assertTrue("feedback".equals(teamFeedback.feedback));
    }

    @Test
    public void assignMemberTask(){
        String memberEmail = "member@email.com";
        lead.assignMemberTask(
                new AssignMemberTaskRequest(
                        5, memberEmail, "description"
                )
        );
        assertNotNull(memberTask);
    }

    @Test
    public void getMemberProfiles(){
        ConcurrentHashMap<String, Profile> memberProfiles = lead.getMemberProfiles(team.teamLead);
        assertNotNull(memberProfiles.get(memberProfile.email));
    }

    @Test
    public void viewMemberFeedback(){
        ConcurrentHashMap<String, MemberFeedback> memberFeedbacks = lead.viewMemberFeedback(team.teamLead);
        assertNotNull(memberFeedbacks.get(memberProfile.email));
    }
}
