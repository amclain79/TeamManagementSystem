package boundary;

import entity.TeamTask;
import model.CreateTeamFeedbackRequest;

public interface ILead {
    TeamTask viewTeamTask(String e);
    void createTeamFeedback(CreateTeamFeedbackRequest cfr);
}
