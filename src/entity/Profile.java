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
        name = r.name;
        email = r.email;
        education = r.education;
        experience = r.experience;
    }

    @Override
    public int compareTo(Profile o) {
        return this.name.compareTo(o.name);
    }
}
