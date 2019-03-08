package controller;

import boundary.IPerson;
import model.ProjectTypes.*;

public class LoginController {
    public IPerson personBoundary;

    public LoginController(IPerson p) {
        personBoundary = p;
    }

    public Role login(String e) {
        isValidEmail(e);
        Role r = personBoundary.login(e);
        return r;
    }

    public boolean isValidEmail(String e) {
        /*
        []+     match one or more in character set
        \\w     word (alphanumeric & underscore)
        -       match '-'
        \\.     match '.'
        @       match '@'
        ()+     match one or more tokens in capture group
        {2,4}   match 2 to 4 of proceding token
        */
        if(e != null && e.matches("[\\w-\\.]+@([\\w-]+\\.)+[\\w-]{2,4}"))
            return true;
        else
            throw new InvalidEmail(e);
    }

    public class InvalidEmail extends RuntimeException{
        public InvalidEmail(String e){
            super(String.format("'%s' is not a valid email address.", e));
        }
    }
}
