package com.example.duanvexemphim.Models;

import java.time.LocalTime;
import java.util.List;

public class Comment {
    private String commentID;
    private int dislike;
    private int like;
    private List<String> listReact;
    private String profileUrl;
    private int rating;
    private String reviewText;
    private LocalTime timestamp;
    private String userId;

    public Comment() {};

    public Comment(String commentID, int dislike, int like, List<String> listReact, int rating, String profileUrl, String reviewText, String userId, LocalTime timestamp) {
        this.commentID = commentID;
        this.dislike = dislike;
        this.like = like;
        this.listReact = listReact;
        this.rating = rating;
        this.profileUrl = profileUrl;
        this.reviewText = reviewText;
        this.userId = userId;
        this.timestamp = timestamp;
    }

    public String getCommentID() {
        return commentID;
    }

    public void setCommentID(String commentID) {
        this.commentID = commentID;
    }

    public int getDislike() {
        return dislike;
    }

    public void setDislike(int dislike) {
        this.dislike = dislike;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }

    public List<String> getListReact() {
        return listReact;
    }

    public void setListReact(List<String> listReact) {
        this.listReact = listReact;
    }

    public String getProfileUrl() {
        return profileUrl;
    }

    public void setProfileUrl(String profileUrl) {
        this.profileUrl = profileUrl;
    }

    public String getReviewText() {
        return reviewText;
    }

    public void setReviewText(String reviewText) {
        this.reviewText = reviewText;
    }

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public LocalTime getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(LocalTime timestamp) {
        this.timestamp = timestamp;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }
}

