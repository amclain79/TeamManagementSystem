package entity;

import model.AssignMemberTaskRequest;
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
        assertEquals(0, dueDate.compareTo(memberTask.dueDate));
        assertEquals(memberEmail, memberTask.memberEmail);
    }

    @Test
    public void toStringTest(){
        String dateString = dueDate.toString();
        assertTrue(memberTask.toString().contains(description));
        assertTrue(memberTask.toString().contains(dateString));
        assertTrue(memberTask.toString().contains(memberEmail));
    }

    @Test
    public void createMemberTask_Request(){
        AssignMemberTaskRequest mtr = new AssignMemberTaskRequest(
                5, "member@email.com", "description"
        );
        MemberTask memberTask = new MemberTask(mtr);
        assertTrue(mtr.description.equals(memberTask.description));
        assertEquals(LocalDate.now().plusDays(mtr.daysToComplete), memberTask.dueDate);
        assertEquals(mtr.memberEmail, memberTask.memberEmail);
    }
}
