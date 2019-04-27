package interactor;

import boundary.ILead;
import entity.Team;
import entity.TeamTask;
import gateway.IGateway;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class LeadInteractor implements ILead {
    public IGateway gateway;

    public LeadInteractor(IGateway g){
        gateway = g;
    }

    @Override
    public TeamTask viewTeamTask(String e) {
        String teamName = "";
        ConcurrentHashMap<String, Team> teams = gateway.getTeams();
        for(String tn : ((Map<String, ?>)teams).keySet()){
            if(teams.get(tn).teamMembers.contains(e)){
                teamName = tn;
            }
        }
        if(teamName.equals("")){
            return null;
        } else {
            return gateway.getTeamTasks().get(teamName);
        }
    }
}
