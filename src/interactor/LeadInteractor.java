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
    public TeamTask viewTeamTask(String lead) {
        String teamName = "";
        ConcurrentHashMap<String, Team> teams = gateway.getTeams();
        for(String tn : ((Map<String, ?>)teams).keySet()){
            if(teams.get(tn).teamMembers.contains(lead)){
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
        for(String teamName : ((Map<String, Team>)teams).keySet()){
            if(teams.get(teamName).teamLead.equals(cfr.teamLead)){
                teamFeedback.date = cfr.date;
                teamFeedback.feedback = cfr.feedback;
                teamFeedback.teamName = teamName;
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
    public ConcurrentHashMap<String, Profile> getMemberProfiles(String lead) {
        ConcurrentHashMap<String, Profile> profiles = gateway.getProfiles();
        ConcurrentHashMap<String, Team> teams = gateway.getTeams();
        List<String> teamMembers = getTeamMembers(lead, teams);
        ConcurrentHashMap<String, Profile> memberProfiles = new ConcurrentHashMap<>();
        for(String member : ((Map<String, Profile>)profiles).keySet()){
            if(teamMembers.contains(member) && !(member.equals(lead))){
                memberProfiles.put(member, profiles.get(member));
            }
        }
        return memberProfiles;
    }

    @Override
    public ConcurrentHashMap<String, MemberFeedback> viewMemberFeedback(String lead) {
        ConcurrentHashMap<String, MemberFeedback> feedbacks = gateway.getMemberFeedbacks();
        ConcurrentHashMap<String, Team> teams = gateway.getTeams();
        List<String> teamMembers = getTeamMembers(lead, teams);
        ConcurrentHashMap<String, MemberFeedback> memberFeedbacks = new ConcurrentHashMap<>();
        for(String member : ((Map<String, MemberFeedback>)feedbacks).keySet()){
            if(teamMembers.contains(member) && !(member.equals(lead))){
                memberFeedbacks.put(member, feedbacks.get(member));
            }
        }
        return memberFeedbacks;
    }

    private List<String> getTeamMembers(String lead, ConcurrentHashMap<String, Team> teams) {
        List<String> members = new ArrayList<>();
        for (String teamName : ((Map<String, Team>) teams).keySet()) {
            if (teams.get(teamName).teamMembers.contains(lead)) {
                members = teams.get(teamName).teamMembers;
                break;
            }
        }
        return members;
    }
}
