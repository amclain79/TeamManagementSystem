package gateway;

import entity.*;
import java.util.concurrent.ConcurrentHashMap;

public interface IGateway {
    ConcurrentHashMap<String, Profile> getProfiles();
    void saveProfile(Profile p);
    ConcurrentHashMap<String, Team> getTeams();
    void saveTeam(Team t);
    ConcurrentHashMap<String, MemberTask> getMemberTasks();
    void saveMemberTask(MemberTask mt);
    ConcurrentHashMap<String, TeamTask> getTeamTasks();
    void saveTeamTask(TeamTask tt);
    ConcurrentHashMap<String, TeamFeedback> getTeamFeedbacks();
    void saveTeamFeedback(TeamFeedback tfb);
    ConcurrentHashMap<String, Nomination> getNominations();
    void saveNomination(Nomination n);
}
