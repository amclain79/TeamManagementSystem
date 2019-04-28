package interactor;

import boundary.ILead;
import entity.*;
import gateway.IGateway;
import model.AssignMemberTaskRequest;
import model.CreateTeamFeedbackRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.Map;

public class LeadInteractor implements ILead {
    public IGateway gateway;

    public LeadInteractor(IGateway g){
        gateway = g;
    }

    @Override
    public TeamTask viewTeamTask(String e) {
        String teamName = "";
        ConcurrentHashMap<String, Team> teams = gateway.getTeams();
        for(String tn : ((Map<String, ?>)teams).keySet()){
            if(teams.get(tn).teamMembers.contains(e)){
                teamName = tn;
            }
        }
        if(teamName.equals("")){
            return null;
        } else {
            return gateway.getTeamTasks().get(teamName);
        }
    }

    @Override
    public void createTeamFeedback(CreateTeamFeedbackRequest cfr) {
        TeamFeedback teamFeedback = new TeamFeedback();
        ConcurrentHashMap<String, Team> teams = gateway.getTeams();
        for(String tn : ((Map<String, Team>)teams).keySet()){
            if(teams.get(tn).teamLead.equals(cfr.teamLead)){
                teamFeedback.date = cfr.date;
                teamFeedback.feedback = cfr.feedback;
                teamFeedback.teamName = tn;
                break;
            }
        }
        gateway.saveTeamFeedback(teamFeedback);
    }

    @Override
    public void assignMemberTask(AssignMemberTaskRequest mtr) {
        gateway.saveMemberTask(new MemberTask(mtr));
    }

    @Override
    public ConcurrentHashMap<String, Profile> getMemberProfiles(String e) {
        ConcurrentHashMap<String, Profile> profiles = gateway.getProfiles();
        ConcurrentHashMap<String, Team> teams = gateway.getTeams();
        List<String> members = new ArrayList<>();
        for(String tn : ((Map<String, Team>)teams).keySet()){
            if(teams.get(tn).teamMembers.contains(e)){
                members = teams.get(tn).teamMembers;
                break;
            }
        }
        ConcurrentHashMap<String, Profile> memberProfiles = new ConcurrentHashMap<>();
        for(String me : ((Map<String, Profile>)profiles).keySet()){
            if(members.contains(me) && !(me.equals(e))){
                memberProfiles.put(me, profiles.get(me));
            }
        }
        return memberProfiles;
    }
}
