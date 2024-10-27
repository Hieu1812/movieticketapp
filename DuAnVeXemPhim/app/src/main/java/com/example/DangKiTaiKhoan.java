package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanvexemphim.R;

public class DangKiTaiKhoan extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etConfirmPassword;
    private Button btnRegister;
    TextView tvLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangki); // Liên kết với layout đăng ký

        // Khởi tạo các thành phần UI
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        // Khi nhấn vào TextView Đăng Ký
        tvLogin.setOnClickListener(v -> {
            // Chuyển sang màn hình đăng ký
            Intent intent = new Intent(DangKiTaiKhoan.this, LoginActivity.class);
            startActivity(intent);
        });

        // Xử lý sự kiện khi nhấn nút Đăng Ký
        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();

            // Kiểm tra thông tin đăng ký
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                Toast.makeText(DangKiTaiKhoan.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(DangKiTaiKhoan.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                return;
            }

            Toast.makeText(DangKiTaiKhoan.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();

        });
    }
}

