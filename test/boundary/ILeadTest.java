package boundary;

import entity.TeamTask;
import org.junit.Test;

public class ILeadTest {
    private class CalledViewTeamTask extends RuntimeException {}

    private class FakeLeadInteractor implements ILead {
        @Override
        public TeamTask viewTeamTask(String e) {
            throw new CalledViewTeamTask();
        }

    }

    @Test (expected = CalledViewTeamTask.class)
    public void viewTeamTask(){
        //arrange
        String teamLeadEmail = "teamLead@email.com";
        ILead lead = new FakeLeadInteractor();

        //act
        TeamTask teamTask = lead.viewTeamTask(teamLeadEmail);
    }
}
