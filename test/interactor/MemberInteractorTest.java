package interactor;

import boundary.IUser;
import entity.Profile;
import entity.Team;
import gateway.IGateway;
import model.CreateProfileRequest;
import model.ProjectTypes.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class MemberInteractorTest {
    private class CalledViewProfile extends RuntimeException {}

    private String email;
    private MemberInteractor memberInteractor;

    private class MockProjectStateManager implements IGateway {
        @Override
        public Profile getProfile(String e) { throw new CalledViewProfile(); }
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
    }




    @Before
    public void setup() {
        memberInteractor = new MemberInteractor(new MockProjectStateManager());
        email = "test@gmail.com";
    }

    @Test
    public void hasGatewayInteractor() {
        assertNotNull(memberInteractor.gatewayInteractor);
    }

    @Test (expected = CalledViewProfile.class)
    public void viewProfileTest() {
        Profile profile = memberInteractor.viewProfile("test@gmail.com");
    }
}
