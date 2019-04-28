package entity;

import model.MemberFeedbackRequest;
import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

public class MemberFeedbackTest {
    private MemberFeedback memberFeedback;
    private LocalDate date;
    @Before
    public void setup(){
        memberFeedback = new MemberFeedback();
        date = LocalDate.now();

    }

    @Test
    public void assignment(){
        memberFeedback.memberEmail = "member@email.com";
        memberFeedback.date = date;
        memberFeedback.feedback = "feedback";
        assertTrue("member@email.com".equals(memberFeedback.memberEmail));
        assertEquals(date, memberFeedback.date);
        assertTrue("feedback".equals(memberFeedback.feedback));
    }

    @Test
    public void constructor(){
        memberFeedback = new MemberFeedback(
                new MemberFeedbackRequest(
                        "member@email.com", "feedback"
                )
        );
        assertTrue("member@email.com".equals(memberFeedback.memberEmail));
        assertEquals(date, memberFeedback.date);
        assertTrue("feedback".equals(memberFeedback.feedback));
    }
}
