package controller;

import boundary.IUser;
import model.CreateProfileRequest;
import model.ProjectTypes.*;

public class CreateProfileController {
    public IUser userBoundary;

    public CreateProfileController(IUser u) {
        userBoundary = u;
    }

    public Role createProfile(CreateProfileRequest r) {
        isValidCreateProfileRequest(r);
        return userBoundary.createProfile(r);
    }

    public boolean isValidCreateProfileRequest(CreateProfileRequest r) {
        if( r != null &&
                r.name != null && !r.name.equals("") &&
                r.education != null && !r.education.equals("") &&
                r.experience != null && !r.experience.equals(""))
            return true;
        else
            throw new InvalidCreateProfileRequest(r);
    }

    public class InvalidCreateProfileRequest extends RuntimeException {
        public InvalidCreateProfileRequest(CreateProfileRequest r) {
            super(String.format("'%s' is not a valid create profile request.", r.toString()));
        }
    }
}