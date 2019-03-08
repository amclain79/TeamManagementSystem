package model;

public class CreateProfileRequest {
    public String name;
    public String email;
    public String education;
    public String experience;

    public CreateProfileRequest() {}

    public CreateProfileRequest(String n, String e, String ed, String ex) {
        name = n;
        email = e;
        education = ed;
        experience = ex;
    }

}
