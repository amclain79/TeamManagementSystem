package model;

import java.time.LocalDate;

public class MemberFeedbackRequest {
    public String memberEmail;
    public LocalDate date;
    public String feedback;

    public MemberFeedbackRequest(String e, String fb) {
        memberEmail = e;
        date = LocalDate.now();
        feedback = fb;
    }
}
