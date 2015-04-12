package com.example.varun.testapp;

import java.util.ArrayList;

/**
 * A Tutor is notified of Tutr Requests for help in any of the subjects this Tutor teaches.
 */
public class Tutor extends User {
    private ArrayList<String> subjects;
    private int minPrice;

    public Tutor(String uname, String pass) {
        super(uname, pass);
        super.setIsTutor(true);
        subjects = new ArrayList<String>();
    }

    public void setMinPrice(int minPrice) {
        this.minPrice = minPrice;
    }

    public int getMinPrice() {
        return minPrice;
    }
}



