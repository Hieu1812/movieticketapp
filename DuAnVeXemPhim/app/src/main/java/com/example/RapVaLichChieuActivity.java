package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.duanvexemphim.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.HashMap;
import java.util.Map;

public class RapVaLichChieuActivity extends AppCompatActivity {
    TextView tvNameTheater, tvAddress, tvHotline;
    ImageButton imgTheater;
    Button btnThoat;
    Button btn1_TH, btn2_TH, btn3_TH, btn4_TH, btn5_TH;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_rap_va_lich_chieu);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //
        tvNameTheater = findViewById(R.id.tvNameTheater);
        tvAddress = findViewById(R.id.tvAddress);
        tvHotline = findViewById(R.id.tvHotline);
        imgTheater = findViewById(R.id.imgTheater);
        btnThoat = findViewById(R.id.btnThoat);
        btn1_TH = findViewById(R.id.btn1_TH);
        btn2_TH = findViewById(R.id.btn2_TH);
        btn3_TH = findViewById(R.id.btn3_TH);
        btn4_TH = findViewById(R.id.btn4_TH);
        btn5_TH = findViewById(R.id.btn5_TH);

        fetchTheaterInfoFromFirebase();

        Intent intent = getIntent();
        String movieID = intent.getStringExtra("movieID");
        String movieName = intent.getStringExtra("movieName");
        String movieGenre = intent.getStringExtra("movieGenre");
        String movieDuration = intent.getStringExtra("movieDuration");
        String movieDescription = intent.getStringExtra("movieDescription");
        String moviePoster = intent.getStringExtra("moviePoster");
        String movieTrailerUrl = intent.getStringExtra("movieTrailer");

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("movieID", movieID);
                returnIntent.putExtra("movieName", movieName);
                returnIntent.putExtra("movieGenre", movieGenre);
                returnIntent.putExtra("movieDuration", movieDuration);
                returnIntent.putExtra("movieDescription", movieDescription);
                returnIntent.putExtra("moviePoster", moviePoster);
                returnIntent.putExtra("movieTrailer", movieTrailerUrl);
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
        btn1_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RapVaLichChieuActivity.this, LichChieuVaGheActivity.class);
                intent.putExtra("GioChieu", "9:15");
                intent.putExtra("movieNameNo2", movieName);
                startActivity(intent);
            }
        });

        btn2_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RapVaLichChieuActivity.this, LichChieuVaGheActivity.class);
                intent.putExtra("GioChieu", "13:15");
                intent.putExtra("movieNameNo2", movieName);
                startActivity(intent);
            }
        });

        btn3_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RapVaLichChieuActivity.this, LichChieuVaGheActivity.class);
                intent.putExtra("GioChieu", "16:35");
                intent.putExtra("movieNameNo2", movieName);
                startActivity(intent);
            }
        });

        btn4_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RapVaLichChieuActivity.this, LichChieuVaGheActivity.class);
                intent.putExtra("GioChieu", "20:45");
                intent.putExtra("movieNameNo2", movieName);
                startActivity(intent);
            }
        });

        btn5_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RapVaLichChieuActivity.this, LichChieuVaGheActivity.class);
                intent.putExtra("GioChieu", "23:05");
                intent.putExtra("movieNameNo2", movieName);
                startActivity(intent);
            }
        });
    }
    private void fetchTheaterInfoFromFirebase() {
        DatabaseReference theaterRef = FirebaseDatabase.getInstance().getReference("Theaters").child("-OCXs03BSPCM809x38-r");

        theaterRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    String name = snapshot.child("name").getValue(String.class);
                    String address = snapshot.child("address").getValue(String.class);
                    String hotline = snapshot.child("hotline").getValue(String.class);
                    String imageURL = snapshot.child("imageURL").getValue(String.class);

                    tvNameTheater.setText(name);
                    tvAddress.setText(address);
                    tvHotline.setText(hotline);
                    Glide.with(RapVaLichChieuActivity.this)
                            .load(imageURL)
                            .into(imgTheater);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(RapVaLichChieuActivity.this, "Không thể tải thông tin rạp.", Toast.LENGTH_SHORT).show();
            }
        });
    }


}