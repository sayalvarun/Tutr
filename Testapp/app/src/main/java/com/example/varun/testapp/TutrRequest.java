package com.example.varun.testapp;

/**
 * Created by Benito on 4/11/2015.
 */
public class TutrRequest {
    private String subject, description;
    private double offer;
    private int userid, id; //userid is the id of the user who created this request, id is this
    //request's id, within its user's list of id's...
    //so if there are two users, and each create one request, both requests will have id=1 but will
    //be identifiable because their userid's will be different

    public TutrRequest(int userid, int id) {
        this.userid = userid;
        this.id = id;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getOffer() {
        return offer;
    }

    public void setOffer(double offer) {
        this.offer = offer;
    }

    public int getUserid() {
        return userid;
    }

    public void setUserid(int userid) {
        this.userid = userid;
    }
}
