package controller;

import boundary.IUser;
import entity.Profile;
import entity.Team;
import model.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertTrue;

public class QueryControllerTest {

    public class FakeUserInteractor implements IUser{
        @Override
        public void createProject(CreateProjectRequest r) {}
        @Override
        public ProjectTypes.Role createProfile(CreateProfileRequest r) {return null; }
        @Override
        public void createTeam(CreateTeamRequest r) {}
        @Override
        public boolean isUniqueTeamName(String n) {return false;}

        @Override
        public boolean isMaxTeams() {
            return true;
        }

        @Override
        public boolean areTeamsFull() {
            return true;
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
        public Team getTeam(String t) {
            return null;
        }

        @Override
        public void joinTeam(JoinTeamRequest chosenTeam) {

        }
    }

    private QueryController queryController;

    @Before
    public void setup(){
        queryController = new QueryController(new FakeUserInteractor());
    }

    @Test
    public void isMaxTeams(){
        assertTrue(queryController.isMaxTeams());
    }

    @Test
    public void areTeamsFull(){
        assertTrue(queryController.areTeamsFull());
    }
}
