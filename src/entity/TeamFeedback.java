package entity;

import java.time.LocalDate;

public class TeamFeedback {
    public String teamName;
    public String feedback;
    public LocalDate date;

    public TeamFeedback(String tn, String fb, LocalDate d) {
        teamName = tn;
        feedback = fb;
        date = d;
    }

    public TeamFeedback() {}

    public String toString(){
        return String.format(
                "Date: %s\nTeam: %s\nFeedback: %s",
                date.toString(),
                teamName,
                feedback
        );
    }
}
