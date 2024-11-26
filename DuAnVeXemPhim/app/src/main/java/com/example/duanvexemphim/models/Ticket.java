package com.example.duanvexemphim.models;

import java.time.LocalTime;
import java.util.List;

public class Ticket {
    private String ticketID;
    private String userID;
    private String showTimeID;
    private Integer ticketPrice;
    private String paymentStatus;
    private List<String> seats;

    public Ticket() {};

    public Ticket(String ticketID, String userID, String showTimeID, Integer ticketPrice, String paymentStatus, List<String> seats) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.showTimeID = showTimeID;
        this.ticketPrice = ticketPrice;
        this.paymentStatus = paymentStatus;
        this.seats = seats;
    }

    public String getTicketID() {
        return ticketID;
    }

    public void setTicketID(String ticketID) {
        this.ticketID = ticketID;
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

    public void setTicketPrice(Integer ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public List<String> getSeats() {
        return seats;
    }

    public void setSeats(List<String> seats) {
        this.seats = seats;
    }
}
