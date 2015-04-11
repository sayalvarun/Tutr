package com.example.varun.testapp;

/**
 * Contains a Message with subject header sent from one user to another. Both users will have a
 * copy of the Message.
 */
public class Message {
    int fromid, toid;
    String subject, content;

    public Message(int fromid, int toid) {
        this.fromid = fromid;
        this.toid = toid;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public int getToid() {
        return toid;
    }

    public int getFromid() {
        return fromid;
    }
}

