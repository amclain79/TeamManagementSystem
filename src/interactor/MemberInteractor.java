package interactor;

import boundary.IMember;
import entity.MemberTask;
import entity.Nomination;
import entity.Profile;
import entity.Team;
import gateway.IGateway;
import model.NominationRequest;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MemberInteractor implements IMember {
    public IGateway gateway;

    public MemberInteractor(IGateway g){
        gateway = g;
    }

    public Profile viewProfile(String email){
       return gateway.getProfiles().get(email);
    }

    public MemberTask viewMemberTask(String email) {
        return gateway.getMemberTasks().get(email);
    }

    @Override
    public void nominateLead(NominationRequest nr) {
        gateway.saveNomination(new Nomination(nr));
    }

    @Override
    public Team getTeam(String e) {
        ConcurrentHashMap<String, Team> teams = gateway.getTeams();
        for(String tn : ((Map<String, ?>)teams).keySet()) {
            if (teams.get(tn).teamMembers.contains(e)) {
                return teams.get(tn);
            }
        }
        return null;
    }

    @Override
    public List<Profile> getCandidateProfiles(String e) {
        ConcurrentHashMap<String, Team> teams = gateway.getTeams();
        ConcurrentHashMap<String, Profile> profiles = gateway.getProfiles();

        List<Profile> candidates = new ArrayList<>();
        List<String> candidateEmails = new ArrayList<>();
        for(String t : ((Map<String, ?>)teams).keySet()) {
            if (teams.get(t).teamMembers.contains(e)) {
                candidateEmails = teams.get(t).teamMembers;
            }
        }

        for(String p : ((Map<String, ?>)profiles).keySet()){
            if(candidateEmails.contains(profiles.get(p).email) &&
                    (!e.equals(profiles.get(p).email))){
                candidates.add(profiles.get(p));
            }
        }

        return candidates;
    }
}
