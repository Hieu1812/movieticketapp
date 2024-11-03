package com.example.duanvexemphim.Models;

import java.util.List;

public class Movies {
    private String movieID;
    private String name;
    private String backgroundImage;
    private String posterImage;
    private String primaryImage;
    private String description;
    private String durationTime;
    private String genre;
    private List<User> listReact;
    private List<String> trailer;
    private double vote;

    public Movies() {}

    public Movies(String movieID, String name, String backgroundImage, String posterImage, String primaryImage, String description, String genre, String durationTime, List<User> listReact, List<String> trailer, double vote) {
        this.movieID = movieID;
        this.name = name;
        this.backgroundImage = backgroundImage;
        this.posterImage = posterImage;
        this.primaryImage = primaryImage;
        this.description = description;
        this.genre = genre;
        this.durationTime = durationTime;
        this.listReact = listReact;
        this.trailer = trailer;
        this.vote = vote;
    }

    public String getMovieID() {
        return movieID;
    }

    public void setMovieID(String movieID) {
        this.movieID = movieID;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getBackgroundImage() {
        return backgroundImage;
    }

    public void setBackgroundImage(String backgroundImage) {
        this.backgroundImage = backgroundImage;
    }

    public String getPrimaryImage() {
        return primaryImage;
    }

    public void setPrimaryImage(String primaryImage) {
        this.primaryImage = primaryImage;
    }

    public String getPosterImage() {
        return posterImage;
    }

    public void setPosterImage(String posterImage) {
        this.posterImage = posterImage;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getDurationTime() {
        return durationTime;
    }

    public void setDurationTime(String durationTime) {
        this.durationTime = durationTime;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public List<User> getListReact() {
        return listReact;
    }

    public void setListReact(List<User> listReact) {
        this.listReact = listReact;
    }

    public List<String> getTrailer() {
        return trailer;
    }

    public void setTrailer(List<String> trailer) {
        this.trailer = trailer;
    }

    public double getVote() {
        return vote;
    }

    public void setVote(double vote) {
        this.vote = vote;
    }
}
