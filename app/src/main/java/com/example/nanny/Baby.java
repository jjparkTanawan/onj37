package com.example.nanny;

public class Baby {

    private String uid_b;
    private String name_b;
    private String age_b;
    private String gender_b;
    private String undis_b;
    private String nameb_b;
    private String relative_b;
    private String mail_b;
    private String pass_b;
    private String phone_b;
    private String line_b;
    private String Image_b;
    private location location_b;
    private Condition condition_b;
    private Welfere welfare_b;


    public Baby() {
    }

    public Baby(String uid_b, String name_b, String age_b, String gender_b, String undis_b, String nameb_b, String relative_b, String mail_b, String pass_b, String phone_b, String line_b, String image_b, location location_b, Condition condition_b, Welfere welfare_b) {
        this.uid_b = uid_b;
        this.name_b = name_b;
        this.age_b = age_b;
        this.gender_b = gender_b;
        this.undis_b = undis_b;
        this.nameb_b = nameb_b;
        this.relative_b = relative_b;
        this.mail_b = mail_b;
        this.pass_b = pass_b;
        this.phone_b = phone_b;
        this.line_b = line_b;
        Image_b = image_b;
        this.location_b = location_b;
        this.condition_b = condition_b;
        this.welfare_b = welfare_b;
    }

    public String getUid_b() {
        return uid_b;
    }

    public void setUid_b(String uid_b) {
        this.uid_b = uid_b;
    }

    public String getName_b() {
        return name_b;
    }

    public void setName_b(String name_b) {
        this.name_b = name_b;
    }

    public String getAge_b() {
        return age_b;
    }

    public void setAge_b(String age_b) {
        this.age_b = age_b;
    }

    public String getGender_b() {
        return gender_b;
    }

    public void setGender_b(String gender_b) {
        this.gender_b = gender_b;
    }

    public String getUndis_b() {
        return undis_b;
    }

    public void setUndis_b(String undis_b) {
        this.undis_b = undis_b;
    }

    public String getNameb_b() {
        return nameb_b;
    }

    public void setNameb_b(String nameb_b) {
        this.nameb_b = nameb_b;
    }

    public String getRelative_b() {
        return relative_b;
    }

    public void setRelative_b(String relative_b) {
        this.relative_b = relative_b;
    }

    public String getMail_b() {
        return mail_b;
    }

    public void setMail_b(String mail_b) {
        this.mail_b = mail_b;
    }

    public String getPass_b() {
        return pass_b;
    }

    public void setPass_b(String pass_b) {
        this.pass_b = pass_b;
    }

    public String getPhone_b() {
        return phone_b;
    }

    public void setPhone_b(String phone_b) {
        this.phone_b = phone_b;
    }

    public String getLine_b() {
        return line_b;
    }

    public void setLine_b(String line_b) {
        this.line_b = line_b;
    }

    public String getImage_b() {
        return Image_b;
    }

    public void setImage_b(String image_b) {
        Image_b = image_b;
    }

    public location getLocation_b() {
        return location_b;
    }

    public void setLocation_b(location location_b) {
        this.location_b = location_b;
    }

    public Condition getCondition_b() {
        return condition_b;
    }

    public void setCondition_b(Condition condition_b) {
        this.condition_b = condition_b;
    }

    public Welfere getWelfare_b() {
        return welfare_b;
    }

    public void setWelfare_b(Welfere welfare_b) {
        this.welfare_b = welfare_b;
    }
}
