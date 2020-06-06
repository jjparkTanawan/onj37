package com.example.nanny;

public class Homedis implements Comparable<Homedis>{
    String phone;
    String image;
    String dis;
    Double x;

    public Homedis() {
    }

    public Homedis(String phone, String image, String dis, Double x) {
        this.phone = phone;
        this.image = image;
        this.dis = dis;
        this.x = x;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getDis() {
        return dis;
    }

    public void setDis(String dis) {
        this.dis = dis;
    }

    public Double getX() {
        return x;
    }

    public void setX(Double x) {
        this.x = x;
    }

    @Override
    public int compareTo(Homedis o) {
        return this.x.compareTo(o.x);
    }
}
