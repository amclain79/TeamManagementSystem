package entity;

import org.junit.Before;
import org.junit.Test;
import java.util.Date;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

public class MemberTaskTest {

    private String description;
    private Date date;
    private String email;
    private MemberTask memberTask;

    @Before
    public void setup(){
        description = "description";
        date = new Date();
        email = "email@email.com";
        memberTask = new MemberTask(description, date, email);
    }

    @Test
    public void canCreateMemberTaskWithAttributes() {
        assertEquals(description, memberTask.description);
        assertEquals(0, date.compareTo(memberTask.date));
        assertEquals(email, memberTask.memberEmail);
    }

    @Test
    public void toStringTest(){
        String dateString = date.toString();
        assertTrue(memberTask.toString().contains(description));
        assertTrue(memberTask.toString().contains(dateString));
        assertTrue(memberTask.toString().contains(email));
    }
}
