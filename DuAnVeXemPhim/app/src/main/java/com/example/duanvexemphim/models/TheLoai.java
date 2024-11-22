package com.example.duanvexemphim.models;

import java.util.List;

public class TheLoai {
    private String tenTheLoai;
    private List<Movie> movies;

    public TheLoai(String tenTheLoai, List<Movie> movies) {
        this.tenTheLoai = tenTheLoai;
        this.movies = movies;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public List<Movie> getPhims() {
        return movies;
    }

    public void setPhims(List<Movie> phims) {
        this.movies = movies;
    }
}
