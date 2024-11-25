package com.example;

import android.content.Intent;
import android.os.Bundle;
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

import com.bumptech.glide.Glide;
import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.models.Movie;
import com.example.duanvexemphim.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class ThongTinPhimActivity extends AppCompatActivity implements Serializable {
    Movie movie;
    ImageView imgPoster, imgActor;
    TextView tvTenPhim, tvTheLoai, tvThoiLuong, tvNDPhim, tvActorName;
    Button btnThoat, btnDatVe, btnThich;
    WebView webViewTrailer;
    private boolean clicktym = false;
    private DatabaseReference mDatabase;

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
        imgActor = findViewById(R.id.imgActor);
        tvTenPhim = findViewById(R.id.tvTenPhim);
        tvTheLoai = findViewById(R.id.tvTheLoai);
        tvNDPhim = findViewById(R.id.tvNDPhim);
        tvThoiLuong = findViewById(R.id.tvThoiLuong);
        btnThoat = findViewById(R.id.btnThoat);
        btnDatVe = findViewById(R.id.btnDatVe);
        btnThich = findViewById(R.id.btnThich);
        webViewTrailer = findViewById(R.id.webViewTrailer);

        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        //

        // Bật JavaScript trong WebView
        webViewTrailer.getSettings().setJavaScriptEnabled(true);
        // Cho phép truy cập từ file URL
        webViewTrailer.getSettings().setAllowUniversalAccessFromFileURLs(true);
        // Cho phép phát video mà không cần yêu cầu hành động từ người dùng
        webViewTrailer.getSettings().setMediaPlaybackRequiresUserGesture(false);
        // Bật DOM Storage
        webViewTrailer.getSettings().setDomStorageEnabled(true);
        // Cho phép WebView tải dữ liệu qua HTTP
        webViewTrailer.getSettings().setMixedContentMode(WebSettings.MIXED_CONTENT_ALWAYS_ALLOW);

        // Nhận thông tin từ Intent
        String movieID = intent.getStringExtra("movieID");
        String movieName = intent.getStringExtra("movieName");
        String movieGenre = intent.getStringExtra("movieGenre");
        String movieDuration = intent.getStringExtra("movieDuration");
        String movieDescription = intent.getStringExtra("movieDescription");
        String moviePoster = intent.getStringExtra("moviePoster");
        String movieTrailerUrl = intent.getStringExtra("movieTrailer");

        // Hiển thị các thông tin phim lên giao diện
        tvTenPhim.setText("Phim: " + movieName);
        tvTheLoai.setText("Thể loại: " + movieGenre);
        tvThoiLuong.setText("Thời lượng: " + movieDuration);
        tvNDPhim.setText(movieDescription);

        // Dùng Glide để tải ảnh từ URL vào ImageView
        Glide.with(this)
                .load(moviePoster)
                .into(imgPoster);

        // Trailer trong WebView
        if (movieTrailerUrl != null && !movieTrailerUrl.isEmpty()) {
            String youtubeEmbedUrl = "https://www.youtube.com/embed/" + extractYouTubeVideoId(movieTrailerUrl);
            String htmlContent = "<html><body style='margin:0; padding:0;'>" +
                    "<iframe width='100%' height='100%' src='" + youtubeEmbedUrl + "' frameborder='0' allowfullscreen></iframe>" +
                    "</body></html>";
            // Đưa nội dung HTML vào WebView
            webViewTrailer.loadData(htmlContent, "text/html", "UTF-8");
        }
        //

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
                startActivity(intentDatVe);
            }
        });
        btnThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DatabaseReference movieRef = FirebaseDatabase.getInstance().getReference("Movies").child(movieID);
                // Lấy dữ liệu hiện tại của vote
                movieRef.child("vote").addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        int currentLikes = 0;
                        if (snapshot.exists()) {
                            currentLikes = snapshot.getValue(Integer.class); // Lấy số like hiện tại
                        }

                        if (clicktym) {
                            // Người dùng bỏ thích, giảm 1 like
                            btnThich.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                            movieRef.child("vote").setValue(currentLikes - 1);
                            clicktym = false;
                        } else {
                            // Người dùng thích, tăng 1 like
                            btnThich.setBackgroundTintList(getResources().getColorStateList(R.color.colorred));
                            movieRef.child("vote").setValue(currentLikes + 1);
                            clicktym = true;
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
                // Trích xuất video ID sau "youtu.be/"
                String[] parts = movieTrailerUrl.split("youtu.be/");
                // Lấy phần video ID trước dấu "?"
                videoId = parts[1].split("\\?")[0];
            } else if (movieTrailerUrl.contains("youtube.com/watch?v=")) {
                // Trường hợp URL youtube thông thường (https://www.youtube.com/watch?v=ID)
                String[] parts = movieTrailerUrl.split("v=");
                // Lấy video ID trước dấu "&"
                videoId = parts[1].split("&")[0];
            }
        }
        return videoId;
    }
}