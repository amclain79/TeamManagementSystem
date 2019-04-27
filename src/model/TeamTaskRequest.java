package model;

import java.time.LocalDate;

public class TeamTaskRequest {
    public String description;
    public String teamName;
    public LocalDate dueDate;

    public TeamTaskRequest(String d, String tn, LocalDate dd) {
        description = d;
        teamName = tn;
        dueDate = dd;
    }
}
