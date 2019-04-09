package interactor;

import boundary.IMember;
import entity.Profile;
import gateway.IGateway;

public class MemberInteractor implements IMember {
    public IGateway gatewayInteractor;

    public MemberInteractor(IGateway g){
        gatewayInteractor = g;
    }

    public Profile viewProfile(String email){
        Profile profile = gatewayInteractor.getProfile(email);
        return profile;
    }
}
