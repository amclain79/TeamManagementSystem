package controller;

import boundary.IUser;

public class QueryController {
    private IUser userBoundary;

    public QueryController(IUser interactor) {
        userBoundary = interactor;
    }

    public boolean isMaxTeams() {
        return userBoundary.isMaxTeams();
    }

    public boolean areTeamsFull() {
        return userBoundary.areTeamsFull();
    }
}
