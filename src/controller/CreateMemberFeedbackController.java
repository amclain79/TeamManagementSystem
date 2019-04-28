package controller;

import boundary.IMember;
import model.MemberFeedbackRequest;

public class CreateMemberFeedbackController {
    private IMember member;

    public CreateMemberFeedbackController(IMember m) {
        member = m;
    }

    public void createMemberFeedback(MemberFeedbackRequest mfr) {
        if(validMemberFeedbackRequest(mfr)){
            member.createMemberFeedback(mfr);
        }
    }

    private boolean validMemberFeedbackRequest(MemberFeedbackRequest mfr) {
        if(mfr.feedback.equals("")) throw new InvalidFeedback();
        return true;
    }

    public class InvalidFeedback extends RuntimeException{}
}
