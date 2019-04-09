package controller;

import boundary.IMember;
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
        Assert.assertNotNull(profile);
    }

    private class FakeMemberInteractor implements IMember {
        @Override
        public Profile viewProfile(String email) {
            return new Profile();
        }
    }
}
