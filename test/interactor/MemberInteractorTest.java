package interactor;

import boundary.IUser;
import entity.MemberTask;
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
    }




    @Before
    public void setup() {
        memberInteractor = new MemberInteractor(new FakeProjectStateManager());
        email = "test@gmail.com";
    }

    @Test
    public void hasGateway() {
        assertNotNull(memberInteractor.gateway);
    }

    @Test
    public void viewProfileTest() {
        Profile profile = memberInteractor.viewProfile("test@gmail.com");
        Assert.assertEquals(email, profile.email);
    }
}
