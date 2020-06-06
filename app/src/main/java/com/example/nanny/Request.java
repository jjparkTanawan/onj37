package com.example.nanny;

public class Request {
    private Boolean status;
    private String Baby_uid;
    private String Nanny_uid;
    private String namebaby;
    private String namenanny;
    private String datetime;

    public Request() {
    }

    public Request(Boolean status, String baby_uid, String nanny_uid, String namebaby, String namenanny, String datetime) {
        this.status = status;
        Baby_uid = baby_uid;
        Nanny_uid = nanny_uid;
        this.namebaby = namebaby;
        this.namenanny = namenanny;
        this.datetime = datetime;
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }

    public String getBaby_uid() {
        return Baby_uid;
    }

    public void setBaby_uid(String baby_uid) {
        Baby_uid = baby_uid;
    }

    public String getNanny_uid() {
        return Nanny_uid;
    }

    public void setNanny_uid(String nanny_uid) {
        Nanny_uid = nanny_uid;
    }

    public String getNamebaby() {
        return namebaby;
    }

    public void setNamebaby(String namebaby) {
        this.namebaby = namebaby;
    }

    public String getNamenanny() {
        return namenanny;
    }

    public void setNamenanny(String namenanny) {
        this.namenanny = namenanny;
    }

    public String getDatetime() {
        return datetime;
    }

    public void setDatetime(String datetime) {
        this.datetime = datetime;
    }
}
