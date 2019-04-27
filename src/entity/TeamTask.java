package entity;

import model.TeamTaskRequest;
import java.time.LocalDate;

public class TeamTask {
    public String description;
    public String teamName;
    public LocalDate dueDate;

    public TeamTask(){}

    public TeamTask(TeamTaskRequest ttr){
        description = ttr.description;
        teamName = ttr.teamName;
        dueDate = ttr.dueDate;
    }

    public TeamTask(String d, String n, LocalDate dd){
        description = d;
        teamName = n;
        dueDate = dd;
    }

    public String toString(){
        return String.format(
                "Due: %s\nTeamName: %s\nDescription: %s\n",
                dueDate.toString(),
                teamName,
                description);
    }
}
