package com.example.nanny;

public class location {

    private double latitude;
    private double longtidu;
    private String location;

    public location() {
    }

    public location(double latitude, double longtidu, String location) {
        this.latitude = latitude;
        this.longtidu = longtidu;
        this.location = location;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongtidu() {
        return longtidu;
    }

    public void setLongtidu(double longtidu) {
        this.longtidu = longtidu;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }
}
