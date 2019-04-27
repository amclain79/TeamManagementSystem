package controller;

import boundary.IManager;
import entity.Profile;
import model.AssignTeamLeadRequest;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class AssignTeamLeadController {
    IManager manager;

    public AssignTeamLeadController(IManager m) {
        manager = m;
    }

    public ConcurrentHashMap<String, List<Profile>> getNomineeProfilesByTeam() {
        return manager.getNomineeProfilesByTeam();
    }

    public void assignTeamLead(AssignTeamLeadRequest atlr) {
        manager.assignTeamLead(atlr);
    }
}
