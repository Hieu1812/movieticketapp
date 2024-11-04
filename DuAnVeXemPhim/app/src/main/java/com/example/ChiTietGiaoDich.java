package com.example;

import android.app.AlertDialog;
import android.content.DialogInterface;
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
                Intent intentThoat = new Intent(ChiTietGiaoDich.this, LichChieuVaGhe.class);
                startActivity(intentThoat);
            }
        });
        //
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogDongY = new AlertDialog.Builder(ChiTietGiaoDich.this);
                dialogDongY.setTitle("Xác nhận giao dịch");
                dialogDongY.setMessage("Bạn có chắc chắn đồng ý thanh toán?");

                dialogDongY.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ChiTietGiaoDich.this, chi_tiet_giao_dich.class);
                        startActivity(intent);
                        finish();
                    }
                });

                dialogDongY.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialogDongY.create().show();
            }
        });
    }
}