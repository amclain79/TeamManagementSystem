package boundary;

import entity.Profile;
import entity.Team;
import model.CreateProfileRequest;
import model.CreateProjectRequest;
import model.CreateTeamRequest;
import model.JoinTeamRequest;
import model.ProjectTypes.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

public class IUserTest {
    private class CalledCreateProject extends RuntimeException {}
    private class CalledCreateProfile extends RuntimeException {}
    private class CalledCreateTeam extends RuntimeException{}
    private class CalledIsUniqueTeamName extends RuntimeException {}
    private class CalledIsMaxTeams extends RuntimeException {}
    private class CalledAreTeamsFull extends RuntimeException {}
    private class CalledGetOpenTeams extends RuntimeException {}
    private class CalledGetProfiles extends RuntimeException {}
    private class CalledJoinTeam extends RuntimeException {}

    private class FakeUserInteractor implements IUser {

        @Override
        public void createProject(CreateProjectRequest r) {
            throw new CalledCreateProject();
        }

        @Override
        public Role createProfile(CreateProfileRequest r) {
            throw new CalledCreateProfile();
        }
        @Override
        public void createTeam(CreateTeamRequest r) {
            throw new CalledCreateTeam();
        }

        @Override
        public boolean isUniqueTeamName(String n) {
            throw new CalledIsUniqueTeamName();
        }

        @Override
        public boolean isMaxTeams() {
            throw new CalledIsMaxTeams();
        }

        @Override
        public boolean areTeamsFull() {
            throw new CalledAreTeamsFull();
        }

        @Override
        public List<Team> getOpenTeams() {
            throw new CalledGetOpenTeams();
        }

        @Override
        public List<Profile> getProfiles(Team t) {
            throw new CalledGetProfiles();
        }

        @Override
        public void joinTeam(JoinTeamRequest t) {
            throw new CalledJoinTeam();
        }

    }

    private IUser user;

    @Before
    public void setup(){
        user = new FakeUserInteractor();
    }

    @Test (expected = CalledCreateProject.class)
    public void createProject(){
        int maxTeams = 1;
        int maxMembers = 1;
        int minFeedbacks = 1;
        CreateProjectRequest request = new CreateProjectRequest(
                maxTeams, maxMembers, minFeedbacks
        );
        user.createProject(request);
    }

    @Test (expected = CalledCreateProfile.class)
    public void createProfile(){
        Role r = user.createProfile(new CreateProfileRequest(
                "name", "email@gmail.com", "edu", "exp"
        ));
    }

    @Test (expected = CalledCreateTeam.class)
    public void createTeam(){
        user.createTeam(new CreateTeamRequest("teamName", "email@gmail.com"));
    }

    @Test (expected = CalledIsUniqueTeamName.class)
    public void isUniqueTeamName(){
        user.isUniqueTeamName("teamName");
    }

    @Test (expected = CalledIsMaxTeams.class)
    public void isMaxTeams(){
        boolean teamsAreMaxed = user.isMaxTeams();
    }

    @Test (expected = CalledAreTeamsFull.class)
    public void areTeamsFull(){
        boolean teamsAreFull = user.areTeamsFull();
    }

    @Test (expected = CalledGetOpenTeams.class)
    public void getOpenTeams(){
        List<Team> openTeams = user.getOpenTeams();
    }

    @Test (expected = CalledGetProfiles.class)
    public void getProfiles(){
        List<Profile> profiles = user.getProfiles(new Team());
    }

    @Test (expected = CalledJoinTeam.class)
    public void joinTeam(){
        user.joinTeam(new JoinTeamRequest());
    }
}
