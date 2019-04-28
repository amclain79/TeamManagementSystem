package gateway;

import entity.*;
import java.util.concurrent.ConcurrentHashMap;

public class ProjectStateManager implements IGateway {
    private static ProjectStateManager instance;
    protected ConcurrentHashMap<String, Profile> profiles;
    protected ConcurrentHashMap<String, Team> teams;
    protected ConcurrentHashMap<String, MemberTask> memberTasks;
    protected ConcurrentHashMap<String, TeamTask> teamTasks;
    protected ConcurrentHashMap<String, TeamFeedback> teamFeedbacks;
    protected ConcurrentHashMap<String, MemberFeedback> memberFeedbacks;
    protected ConcurrentHashMap<String, Nomination> nominations;

    private ProjectStateManager(){
        profiles = new ConcurrentHashMap<>();
        teams = new ConcurrentHashMap<>();
        memberTasks = new ConcurrentHashMap<>();
        teamTasks = new ConcurrentHashMap<>();
        teamFeedbacks = new ConcurrentHashMap<>();
        memberFeedbacks = new ConcurrentHashMap<>();
        nominations = new ConcurrentHashMap<>();
    }

    protected void clear(){
        profiles.clear();
        teams.clear();
        memberTasks.clear();
        teamTasks.clear();
        teamFeedbacks.clear();
        memberFeedbacks.clear();
        nominations.clear();
    }

    public static ProjectStateManager getInstance() {
        if(instance == null)
            instance = new ProjectStateManager();
        return instance;
    }

    @Override
    public ConcurrentHashMap<String, Profile> getProfiles() {
        return profiles;
    }

    @Override
    public void saveProfile(Profile p) {
        profiles.put(p.email, p);
    }

    @Override
    public ConcurrentHashMap<String, Team> getTeams() {
        return teams;
    }

    @Override
    public void saveTeamTask(TeamTask tt) {
        teamTasks.put(tt.teamName, tt);
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
    public ConcurrentHashMap<String, MemberFeedback> getMemberFeedbacks() {
        return memberFeedbacks;
    }

    @Override
    public void saveMemberFeedback(MemberFeedback mfb) {
        memberFeedbacks.put(mfb.memberEmail, mfb);
    }

    @Override
    public void saveNomination(Nomination n) {
        nominations.put(n.nominator, n);
    }

    @Override
    public void saveNominations(ConcurrentHashMap<String, Nomination> n) {
        nominations = n;
    }

    @Override
    public ConcurrentHashMap<String, Nomination> getNominations() {
        return nominations;
    }

    @Override
    public void saveTeam(Team t) {
        teams.put(t.teamName, t);
    }

    @Override
    public ConcurrentHashMap<String, MemberTask> getMemberTasks(){
        return memberTasks;
    }

    @Override
    public void saveMemberTask(MemberTask task) {
        memberTasks.put(task.memberEmail, task);
    }

    @Override
    public ConcurrentHashMap<String, TeamTask> getTeamTasks() {
        return teamTasks;
    }
}
