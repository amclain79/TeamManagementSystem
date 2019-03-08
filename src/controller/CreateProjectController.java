package controller;

import boundary.IUser;
import model.CreateProjectRequest;

public class CreateProjectController {
    IUser userBoundary;

    public CreateProjectController(IUser u){
        userBoundary = u;
    }

    public void createProject(CreateProjectRequest r) {
        isValidCreateProjectRequest(r);
        userBoundary.createProject(r);
    }

    private void isValidCreateProjectRequest(CreateProjectRequest r) {
        if(r.maxTeams < 1) throw new InvalidNumberOfTeams();
        if(r.maxMembers < 1) throw new InvalidNumberOfMembers();
        if(r.minFeedbacks < 1) throw new InvalidNumberOfFeedbacks();
    }

    public static class InvalidNumberOfTeams extends RuntimeException {}
    public static class InvalidNumberOfMembers extends RuntimeException {}
    public static class InvalidNumberOfFeedbacks extends RuntimeException {}
}
