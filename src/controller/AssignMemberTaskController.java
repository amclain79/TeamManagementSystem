package controller;

import boundary.ILead;
import entity.Profile;
import model.AssignMemberTaskRequest;

import java.util.concurrent.ConcurrentHashMap;

public class AssignMemberTaskController {
    ILead lead;

    public AssignMemberTaskController(ILead l) {
        lead = l;
    }

    public void assignMemberTask(AssignMemberTaskRequest mtr) {
        if(validAssignMemberTaskRequest(mtr)){
            lead.assignMemberTask(mtr);
        }
    }

    private boolean validAssignMemberTaskRequest(AssignMemberTaskRequest mtr) {
        if(mtr.daysToComplete <= 0) throw new InvalidDueDate();
        if(mtr.description.equals("")) throw new InvalidDescription();
        return true;
    }

    public ConcurrentHashMap<String, Profile> getMemberProfiles(String e) {
        return lead.getMemberProfiles(e);
    }

    public class InvalidDueDate extends RuntimeException{}
    public class InvalidDescription extends RuntimeException{}
}
