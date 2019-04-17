package interactor;

import boundary.IUser;
import entity.Profile;
import entity.Project;
import entity.Team;
import gateway.IGateway;
import model.CreateProfileRequest;
import model.CreateProjectRequest;
import model.CreateTeamRequest;
import model.JoinTeamRequest;
import model.ProjectTypes.*;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class UserInteractorTest {
    private class CalledGetProfiles extends RuntimeException{}

    private boolean isManager = true;
    private Profile profile;
    private Team team;
    private int numTeams;
    private int numOpenTeams;

    private class MockProjectStateManager implements IGateway {
        @Override
        public Profile getProfile(String e) {
            if(e.equals(email))
                return null;

            Profile p = new Profile();
            p.role = Role.USER;
            return p;
        }

        @Override
        public boolean isFirstProfile() {
            if(isManager)
                return true;
            return false;
        }

        @Override
        public void saveProfile(Profile p) {
            profile = p;
        }

        @Override
        public void saveTeam(Team t) {
            team = t;
        }

        @Override
        public boolean isUniqueTeamName(String n) {
            if(n.equals("unique"))
                return true;
            return false;
        }

        @Override
        public int getNumTeams() {
            return numTeams;
        }

        @Override
        public List<Team> getOpenTeams() {
            List<Team> teams = new ArrayList<>();
            switch(numOpenTeams){
                case 0:
                    break;
                case 1:
                    teams.add(new Team());
            }
            return teams;
        }

        @Override
        public List<Profile> getProfiles(Team t) {
            throw new CalledGetProfiles();
        }
    }

    private String email;
    private UserInteractor userInteractor;
    private CreateProfileRequest createProfileRequest;

    @Before
    public void setup(){
        userInteractor = new UserInteractor(new MockProjectStateManager());
        email = "aaron.mclain@mavs.uta.edu";
        createProfileRequest = new CreateProfileRequest(
                "First Last",
                "email@gmail.com",
                "education",
                "experience"
        );
    }

    @Test
    public void instanceOfIUser(){
        assertTrue(userInteractor instanceof IUser);
    }

    @Test
    public void createProject(){
        int maxTeams = 1;
        int maxMembers = 1;
        int minFeedbacks = 1;
        CreateProjectRequest request = new CreateProjectRequest(
                maxTeams, maxMembers, minFeedbacks
        );
        userInteractor.createProject(request);
        assertEquals(maxTeams, Project.getInstance().maxTeams);
        assertEquals(maxMembers, Project.getInstance().maxMembers);
        assertEquals(minFeedbacks, Project.getInstance().minFeedbacks);
    }

    @Test
    public void hasGateway(){
        assertNotNull(userInteractor.gateway);
    }

    @Test
    public void createProfile_isManagerTrue(){
        isManager = true;
        Role r = userInteractor.createProfile(createProfileRequest);
        assertEquals(r, Role.MANAGER);
        assertEquals(profile.email, createProfileRequest.email);
        assertEquals(profile.education, createProfileRequest.education);
        assertEquals(profile.experience, createProfileRequest.experience);
        assertEquals(profile.name, createProfileRequest.name);
    }

    @Test
    public void createProfile_isManagerFalse(){
        isManager = false;
        Role r = userInteractor.createProfile(createProfileRequest);
        assertEquals(r, Role.USER);
    }

    @Test
    public void isFirstProfile_isManagerTrue(){
        isManager = true;
        assertTrue(userInteractor.gateway.isFirstProfile());
    }

    @Test
    public void isFirstProfile_isManagerFalse(){
        isManager = false;
        assertFalse(userInteractor.gateway.isFirstProfile());
    }

    @Test
    public void isUniqueTeamName(){
        assertTrue(userInteractor.isUniqueTeamName("unique"));
        assertFalse(userInteractor.isUniqueTeamName("duplicate"));
    }

    @Test
    public void createTeam(){
        userInteractor.createTeam(new CreateTeamRequest(
                "teamName", "email@gmail.com"
        ));
        assertTrue(team.teamName.equals("teamName"));
        assertTrue(team.teamMembers.get(0).equals("email@gmail.com"));
        assertEquals(Role.MEMBER.getValue(), profile.role.getValue());
    }

    @Test
    public void isMaxTeams(){
        Project.getInstance().maxTeams = 1;
        numTeams = 1;
        assertTrue(userInteractor.isMaxTeams());
        numTeams = 0;
        assertFalse(userInteractor.isMaxTeams());
    }

    @Test
    public void getOpenTeams(){
        numOpenTeams = 1;
        List<Team> openTeams = userInteractor.getOpenTeams();
        assertEquals(1, openTeams.size());
    }

    @Test
    public void areTeamsFull(){
        Project.getInstance().maxMembers = 1;
        numOpenTeams = 0;
        assertTrue(userInteractor.areTeamsFull());
        numOpenTeams = 1;
        assertFalse(userInteractor.areTeamsFull());
    }

    @Test (expected = CalledGetProfiles.class)
    public void getProfiles(){
        List<Profile> profiles = userInteractor.getProfiles(new Team());
    }

    @Test
    public void joinTeam(){
        isManager = false;
        userInteractor.createProfile(createProfileRequest);
        userInteractor.joinTeam(new JoinTeamRequest(new Team("team","creator@email.com"), "email@gmail.com"));
        assertTrue(team.teamMembers.contains("email@gmail.com"));
        assertEquals(profile.role, Role.MEMBER);
    }
}
