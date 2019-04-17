package entity;

import org.junit.Assert;
import org.junit.Test;

import java.util.Date;

public class MemberTaskTest {

    @Test
    public void canCreateMemberTask() {
        MemberTask memberTask = new MemberTask();
        Assert.assertNotNull(memberTask);
    }

    @Test
    public void canCreateMemberTaskWithAttributes() {
        String description = "Testing Member Task";
        Date date = new Date();
        String email = "email@email.com";
        MemberTask memberTask = new MemberTask(description, date, email);
        Assert.assertEquals(description, memberTask.description);
        Assert.assertEquals(0, date.compareTo(memberTask.date));
        Assert.assertEquals(email, memberTask.memberEmail);
    }

}
