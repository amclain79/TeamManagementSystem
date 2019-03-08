package entity;

public class Project {
    public int maxTeams;
    public int maxMembers;
    public int minFeedbacks;
    private static Project instance;
    public int numTeams;

    private Project() {}

    public static Project getInstance() {
        if(instance == null)
            instance = new Project();
        return instance;
    }
}
