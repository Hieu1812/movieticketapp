package com.example.duanvexemphim.models;

import java.util.List;

public class Movie {
    private String movieID;
    private String name;
    private String posterImage;
    private String description;
    private String genre;
    private String durationTime;
    private List<User> listReact;
    private String trailer;
    private double vote;
    private List<Actor> actorList;

    public Movie() {}

    public Movie(String movieID, String name, String posterImage, String description, String genre, String durationTime, List<User> listReact, String trailer, double vote, List<Actor> actorList) {
        this.movieID = movieID;
        this.name = name;
        this.posterImage = posterImage;
        this.description = description;
        this.genre = genre;
        this.durationTime = durationTime;
        this.listReact = listReact;
        this.trailer = trailer;
        this.vote = vote;
        this.actorList = actorList;
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

    public String getTrailer() {
        return trailer;
    }

    public void setTrailer(String trailer) {
        this.trailer = trailer;
    }

    public double getVote() {
        return vote;
    }

    public void setVote(double vote) {
        this.vote = vote;
    }

    public List<Actor> getActorList() {
        return actorList;
    }

    public void setActorList(List<Actor> actorList) {
        this.actorList = actorList;
    }
}
