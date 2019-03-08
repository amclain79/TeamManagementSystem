package entity;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class ProjectTest {
    @Test
    public void canCreateProject(){
        Project project = Project.getInstance();
    }

    @Test
    public void createProjectWithAttributes(){
        int maxTeams = 1;
        int maxMembers = 1;
        int minFeedbacks = 1;
        Project project = Project.getInstance();
        project.maxTeams = maxTeams;
        project.maxMembers = maxMembers;
        project.minFeedbacks = minFeedbacks;
        assertEquals(maxTeams, project.maxTeams);
        assertEquals(maxMembers, project.maxMembers);
        assertEquals(minFeedbacks, project.minFeedbacks);
    }
}
