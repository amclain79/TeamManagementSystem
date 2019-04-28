package interactor;

import boundary.IManager;
import entity.*;
import gateway.IGateway;
import model.AssignTeamLeadRequest;
import model.ProjectTypes.*;
import model.TeamTaskRequest;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.Assert.*;

public class ManagerInteractorTest {
    private class FakeProjectStateManager implements IGateway {

        @Override
        public ConcurrentHashMap<String, Profile> getProfiles() {
            ConcurrentHashMap<String, Profile> profiles = new ConcurrentHashMap<>();
            profiles.put(leadProfile.email, leadProfile);
            profiles.put(nominee.email, nominee);
            return profiles;
        }

        @Override
        public void saveProfile(Profile p) {
            nominee = p;
        }

        @Override
        public ConcurrentHashMap<String, Team> getTeams() {
            ConcurrentHashMap<String, Team> teams = new ConcurrentHashMap<>();
            teams.put(team.teamName, team);
            return teams;
        }

        @Override
        public void saveTeam(Team t) {
            team = t;
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
            return new ConcurrentHashMap<String, TeamFeedback>();
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

        }

        @Override
        public ConcurrentHashMap<String, Nomination> getNominations() {
            return nominations;
        }

        @Override
        public void saveNomination(Nomination n) {

        }

        @Override
        public void saveNominations(ConcurrentHashMap<String, Nomination> n) {
            nominations = n;
        }
    }

    private ManagerInteractor manager;
    private static TeamTask teamTask;
    private static Profile leadProfile = new Profile("lead", "lead@email.com", "edu", "exp");

    private static Profile nominee = new Profile("nominee", "nominee@email.com", "edu", "exp");
    private static Team team = new Team("teamName", nominee.email);
    private static Nomination nomination = new Nomination(nominee.email, "teamName", "nominator@email.com");
    private static ConcurrentHashMap<String, Nomination> nominations = new ConcurrentHashMap<>();


    @Before
    public void setup(){
        manager = new ManagerInteractor(new FakeProjectStateManager());
        leadProfile.role = Role.LEAD;
        nominations.put(nomination.nominator, nomination);
    }

    @Test
    public void implementsIManager(){
        assertTrue(manager instanceof IManager);
    }

    @Test
    public void hasIGateway(){
        assertNotNull(manager.gateway);
    }

    @Test
    public void getTeamFeedbacks(){
        ConcurrentHashMap<String, TeamFeedback> teamFeedbacks = manager.viewTeamFeedbacks();
        assertNotNull(teamFeedbacks);
    }

    @Test
    public void assignTeamTask(){
        String description = "description";
        String teamName = "teamName";
        LocalDate dueDate = LocalDate.now();
        String leadEmail = "lead@email.com";
        TeamTaskRequest expected = new TeamTaskRequest(description, teamName, dueDate);
        manager.assignTeamTask(expected);
    }

    @Test
    public void isValidTeamName(){
        assertTrue(manager.isValidTeamName(team.teamName));
    }

    @Test
    public void isValidLeadEmail(){
        assertTrue(manager.isValidLeadEmail(leadProfile.email));
    }

    @Test
    public void getTeamsWithLeads(){
        assertNotNull(manager.getTeamsWithLeads());
    }

    @Test
    public void getNomineeProfilesByTeam(){
        ConcurrentHashMap<String, List<Profile>> nomineeProfilesByTeam = manager.getNomineeProfilesByTeam();
        assertNotNull(nomineeProfilesByTeam);
        assertEquals(1, nomineeProfilesByTeam.size());
        assertTrue(nominee.email.equals(nomineeProfilesByTeam.get(nomination.teamName).get(0).email));
    }

    @Test
    public void assignTeamLead(){
        assertFalse(team.hasLead());
        assertTrue(nominations.contains(nomination));
        manager.assignTeamLead(
                new AssignTeamLeadRequest(
                        nominee, team.teamName
                )
        );
        assertTrue(nominee.email.equals(team.teamLead));
        assertTrue(team.teamMembers.contains(nominee.email));
        assertEquals(Role.LEAD.getValue(), nominee.role.getValue());
        assertTrue(team.hasLead());
        assertTrue(!nominations.contains(nomination));
    }
}
