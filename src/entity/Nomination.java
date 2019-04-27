package entity;

import model.NominationRequest;

public class Nomination {
    public String nominee;
    public String nominator;
    public String teamName;

    public Nomination(String n, String tn, String ntr) {
        nominee = n;
        teamName = tn;
        nominator = ntr;
    }

    public Nomination(NominationRequest nr) {
        nominee = nr.nominee;
        teamName = nr.teamName;
        nominator = nr.nominator;
    }

    public Nomination() {}
}
