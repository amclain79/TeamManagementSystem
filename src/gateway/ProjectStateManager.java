package gateway;

import entity.*;
import model.ProjectTypes;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ProjectStateManager implements IGateway {
    private static ProjectStateManager instance;
    protected ConcurrentHashMap<String, Profile> profiles;
    protected ConcurrentHashMap<String, Team> teams;
    protected ConcurrentHashMap<String, TeamTask> teamTasks;
    protected ConcurrentHashMap<String, TeamFeedback> teamFeedbacks;
    protected ConcurrentHashMap<String, TeamLeadNominations> teamLeadNominations;

    private ProjectStateManager(){
        profiles = new ConcurrentHashMap<>();
        teams = new ConcurrentHashMap<>();
        teamTasks = new ConcurrentHashMap<>();
        teamFeedbacks = new ConcurrentHashMap<>();
        teamLeadNominations = new ConcurrentHashMap<>();
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
    public Team getTeam(String t) {
        return teams.get(t);
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
    public ConcurrentHashMap<String, TeamFeedback> getTeamFeedbacks() {
        return teamFeedbacks;
    }

    @Override
    public void saveTeamFeedback(TeamFeedback teamFeedback) {
        teamFeedbacks.put(teamFeedback.teamName, teamFeedback);
    }

    @Override
    public ConcurrentHashMap<String, TeamLeadNominations> getTeamLeadNominations() {
        return teamLeadNominations;
    }

    @Override
    public void saveTeamLeadNominations(TeamLeadNominations tln) {
        teamLeadNominations.put(tln.teamName, tln);
    }

    @Override
    public void removeTeamLeadNominations(String tn) {
        teamLeadNominations.remove(tn);
    }

    @Override
    public void saveTeam(Team t) {
        teams.put(t.teamName, t);
    }
}
