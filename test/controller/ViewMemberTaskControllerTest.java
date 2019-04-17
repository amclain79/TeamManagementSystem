package controller;

import boundary.IMember;
import entity.MemberTask;
import entity.Profile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Date;

public class ViewMemberTaskControllerTest {

    private ViewMemberTaskController controller;

    @Before
    public void setup(){ 
        controller = new ViewMemberTaskController(new FakeMemberInteractor() );
    }

    @Test
    public void hasBoundary() {
        Assert.assertNotNull(controller.boundary);
    }

    @Test
    public void viewMemberTask(){
        String email = "email@email.com";
        MemberTask task = controller.viewMemberTask(email);
        Assert.assertEquals(email, task.memberEmail);
    }

    private class FakeMemberInteractor implements IMember {
        @Override
        public MemberTask viewTask(String email){
            Date  date = new Date();
            MemberTask memberTask = new MemberTask("Complete member task controller", date, "email@email.com");
            return memberTask;
        }

        @Override
        public Profile viewProfile(String email){
            return null;
        }
    }

}
