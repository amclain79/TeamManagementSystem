package interactor;

import boundary.IUser;
import entity.*;
import gateway.IGateway;
import model.*;
import model.ProjectTypes.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class UserInteractor implements IUser {
    public IGateway gateway;

    public UserInteractor(IGateway g){
        gateway = g;
    }

    @Override
    public void createProject(CreateProjectRequest r) {
        Project p = Project.getInstance();
        p.maxTeams = r.maxTeams;
        p.maxMembers = r.maxMembers;
        p.minFeedbacks = r.minFeedbacks;
    }

    @Override
    public Role createProfile(CreateProfileRequest r) {
        Profile p = new Profile(r);
        if(isFirstProfile()) {
            p.role = Role.MANAGER;
        }else{
            p.role = Role.USER;
        }
        gateway.saveProfile(p);
        return p.role;
    }

    protected boolean isFirstProfile() {
        return gateway.getProfiles().isEmpty();
    }

    @Override
    public void createTeam(CreateTeamRequest ctr) {
        Team t = new Team(ctr);
        Profile p = gateway.getProfiles().get(t.teamMembers.get(0));
        p.role = Role.MEMBER;
        gateway.saveProfile(p);
        gateway.saveTeam(t);
    }

    @Override
    public boolean isUniqueTeamName(String n) {
        return !gateway.getTeams().containsKey(n);
    }

    @Override
    public boolean isMaxTeams() {
        int numTeams = gateway.getTeams().size();
        int maxTeams = Project.getInstance().maxTeams;
        return (numTeams == maxTeams);
    }

    @Override
    public boolean areTeamsFull() {
        return (createOpenTeamsList().size() == 0);
    }


    protected List<Team> createOpenTeamsList() {
        ConcurrentHashMap<String, Team> teams = gateway.getTeams();
        List<Team> l = new ArrayList<>();
        for(String k : ((Map<String, ?>)teams).keySet()) {
            if (teams.get(k).isOpen()) {
                l.add(teams.get(k));
            }
        }
        return l;
    }

    @Override
    public List<Team> getOpenTeams() {
        return createOpenTeamsList();
    }

    @Override
    public List<Profile> getTeamProfiles(Team t) {
        ConcurrentHashMap<String, Profile> profiles = gateway.getProfiles();
        List<Profile> result = new ArrayList<>();
        for(String tm : t.teamMembers) {
            result.add(profiles.get(tm));
        }
        return result;
    }

    @Override
    public void joinTeam(JoinTeamRequest jtr) {
        jtr.team.addMember(jtr.email);
        gateway.saveTeam(jtr.team);
        Profile p = gateway.getProfiles().get(jtr.email);
        p.role = Role.MEMBER;
        gateway.saveProfile(p);
    }
}
