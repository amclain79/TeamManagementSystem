package entity;

import org.junit.Before;
import org.junit.Test;
import java.time.LocalDate;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MemberTaskTest {

    private String description;
    private LocalDate dueDate;
    private String memberEmail;
    private MemberTask memberTask;

    @Before
    public void setup(){
        description = "description";
        dueDate = LocalDate.now().plusDays(5);
        memberEmail = "member@email.com";
        memberTask = new MemberTask(description, dueDate, memberEmail);
    }

    @Test
    public void canCreateMemberTaskWithAttributes() {
        assertEquals(description, memberTask.description);
        assertEquals(0, dueDate.compareTo(memberTask.date));
        assertEquals(memberEmail, memberTask.memberEmail);
    }

    @Test
    public void toStringTest(){
        String dateString = dueDate.toString();
        assertTrue(memberTask.toString().contains(description));
        assertTrue(memberTask.toString().contains(dateString));
        assertTrue(memberTask.toString().contains(memberEmail));
    }
}
