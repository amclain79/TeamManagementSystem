package controller;

import boundary.IPerson;
import model.CreateProfileRequest;
import model.ProjectTypes.*;
import org.junit.Before;
import org.junit.Test;
import static org.junit.Assert.*;

public class LoginControllerTest {
    private class FakePersonInteractor implements IPerson {
        @Override
        public Role login(String e) {
            if (e.equals(email))
                return Role.PERSON;
            else
                return Role.USER;
        }
    }

    private LoginController loginController;
    private String email;
    private CreateProfileRequest request;

    @Before
    public void setup(){
        loginController = new LoginController(new FakePersonInteractor());
        email = "aaron.mclain@mavs.uta.edu";
        request = new CreateProfileRequest(
                "First Last",
                "email@gmail.com",
                "education",
                "experience"
        );
    }

    @Test
    public void hasUserInteractor(){
        assertNotNull(loginController.personBoundary);
    }

    @Test
    public void login_createProfile(){
        Role role = loginController.login(email);
        assertEquals(Role.PERSON, role);
    }

    @Test
    public void login_showRoleMenu(){
        Role role = loginController.login("test@email.com");
        assertNotEquals(Role.PERSON, role);
    }

    @Test
    public void isValidEmail_true(){
        assertTrue(loginController.isValidEmail(email));
    }

    @Test (expected = LoginController.InvalidEmail.class)
    public void isValidEmail_false(){
        loginController.isValidEmail("invalid");
    }
}
