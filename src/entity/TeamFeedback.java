package entity;

import java.util.Date;

public class TeamFeedback {
    public String teamName;
    public String feedback;
    public Date date;

    public String toString(){
        return String.format(
                "Date: %s\nTeam: %s\nFeedback: %s",
                date.toString(),
                teamName,
                feedback
        );
    }
}
