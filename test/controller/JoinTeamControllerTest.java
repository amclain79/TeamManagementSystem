package controller;

import boundary.IUser;
import entity.Profile;
import entity.Team;
import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertNotNull;

public class JoinTeamControllerTest {
    private class CalledGetOpenTeams extends RuntimeException {}
    private class CalledGetProfiles extends RuntimeException {}
    private class CalledJoinTeam extends RuntimeException {}

    private class FakeUserInteractor implements IUser {

        @Override
        public void createProject(CreateProjectRequest r) {

        }

        @Override
        public ProjectTypes.Role createProfile(CreateProfileRequest r) {
            return null;
        }

        @Override
        public void createTeam(CreateTeamRequest r) {

        }

        @Override
        public boolean isUniqueTeamName(String n) {
            return false;
        }

        @Override
        public boolean isMaxTeams() {
            return false;
        }

        @Override
        public boolean areTeamsFull() {
            return false;
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
        public void joinTeam(JoinTeamRequest chosenTeam) {
            throw new CalledJoinTeam();
        }
    }

    private JoinTeamController joinTeamController;

    @Before
    public void setup(){
        joinTeamController = new JoinTeamController(new FakeUserInteractor());
    }

    @Test
    public void hasUserBoundary(){
        assertNotNull(joinTeamController.userBoundary);
    }

    @Test (expected = CalledGetOpenTeams.class)
    public void getOpenTeams(){
        List<Team> openTeams = joinTeamController.getOpenTeams();
    }

    @Test (expected = CalledGetProfiles.class)
    public void getProfiles(){
        List<Profile> profiles = joinTeamController.getProfiles(new Team());
    }

    @Test (expected = CalledJoinTeam.class)
    public void joinTeam(){
        joinTeamController.joinTeam(new JoinTeamRequest());
    }
}
