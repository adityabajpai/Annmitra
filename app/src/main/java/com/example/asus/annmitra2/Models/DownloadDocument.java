package com.example.asus.annmitra2.Models;

/**
 * Created by asus on 9/16/2018.
 */

public class DownloadDocument {
    private String subject, timeStamp;
    private String url;

    public DownloadDocument(String subject, String url,String timeStamp) {
        this.subject = subject;
        this.timeStamp = timeStamp;
        this.url=url;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    public String getTimeStamp() {
        return timeStamp;
    }

    public void setTimeStamp(String timeStamp) {
        this.timeStamp = timeStamp;
    }

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }
}
