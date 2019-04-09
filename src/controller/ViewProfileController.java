package controller;

import boundary.IMember;
import entity.Profile;

public class ViewProfileController {
    IMember boundary;

    public ViewProfileController(IMember boundary) {
        this.boundary = boundary;
    }

    public Profile viewProfile(String email) {
        return boundary.viewProfile(email);
    }
}
