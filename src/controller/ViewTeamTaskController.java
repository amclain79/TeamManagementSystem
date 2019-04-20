package controller;
import boundary.ILead;
import entity.TeamTask;

public class ViewTeamTaskController {
    public ILead lead;

    public ViewTeamTaskController(ILead l){
        lead = l;
    }

    public TeamTask viewTeamTask(String e) {
        return lead.viewTeamTask(e);
    }
}
