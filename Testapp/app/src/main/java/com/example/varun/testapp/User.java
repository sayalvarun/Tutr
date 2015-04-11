package com.example.varun.testapp;

import java.util.ArrayList;

/**
 * A User of the Tutr app. Can make Tutoring Requests and send Messages in response to Requests.
 */
public class User {
    private static int USERS;
    private int id;
    private String username, password;
    private ArrayList<Message> messages;
    private ArrayList<TutrRequest> requests;
    private boolean isTutor;

    public User(String uname, String pass) {
        id = USERS++;
        username = uname;
        password = pass;
        messages = new ArrayList<Message>();
        requests = new ArrayList<TutrRequest>();
        isTutor = false;
    }

    protected void setIsTutor(boolean b) {
        isTutor = b;
    }

    public boolean isTutor() {
        return isTutor;
    }

}

