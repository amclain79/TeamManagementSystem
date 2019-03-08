package model;

import org.junit.Test;
import static org.junit.Assert.assertEquals;
import model.ProjectTypes.*;

public class ProjectTypesTest {
    @Test
    public void role(){
        assertEquals(0, Role.PERSON.getValue());
        assertEquals(1, Role.USER.getValue());
        assertEquals(2, Role.MEMBER.getValue());
        assertEquals(3, Role.LEAD.getValue());
        assertEquals(4, Role.MANAGER.getValue());
    }

    @Test
    public void managerMenu(){
        assertEquals(0, ManagerMenu.LOGOUT.getValue());
    }

    @Test
    public void userMenu(){
        assertEquals(0, UserMenu.LOGOUT.getValue());
        assertEquals(1, UserMenu.CREATE_TEAM.getValue());
        assertEquals(2, UserMenu.JOIN_TEAM.getValue());
    }

    @Test
    public void memberMenu(){
        assertEquals(0, MemberMenu.LOGOUT.getValue());
    }
}
