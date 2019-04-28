package interactor;

import boundary.IPerson;
import entity.*;
import gateway.IGateway;
import model.ProjectTypes.*;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;
import static org.junit.Assert.assertTrue;

public class PersonInteractorTest {
    public class FakeProjectStateManager implements IGateway {
        @Override
        public ConcurrentHashMap<String, Profile> getProfiles() {
            ConcurrentHashMap<String, Profile> profiles = new ConcurrentHashMap<>();
            profiles.put(userProfile.email, userProfile);
            return profiles;
        }

        @Override
        public void saveProfile(Profile p) {

        }

        @Override
        public ConcurrentHashMap<String, Team> getTeams() {
            return null;
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

    private PersonInteractor interactor;
    private String noProfile;
    private String profile;
    private static Profile userProfile = new Profile("user", "user@email.com", "edu", "exp");

    @Before
    public void setup(){
        interactor = new PersonInteractor(new FakeProjectStateManager());
        noProfile = "noProfile@gmail.com";
        profile = "profile@gmail.com";
        userProfile.role = Role.USER;
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
        Role r = interactor.login(userProfile.email);
        assertEquals(r, Role.USER);
    }
}
