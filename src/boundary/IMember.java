package boundary;

import entity.MemberTask;
import entity.Profile;
import entity.Team;
import model.NominationRequest;
import java.util.List;

public interface IMember {
    Profile viewProfile(String email);
    MemberTask viewMemberTask(String email);
    void nominateLead(NominationRequest nr);
    Team getTeam(String e);
    List<Profile> getCandidateProfiles(String e);
}
