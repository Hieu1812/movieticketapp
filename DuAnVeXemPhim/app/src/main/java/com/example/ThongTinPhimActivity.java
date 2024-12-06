package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.adapters.ActorAdapter;
import com.example.duanvexemphim.adapters.ActorThongTinPhimAdapter;
import com.example.duanvexemphim.models.Actor;
import com.example.duanvexemphim.models.Movie;
import com.example.duanvexemphim.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;

public class ThongTinPhimActivity extends AppCompatActivity implements Serializable {
    ActorThongTinPhimAdapter actorThongTinPhimAdapter;
    RecyclerView rvActors;
    Movie movie;
    ImageView imgPoster, imgActor;
    TextView tvTenPhim, tvTheLoai, tvThoiLuong, tvNDPhim, tvTinhTrang;
    Button btnThoat, btnDatVe, btnThich;
    WebView webViewTrailer;
    private boolean clicktym = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_tin_phim);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        imgPoster = findViewById(R.id.imgPhim);
        tvTenPhim = findViewById(R.id.tvTenPhim);
        tvTheLoai = findViewById(R.id.tvTheLoai);
        tvNDPhim = findViewById(R.id.tvNDPhim);
        tvTinhTrang = findViewById(R.id.tvTinhTrang);
        tvThoiLuong = findViewById(R.id.tvThoiLuong);
        btnThoat = findViewById(R.id.btnThoat);
        btnDatVe = findViewById(R.id.btnDatVe);
        btnThich = findViewById(R.id.btnThich);
        webViewTrailer = findViewById(R.id.webViewTrailer);
        rvActors = findViewById(R.id.rvActors);

        Intent intent = getIntent();


        webViewTrailer.getSettings().setJavaScriptEnabled(true);

        webViewTrailer.getSettings().setAllowUniversalAccessFromFileURLs(true);

        webViewTrailer.getSettings().setMediaPlaybackRequiresUserGesture(false);

        webViewTrailer.getSettings().setDomStorageEnabled(true);

        webViewTrailer.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // Nhận thông tin từ Intent
        String movieID = intent.getStringExtra("movieID");
        String movieName = intent.getStringExtra("movieName");
        String movieGenre = intent.getStringExtra("movieGenre");
        String movieDuration = intent.getStringExtra("movieDuration");
        String movieDescription = intent.getStringExtra("movieDescription");
        String moviePoster = intent.getStringExtra("moviePoster");
        String movieTrailerUrl = intent.getStringExtra("movieTrailer");
        ArrayList<Actor> actorArrayList = (ArrayList<Actor>) getIntent().getSerializableExtra("actorList");
        actorThongTinPhimAdapter = new ActorThongTinPhimAdapter(this, actorArrayList);
        GridLayoutManager gridLayoutManager = new GridLayoutManager(this, 3);
        rvActors.setLayoutManager(gridLayoutManager);
        rvActors.setAdapter(actorThongTinPhimAdapter);

        tvTenPhim.setText("Phim: " + movieName);
        tvTheLoai.setText("Thể loại: " + movieGenre);
        tvThoiLuong.setText("Thời lượng: " + movieDuration);
        tvNDPhim.setText(movieDescription);
        Glide.with(this)
                .load(moviePoster)
                .into(imgPoster);


        if (movieTrailerUrl != null && !movieTrailerUrl.isEmpty()) {
            String youtubeEmbedUrl = "https://www.youtube.com/embed/" + extractYouTubeVideoId(movieTrailerUrl);
            String htmlContent = "<html><body style='margin:0; padding:0;'>" +
                    "<iframe width='100%' height='100%' src='" + youtubeEmbedUrl + "' frameborder='0' allowfullscreen></iframe>" +
                    "</body></html>";

            webViewTrailer.loadData(htmlContent, "text/html", "UTF-8");
        }

        DatabaseReference seatRef = FirebaseDatabase.getInstance().getReference("Seats");
        seatRef.child(movieName).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                int totalSeats = 60;
                int countSold = 0;
                boolean allSold = true;
                for (DataSnapshot timeSnapshot : snapshot.getChildren()) {
                    for (DataSnapshot seatSnapshot : timeSnapshot.getChildren()) {
                        String seatStatus = seatSnapshot.getValue(String.class);
                        if ("sold".equals(seatStatus)) {
                            countSold ++;
                        }
                    }
                }
                allSold = countSold < totalSeats ? false : true;
                String tinhTrang = allSold ? "Hết vé" : "Còn vé";
                tvTinhTrang.setText("Tình trạng: " + tinhTrang);
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Lỗi khi truy vấn dữ liệu: " + error.getMessage());
            }
        });

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference userRef = FirebaseDatabase.getInstance().getReference("Users").child(id);
        userRef.child("likedFilms").child(movieID).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    btnThich.setBackgroundTintList(getResources().getColorStateList(R.color.colorred));
                    clicktym = true;
                } else {
                    btnThich.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                    clicktym = false;
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("Firebase", "Failed to check like status: " + error.getMessage());
            }
        });

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentThoat = new Intent(ThongTinPhimActivity.this, MainActivity.class);
                startActivity(intentThoat);
            }
        });
        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDatVe = new Intent(ThongTinPhimActivity.this, RapVaLichChieuActivity.class);
                intentDatVe.putExtra("movieID", movieID);
                intentDatVe.putExtra("movieName", movieName);
                intentDatVe.putExtra("movieGenre", movieGenre);
                intentDatVe.putExtra("movieDuration", movieDuration);
                intentDatVe.putExtra("movieDescription", movieDescription);
                intentDatVe.putExtra("moviePoster", moviePoster);
                intentDatVe.putExtra("movieTrailer", movieTrailerUrl);
                intentDatVe.putExtra("actorList", actorArrayList);
                startActivity(intentDatVe);
            }
        });
        btnThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference movieRef = FirebaseDatabase.getInstance().getReference("Movies").child(movieID);
                movieRef.child("vote").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int currentLikes = 0;
                        if (snapshot.exists()) {
                            currentLikes = snapshot.getValue(Integer.class);
                        }

                        if (clicktym) {
                            btnThich.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                            movieRef.child("vote").setValue(currentLikes - 1);
                            clicktym = false;
                            userRef.child("likedFilms").child(movieID).removeValue();
                        } else {
                            btnThich.setBackgroundTintList(getResources().getColorStateList(R.color.colorred));
                            movieRef.child("vote").setValue(currentLikes + 1);
                            clicktym = true;
                            userRef.child("likedFilms").child(movieID).setValue(true);
                        }
                    }
                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {
                        Log.e("Firebase", "Failed to fetch likes: " + error.getMessage());
                    }
                });
            }
        });

    }

    private String extractYouTubeVideoId(String movieTrailerUrl) {
        String videoId = "";
        if (movieTrailerUrl != null && !movieTrailerUrl.isEmpty()) {
            if (movieTrailerUrl.contains("youtu.be/")) {
                String[] parts = movieTrailerUrl.split("youtu.be/");
                videoId = parts[1].split("\\?")[0];
            } else if (movieTrailerUrl.contains("youtube.com/watch?v=")) {
                String[] parts = movieTrailerUrl.split("v=");
                videoId = parts[1].split("&")[0];
            }
        }
        return videoId;
    }
}