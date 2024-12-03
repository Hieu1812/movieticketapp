package com.example.duanvexemphim.models;

import java.util.List;

public class Ticket {
    private String ticketId;
    private String userID;
    private String showTime;
    private int ticketPrice;
    private String paymentStatus;
    private List<String> bookedSeats;
    private String movieName;
    private String movieID;

    // Constructor

    public Ticket(String ticketId, String userID, String showTime, int ticketPrice, String paymentStatus, List<String> bookedSeats, String movieName, String movieID) {
        this.ticketId = ticketId;
        this.userID = userID;
        this.showTime = showTime;
        this.ticketPrice = ticketPrice;
        this.paymentStatus = paymentStatus;
        this.bookedSeats = bookedSeats;
        this.movieName = movieName;
        this.movieID = movieID;
    }

    // Getters and Setters
    public String getTicketId() {
        return ticketId;
    }

    public void setTicketId(String ticketId) {
        this.ticketId = ticketId;
    }

    public String getUserID() {
        return userID;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public String getshowTime() {
        return showTime;
    }

    public void setshowTime(String showTime) {
        this.showTime = showTime;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }

    public List<String> getBookedSeats() {
        return bookedSeats;
    }

    public void setBookedSeats(List<String> bookedSeats) {
        this.bookedSeats = bookedSeats;
    }

    public String getMovieName() {
        return movieName;
    }

    public void setMovieName(String movieName) {
        this.movieName = movieName;
    }

    public String getMovieID() {
        return movieID;
    }
    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }
}
