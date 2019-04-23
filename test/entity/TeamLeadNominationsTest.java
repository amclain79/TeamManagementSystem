package entity;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

import static junit.framework.TestCase.assertTrue;

public class TeamLeadNominationsTest {
    private TeamLeadNominations teamLeadNominations;
    String teamName;
    List<String> memberNominations;


    @Before
    public void setup(){
        teamLeadNominations = new TeamLeadNominations();
        teamName = "teamName";
        memberNominations = Arrays.asList("member 1", "member 2");

        teamLeadNominations.teamName = teamName;
        teamLeadNominations.memberNominations = memberNominations;
    }

    @Test
    public void canCreateTeamLeadNominationsWithAttributes(){
        assertTrue(teamName.equals(teamLeadNominations.teamName));
        assertTrue(memberNominations.equals(teamLeadNominations.memberNominations));
    }

    //@Test
    //public void toStringTest(){
    //    String formatString = String.format(
    //            "Team: %s\nMember Nominations: %s",
    //            teamName,
    //            memberNominations
    //    );
    //
    //    Assert.assertTrue(formatString.equals(teamLeadNominations.toString()));
    //}

}
