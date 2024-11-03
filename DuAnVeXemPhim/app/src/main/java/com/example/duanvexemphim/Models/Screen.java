package com.example.duanvexemphim.Models;

public class Screen {
    private String screenID;
    private String theaterID;
    private int screenRoom;
    private int totalSeats;
    private int availableSeats;

    public Screen() {};

    public Screen(String screenID, String theaterID, int screenRoom, int totalSeats, int availableSeats) {
        this.screenID = screenID;
        this.theaterID = theaterID;
        this.screenRoom = screenRoom;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    public String getScreenID() {
        return screenID;
    }

    public void setScreenID(String screenID) {
        this.screenID = screenID;
    }

    public String getTheaterID() {
        return theaterID;
    }

    public void setTheaterID(String theaterID) {
        this.theaterID = theaterID;
    }

    public int getScreenRoom() {
        return screenRoom;
    }

    public void setScreenRoom(int screenRoom) {
        this.screenRoom = screenRoom;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
