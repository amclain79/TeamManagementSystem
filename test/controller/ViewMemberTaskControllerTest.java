package controller;

import boundary.IMemberTask;
import entity.MemberTask;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ViewMemberTaskControllerTest {

    private ViewMemberTaskController controller;

    @Before
    public void setup(){ controller = new ViewMemberTaskController(new FakeTaskInteractor() );}

    @Test
    public void hasBoundary() {
        Assert.assertNotNull(controller.memberTaskBoundary);
    }

    @Test
    public void viewMemberTask(){
        String email = "email@email.com";
        MemberTask task = controller.viewMemberTask(email);
        Assert.assertEquals(email,task.owner);
    }

    private class FakeTaskInteractor implements IMemberTask {
        @Override
        public MemberTask viewTask(String email){
            Date  date = new Date();
            MemberTask memberTask = new MemberTask("Complete member task controller", date, "email@email.com");
            return memberTask;
        }
    }

}
