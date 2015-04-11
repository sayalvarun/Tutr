package com.example.varun.testapp;

import java.util.ArrayList;

/**
 * A Tutor is notified of Tutr Requests for help in any of the subjects this Tutor teaches.
 */
public class Tutor extends User {
    private ArrayList<String> subjects;

    public Tutor(String uname, String pass) {
        super(uname, pass);
        super.setIsTutor(true);
        subjects = new ArrayList<String>();
    }


}
