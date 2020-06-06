package com.example.nanny;

public class Nanny {

    private String nanny_uid;
    private String name_n;
    private String citizen_n;
    private String age_n;
    private String mail_n;
    private String pass_n;
    private String phone_n;
    private String line_n;
    private String Image_n;
    private Condition condition_n;
    private Welfere welfare_n;
    private location location_n;


    public Nanny() {
    }

    public Nanny(String nanny_uid, String name_n, String citizen_n, String age_n, String mail_n, String pass_n, String phone_n, String line_n, String image_n, Condition condition_n, Welfere welfare_n, location location_n) {
        this.nanny_uid = nanny_uid;
        this.name_n = name_n;
        this.citizen_n = citizen_n;
        this.age_n = age_n;
        this.mail_n = mail_n;
        this.pass_n = pass_n;
        this.phone_n = phone_n;
        this.line_n = line_n;
        Image_n = image_n;
        this.condition_n = condition_n;
        this.welfare_n = welfare_n;
        this.location_n = location_n;
    }

    public String getNanny_uid() {
        return nanny_uid;
    }

    public void setNanny_uid(String nanny_uid) {
        this.nanny_uid = nanny_uid;
    }

    public String getName_n() {
        return name_n;
    }

    public void setName_n(String name_n) {
        this.name_n = name_n;
    }

    public String getCitizen_n() {
        return citizen_n;
    }

    public void setCitizen_n(String citizen_n) {
        this.citizen_n = citizen_n;
    }

    public String getAge_n() {
        return age_n;
    }

    public void setAge_n(String age_n) {
        this.age_n = age_n;
    }

    public String getMail_n() {
        return mail_n;
    }

    public void setMail_n(String mail_n) {
        this.mail_n = mail_n;
    }

    public String getPass_n() {
        return pass_n;
    }

    public void setPass_n(String pass_n) {
        this.pass_n = pass_n;
    }

    public String getPhone_n() {
        return phone_n;
    }

    public void setPhone_n(String phone_n) {
        this.phone_n = phone_n;
    }

    public String getLine_n() {
        return line_n;
    }

    public void setLine_n(String line_n) {
        this.line_n = line_n;
    }

    public String getImage_n() {
        return Image_n;
    }

    public void setImage_n(String image_n) {
        Image_n = image_n;
    }

    public Condition getCondition_n() {
        return condition_n;
    }

    public void setCondition_n(Condition condition_n) {
        this.condition_n = condition_n;
    }

    public Welfere getWelfare_n() {
        return welfare_n;
    }

    public void setWelfare_n(Welfere welfare_n) {
        this.welfare_n = welfare_n;
    }

    public location getLocation_n() {
        return location_n;
    }

    public void setLocation_n(location location_n) {
        this.location_n = location_n;
    }
}
