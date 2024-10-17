package com.example.duanvexemphim.Models;


public class Movie {
    private String title;
    private String description;
    private String genre;
//    private String releaseDate;
//    private String status; // "Đang Chiếu", "Đã Hết Chiếu", "Sắp Chiếu"
//    private String imageUrl; // URL hình ảnh
//    private int duration; // Thời gian chiếu (phút)
//    private String trailerUrl; // URL trailer
//    private String cast; // Diễn viên nổi bật

    public Movie(String title,  String genre ) {
        this.title = title;
//        this.description = description;
        this.genre = genre;
//        this.releaseDate = releaseDate;
//        this.status = status;
//        this.imageUrl = imageUrl;
//        this.duration = duration;
//        this.trailerUrl = trailerUrl;
//        this.cast = cast;
    }
//
    // Getter methods
    public String getTitle() { return title; }
//    public String getDescription() { return description; }
    public String getGenre() { return genre; }
//    public String getReleaseDate() { return releaseDate; }
//    public String getStatus() { return status; }
//    public String getImageUrl() { return imageUrl; }
//    public int getDuration() { return duration; }
//    public String getTrailerUrl() { return trailerUrl; }
//    public String getCast() { return cast; }

//    @Override
//    public String toString() {
//        return title + " - " + status; // Hiển thị thông tin phim trong ListView
//    }
}

