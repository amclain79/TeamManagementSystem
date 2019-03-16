package interactor;

import boundary.IUser;
import entity.Profile;
import entity.Project;
import entity.Team;
import gateway.IGateway;
import model.CreateProfileRequest;
import model.CreateProjectRequest;
import model.CreateTeamRequest;
import model.JoinTeamRequest;
import model.ProjectTypes.*;

import java.util.List;

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
        if(gateway.isFirstProfile()) {
            p.role = Role.MANAGER;
        }else{
            p.role = Role.USER;
        }
        gateway.saveProfile(p);
        return p.role;
    }

    @Override
    public void createTeam(CreateTeamRequest r) {
        Team t = new Team(r);
        Profile p = gateway.getProfile(t.teamMembers.get(0));
        p.role = Role.MEMBER;
        gateway.saveProfile(p);
        gateway.saveTeam(t);
    }

    @Override
    public boolean isUniqueTeamName(String n) {
        return gateway.isUniqueTeamName(n);
    }

    @Override
    public boolean isMaxTeams() {
        int numTeams = gateway.getNumTeams();
        int maxTeams = Project.getInstance().maxTeams;
        return (numTeams == maxTeams);
    }

    @Override
    public boolean areTeamsFull() {
        int numOpenTeams = gateway.getOpenTeams().size();
        return (numOpenTeams == 0);
    }

    @Override
    public List<Team> getOpenTeams() {
        return gateway.getOpenTeams();
    }

    @Override
    public List<Profile> getProfiles(Team t) {
        return gateway.getProfiles(t);
    }

    @Override
    public void joinTeam(JoinTeamRequest r) {
        r.team.addMember(r.email);
        gateway.saveTeam(r.team);
        Profile p = gateway.getProfile(r.email);
        p.role = Role.MEMBER;
        gateway.saveProfile(p);
    }
}
