package com.example.duanvexemphim.models;

import java.util.List;

public class Ticket {
    private String ticketId;
    private String userID;
    private String showTimeID;
    private int ticketPrice;
    private String paymentStatus;
    private List<String> bookedSeats;
    private String movieName;
    private String movieID;

    // Constructor

    public Ticket(String ticketId, String userID, String showTimeID, int ticketPrice, String paymentStatus, List<String> bookedSeats, String movieName, String movieID) {
        this.ticketId = ticketId;
        this.userID = userID;
        this.showTimeID = showTimeID;
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

    public String getShowTimeID() {
        return showTimeID;
    }

    public void setShowTimeID(String showTimeID) {
        this.showTimeID = showTimeID;
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
