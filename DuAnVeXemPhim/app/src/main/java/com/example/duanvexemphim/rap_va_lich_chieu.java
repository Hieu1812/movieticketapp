package com.example.duanvexemphim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.LichChieuVaGhe;
import com.example.duanvexemphim.adapters.ThongTinPhimActivity;

public class rap_va_lich_chieu extends AppCompatActivity {

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
        String movieNameNo1 = intent.getStringExtra("movieNameNo1");
        //
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentThoat = new Intent(rap_va_lich_chieu.this, ThongTinPhimActivity.class);
                startActivity(intentThoat);
            }
        });
        btn1_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                intent.putExtra("GioChieu", "9:15");
                intent.putExtra("movieNameNo2", movieNameNo1);
                startActivity(intent);
            }
        });

        btn2_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                intent.putExtra("GioChieu", "13:15");
                intent.putExtra("movieNameNo2", movieNameNo1);
                startActivity(intent);
            }
        });

        btn3_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                intent.putExtra("GioChieu", "16:35");
                intent.putExtra("movieNameNo2", movieNameNo1);
                startActivity(intent);
            }
        });

        btn4_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                intent.putExtra("GioChieu", "20:45");
                intent.putExtra("movieNameNo2", movieNameNo1);
                startActivity(intent);
            }
        });

        btn5_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                intent.putExtra("GioChieu", "23:05");
                intent.putExtra("movieNameNo2", movieNameNo1);
                startActivity(intent);
            }
        });
    }
}