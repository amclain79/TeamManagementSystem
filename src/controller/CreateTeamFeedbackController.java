package controller;

import boundary.ILead;
import model.CreateTeamFeedbackRequest;

public class CreateTeamFeedbackController {
    ILead lead;

    public CreateTeamFeedbackController(ILead l) {
        lead = l;
    }

    public void createTeamFeedback(CreateTeamFeedbackRequest fbr) {
        if(validTeamFeedbackRequest(fbr)){
            lead.createTeamFeedback(fbr);
        }
    }

    private boolean validTeamFeedbackRequest(CreateTeamFeedbackRequest fbr) {
        if(fbr.feedback.equals("")){
            throw new InvalidFeedback();
        }
        return true;
    }

    public class InvalidFeedback extends RuntimeException {}
}
