package com.example.duanvexemphim.Quanliphim;

import android.annotation.SuppressLint;
import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanvexemphim.models.Movie;
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
        recyclerViewMovies.setLayoutManager(new LinearLayoutManager(this));
        recyclerViewMovies.setAdapter(movieAdapter);
    }
}
