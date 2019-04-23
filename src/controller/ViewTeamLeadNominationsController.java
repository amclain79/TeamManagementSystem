package controller;

import boundary.IManager;
import entity.TeamFeedback;
import entity.TeamLeadNominations;

import java.util.concurrent.ConcurrentHashMap;

public class ViewTeamLeadNominationsController {
    public IManager managerBoundary;

    public ViewTeamLeadNominationsController(IManager m){

        managerBoundary = m;
    }

    public ConcurrentHashMap<String, TeamLeadNominations> viewTeamLeadNominations() {
        return managerBoundary.viewTeamLeadNominations();
    }

}
