package com.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.R;
import com.example.duanvexemphim.adapters.ActorAdapter;
import com.example.duanvexemphim.adapters.ActorThongTinPhimAdapter;
import com.example.duanvexemphim.models.Actor;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ThongTinPhimAdminActivity extends AppCompatActivity {

    Button btnThoat;
    TextView tvTenPhim, tvTheLoai, tvThoiLuong, tvND, tvTinhTrang, tvActorList;
    ImageView imgPoster;
    WebView webViewTrailer;
    ActorThongTinPhimAdapter actorThongTinPhimAdapter;
    RecyclerView rvActors;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_thong_tin_phim_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnThoat = findViewById(R.id.btnThoat);
        tvTenPhim = findViewById(R.id.tvTenPhim);
        tvTheLoai = findViewById(R.id.tvTheLoai);
        tvThoiLuong = findViewById(R.id.tvThoiLuong);
        tvND = findViewById(R.id.tvND);
        tvTinhTrang = findViewById(R.id.tvTinhTrang);
        imgPoster = findViewById(R.id.imgCam);
        webViewTrailer = findViewById(R.id.webViewTrailer);
        rvActors = findViewById(R.id.rvActors);

        webViewTrailer.getSettings().setJavaScriptEnabled(true);
        webViewTrailer.getSettings().setAllowUniversalAccessFromFileURLs(true);
        webViewTrailer.getSettings().setMediaPlaybackRequiresUserGesture(false);
        webViewTrailer.getSettings().setDomStorageEnabled(true);
        webViewTrailer.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        Intent intent = getIntent();
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
        tvND.setText(movieDescription);

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

        //
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentThoat = new Intent(ThongTinPhimAdminActivity.this, QuanTriAdminActivity.class);
                startActivity(intentThoat);
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