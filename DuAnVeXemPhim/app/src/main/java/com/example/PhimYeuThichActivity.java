package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Adapter;
import android.widget.Button;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.R;
import com.example.duanvexemphim.adapters.PhimYeuThichAdapter;
import com.example.duanvexemphim.models.Movie;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class PhimYeuThichActivity extends AppCompatActivity {

    Button btnThoat;
    ListView lvPhimYT;
    PhimYeuThichAdapter phimYeuThichAdapter;
    ArrayList<Movie> listMovieYT;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_phim_yeu_thich);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        lvPhimYT = findViewById(R.id.lvPhimYT);
        btnThoat = findViewById(R.id.btnThoat);
        listMovieYT = new ArrayList<>();
        phimYeuThichAdapter = new PhimYeuThichAdapter(PhimYeuThichActivity.this, R.layout.lv_phim_yeu_thich, listMovieYT);
        lvPhimYT.setAdapter(phimYeuThichAdapter);
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(id);
        userRef.child("likedFilms").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                listMovieYT.clear();
                for (DataSnapshot movieSnapshot : snapshot.getChildren()) {
                    String movieId = movieSnapshot.getKey();
                    if (movieId != null) {
                        DatabaseReference movieRef = FirebaseDatabase.getInstance().getReference("Movies").child(movieId);
                        movieRef.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot snapshot) {
                                Movie movie = snapshot.getValue(Movie.class);
                                if (movie != null) {
                                    listMovieYT.add(movie);
                                    phimYeuThichAdapter.notifyDataSetChanged();
                                }
                            }
                            @Override
                            public void onCancelled(@NonNull DatabaseError error) {
                                Log.e("Firebase", "Error fetching movie details: " + error.getMessage());
                            }
                        });
                    }
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", error.getMessage());
            }
        });
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PhimYeuThichActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });
    }
}