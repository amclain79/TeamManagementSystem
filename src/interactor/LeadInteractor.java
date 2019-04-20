package interactor;

import boundary.ILead;
import entity.TeamTask;
import gateway.IGateway;

public class LeadInteractor implements ILead {
    public IGateway gateway;

    public LeadInteractor(IGateway g){
        gateway = g;
    }

    @Override
    public TeamTask viewTeamTask(String e) {
        return gateway.getTeamTask(e);
    }
}
