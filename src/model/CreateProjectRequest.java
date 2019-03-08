package model;

public class CreateProjectRequest {
    public int maxTeams;
    public int maxMembers;
    public int minFeedbacks;

    public CreateProjectRequest(int t, int m, int f) {
        maxTeams = t;
        maxMembers = m;
        minFeedbacks = f;
    }

    public CreateProjectRequest() {}
}
