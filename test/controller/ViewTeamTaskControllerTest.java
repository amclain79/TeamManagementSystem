package controller;

import boundary.ILead;
import entity.TeamTask;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

public class ViewTeamTaskControllerTest {
    private class FakeLeadInteractor implements ILead {
        @Override
        public TeamTask viewTeamTask(String e) {
            TeamTask teamTask = new TeamTask();
            teamTask.teamLeadEmail = e;
            return teamTask;
        }
    }

    private ViewTeamTaskController viewTeamTaskController;

    @Before
    public void setup() {
        viewTeamTaskController = new ViewTeamTaskController(new FakeLeadInteractor());
    }

    @Test
    public void hasILead() {
        assertNotNull(viewTeamTaskController.lead);
    }

    @Test
    public void viewTeamTask() {
        String leadEmail = "lead@email.com";
        TeamTask teamTask = viewTeamTaskController.viewTeamTask(leadEmail);
        assertEquals(leadEmail, teamTask.teamLeadEmail);
    }
}
