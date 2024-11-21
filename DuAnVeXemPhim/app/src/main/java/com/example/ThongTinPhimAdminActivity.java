package com.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.R;
import com.example.duanvexemphim.adapters.ActorAdapter;
import com.example.duanvexemphim.models.Actor;

import java.util.List;

public class ThongTinPhimAdminActivity extends AppCompatActivity {

    Button btnThoat;
    TextView tvTenPhim, tvTheLoai, tvThoiLuong, tvND, tvActorList;
    ImageView imgPoster;
    WebView webViewTrailer;
    RecyclerView recyclerViewActors;
    ActorAdapter actorAdapter;
    List<Actor> actorList;

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
        //
        // Ánh xạ các View trong layout
        btnThoat = findViewById(R.id.btnThoat);
        tvTenPhim = findViewById(R.id.tvTenPhim);
        tvTheLoai = findViewById(R.id.tvTheLoai);
        tvThoiLuong = findViewById(R.id.tvThoiLuong);
        tvND = findViewById(R.id.tvND);
        imgPoster = findViewById(R.id.imgCam);
        webViewTrailer = findViewById(R.id.webViewTrailer);
        recyclerViewActors = findViewById(R.id.recyclerViewActors);

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
        Intent intent = getIntent();
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
        tvND.setText(movieDescription);

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
                Intent intentThoat = new Intent(ThongTinPhimAdminActivity.this, QuanTriAdminActivity.class);
                startActivity(intentThoat);
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