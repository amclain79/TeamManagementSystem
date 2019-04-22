package controller;

import boundary.IUser;
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

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class CreateTeamControllerTest {
    private Team team;

    private class FakeUserInteractor implements IUser {
        @Override
        public void createProject(CreateProjectRequest r) {

        }

        @Override
        public Role createProfile(CreateProfileRequest r) {
            return null;
        }

        @Override
        public void createTeam(CreateTeamRequest r) {
            team = new Team(r);
        }

        @Override
        public boolean isUniqueTeamName(String n) {
            if(n.equals("duplicateTeamName"))
                return false;
            return true;
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

    private CreateTeamController c;
    @Before
    public void setup(){
        c = new CreateTeamController(new FakeUserInteractor());
    }

    @Test
    public void hasInteractor(){
        assertNotNull(c.userBoundary);
    }

    @Test
    public void createTeam(){
        c.createTeam(new CreateTeamRequest("teamName", "email@gmail.com"));
        assertTrue(team.teamName.equals("teamName"));
        assertTrue(team.teamMembers.get(0).equals("email@gmail.com"));
    }

    @Test (expected = CreateTeamController.InvalidTeamName.class)
    public void isValidCreateTeamRequest(){
        c.isValidCreateTeamRequest(new CreateTeamRequest());
    }

    @Test
    public void isUniqueTeamName(){
        assertTrue(c.isUniqueTeamName("teamName"));
        assertFalse(c.isUniqueTeamName("duplicateTeamName"));
    }
}
