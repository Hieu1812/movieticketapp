package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanvexemphim.R;

public class RapVaLichChieuActivity extends AppCompatActivity {

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
        btnThoat = findViewById(R.id.btnThoat);
        btn1_TH = findViewById(R.id.btn1_TH);
        btn2_TH = findViewById(R.id.btn2_TH);
        btn3_TH = findViewById(R.id.btn3_TH);
        btn4_TH = findViewById(R.id.btn4_TH);
        btn5_TH = findViewById(R.id.btn5_TH);
        //
        Intent intent = getIntent();
        String movieID1 = intent.getStringExtra("movieID1");
        String movieNameNo1 = intent.getStringExtra("movieNameNo1");
        String movieGenre1 = intent.getStringExtra("movieGenre1");
        String movieDuration1 = intent.getStringExtra("movieDuration1");
        String movieDescription1 = intent.getStringExtra("movieDescription1");
        String moviePoster1 = intent.getStringExtra("moviePoster1");
        String movieTrailer1 = intent.getStringExtra("movieTrailer1");
        //
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("movieID1", movieID1.toString());
                returnIntent.putExtra("movieNameNo1", movieNameNo1.toString());
                returnIntent.putExtra("movieGenre1", movieGenre1.toString());
                returnIntent.putExtra("movieDuration1", movieDuration1.toString());
                returnIntent.putExtra("movieDescription1", movieDescription1.toString());
                returnIntent.putExtra("moviePoster1", moviePoster1.toString());
                returnIntent.putExtra("movieTrailer1", movieTrailer1.toString());
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
        btn1_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RapVaLichChieuActivity.this, LichChieuVaGheActivity.class);
                intent.putExtra("GioChieu", "9:15");
                intent.putExtra("movieNameNo2", movieNameNo1);
                startActivity(intent);
            }
        });

        btn2_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RapVaLichChieuActivity.this, LichChieuVaGheActivity.class);
                intent.putExtra("GioChieu", "13:15");
                intent.putExtra("movieNameNo2", movieNameNo1);
                startActivity(intent);
            }
        });

        btn3_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RapVaLichChieuActivity.this, LichChieuVaGheActivity.class);
                intent.putExtra("GioChieu", "16:35");
                intent.putExtra("movieNameNo2", movieNameNo1);
                startActivity(intent);
            }
        });

        btn4_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RapVaLichChieuActivity.this, LichChieuVaGheActivity.class);
                intent.putExtra("GioChieu", "20:45");
                intent.putExtra("movieNameNo2", movieNameNo1);
                startActivity(intent);
            }
        });

        btn5_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RapVaLichChieuActivity.this, LichChieuVaGheActivity.class);
                intent.putExtra("GioChieu", "23:05");
                intent.putExtra("movieNameNo2", movieNameNo1);
                startActivity(intent);
            }
        });
    }
}