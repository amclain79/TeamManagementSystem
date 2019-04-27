package boundary;

import entity.Profile;
import entity.Team;
import model.CreateProfileRequest;
import model.CreateProjectRequest;
import model.CreateTeamRequest;
import model.JoinTeamRequest;
import model.ProjectTypes.*;

import java.util.List;

public interface IUser {
    void createProject(CreateProjectRequest r);
    Role createProfile(CreateProfileRequest r);
    void createTeam(CreateTeamRequest r);
    boolean isUniqueTeamName(String n);
    boolean isMaxTeams();
    boolean areTeamsFull();
    List<Team> getOpenTeams();
    List<Profile> getTeamProfiles(Team t);
    void joinTeam(JoinTeamRequest r);
}
