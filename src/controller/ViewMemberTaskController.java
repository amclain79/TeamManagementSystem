package controller;

import boundary.IMemberTask;
import entity.MemberTask;

public class ViewMemberTaskController {

    IMemberTask memberTaskBoundary;

    public ViewMemberTaskController(IMemberTask boundary){
        this.memberTaskBoundary = boundary;
    }

    public MemberTask viewMemberTask(String email) {
        return memberTaskBoundary.viewTask(email);
    }
}
