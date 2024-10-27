package com.example;


import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.R;

public class LoginActivity extends AppCompatActivity {

    TextView tvRegister;
    Button login_button;
    EditText email_input, password_input;

    @SuppressLint("CutPasteId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login); // Liên kết với layout login.xml


        tvRegister = findViewById(R.id.tvRegister);
        login_button = findViewById(R.id.login_button);
        email_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);

        // Khi nhấn vào TextView Đăng Ký
        tvRegister.setOnClickListener(v -> {
            // Chuyển sang màn hình đăng ký
            Intent intent = new Intent(LoginActivity.this, DangKiTaiKhoan.class);
            startActivity(intent);
        });

        login_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_input.getText().toString().trim();
                String password = password_input.getText().toString().trim();

                // Kiểm tra thông tin người dùng
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
//                if (email.equals("admin@gmail.com") && password.equals("123456")){
//                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
//                    startActivity(intent);
//                }
                if (email.equals("user@gmail.com") && password.equals("123456")) {
                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                } else {
                    Toast.makeText(LoginActivity.this, "Thông tin đăng nhập không chính xác!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
}
