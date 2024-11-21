package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanvexemphim.adapters.ListMovieAdminAdapter;
import com.example.duanvexemphim.R;
import com.example.duanvexemphim.models.Movie;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class QuanTriAdminActivity extends AppCompatActivity {

    private ListView lvMovieAdmin;
    private ListMovieAdminAdapter adapter;
    private ArrayList<Movie> listMovies;
    private DatabaseReference moviesRef;
    BottomNavigationView bottomNavigationViewAdmin;
    FloatingActionButton btnThemPhim;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quan_tri_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        lvMovieAdmin = findViewById(R.id.lvMovieAdmin);
        listMovies = new ArrayList<>();
        adapter = new ListMovieAdminAdapter(this, R.layout.lv_phim_admin, listMovies);
        lvMovieAdmin.setAdapter(adapter);
        bottomNavigationViewAdmin = findViewById(R.id.bottomNavigationViewAdmin);
        btnThemPhim = findViewById(R.id.btnThemPhim);

        moviesRef = FirebaseDatabase.getInstance().getReference("Movies");

        // Tải dữ liệu từ Firebase
        loadMoviesFromFirebase();

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnThemPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanTriAdminActivity.this, ThemPhimActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationViewAdmin.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homeAdmin) {
                    Intent intent = new Intent(QuanTriAdminActivity.this, QuanTriAdminActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.revenueAdmin) {
                    Intent intent = new Intent(QuanTriAdminActivity.this, DoanhThuActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.accountAdmin) {
                    Intent intent = new Intent(QuanTriAdminActivity.this, TaiKhoanAdminActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
    private void loadMoviesFromFirebase() {
        moviesRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    Movie movie = dataSnapshot.getValue(Movie.class);
                    if (movie != null) {
                        // Kiểm tra nếu movieID chưa có trong danh sách thì mới thêm
                        boolean isExist = false;
                        for (Movie existingMovie : listMovies) {
                            if (existingMovie.getMovieID().equals(movie.getMovieID())) {
                                isExist = true;
                                break;
                            }
                        }
                        if (!isExist) {
                            listMovies.add(movie);
                        }
                    }
                }
                adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(QuanTriAdminActivity.this, "Lỗi khi tải dữ liệu từ Firebase!", Toast.LENGTH_SHORT).show();
            }
        });
    }
}