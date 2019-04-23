package gateway;

import entity.*;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface IGateway {
    Profile getProfile(String e);
    Team getTeam(String t);
    boolean isFirstProfile();
    void saveProfile(Profile p);
    void saveTeam(Team t);
    boolean isUniqueTeamName(String n);
    int getNumTeams();
    List<Team> getOpenTeams();
    List<Profile> getProfiles(Team t);
    MemberTask getMemberTask(String e);
    void saveMemberTask(MemberTask task);
    TeamTask getTeamTask(String e);
    void saveTeamTask(TeamTask tt);
    ConcurrentHashMap<String, TeamFeedback> getTeamFeedbacks();
    void saveTeamFeedback(TeamFeedback teamFeedback);
    ConcurrentHashMap<String, TeamLeadNominations> getTeamLeadNominations();
    void saveTeamLeadNominations(TeamLeadNominations teamLeadNominations);
    void removeTeamLeadNominations(String tn);
}
