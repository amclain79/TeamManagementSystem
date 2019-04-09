package interactor;

import entity.Profile;
import entity.Team;
import gateway.IGateway;
import model.ProjectTypes.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

import java.util.List;

public class MemberInteractorTest {

    private String email;
    private MemberInteractor memberInteractor;

    private class MockProjectStateManager implements IGateway {
        @Override
        public Profile getProfile(String e) {
            if (e.equals(email)) { return null; }
            Profile p = new Profile();
            p.role = Role.USER;
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
    }

    @Before
    public void setup() {
        memberInteractor = new MemberInteractor(new MockProjectStateManager());
        email = "email@gmail.com";
    }

    @Test
    public void viewProfileTest() {
        String e = "Sabreen@gmail.com";
        Profile profile = memberInteractor.viewProfile(e);
        assertEquals(profile.email, e);
    }
}
