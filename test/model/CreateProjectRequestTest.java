package model;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateProjectRequestTest {
    @Test
    public void canMakeCreateProjectRequest(){
        CreateProjectRequest cpr = new CreateProjectRequest();
    }

    @Test
    public void canInitializeCreateProjectRequest(){
        int maxTeams = 1;
        int maxMembers = 1;
        int minFeedbacks = 1;
        CreateProjectRequest pr = new CreateProjectRequest(
                maxTeams, maxMembers, minFeedbacks
        );
        assertEquals(maxTeams, pr.maxTeams);
        assertEquals(maxMembers, pr.maxMembers);
        assertEquals(minFeedbacks, pr.minFeedbacks);
    }
}
