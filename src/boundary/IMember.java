package boundary;

import entity.Profile;

public interface IMember {
    Profile viewProfile(String email);
    MemberTask viewTask(String email);
}
