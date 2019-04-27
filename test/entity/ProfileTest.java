package entity;

import model.CreateProfileRequest;
import model.ProjectTypes.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class ProfileTest {
    private Profile p;

    @Before
    public void setup(){
        p = new Profile();
        p.role = Role.USER;
    }

    @Test
    public void createProfile(){
        assertEquals(Role.USER, p.role);
    }

    @Test
    public void createProfile_createProfileRequest(){
        CreateProfileRequest r = new CreateProfileRequest(
                "First Last",
                "email@gmail.com",
                "education",
                "experience"
        );
        Profile p = new Profile(r);
        assertEquals(Role.PERSON.getValue(), p.role.getValue());
        assertTrue(r.name.equals(p.name));
        assertTrue(r.email.equals(p.email));
        assertTrue(r.education.equals(p.education));
        assertTrue(r.experience.equals(p.experience));
    }

    @Test
    public void compareTo(){
        Profile profile1 = new Profile();
        profile1.name = "profile1";
        Profile profile2 = new Profile();
        profile2.name = "profile2";
        int result1 = profile1.compareTo(profile2);
        int result2 = profile2.compareTo(profile1);
        assertTrue(result1 < 0);
        assertTrue(result2 > 0);
    }
}
