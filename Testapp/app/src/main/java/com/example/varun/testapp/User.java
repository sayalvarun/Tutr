package com.example.varun.testapp;

import java.util.ArrayList;

/**
 * A User of the Tutr app. Can make Tutoring Requests and send Messages in response to Requests.
 */
public class User {
    private static int USERS;
    private int id;
    private String username, password, firstName, lastName, email, phoneNum, school;
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

    /**
     * @param i
     * @return this User's ith Message, where i=0 is the newest Message
     */
    public Message getMessage(int i) {
        return messages.get(i);
    }

    public void addMessage(Message m) {
        messages.add(0, m);
    }

    public int getId() {
        return id;
    }

    public String getUsername() {
        return username;
    }

    public String getPassword() {
        return password;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhoneNum() {
        return this.phoneNum;
    }

    public void setPhoneNum(String num) {
        this.phoneNum = num;
    }

    public String getSchool() {
        return this.school;
    }

    public void setSchool(String school) {
        this.school = school;
    }

}