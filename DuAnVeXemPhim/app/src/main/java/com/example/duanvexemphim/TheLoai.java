package com.example.duanvexemphim;

import java.util.List;

public class TheLoai {
    private String tenTheLoai;
    private List<Phim> phims;

    public TheLoai(String tenTheLoai, List<Phim> phims) {
        this.tenTheLoai = tenTheLoai;
        this.phims = phims;
    }

    public String getTenTheLoai() {
        return tenTheLoai;
    }

    public void setTenTheLoai(String tenTheLoai) {
        this.tenTheLoai = tenTheLoai;
    }

    public List<Phim> getPhims() {
        return phims;
    }

    public void setPhims(List<Phim> phims) {
        this.phims = phims;
    }
}
