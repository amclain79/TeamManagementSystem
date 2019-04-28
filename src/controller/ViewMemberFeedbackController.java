package controller;

import boundary.ILead;
import entity.MemberFeedback;
import entity.Profile;

import java.util.concurrent.ConcurrentHashMap;

public class ViewMemberFeedbackController {
    public ILead lead;
    public ViewMemberFeedbackController(ILead l){
        lead = l;
    }

    public ConcurrentHashMap<String, MemberFeedback> viewMemberFeedback(String leadEmail) {
        return lead.viewMemberFeedback(leadEmail);
    }

    public ConcurrentHashMap<String, Profile> getMemberProfiles(String leadEmail) {
        return lead.getMemberProfiles(leadEmail);
    }
}
