package gateway;

import entity.MemberTask;
import entity.Profile;
import entity.Team;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public class ProjectStateManager implements IGateway {
    private static ProjectStateManager instance;
    protected ConcurrentHashMap<String, Profile> profiles;
    protected ConcurrentHashMap<String, Team> teams;
    protected ConcurrentHashMap<String, MemberTask> memberTasks;

    private ProjectStateManager(){
        profiles = new ConcurrentHashMap<>();
        teams = new ConcurrentHashMap<>();
        memberTasks = new ConcurrentHashMap<>();
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
    public void saveTeam(Team t) {
        teams.put(t.teamName, t);
    }

    @Override
    public MemberTask getMemberTask(String e){
        return memberTasks.get(e);
    }

    @Override
    public void saveMemberTask(MemberTask task) {
        memberTasks.put(task.memberEmail, task);
    }
}
