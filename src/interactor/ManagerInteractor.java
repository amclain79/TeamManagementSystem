package interactor;

import boundary.IManager;
import entity.TeamFeedback;
import gateway.IGateway;

import java.util.concurrent.ConcurrentHashMap;

public class ManagerInteractor implements IManager {
    public IGateway gateway;

    public ManagerInteractor(IGateway g){
        gateway = g;
    }

    @Override
    public ConcurrentHashMap<String, TeamFeedback> viewTeamFeedbacks() {
        return gateway.getTeamFeedbacks();
    }
}
