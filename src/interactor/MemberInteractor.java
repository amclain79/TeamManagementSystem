package interactor;

import boundary.IMember;
import entity.Profile;
import gateway.IGateway;

public class MemberInteractor implements IMember {
    public IGateway gateway;

    public MemberInteractor(IGateway g){
        gateway = g;
    }

    public Profile viewProfile(String email){
        Profile p = new Profile();
        return p;
    }
}
