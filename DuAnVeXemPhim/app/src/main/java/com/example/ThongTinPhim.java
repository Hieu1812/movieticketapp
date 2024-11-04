package com.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.VideoView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.R;
import com.google.firebase.Firebase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;

public class ThongTinPhim extends AppCompatActivity implements Serializable {

    TextView named;
    Button btnThoat, btnDatVe, btnThich, btnTrailer;
    VideoView videoTrailer;
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
        //
        named = findViewById(R.id.tvTenPhim);
        btnThoat = findViewById(R.id.btnThoat);
        btnDatVe = findViewById(R.id.btnDatVe);
        btnThich = findViewById(R.id.btnThich);
        btnTrailer = findViewById(R.id.btnTrailer);
        videoTrailer = findViewById(R.id.videoTrailer);
        //
        Intent intent = getIntent();
        Bundle data = intent.getExtras();
        //onCreategetdata();
        //
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentThoat = new Intent(ThongTinPhim.this, MainActivity.class);
                startActivity(intentThoat);
            }
        });
        btnDatVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDatVe = new Intent(ThongTinPhim.this, LichChieuVaGhe.class);
                startActivity(intentDatVe);
            }
        });
        btnTrailer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String youtubeUrl = "https://www.youtube.com/watch?v=_8qUFEmPQbc";
                // Mở video trong ứng dụng YouTube hoặc trình duyệt
                Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                intent.putExtra("force_fullscreen", true);  // Bật chế độ toàn màn hình
                // Kiểm tra nếu có ứng dụng YouTube để phát video, nếu không mở bằng trình duyệt
                if (intent.resolveActivity(getPackageManager()) != null) {
                    startActivity(intent);
                } else {
                    Intent browserIntent = new Intent(Intent.ACTION_VIEW, Uri.parse(youtubeUrl));
                    startActivity(browserIntent);
                }
            }
        });
        //
        btnThich.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clicktym) {
                    btnThich.setBackgroundTintList(getResources().getColorStateList(R.color.white));
                    clicktym = false;
                } else {
                    btnThich.setBackgroundTintList(getResources().getColorStateList(R.color.colorred));
                    clicktym = true;
                }
            }
        });
    }
}