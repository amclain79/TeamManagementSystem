package gateway;

import entity.*;
import model.CreateProfileRequest;
import model.ProjectTypes.*;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import static org.junit.Assert.*;

public class ProjectStateManagerTest {
    private void reset() {
        projectStateManager.profiles.clear();
        projectStateManager.teams.clear();
        projectStateManager.memberTasks.clear();
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
    public void hasProfiles(){
        assertNotNull(projectStateManager.profiles);
    }

    @Test
    public void hasTeams(){
        assertNotNull(projectStateManager.teams);
    }

    @Test
    public void hasMemberTasks(){
        assertNotNull(projectStateManager.memberTasks);
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
    public void saveMemberTask(){
        projectStateManager.saveMemberTask(new MemberTask("Description", new Date(), "email@gmail.com"));
        assertEquals(1, projectStateManager.memberTasks.size());
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
    public void saveTeamTask(){
        String leadEmail = "teamLead@email.com";
        TeamTask tt = new TeamTask("descr","teamName", LocalDate.now(),leadEmail);
        projectStateManager.saveTeamTask(tt);
        assertTrue(leadEmail.equals(projectStateManager.getTeamTask(leadEmail).teamLeadEmail));
    }

    @Test
    public void getTeamTask(){
        String description = "description";
        LocalDate dueDate = LocalDate.now();
        String teamName = "teamName";
        String teamLeadEmail = "teamLead@email.com";

        TeamTask teamTask = new TeamTask();
        teamTask.teamLeadEmail = teamLeadEmail;
        teamTask.description = description;
        teamTask.dueDate = dueDate;
        teamTask.teamName = teamName;

        projectStateManager.saveTeamTask(teamTask);

        TeamTask teamTaskAct = projectStateManager.getTeamTask(teamLeadEmail);

        assertTrue(teamTask.teamLeadEmail.equals(teamTaskAct.teamLeadEmail));
        assertTrue(teamTask.description.equals(teamTaskAct.description));
        assertTrue(teamTask.dueDate.equals(teamTaskAct.dueDate));
        assertTrue(teamTask.teamName.equals(teamTaskAct.teamName));
    }

    @Test
    public void getTeamFeedbacks(){
        //Arrange
        String teamName = "teamName";
        TeamFeedback teamFeedback = new TeamFeedback();
        teamFeedback.teamName = teamName;
        projectStateManager.saveTeamFeedback(teamFeedback);
        //Act
        ConcurrentHashMap<String, TeamFeedback> teamFeedbacks = projectStateManager.getTeamFeedbacks();
        //Assert
        assertNotNull(teamFeedbacks);
        assertEquals(1, teamFeedbacks.size());
        assertTrue(teamName.equals(teamFeedbacks.get(teamName).teamName));
    }

    @Test
    public void isValidTeamName(){
        String invalid = "invalid";
        String valid = "valid";
        Team team = new Team(valid,"valid@email.com");
        projectStateManager.saveTeam(team);
        assertFalse(projectStateManager.isValidTeamName(invalid));
        assertTrue(projectStateManager.isValidTeamName(valid));
    }

    @Test
    public void isValidLeadEmail(){
        String invalidEmail = "doesNotExist@email.com";
        String validLeadEmail = "validLead@email.com";
        String validMemberEmail = "validMember@email.com";
        Profile lead = new Profile();
        lead.role = Role.LEAD;
        lead.email = validLeadEmail;
        Profile member = new Profile();
        member.role = Role.MEMBER;
        member.email = validMemberEmail;

        projectStateManager.saveProfile(lead);
        projectStateManager.saveProfile(member);

        assertFalse(projectStateManager.isValidLeadEmail(invalidEmail));
        assertFalse(projectStateManager.isValidLeadEmail(validMemberEmail));
        assertTrue(projectStateManager.isValidLeadEmail(validLeadEmail));
    }

    @Test
    public void getTeamsWithLeads(){
        Team t = new Team("teamName",email);
        t.assignTeamLead(email);
        projectStateManager.saveTeam(t);
        Team t2 = new Team("teamName2", email);
        projectStateManager.saveTeam(t2);

        List<Team> l = projectStateManager.getTeamsWithLeads();
        assertNotNull(l);
        assertTrue(l.get(0).teamLead.equals(email));
        assertEquals(1, l.size());
    }
}
