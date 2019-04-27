package entity;

import org.junit.Before;
import org.junit.Test;

import java.time.LocalDate;
import java.util.Date;

import static org.junit.Assert.assertTrue;

public class TeamFeedbackTest {
    private TeamFeedback teamFeedback;
    String teamName;
    String feedback;
    LocalDate date;

    @Before
    public void setup(){
        teamFeedback = new TeamFeedback();
        teamName = "teamName";
        feedback = "feedback";
        date = LocalDate.now();
        teamFeedback.teamName = teamName;
        teamFeedback.feedback = feedback;
        teamFeedback.date = date;
    }

    @Test
    public void canCreateTeamFeedbackWithAttributes(){
        assertTrue(teamName.equals(teamFeedback.teamName));
        assertTrue(feedback.equals(teamFeedback.feedback));
        assertTrue(date.equals(teamFeedback.date));
    }

    @Test
    public void toStringTest(){
        String formatString = String.format(
                "Date: %s\nTeam: %s\nFeedback: %s",
                date.toString(),
                teamName,
                feedback
        );

        assertTrue(formatString.equals(teamFeedback.toString()));
    }
}
