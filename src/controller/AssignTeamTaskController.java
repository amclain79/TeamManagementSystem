package controller;

import boundary.IManager;
import entity.Team;
import model.TeamTaskRequest;

import java.util.List;

public class AssignTeamTaskController {
    public IManager manager;

    public AssignTeamTaskController(IManager m){
        manager = m;
    }

    public void assignTeamTask(TeamTaskRequest ttr) {
        if(isValidTeamTaskRequest(ttr)){
            manager.assignTeamTask(ttr);
        }
    }

    protected boolean isValidTeamTaskRequest(TeamTaskRequest ttr) {
        if(ttr.description.equals("")) throw new InvalidDescription();
        if(!manager.isValidTeamName(ttr.teamName)) throw new InvalidTeamName();
        if(ttr.dueDate == null) throw new InvalidDueDate();
        return true;
    }

    public List<Team> getTeamsWithLeads() {
        return manager.getTeamsWithLeads();
    }

    public class InvalidDescription extends RuntimeException {}
    public class InvalidTeamName extends RuntimeException {}
    public class InvalidDueDate extends RuntimeException {}
}
