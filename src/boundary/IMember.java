package boundary;

import entity.MemberTask;
import entity.Profile;

public interface IMember {
    Profile viewProfile(String email);
    MemberTask viewMemberTask(String email);
}
