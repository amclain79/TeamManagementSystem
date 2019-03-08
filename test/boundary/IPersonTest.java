package boundary;

import model.ProjectTypes.*;
import org.junit.Before;
import org.junit.Test;

public class IPersonTest {
    private class CalledLogin extends RuntimeException {}

    private class FakePersonInteractor implements IPerson {
        @Override
        public Role login(String e) {
            throw new CalledLogin();
        }
    }

    private IPerson person;

    @Before
    public void setup(){
        person = new FakePersonInteractor();
    }

    @Test (expected = CalledLogin.class)
    public void login(){
        String email = "aaron.r.mclain@gmail.com";
        Role r = person.login(email);
    }
}