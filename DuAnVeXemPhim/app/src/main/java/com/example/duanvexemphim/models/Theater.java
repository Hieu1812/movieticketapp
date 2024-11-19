package com.example.duanvexemphim.models;

public class Theater {
    private String address;
    private String theaterID;
    private String hotline;
    private String image;
    private String name;

    public Theater() {};

    public Theater(String address, String theaterID, String image, String hotline, String name) {
        this.address = address;
        this.theaterID = theaterID;
        this.image = image;
        this.hotline = hotline;
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getTheaterID() {
        return theaterID;
    }

    public void setTheaterID(String theaterID) {
        this.theaterID = theaterID;
    }

    public String getHotline() {
        return hotline;
    }

    public void setHotline(String hotline) {
        this.hotline = hotline;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
