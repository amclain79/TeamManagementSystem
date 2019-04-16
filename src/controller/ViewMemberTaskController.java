package controller;

import boundary.IMember;
import entity.MemberTask;

public class ViewMemberTaskController {

    IMember boundary;

    public ViewMemberTaskController(IMemberTask boundary){
        this.memberTaskBoundary = boundary;
    }

    public MemberTask viewMemberTask(String email) {
        return memberTaskBoundary.viewTask(email);
    }
}
