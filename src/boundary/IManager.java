package boundary;

import entity.TeamFeedback;

import java.util.concurrent.ConcurrentHashMap;

public interface IManager {
    ConcurrentHashMap<String, TeamFeedback> viewTeamFeedbacks();
}
