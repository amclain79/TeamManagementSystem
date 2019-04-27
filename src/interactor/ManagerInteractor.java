package interactor;

import boundary.IManager;
import entity.*;
import gateway.IGateway;
import model.AssignTeamLeadRequest;
import model.ProjectTypes.*;
import model.TeamTaskRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class ManagerInteractor implements IManager {
    public IGateway gateway;

    public ManagerInteractor(IGateway g){
        gateway = g;
    }

    @Override
    public ConcurrentHashMap<String, TeamFeedback> viewTeamFeedbacks() {
        return gateway.getTeamFeedbacks();
    }

    @Override
    public void assignTeamTask(TeamTaskRequest ttr) {
        gateway.saveTeamTask(new TeamTask(ttr));
    }

    @Override
    public boolean isValidTeamName(String tn) {
        return gateway.getTeams().containsKey(tn);
    }

    @Override
    public boolean isValidLeadEmail(String e) {
        Profile p = gateway.getProfiles().get(e);
        if(p == null) {
            return false;
        }else if(p.role != Role.LEAD) {
            return false;
        }
        return true;
    }

    @Override
    public List<Team> getTeamsWithLeads() {
        ConcurrentHashMap<String, Team> teams = gateway.getTeams();
        List<Team> result = new ArrayList<>();
        for(String m : ((Map<String, ?>)teams).keySet())
            if(teams.get(m).hasLead())
                result.add(teams.get(m));
        return result;
    }

    @Override
    public ConcurrentHashMap<String, List<Profile>> getNomineeProfilesByTeam() {
        ConcurrentHashMap<String, List<Profile>> nomineeProfilesByTeam = new ConcurrentHashMap<>();
        ConcurrentHashMap<String, Nomination> nominations = gateway.getNominations();
        Nomination nomination;
        for(String nominator : ((Map<String, ?>)nominations).keySet()){
            nomination = nominations.get(nominator);
            if(nomineeProfilesByTeam.containsKey(nomination.teamName)){
                List<Profile> nomineeProfiles = nomineeProfilesByTeam.get(nomination.teamName);
                nomineeProfiles.add(gateway.getProfiles().get(nomination.nominee));
                nomineeProfilesByTeam.put(nomination.teamName, nomineeProfiles);
            } else {
                List<Profile> nomineeProfiles = new ArrayList<>();
                nomineeProfiles.add(gateway.getProfiles().get(nomination.nominee));
                nomineeProfilesByTeam.put(nomination.teamName, nomineeProfiles);
            }
        }
        return nomineeProfilesByTeam;
    }

    @Override
    public void assignTeamLead(AssignTeamLeadRequest atlr) {
        Team team = gateway.getTeams().get(atlr.teamName);
        team.assignTeamLead(atlr.nominee.email);
        atlr.nominee.role = Role.LEAD;
        gateway.saveTeam(team);
        gateway.saveProfile(atlr.nominee);
        removeNominations(team.teamName);
    }

    private void removeNominations(String teamName){
        ConcurrentHashMap<String, Nomination> nominations = gateway.getNominations();
        Nomination nomination;
        for(String nominator : ((Map<String, ?>)nominations).keySet()) {
            nomination = nominations.get(nominator);
            if(nomination.teamName == teamName) {
                nominations.remove(nominator);
            }
        }
        gateway.saveNominations(nominations);
    }
}
