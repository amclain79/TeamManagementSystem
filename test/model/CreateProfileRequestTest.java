package model;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class CreateProfileRequestTest {
    CreateProfileRequest cpr1;
    CreateProfileRequest cpr2;
    String name;
    String email;
    String education;
    String experience;

    @Before
    public void setup(){
        cpr1 = new CreateProfileRequest();
        name = "first last";
        email = "email@gmail.com";
        education = "my education";
        experience = "my experience";
    }

    @Test
    public void createProfileRequest_assignment(){
        cpr1.name = name;
        cpr1.email = email;
        cpr1.education = education;
        cpr1.experience = experience;
        assertEquals(name, cpr1.name);
        assertEquals(email, cpr1.email);
        assertEquals(education, cpr1.education);
        assertEquals(experience, cpr1.experience);
    }

    @Test
    public void createProfileRequest_constructor(){
        cpr2 = new CreateProfileRequest(
                name, email, education, experience
        );
    }
}
