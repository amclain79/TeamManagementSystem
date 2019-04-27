package model;

import java.time.LocalDate;

public class CreateTeamFeedbackRequest {
    public LocalDate date;
    public String teamLead;
    public String feedback;

    public CreateTeamFeedbackRequest(String le, String tfb){
        date = LocalDate.now();
        teamLead = le;
        feedback = tfb;
    }
}
