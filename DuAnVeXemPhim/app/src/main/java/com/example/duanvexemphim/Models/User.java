package com.example.duanvexemphim.Models;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class User {
    private String userID;
    private String name;
    private String avatar;
    private String email;
    private String role;
    private int wallet;
    private List<String> likeFilms;

    public User() {};

    public User(String userID, String name, String avatar, String email, String role, int wallet, List<String> likeFilms) {
        this.userID = userID;
        this.name = name;
        this.avatar = avatar;
        this.email = email;
        this.role = role;
        this.wallet = wallet;
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

    public int getWallet() {
        return wallet;
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

    public void setWallet(int wallet) {
        this.wallet = wallet;
    }

    public void setLikeFilms(List<String> likeFilms) {
        this.likeFilms = likeFilms;
    }
}