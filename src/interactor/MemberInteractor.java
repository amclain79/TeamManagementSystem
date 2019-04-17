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
        Profile profile = gateway.getProfile(email);
        return profile;
    }

    public MemberTask viewTask(String email) {
        MemberTask memberTask = gatewayInteractor.getMemberTask(email);
        return memberTask;
    }
}
