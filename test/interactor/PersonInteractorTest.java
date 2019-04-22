package interactor;

import boundary.IPerson;
import entity.*;
import gateway.IGateway;
import model.ProjectTypes.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class PersonInteractorTest {
    public class FakeProjectStateManager implements IGateway {

        @Override
        public Profile getProfile(String e) {
            if(e.equals(noProfile))
                return null;
            else
                return new Profile();
        }

        @Override
        public Team getTeam(String t) {
            return null;
        }

        @Override
        public boolean isFirstProfile() {
            return false;
        }

        @Override
        public void saveProfile(Profile p) {

        }

        @Override
        public void saveTeam(Team t) {

        }

        @Override
        public boolean isUniqueTeamName(String n) {
            return false;
        }

        @Override
        public int getNumTeams() {
            return 0;
        }

        @Override
        public List<Team> getOpenTeams() {
            return null;
        }

        @Override
        public List<Profile> getProfiles(Team t) {
            return null;
        }

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
        public ConcurrentHashMap<String, TeamLeadNominations> getTeamLeadNominations() {
            return null;
        }

        @Override
        public void saveTeamLeadNominations(TeamLeadNominations teamLeadNominations) {

        }

        @Override
        public void removeTeamLeadNominations(String tn) {

        }

    }

    private PersonInteractor interactor;
    private String noProfile;
    private String profile;

    @Before
    public void setup(){
        interactor = new PersonInteractor(new FakeProjectStateManager());
        noProfile = "noProfile@gmail.com";
        profile = "profile@gmail.com";
    }

    @Test
    public void instanceOfIPerson(){
        assertTrue(interactor instanceof IPerson);
    }

    @Test
    public void login_createProfile(){

        Role r = interactor.login(noProfile);
        assertEquals(r, Role.PERSON);
    }

    @Test
    public void login_showRoleMenu(){
        Role r = interactor.login(profile);
        assertNotEquals(r, Role.PERSON);
    }
}
