package controller;

import boundary.IMember;
import entity.Profile;

public class ViewProfileController {
    IMember memberBoundary;

    public ViewProfileController(IMember boundary) {
        this.memberBoundary = boundary;
    }

    public Profile viewProfile(String email) {
        return memberBoundary.viewProfile(email);
    }
}
