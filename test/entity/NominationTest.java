package entity;

import model.NominationRequest;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class NominationTest {
    private Nomination nomination;
    String nominator;
    String nominee;
    String teamName;

    @Before
    public void setup(){
        nominator = "nominator@email.com";
        nominee = "nominee@email.com";
        teamName = "teamName";
    }

    @Test
    public void assignFieldsDirect(){
        nomination = new Nomination();
        nomination.nominee = nominee;
        nomination.nominator = nominator;
        nomination.teamName = teamName;
        assertEquals(nominee, nomination.nominee);
        assertEquals(nominator, nomination.nominator);
        assertEquals(teamName, nomination.teamName);
    }

    @Test
    public void assignFieldsViaConstructor(){
        nomination = new Nomination(nominee, teamName, nominator);
        assertEquals(nominee, nomination.nominee);
        assertEquals(nominator, nomination.nominator);
        assertEquals(teamName, nomination.teamName);
    }

    @Test
    public void assignFieldsViaNominationRequest(){
        nomination = new Nomination(
                new NominationRequest(nominee, teamName, nominator));
        assertEquals(nominee, nomination.nominee);
        assertEquals(nominator, nomination.nominator);
        assertEquals(teamName, nomination.teamName);
    }
}
