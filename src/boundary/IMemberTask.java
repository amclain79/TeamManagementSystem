package boundary;

import entity.MemberTask;

public interface IMemberTask {
    MemberTask viewTask(String email);
}
