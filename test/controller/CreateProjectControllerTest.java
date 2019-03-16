package controller;

import boundary.IUser;
import entity.Profile;
import entity.Project;
import entity.Team;
import model.CreateProfileRequest;
import model.CreateProjectRequest;
import model.CreateTeamRequest;
import model.JoinTeamRequest;
import model.ProjectTypes.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;

import static org.junit.Assert.assertEquals;

public class CreateProjectControllerTest {
    private class FakeUserInteractor implements IUser {
        @Override
        public void createProject(CreateProjectRequest r) {
            Project project = Project.getInstance();
            project.maxTeams = r.maxTeams;
            project.maxMembers = r.maxMembers;
            project.minFeedbacks = r.minFeedbacks;
        }

        @Override
        public Role createProfile(CreateProfileRequest r) {
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
            return null;
        }

        @Override
        public List<Profile> getProfiles(Team t) {
            return null;
        }

        @Override
        public void joinTeam(JoinTeamRequest chosenTeam) {

        }
    }

    int maxTeams;
    int maxMembers;
    int minFeedbacks;
    CreateProjectController controller;

    @Before
    public void setup(){
        maxTeams = 1;
        maxMembers = 1;
        minFeedbacks = 1;
        controller = new CreateProjectController(new FakeUserInteractor());
    }

    @Test
    public void createProject(){
        CreateProjectRequest request = new CreateProjectRequest(
                maxTeams, maxMembers, minFeedbacks
        );
        controller.createProject(request);
        assertEquals(Project.getInstance().maxTeams, maxTeams);
        assertEquals(Project.getInstance().maxMembers, maxMembers);
        assertEquals(Project.getInstance().minFeedbacks, minFeedbacks);
    }

    @Test (expected = CreateProjectController.InvalidNumberOfTeams.class)
    public void zeroTeams(){
        maxTeams = 0;
        CreateProjectRequest zeroTeams = new CreateProjectRequest(
                maxTeams, maxMembers, minFeedbacks
        );
        controller.createProject(zeroTeams);
    }

    @Test (expected = CreateProjectController.InvalidNumberOfMembers.class)
    public void zeroMembers(){
        maxMembers = 0;
        CreateProjectRequest zeroMembers = new CreateProjectRequest(
                maxTeams, maxMembers, minFeedbacks
        );
        controller.createProject(zeroMembers);
    }

    @Test (expected = CreateProjectController.InvalidNumberOfFeedbacks.class)
    public void zeroFeedbacks() {
        minFeedbacks = 0;
        CreateProjectRequest zeroFeedbacks = new CreateProjectRequest(
                maxTeams, maxMembers, minFeedbacks
        );
        controller.createProject(zeroFeedbacks);
    }
}
