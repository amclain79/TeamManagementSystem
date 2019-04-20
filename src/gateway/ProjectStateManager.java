package gateway;

import entity.Profile;
import entity.Team;
import entity.TeamTask;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ProjectStateManager implements IGateway {
    private static ProjectStateManager instance;
    protected ConcurrentHashMap<String, Profile> profiles;
    protected ConcurrentHashMap<String, Team> teams;
    protected ConcurrentHashMap<String, TeamTask> teamTasks;

    private ProjectStateManager(){
        profiles = new ConcurrentHashMap<>();
        teams = new ConcurrentHashMap<>();
        teamTasks = new ConcurrentHashMap<>();
    }

    public static ProjectStateManager getInstance() {
        if(instance == null)
            instance = new ProjectStateManager();
        return instance;
    }

    @Override
    public Profile getProfile(String e) {
        return profiles.get(e);
    }

    @Override
    public boolean isFirstProfile() {
        return profiles.isEmpty();
    }

    @Override
    public void saveProfile(Profile p) {
        profiles.put(p.email, p);
    }

    @Override
    public boolean isUniqueTeamName(String n) {
        return !teams.containsKey(n);
    }

    @Override
    public int getNumTeams() {
        return teams.size();
    }

    @Override
    public List<Team> getOpenTeams() {
        List<Team> l = new ArrayList<>();
        for(String k : teams.keySet())
            if(teams.get(k).isOpen())
                l.add(teams.get(k));
        return l;
    }

    @Override
    public List<Profile> getProfiles(Team t) {
        List<Profile> result = new ArrayList<>();
        for(String m : t.teamMembers)
            result.add(profiles.get(m));
        return result;
    }

    @Override
    public TeamTask getTeamTask(String e) {
        return teamTasks.get(e);
    }

    @Override
    public void saveTeamTask(TeamTask tt) {
        teamTasks.put(tt.teamLeadEmail, tt);
    }

    @Override
    public void saveTeam(Team t) {
        teams.put(t.teamName, t);
    }
}
