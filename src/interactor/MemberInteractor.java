package interactor;

import boundary.IMember;
import entity.MemberTask;
import entity.Profile;
import gateway.IGateway;

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
}
