package entity;

import java.util.List;

public class TeamLeadNominations {
    public String teamName;
    public List<String> memberNominations;


    public String toString(){
        return String.format(
                "Team: %s\nNominations: %s",
                teamName,
                memberNominations
        );
    }
}
