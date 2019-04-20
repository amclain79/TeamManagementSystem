package gateway;

import entity.Profile;
import entity.Project;
import entity.Team;
import entity.TeamTask;
import model.CreateProfileRequest;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Date;
import java.util.List;
import static org.junit.Assert.*;

public class ProjectStateManagerTest {
    private void reset() {
        projectStateManager.profiles.clear();
        projectStateManager.teams.clear();
    }

    private ProjectStateManager projectStateManager = ProjectStateManager.getInstance();
    private String email;

    @Before
    public void setup(){
        reset();
        email = "aaron.r.mclain@gmail.com";
    }

    @Test
    public void instanceOfIGateway(){
        assertTrue(projectStateManager instanceof IGateway);
    }

    @Test
    public void getProfile_exists(){
        projectStateManager.profiles.put(email, new Profile());
        assertNotNull(projectStateManager.getProfile(email));
    }

    @Test
    public void getProfile_notExist(){
        assertNull(projectStateManager.getProfile(email));
    }

    @Test
    public void hasProfileMap(){
        assertNotNull(projectStateManager.profiles);
    }

    @Test
    public void saveProfile(){
        Profile p = new Profile();
        p.email = "email@gmail.com";
        projectStateManager.saveProfile(p);
        assertTrue(projectStateManager.profiles.containsKey(p.email));
        assertNotNull(projectStateManager.getProfile(p.email));
    }

    @Test
    public void isFirstProfile(){
        assertTrue(projectStateManager.isFirstProfile());
    }

    @Test
    public void isUniqueTeamName(){
        projectStateManager.saveTeam(new Team("duplicate","email@gmail.com"));
        assertTrue(projectStateManager.isUniqueTeamName("unique"));
        assertFalse(projectStateManager.isUniqueTeamName("duplicate"));
    }

    @Test
    public void saveTeam(){
        projectStateManager.saveTeam(new Team("teamName","email@gmail.com"));
        assertEquals(1, projectStateManager.teams.size());
    }

    @Test
    public void getNumTeams(){
        projectStateManager.teams.put("n", new Team("n","e"));
        assertEquals(1, projectStateManager.getNumTeams());
    }

    @Test
    public void getOpenTeams(){
        Project.getInstance().maxMembers = 1;
        Team open = new Team();
        open.teamName = "open";
        Team closed = new Team("closed", "closed@gmail.com");
        projectStateManager.saveTeam(open);
        projectStateManager.saveTeam(closed);
        List<Team> openTeams = projectStateManager.getOpenTeams();
        assertEquals(1, openTeams.size());
        assertTrue(open.teamName.equals(openTeams.get(0).teamName));
    }

    @Test
    public void getProfiles(){
        Profile member1 = new Profile(new CreateProfileRequest(
                "member1", "member1@email.com", "edu", "exp"
        ));
        Profile member2 = new Profile(new CreateProfileRequest(
                "member2", "member2@email.com", "edu", "exp"
        ));
        Team team = new Team("team", member1.email);
        team.addMember(member2.email);
        projectStateManager.saveProfile(member1);
        projectStateManager.saveProfile(member2);
        projectStateManager.saveTeam(team);
        List<Profile> profiles = projectStateManager.getProfiles(team);
        assertEquals(2, profiles.size());
        assertTrue(profiles.contains(member1));
        assertTrue(profiles.contains(member2));
    }

    @Test
    public void getTeamTask(){
        //Arrange
        String description = "description";
        Date dueDate = new Date();
        String teamName = "teamName";
        String teamLeadEmail = "teamLead@email.com";

        TeamTask teamTaskArrange = new TeamTask();
        teamTaskArrange.teamLeadEmail = teamLeadEmail;
        teamTaskArrange.description = description;
        teamTaskArrange.dueDate = dueDate;
        teamTaskArrange.teamName = teamName;

        projectStateManager.saveTeamTask(teamTaskArrange);

        //Act
        TeamTask teamTaskAct = projectStateManager.getTeamTask(teamLeadEmail);

        //Assert
        assertTrue(teamTaskArrange.teamLeadEmail.equals(teamTaskAct.teamLeadEmail));
        assertTrue(teamTaskArrange.description.equals(teamTaskAct.description));
        assertTrue(teamTaskArrange.dueDate.equals(teamTaskAct.dueDate));
        assertTrue(teamTaskArrange.teamName.equals(teamTaskAct.teamName));
    }
}
