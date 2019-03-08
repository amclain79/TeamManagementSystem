package interactor;

import boundary.IPerson;
import entity.Profile;
import gateway.IGateway;
import model.ProjectTypes.*;

public class PersonInteractor implements IPerson {
    public IGateway gateway;

    public PersonInteractor(IGateway g){
        gateway = g;
    }

    @Override
    public Role login(String e) {
        Profile p = gateway.getProfile(e);

        if(p == null)
            return Role.PERSON;

        return p.role;
    }
}