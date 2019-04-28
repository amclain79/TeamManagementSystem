package entity;

import model.MemberFeedbackRequest;

import java.time.LocalDate;

public class MemberFeedback {
    public String memberEmail;
    public LocalDate date;
    public String feedback;

    public MemberFeedback(){}

    public MemberFeedback(MemberFeedbackRequest mfr){
        memberEmail = mfr.memberEmail;
        date = mfr.date;
        feedback = mfr.feedback;
    }

    @Override
    public String toString(){
        return String.format(
                "Date: %s\nMember: %s\nFeedback: %s",
                date,
                memberEmail,
                feedback
        );
    }
}
