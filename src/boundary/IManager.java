package boundary;

import entity.Team;
import entity.TeamFeedback;
import model.TeamTaskRequest;

import java.util.List;
import java.util.concurrent.ConcurrentHashMap;

public interface IManager {
    ConcurrentHashMap<String, TeamFeedback> viewTeamFeedbacks();
    void assignTeamTask(TeamTaskRequest ttr);
    boolean isValidTeamName(String n);
    boolean isValidLeadEmail(String e);
    List<Team> getTeamsWithLeads();
}
