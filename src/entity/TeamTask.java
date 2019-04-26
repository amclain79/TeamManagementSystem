package entity;

import model.TeamTaskRequest;
import java.time.LocalDate;

public class TeamTask {
    public String description;
    public String teamName;
    public LocalDate dueDate;
    public String teamLeadEmail;

    public TeamTask(){}

    public TeamTask(TeamTaskRequest ttr){
        description = ttr.description;
        teamName = ttr.teamName;
        dueDate = ttr.dueDate;
        teamLeadEmail = ttr.leadEmail;
    }

    public TeamTask(String d, String n, LocalDate dd, String e){
        description = d;
        teamName = n;
        dueDate = dd;
        teamLeadEmail = e;
    }

    public String toString(){
        return String.format(
                "Due: %s\nTeamLeadEmail: %s\nTeamName: %s\nDescription: %s\n",
                dueDate.toString(),
                teamLeadEmail,
                teamName,
                description);
    }
}
