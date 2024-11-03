package com.example.duanvexemphim.Quanliphim;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanvexemphim.Models.Movie;
import com.example.duanvexemphim.Models.Movies;
import com.example.duanvexemphim.R;

import java.util.ArrayList;
import java.util.List;

public class DanhSachMovie extends AppCompatActivity {
    private RecyclerView recyclerViewMovies;
    private MovieAdapter movieAdapter;
    private List<Movie> movies;

    @SuppressLint("WrongViewCast")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.danh_sach_phim);

        recyclerViewMovies = findViewById(R.id.tvMovieTitle);
        movies = new ArrayList<>();

        // Thêm một số phim giả mạo vào danh sách
        movies.add(new Movie("Avengers: Endgame", "Hành Động"));
        movies.add(new Movie("The Lion King", "Hoạt Hình"));
        movies.add(new Movie("Joker", "Tâm Lý"));
        movies.add(new Movie("Spider-Man: No Way Home", "Hành Động"));
        movies.add(new Movie("Frozen II", "Hoạt Hình"));
        movies.add(new Movie("Inception", "Khoa Học Viễn Tưởng"));

//        movieAdapter = new MovieAdapter(this, movies);
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMovies.setAdapter(movieAdapter);
    }
}
