package com.example;


import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanvexemphim.R;

public class LoginActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); // Liên kết với layout login.xml

        // Khởi tạo TextView Đăng Ký
        TextView tvRegister = findViewById(R.id.tvRegister);

        // Khi nhấn vào TextView Đăng Ký
        tvRegister.setOnClickListener(v -> {
            // Chuyển sang màn hình đăng ký
            Intent intent = new Intent(LoginActivity.this, DangKiTaiKhoan.class);
            startActivity(intent);
        });

//

    }
}
