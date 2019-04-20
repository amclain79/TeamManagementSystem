package entity;

import java.util.Date;

public class TeamTask {
    public String description;
    public String teamName;
    public Date dueDate;
    public String teamLeadEmail;

    public String toString(){
        return String.format(
                "Date: %s\nTeamLeadEmail: %s\nTeamName: %s\nDescription: %s\n",
                dueDate.toString(),
                teamLeadEmail,
                teamName,
                description);
    }
}
