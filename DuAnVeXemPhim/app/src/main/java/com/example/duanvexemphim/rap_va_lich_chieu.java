package com.example.duanvexemphim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.LichChieuVaGhe;
import com.example.duanvexemphim.adapters.ThongTinPhimActivity;

public class rap_va_lich_chieu extends AppCompatActivity {

    Button btnThoat;
    Button btn1_TH, btn2_TH, btn3_TH, btn4_TH, btn5_TH, btn1_HD, btn2_HD, btn3_HD, btn4_HD, btn1_TL, btn2_TL, btn3_TL, btn4_TL;

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
        btn1_HD = findViewById(R.id.btn1_HD);
        btn2_HD = findViewById(R.id.btn2_HD);
        btn3_HD = findViewById(R.id.btn3_HD);
        btn4_HD = findViewById(R.id.btn4_HD);
        btn1_TL = findViewById(R.id.btn1_TL);
        btn2_TL = findViewById(R.id.btn2_TL);
        btn3_TL = findViewById(R.id.btn3_TL);
        btn4_TL = findViewById(R.id.btn4_TL);
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
                Intent intentbtn1_TH = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                startActivity(intentbtn1_TH);
            }
        });
        btn2_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn2_TH = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                startActivity(intentbtn2_TH);
            }
        });
        btn3_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn3_TH = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                startActivity(intentbtn3_TH);
            }
        });
        btn4_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn4_TH = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                startActivity(intentbtn4_TH);
            }
        });
        btn5_TH.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn5_TH = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                startActivity(intentbtn5_TH);
            }
        });
        btn1_HD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn1_HD = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                startActivity(intentbtn1_HD);
            }
        });
        btn2_HD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn2_HD = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                startActivity(intentbtn2_HD);
            }
        });
        btn3_HD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn3_HD = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                startActivity(intentbtn3_HD);
            }
        });
        btn4_HD.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn4_HD = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                startActivity(intentbtn4_HD);
            }
        });
        btn1_TL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn1_TL = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                startActivity(intentbtn1_TL);
            }
        });
        btn2_TL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn2_TL = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                startActivity(intentbtn2_TL);
            }
        });
        btn3_TL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn3_TL = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                startActivity(intentbtn3_TL);
            }
        });
        btn4_TL.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentbtn4_TL = new Intent(rap_va_lich_chieu.this, LichChieuVaGhe.class);
                startActivity(intentbtn4_TL);
            }
        });
    }
}