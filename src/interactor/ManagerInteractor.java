package interactor;

import boundary.IManager;
import entity.Profile;
import entity.Team;
import entity.TeamFeedback;
import entity.TeamLeadNominations;
import gateway.IGateway;
import model.AssignTeamLeadRequest;
import model.ProjectTypes;

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
    public ConcurrentHashMap<String, TeamLeadNominations> viewTeamLeadNominations() {
        return gateway.getTeamLeadNominations();
    }

    @Override
    public void assignTeamLead(AssignTeamLeadRequest a) {
        //Profile member = gateway.getProfile(a.memberEmail);
        //member.role = ProjectTypes.Role.LEAD;
        //gateway.saveProfile(member);

        Team t = gateway.getTeam(a.teamName);
        t.addLead(a.memberEmail);
        gateway.saveTeam(t);

        gateway.removeTeamLeadNominations(a.teamName);
    }
}
