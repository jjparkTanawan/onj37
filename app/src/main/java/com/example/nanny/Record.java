package com.example.nanny;

public class Record {
    private String baby_uid;
    private String nanny_uid;
    private String namebaby;
    private String namenanny;


    public Record() {
    }


    public Record(String baby_uid, String nanny_uid, String namebaby, String namenanny) {
        this.baby_uid = baby_uid;
        this.nanny_uid = nanny_uid;
        this.namebaby = namebaby;
        this.namenanny = namenanny;
    }

    public String getBaby_uid() {
        return baby_uid;
    }

    public void setBaby_uid(String baby_uid) {
        this.baby_uid = baby_uid;
    }

    public String getNanny_uid() {
        return nanny_uid;
    }

    public void setNanny_uid(String nanny_uid) {
        this.nanny_uid = nanny_uid;
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
}
