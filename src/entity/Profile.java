package entity;

import model.CreateProfileRequest;
import model.ProjectTypes.*;

public class Profile implements Comparable<Profile>{
    public Role role;
    public String name;
    public String email;
    public String education;
    public String experience;

    public Profile() {}

    public Profile(CreateProfileRequest r){
        role = Role.PERSON;
        name = r.name;
        email = r.email;
        education = r.education;
        experience = r.experience;
    }

    public Profile(String n, String e, String edu, String exp) {
        role = Role.PERSON;
        name = n;
        email = e;
        education = edu;
        experience = exp;
    }

    @Override
    public int compareTo(Profile o) {
        return this.name.compareTo(o.name);
    }

    @Override
    public String toString(){
        return String.format(
                "Name: %s\nEmail: %s\nEducation: %s\nExperience: %s",
                name, email, education, experience);
    }
}
