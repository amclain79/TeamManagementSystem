package controller;

import boundary.IMember;
import entity.Profile;
import entity.Team;
import model.NominationRequest;

import java.util.List;

public class NominateLeadController {
    public IMember member;

    public NominateLeadController(IMember m){
        member = m;
    }

    public void nominateLead(NominationRequest nr) {
        member.nominateLead(nr);
    }

    public Team getTeam(String e) {
        return member.getTeam(e);
    }

    public List<Profile> getCandidateProfiles(String e) {
        return member.getCandidateProfiles(e);
    }
}
