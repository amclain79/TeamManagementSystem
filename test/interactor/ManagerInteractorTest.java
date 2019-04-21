package interactor;

import boundary.IManager;
import entity.*;
import gateway.IGateway;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class ManagerInteractorTest {
    private class FakeProjectStateManager implements IGateway {
        @Override
        public Profile getProfile(String e) {return null;}

        @Override
        public boolean isFirstProfile() {return false;}

        @Override
        public void saveProfile(Profile p) {}

        @Override
        public void saveTeam(Team t) {}

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
    }

    ManagerInteractor managerInteractor;

    @Before
    public void setup(){
        managerInteractor = new ManagerInteractor(new FakeProjectStateManager());
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
}
