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

import static org.junit.Assert.*;

public class CreateProfileControllerTest {
    private class FakeUserInteractor implements IUser {
        @Override
        public void createProject(CreateProjectRequest r) {}

        @Override
        public Role createProfile(CreateProfileRequest r) {
            return Role.USER;
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

    private CreateProfileController createProfileController;
    private String email;
    private CreateProfileRequest request;

    @Before
    public void setup(){
        createProfileController = new CreateProfileController(new FakeUserInteractor());
        email = "aaron.mclain@mavs.uta.edu";
        request = new CreateProfileRequest(
                "First Last",
                "email@gmail.com",
                "education",
                "experience"
        );
    }

    @Test
    public void hasUserInteractor(){
        assertNotNull(createProfileController.userBoundary);
    }

    @Test
    public void createProfile(){
        CreateProfileRequest request = new CreateProfileRequest(
                "First Last",
                "email@gmail.com",
                "education",
                "experience"
        );
        Role r = createProfileController.createProfile(request);
        assertEquals(r, Role.USER);
    }

    @Test(expected = CreateProfileController.InvalidCreateProfileRequest.class)
    public void isValidCreateProfileRequest_false() {
        createProfileController.isValidCreateProfileRequest(new CreateProfileRequest());
    }

    @Test
    public void isValidCreateProfileRequest_true(){
        assertTrue(createProfileController.isValidCreateProfileRequest(request));
    }
}