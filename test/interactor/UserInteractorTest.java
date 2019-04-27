package interactor;

import entity.*;
import gateway.IGateway;
import model.CreateProfileRequest;
import model.CreateProjectRequest;
import model.CreateTeamRequest;
import model.JoinTeamRequest;
import model.ProjectTypes.*;
import org.junit.Before;
import org.junit.Test;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

import static org.junit.Assert.*;

public class UserInteractorTest{

    private class FakeProjectStateManager implements IGateway{

        @Override
        public ConcurrentHashMap<String, Profile> getProfiles() {
            ConcurrentHashMap<String, Profile> profiles = new ConcurrentHashMap<>();
            if(!isFirstProfile){
                if(isJoinTeam){
                    profiles.put(joinTeamProfile.email, joinTeamProfile);
                } else {
                    profiles.put(profile.email, profile);
                }
            }
            return profiles;
        }

        @Override
        public void saveProfile(Profile p) {
            if(isJoinTeam){
                joinTeamProfile = p;
            } else {
                profile = p;
            }
        }

        @Override
        public ConcurrentHashMap<String, Team> getTeams() {
            ConcurrentHashMap<String, Team> teams = new ConcurrentHashMap<>();
            teams.put(team.teamName, team);
            return teams;
        }

        @Override
        public void saveTeam(Team t) {
            if(isJoinTeam){
                joinTeam = t;
            } else {
                team = t;
            }
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

    private UserInteractor userInteractor;
    private static Profile profile = new Profile("name", "name@email.com", "edu", "exp");
    private static boolean isFirstProfile;
    private static Team team = new Team("teamName", profile.email);
    private static Team joinTeam;
    private static Profile joinTeamProfile = new Profile("name", "name@email.com", "edu", "exp");
    private static boolean isJoinTeam;

    @Before
    public void setup(){
        userInteractor = new UserInteractor(new FakeProjectStateManager());
        isJoinTeam = false;
    }

    @Test
    public void hasGateway(){
        assertNotNull(userInteractor.gateway);
    }

    @Test
    public void createProject(){
        int maxTeams =  1;
        int maxMembers = 1;
        int minFeedbacks = 1;
        userInteractor.createProject(
                new CreateProjectRequest(
                        maxTeams, maxMembers, minFeedbacks
                )
        );
        assertEquals(maxTeams, Project.getInstance().maxTeams);
        assertEquals(maxMembers, Project.getInstance().maxMembers);
        assertEquals(minFeedbacks, Project.getInstance().minFeedbacks);
    }

    @Test
    public void createProfile(){
        String name = "name";
        String email = "name@email.com";
        String education = "edu";
        String experience = "exp";

        isFirstProfile = true;
        userInteractor.createProfile(
                new CreateProfileRequest(
                        name, email, education, experience
                )
        );
        assertEquals(Role.MANAGER.getValue(), profile.role.getValue());

        isFirstProfile = false;
        userInteractor.createProfile(
                new CreateProfileRequest(
                        name, email, education, experience
                )
        );
        assertEquals(Role.USER.getValue(), profile.role.getValue());
    }

    @Test
    public void isFirstProfile(){
        isFirstProfile = true;
        assertTrue(userInteractor.isFirstProfile());
        isFirstProfile = false;
        assertFalse(userInteractor.isFirstProfile());
    }

    @Test
    public void createTeam(){
        isFirstProfile = false;
        String teamName = "myTeam";
        userInteractor.createTeam(
                new CreateTeamRequest(
                        teamName, profile.email
                )
        );
        assertTrue(teamName.equals(team.teamName));
        assertTrue(team.teamMembers.contains(profile.email));
        assertEquals(Role.MEMBER.getValue(), profile.role.getValue());
    }

    @Test
    public void isUniqueTeamName(){
        assertFalse(userInteractor.isUniqueTeamName(team.teamName));
        assertTrue(userInteractor.isUniqueTeamName("unique"));
    }

    @Test
    public void isMaxTeams(){
        Project.getInstance().maxTeams = 2;
        assertFalse(userInteractor.isMaxTeams());
        Project.getInstance().maxTeams = 1;
        assertTrue(userInteractor.isMaxTeams());
    }

    @Test
    public void areTeamsFull(){
        Project.getInstance().maxMembers = 1;
        assertTrue(userInteractor.areTeamsFull());
        Project.getInstance().maxMembers = 2;
        assertFalse(userInteractor.areTeamsFull());
    }

    @Test
    public void createOpenTeamsList(){
        Project.getInstance().maxMembers = 1;
        assertEquals(0, userInteractor.createOpenTeamsList().size());
        Project.getInstance().maxMembers = 2;
        assertEquals(1, userInteractor.createOpenTeamsList().size());
    }

    @Test
    public void getOpenTeams(){
        Project.getInstance().maxMembers = 1;
        assertEquals(0, userInteractor.getOpenTeams().size());
        Project.getInstance().maxMembers = 2;
        assertEquals(1, userInteractor.getOpenTeams().size());
    }

    @Test
    public void getTeamProfiles(){
        isFirstProfile = false;
        List<Profile> teamProfiles = userInteractor.getTeamProfiles(team);
        assertEquals(1, teamProfiles.size());
        assertTrue(profile.email.equals(teamProfiles.get(0).email));
    }

    @Test
    public void joinTeam(){
        isJoinTeam = true;
        userInteractor.joinTeam(
                new JoinTeamRequest(
                        new Team("myTeam", "myTeam@email.com"), joinTeamProfile.email
                )
        );
        assertTrue(joinTeam.teamMembers.contains(joinTeamProfile.email));
        assertEquals(Role.MEMBER.getValue(), joinTeamProfile.role.getValue());
    }
}