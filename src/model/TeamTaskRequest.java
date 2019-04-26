package model;

import java.time.LocalDate;

public class TeamTaskRequest {
    public String description;
    public String teamName;
    public LocalDate dueDate;
    public String leadEmail;

    public TeamTaskRequest(String d, String tn, LocalDate dd, String e) {
        description = d;
        teamName = tn;
        dueDate = dd;
        leadEmail = e;
    }
}
