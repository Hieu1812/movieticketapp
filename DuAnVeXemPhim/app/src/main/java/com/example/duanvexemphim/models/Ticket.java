package com.example.duanvexemphim.models;

import java.time.LocalTime;
import java.util.List;

public class Ticket {
    private String ticketID;
    private String userID;
    private String showTimeID;
    private List<String> bookedSeat;
    private LocalTime purchaseTime;
    private int ticketPrice;
    private int totalAmount;
    private String paymentStatus;

    public Ticket() {};

    public Ticket(String ticketID, String userID, String showTimeID, List<String> bookedSeat, LocalTime purchaseTime, int ticketPrice, int totalAmount, String paymentStatus) {
        this.ticketID = ticketID;
        this.userID = userID;
        this.showTimeID = showTimeID;
        this.bookedSeat = bookedSeat;
        this.purchaseTime = purchaseTime;
        this.ticketPrice = ticketPrice;
        this.totalAmount = totalAmount;
        this.paymentStatus = paymentStatus;
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

    public List<String> getBookedSeat() {
        return bookedSeat;
    }

    public void setBookedSeat(List<String> bookedSeat) {
        this.bookedSeat = bookedSeat;
    }

    public LocalTime getPurchaseTime() {
        return purchaseTime;
    }

    public void setPurchaseTime(LocalTime purchaseTime) {
        this.purchaseTime = purchaseTime;
    }

    public int getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(int ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

    public int getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus;
    }
}
