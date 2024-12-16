package com.example.duanvexemphim.models;

import java.util.List;

public class User {
    private String userID;
    private String name;
    private String avatar;
    private String email;
    private String role;
    private String phonenumber;
    private List<String> likeFilms;

    public User() {}


    public User(String userID, String name, String avatar, String email, String role, String phonenumber, List<String> likeFilms) {
        this.userID = userID;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.role = role;
        this.phonenumber = phonenumber;
        this.likeFilms = likeFilms;
    }

    public String getUserID() {
        return userID;
    }

    public String getAvatar() {
        return avatar;
    }

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public String getRole() {
        return role;
    }

    public List<String> getLikeFilms() {
        return likeFilms;
    }

    public void setUserID(String userID) {
        this.userID = userID;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public void setLikeFilms(List<String> likeFilms) {
        this.likeFilms = likeFilms;
    }

    public String getPhonenumber() {
        return phonenumber;
    }

    public void setPhonenumber(String phonenumber) {
        this.phonenumber = phonenumber;
    }
}
