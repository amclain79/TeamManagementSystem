package boundary;

import entity.TeamFeedback;
import entity.TeamLeadNominations;
import model.AssignTeamLeadRequest;

import java.util.concurrent.ConcurrentHashMap;

public interface IManager {
    ConcurrentHashMap<String, TeamFeedback> viewTeamFeedbacks();
    ConcurrentHashMap<String, TeamLeadNominations> viewTeamLeadNominations();
    void assignTeamLead(AssignTeamLeadRequest a);
}
