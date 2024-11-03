package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanvexemphim.R;

public class ChiTietGiaoDich extends AppCompatActivity {

    Button btnThoat, btnDongY;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_giao_dich);

        btnThoat = findViewById(R.id.btnThoat);
        btnDongY = findViewById(R.id.btnDongY);
        //
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentThoat = new Intent(ChiTietGiaoDich.this, ThongTinPhim.class);
                startActivity(intentThoat);
            }
        });
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentDongY = new Intent(ChiTietGiaoDich.this, chi_tiet_giao_dich.class);
                startActivity(intentDongY);
            }
        });
    }
}