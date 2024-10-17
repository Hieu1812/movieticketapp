package com.example.duanvexemphim.Models;

import java.io.Serializable;

public class ListPhim implements Serializable {
    String tenPhim;
    String theLoai;
    double giaVe;
    int posterPhim;
//Hàm tạo ko tham số
    public ListPhim(){

    }
//Hàm tạo có tham số
    public ListPhim(String theLoai, String tenPhim, double giaVe, int posterPhim) {
        this.theLoai = theLoai;
        this.tenPhim = tenPhim;
        this.giaVe = giaVe;
        this.posterPhim = posterPhim;
    }

    public String getTheLoai() {
        return theLoai;
    }

    public void setTheLoai(String theLoai) {
        this.theLoai = theLoai;
    }

    public double getGiaVe() {
        return giaVe;
    }

    public void setGiaVe(double giaVe) {
        this.giaVe = giaVe;
    }

    public int getPosterPhim() {
        return posterPhim;
    }

    public void setPosterPhim(int posterPhim) {
        this.posterPhim = posterPhim;
    }

    public String getTenPhim() {
        return tenPhim;
    }

    public void setTenPhim(String tenPhim) {
        this.tenPhim = tenPhim;
    }


}
