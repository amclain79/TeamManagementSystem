package interactor;

import boundary.IManager;
import entity.*;
import gateway.IGateway;
import model.AssignTeamLeadRequest;
import model.CreateTeamRequest;
import model.ProjectTypes;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static delivery.console.main.managerInteractor;
import static org.junit.Assert.*;
import static org.junit.Assert.assertNotNull;

public class ManagerInteractorTest {
    private class FakeProjectStateManager implements IGateway {
        @Override
        public Profile getProfile(String e) {
            if(e.equals(email))
                return null;

            Profile p = new Profile();
            p.role = ProjectTypes.Role.USER;
            return p;
        }

        @Override
        public Team getTeam(String t)
        {
            if(t.equals(team.teamName))
                return null;

            Team p = new Team(new CreateTeamRequest(
                    "Team 0",
                    "member0@email.com"
            ));

            return p;
        }

        @Override
        public boolean isFirstProfile() {return false;}

        @Override
        public void saveProfile(Profile p) {
            profile = p;
        }

        @Override
        public void saveTeam(Team t) {
            team = t;
        }


        @Override
        public boolean isUniqueTeamName(String n) {return false;}

        @Override
        public int getNumTeams() {return 0;}

        @Override
        public List<Team> getOpenTeams() {return null;}

        @Override
        public List<Profile> getProfiles(Team t) {return null;}

        @Override
        public MemberTask getMemberTask(String e) {
            return null;
        }

        @Override
        public void saveMemberTask(MemberTask task) {

        }

        @Override
        public TeamTask getTeamTask(String e) {return null;}

        @Override
        public void saveTeamTask(TeamTask tt) {}

        @Override
        public ConcurrentHashMap<String, TeamFeedback> getTeamFeedbacks() {
            return new ConcurrentHashMap<>();
        }

        @Override
        public void saveTeamFeedback(TeamFeedback teamFeedback) {

        }

        @Override
        public ConcurrentHashMap<String, TeamLeadNominations> getTeamLeadNominations() {
            return new ConcurrentHashMap<>();
        }

        @Override
        public void saveTeamLeadNominations(TeamLeadNominations teamLeadNominations) {

        }

        @Override
        public void removeTeamLeadNominations(String tn) {

        }

    }

    ManagerInteractor managerInteractor;
    Team team;
    private Profile profile;
    private String email;

    @Before
    public void setup(){
        managerInteractor = new ManagerInteractor(new FakeProjectStateManager());
        team = new Team();
        profile = new Profile();
    }

    @Test
    public void implementsIManager(){
        assertTrue(managerInteractor instanceof IManager);
    }

    @Test
    public void hasIGateway(){
        assertNotNull(managerInteractor.gateway);
    }

    @Test
    public void getTeamFeedbacks(){
        ConcurrentHashMap<String, TeamFeedback> teamFeedbacks = managerInteractor.viewTeamFeedbacks();
        assertNotNull(teamFeedbacks);
    }

    @Test
    public void getTeamLeadNominations(){
        ConcurrentHashMap<String, TeamLeadNominations> teamLeadNominations = managerInteractor.viewTeamLeadNominations();
        assertNotNull(teamLeadNominations);
    }

    @Test
    public void assignTeamLead(){
        email = "member0@email.com";
        AssignTeamLeadRequest a = new AssignTeamLeadRequest("Team 0", email);

        managerInteractor.assignTeamLead(a);
        assertEquals(email, team.teamLead);
    }



}
