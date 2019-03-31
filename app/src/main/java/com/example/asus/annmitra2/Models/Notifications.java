package com.example.asus.annmitra2.Models;

/**
 * Created by asus on 9/16/2018.
 */

public class Notifications {
    private String subject, description, timeStamp;

    public Notifications(String subject, String description, String timeStamp) {
        this.subject = subject;
        this.description = description;
        this.timeStamp = timeStamp;
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

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }
}

