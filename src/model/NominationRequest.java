package model;

public class NominationRequest {
    public String nominee;
    public String teamName;
    public String nominator;

    public NominationRequest(String n, String tn, String ntr) {
        nominee = n;
        teamName = tn;
        nominator = ntr;
    }
}
