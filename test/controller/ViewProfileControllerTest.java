package controller;

import boundary.IMember;
import entity.MemberTask;
import entity.Profile;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

public class ViewProfileControllerTest {

    private ViewProfileController controller;

    @Before
    public void setup(){
        controller = new ViewProfileController(new FakeMemberInteractor() );
    }

    @Test
    public void hasBoundary(){
       Assert.assertNotNull(controller.memberBoundary);

    }

    @Test
    public void viewProfile(){
        String email = "email@email.com";
        Profile profile = controller.viewProfile(email);
        Assert.assertEquals(email, profile.email);
    }

    private class FakeMemberInteractor implements IMember {
        @Override
        public Profile viewProfile(String email) {
            Profile p = new Profile();
            p.email = email;
            return p;
        }

        @Override
        public MemberTask viewTask(String email) { return null; }
    }
}
