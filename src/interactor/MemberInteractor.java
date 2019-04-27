package interactor;

import boundary.IMember;
import entity.MemberTask;
import entity.Nomination;
import entity.Profile;
import entity.Team;
import gateway.IGateway;
import model.NominationRequest;

import java.util.List;

public class MemberInteractor implements IMember {
    public IGateway gateway;

    public MemberInteractor(IGateway g){
        gateway = g;
    }

    public Profile viewProfile(String email){
       return gateway.getProfile(email);
    }

    public MemberTask viewMemberTask(String email) {
        return gateway.getMemberTask(email);
    }

    @Override
    public void nominateLead(NominationRequest nr) {
        gateway.saveNomination(new Nomination(nr));
    }

    @Override
    public Team getTeam(String e) {
        return gateway.getTeam(e);
    }

    @Override
    public List<Profile> getCandidateProfiles(String e) {
        return gateway.getCandidateProfiles(e);
    }
}
