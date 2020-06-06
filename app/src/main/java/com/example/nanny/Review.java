package com.example.nanny;

public class Review {
    private String nanny_uid;
    private String baby_uid;
    private String namebaby;
    private Float score;
    private String review_detail;

    public Review() {
    }

    public Review(String nanny_uid, String baby_uid, String namebaby, Float score, String review_detail) {
        this.nanny_uid = nanny_uid;
        this.baby_uid = baby_uid;
        this.namebaby = namebaby;
        this.score = score;
        this.review_detail = review_detail;
    }

    public String getNanny_uid() {
        return nanny_uid;
    }

    public void setNanny_uid(String nanny_uid) {
        this.nanny_uid = nanny_uid;
    }

    public String getBaby_uid() {
        return baby_uid;
    }

    public void setBaby_uid(String baby_uid) {
        this.baby_uid = baby_uid;
    }

    public String getNamebaby() {
        return namebaby;
    }

    public void setNamebaby(String namebaby) {
        this.namebaby = namebaby;
    }

    public Float getScore() {
        return score;
    }

    public void setScore(Float score) {
        this.score = score;
    }

    public String getReview_detail() {
        return review_detail;
    }

    public void setReview_detail(String review_detail) {
        this.review_detail = review_detail;
    }
}
