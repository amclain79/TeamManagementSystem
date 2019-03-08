package entity;

import model.CreateTeamRequest;

import java.util.ArrayList;
import java.util.List;

public class Team implements Comparable<Team>{
    public String teamName;
    public List<String> teamMembers;

    public Team() {
        teamMembers = new ArrayList<>();
    }

    public Team(String n, String e){
        teamMembers = new ArrayList<>();
        teamName = n;
        teamMembers.add(e);
    }

    public Team(CreateTeamRequest r) {
        teamName = r.teamName;
        teamMembers = new ArrayList<>();
        addMember(r.memberEmail);
    }

    public void addMember(String email) {
        teamMembers.add(email);
    }

    public boolean isOpen() {
        return (teamMembers.size() < Project.getInstance().maxMembers);
    }

    @Override
    public int compareTo(Team o) {
        return this.teamName.compareTo(o.teamName);
    }
}
