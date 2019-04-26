package interactor;

import boundary.IManager;
import entity.Team;
import entity.TeamFeedback;
import entity.TeamTask;
import gateway.IGateway;
import model.TeamTaskRequest;

import java.util.List;
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

    @Override
    public void assignTeamTask(TeamTaskRequest ttr) {
        gateway.saveTeamTask(new TeamTask(ttr));
    }

    @Override
    public boolean isValidTeamName(String n) {
        return gateway.isValidTeamName(n);
    }

    @Override
    public boolean isValidLeadEmail(String e) {
        return gateway.isValidLeadEmail(e);
    }

    @Override
    public List<Team> getTeamsWithLeads() {
        return gateway.getTeamsWithLeads();
    }
}
