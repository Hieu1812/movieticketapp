package com.example.duanvexemphim.Quanliphim;

public class DoanhThu {
    private String movieTitle;
    private int ticketsSold;
    private double totalRevenue;

    public DoanhThu(String movieTitle, int ticketsSold, double totalRevenue) {
        this.movieTitle = movieTitle;
        this.ticketsSold = ticketsSold;
        this.totalRevenue = totalRevenue;
    }

    public String getMovieTitle() {
        return movieTitle;
    }

    public int getTicketsSold() {
        return ticketsSold;
    }

    public double getTotalRevenue() {
        return totalRevenue;
    }
}

