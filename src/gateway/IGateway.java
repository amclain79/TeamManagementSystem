package gateway;

import entity.Profile;
import entity.Team;
import entity.MemberTask;

import java.util.List;

public interface IGateway {
    Profile getProfile(String e);
    boolean isFirstProfile();
    void saveProfile(Profile p);
    void saveTeam(Team t);
    boolean isUniqueTeamName(String n);
    int getNumTeams();
    List<Team> getOpenTeams();
    List<Profile> getProfiles(Team t);
    MemberTask getMemberTask(String e);
}
