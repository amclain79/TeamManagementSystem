package interactor;

import boundary.IManager;
import entity.Profile;
import entity.Team;
import entity.TeamFeedback;
import entity.TeamTask;
import gateway.IGateway;
import model.ProjectTypes.*;
import model.TeamTaskRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
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
    public boolean isValidTeamName(String tn) {
        return gateway.getTeams().containsKey(tn);
    }

    @Override
    public boolean isValidLeadEmail(String e) {
        Profile p = gateway.getProfiles().get(e);
        if(p == null) {
            return false;
        }else if(p.role != Role.LEAD) {
            return false;
        }
        return true;
    }

    @Override
    public List<Team> getTeamsWithLeads() {
        ConcurrentHashMap<String, Team> teams = gateway.getTeams();
        List<Team> result = new ArrayList<>();
        for(String m : ((Map<String, ?>)teams).keySet())
            if(teams.get(m).hasLead())
                result.add(teams.get(m));
        return result;
    }
}
