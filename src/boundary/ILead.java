package boundary;

import entity.Profile;
import entity.TeamTask;
import model.AssignMemberTaskRequest;
import model.CreateTeamFeedbackRequest;

import java.util.concurrent.ConcurrentHashMap;

public interface ILead {
    TeamTask viewTeamTask(String e);
    void createTeamFeedback(CreateTeamFeedbackRequest cfr);
    void assignMemberTask(AssignMemberTaskRequest mtr);
    ConcurrentHashMap<String, Profile> getMemberProfiles(String e);
}
