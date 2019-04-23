package controller;

import boundary.IManager;
import model.AssignTeamLeadRequest;

public class AssignTeamLeadController {
    public IManager managerBoundary;

    public AssignTeamLeadController(IManager m){
        managerBoundary = m;
    }

    public void assignTeamLead(AssignTeamLeadRequest a)
    {
        managerBoundary.assignTeamLead(a);
    }

}
