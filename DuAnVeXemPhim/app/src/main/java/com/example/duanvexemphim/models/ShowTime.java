package com.example.duanvexemphim.models;

import java.time.LocalTime;

public class ShowTime {
    private String showTimeID;
    private String movieID;
    private String screenID;
    private String theaterID;
    private LocalTime startTime;
    private String duration;
    private int ticketPrice;
    private int totalSeats;
    private int availableSeats;

    public ShowTime() {};

    public ShowTime(String showTimeID, String movieID, String theaterID, String screenID, LocalTime startTime, String duration, int ticketPrice, int totalSeats, int availableSeats) {
        this.showTimeID = showTimeID;
        this.movieID = movieID;
        this.theaterID = theaterID;
        this.screenID = screenID;
        this.startTime = startTime;
        this.duration = duration;
        this.ticketPrice = ticketPrice;
        this.totalSeats = totalSeats;
        this.availableSeats = availableSeats;
    }

    public String getShowTimeID() {
        return showTimeID;
    }

    public void setShowTimeID(String showTimeID) {
        this.showTimeID = showTimeID;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
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

    public LocalTime getStartTime() {
        return startTime;
    }

    public void setStartTime(LocalTime startTime) {
        this.startTime = startTime;
    }

    public String getDuration() {
        return duration;
    }

    public void setDuration(String duration) {
        this.duration = duration;
    }

    public int getTotalSeats() {
        return totalSeats;
    }

    public void setTotalSeats(int totalSeats) {
        this.totalSeats = totalSeats;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getAvailableSeats() {
        return availableSeats;
    }

    public void setAvailableSeats(int availableSeats) {
        this.availableSeats = availableSeats;
    }
}
