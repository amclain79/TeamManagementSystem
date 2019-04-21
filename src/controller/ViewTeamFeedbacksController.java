package controller;

import boundary.IManager;
import entity.TeamFeedback;

import java.util.concurrent.ConcurrentHashMap;

public class ViewTeamFeedbacksController {
    public IManager managerBoundary;

    public ViewTeamFeedbacksController(IManager m){
        managerBoundary = m;
    }

    public ConcurrentHashMap<String, TeamFeedback> viewTeamFeedbacks() {
        return managerBoundary.viewTeamFeedbacks();
    }
}
