package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanvexemphim.models.User;
import com.example.duanvexemphim.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.FirebaseAuthUserCollisionException;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class DangKiTaiKhoan extends AppCompatActivity {

    private EditText etUsername, etEmail, etPassword, etConfirmPassword, etPhoneNumber;
    private Button btnRegister;
    private TextView tvLogin;
    private FirebaseAuth auth;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dangki);

        // Khởi tạo FirebaseAuth
        auth = FirebaseAuth.getInstance();
        firebaseDatabase = FirebaseDatabase.getInstance();
        database = firebaseDatabase.getReference("Users");

        // Khởi tạo các thành phần UI
        etUsername = findViewById(R.id.etUsername);
        etEmail = findViewById(R.id.etEmail);
        etPhoneNumber = findViewById(R.id.etPhoneNumber);
        etPassword = findViewById(R.id.etPassword);
        etConfirmPassword = findViewById(R.id.etConfirmPassword);
        btnRegister = findViewById(R.id.btnRegister);
        tvLogin = findViewById(R.id.tvLogin);

        // Khi nhấn vào TextView Đăng Nhập
        tvLogin.setOnClickListener(v -> {
            // Chuyển sang màn hình đăng nhập
            Intent intent = new Intent(DangKiTaiKhoan.this, LoginActivity.class);
            startActivity(intent);
        });

        // Xử lý sự kiện khi nhấn nút Đăng Ký
        btnRegister.setOnClickListener(v -> {
            String username = etUsername.getText().toString().trim();
            String email = etEmail.getText().toString().trim();
            String sdt = etPhoneNumber.getText().toString().trim();
            String password = etPassword.getText().toString().trim();
            String confirmPassword = etConfirmPassword.getText().toString().trim();
            // Kiểm tra thông tin đăng ký
            if (username.isEmpty() || email.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() || sdt.isEmpty()) {
                Toast.makeText(DangKiTaiKhoan.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            if (!password.equals(confirmPassword)) {
                Toast.makeText(DangKiTaiKhoan.this, "Mật khẩu không khớp!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Thực hiện đăng ký người dùng với Firebase
            auth.createUserWithEmailAndPassword(email, password)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            FirebaseUser firebaseUser = auth.getCurrentUser(); // Lấy người dùng vừa tạo
                            String userID = firebaseUser.getUid(); // Lấy UID của người dùng

                            // Tạo đối tượng User và lưu vào Realtime Database
                            User newUser = new User(userID, username, "", email, "user", 0, sdt, new ArrayList<>());
                            database.child(userID).setValue(newUser) // Lưu thông tin người dùng
                                    .addOnSuccessListener(aVoid -> {
                                        Toast.makeText(DangKiTaiKhoan.this, "Đăng ký thành công!", Toast.LENGTH_SHORT).show();
                                        // Chuyển sang màn hình đăng nhập
                                        Intent intent = new Intent(DangKiTaiKhoan.this, LoginActivity.class);
                                        startActivity(intent);
                                        finish();// Đóng màn hình đăng ký
                                        });
                        } else {
                            if (task.getException() instanceof FirebaseAuthUserCollisionException) {
                                Toast.makeText(DangKiTaiKhoan.this, "Email đã tồn tại!", Toast.LENGTH_SHORT).show();
                            } else {
                                Toast.makeText(DangKiTaiKhoan.this, "Đăng ký thất bại: " + task.getException().getMessage(), Toast.LENGTH_LONG).show();
                            }
                        }
                    });
        });
    }
}
