package boundary;

import entity.TeamFeedback;
import org.junit.Before;
import org.junit.Test;
import java.util.concurrent.ConcurrentHashMap;

public class IManagerTest {
    private class CalledViewTeamFeedbacks extends RuntimeException {}

    private class FakeManagerInteractor implements IManager {
        @Override
        public ConcurrentHashMap<String, TeamFeedback> viewTeamFeedbacks() {
            throw new CalledViewTeamFeedbacks();
        }

    }

    IManager managerBoundary;

    @Before
    public void setup(){
        managerBoundary = new FakeManagerInteractor();
    }

    @Test (expected = CalledViewTeamFeedbacks.class)
    public void viewTeamFeedbacks(){
        ConcurrentHashMap<String, TeamFeedback> teamFeedbacks = managerBoundary.viewTeamFeedbacks();
    }
}
