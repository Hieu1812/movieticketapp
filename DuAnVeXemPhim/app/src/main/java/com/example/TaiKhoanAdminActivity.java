package com.example;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.R;

public class TaiKhoanAdminActivity extends AppCompatActivity {

    Button btnThoat, btnDangXuat;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tai_khoan_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnThoat = findViewById(R.id.btnThoat);
        btnDangXuat = findViewById(R.id.btnDangXuat);
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaiKhoanAdminActivity.this, QuanTriAdminActivity.class);
                startActivity(intent);
            }
        });

        // Sự kiện click cho nút Đăng Xuất
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogDangXuat = new AlertDialog.Builder(TaiKhoanAdminActivity.this);
                dialogDangXuat.setTitle("Đăng xuất");
                dialogDangXuat.setMessage("Bạn có chắc chắn muốn đăng xuất?");

                dialogDangXuat.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Chuyển hướng về trang đăng nhập (LoginActivity)
                        Intent intent = new Intent(TaiKhoanAdminActivity.this, LoginActivity.class);
                        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK); // Xóa ngăn xếp
                        startActivity(intent);
                        finish();
                    }
                });

                dialogDangXuat.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel(); // Đóng dialog
                    }
                });

                dialogDangXuat.create().show();
            }
        });
    }
}