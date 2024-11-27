package com.example;


import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthInvalidCredentialsException;
import com.google.firebase.auth.FirebaseAuthInvalidUserException;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class LoginActivity extends AppCompatActivity {

    TextView tvRegister;
    Button btnLogin;
    EditText email_input, password_input;
    private FirebaseAuth auth;
    private DatabaseReference database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        // Khởi tạo FirebaseAuth
        auth = FirebaseAuth.getInstance();
        // Khởi tạo FirebaseDatabase reference
        database = FirebaseDatabase.getInstance().getReference("Users");
        tvRegister = findViewById(R.id.tvRegister);
        btnLogin = findViewById(R.id.btnLogin);
        email_input = findViewById(R.id.email_input);
        password_input = findViewById(R.id.password_input);

        // Khi nhấn vào TextView Đăng Ký
        tvRegister.setOnClickListener(v -> {
            // Chuyển sang màn hình đăng ký
            Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
            startActivity(intent);
        });

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String email = email_input.getText().toString().trim();
                String password = password_input.getText().toString().trim();

                // Kiểm tra thông tin người dùng
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Vui lòng nhập đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                    return;
                }
                // Thực hiện đăng nhập người dùng với Firebase
                auth.signInWithEmailAndPassword(email, password)
                        .addOnCompleteListener(task -> {
                            if (task.isSuccessful()) {
                                // Đăng nhập thành công
                                FirebaseUser firebaseUser = auth.getCurrentUser();
                                Toast.makeText(LoginActivity.this, "Đăng nhập thành công!", Toast.LENGTH_SHORT).show();

                                String userId = firebaseUser.getUid(); // Lấy UID của người dùng
                                Log.d("UserID", "Current user ID: " + userId);

                                database.child(userId).child("role").get().addOnCompleteListener(roleTask -> {
                                    if (roleTask.isSuccessful()) {
                                        DataSnapshot dataSnapshot = roleTask.getResult();
                                        String role = dataSnapshot.getValue(String.class);
                                        // Kiểm tra vai trò và điều hướng đến trang thích hợp
                                        if ("admin".equals(role)) {
                                            // Nếu là admin, chuyển đến trang Admin
                                            Intent intent = new Intent(LoginActivity.this, QuanTriAdminActivity.class);
                                            startActivity(intent);
                                        } else {
                                            // Nếu không phải là admin, chuyển đến MainActivity
                                            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                            intent.putExtra("email", email);
                                            SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
                                            SharedPreferences.Editor editor = sharedPreferences.edit();
                                            editor.putString("email", email); // Lưu email
                                            editor.apply();
                                            startActivity(intent);
                                        }
                                        finish(); // Đóng màn hình đăng nhập
                                    } else {
                                        Toast.makeText(LoginActivity.this, "Không thể lấy vai trò người dùng!", Toast.LENGTH_SHORT).show();
                                    }
                                });
                            } else {
                                // Xử lý các loại lỗi cụ thể
                                if (task.getException() instanceof FirebaseAuthInvalidUserException) {
                                    Toast.makeText(LoginActivity.this, "Email chưa được đăng ký!", Toast.LENGTH_SHORT).show();
                                } else if (task.getException() instanceof FirebaseAuthInvalidCredentialsException) {
                                    Toast.makeText(LoginActivity.this, "Mật khẩu không chính xác hoặc thông tin xác thực không hợp lệ!", Toast.LENGTH_SHORT).show();
                                } else {
                                    // Các lỗi khác
                                    Toast.makeText(LoginActivity.this, "Đăng nhập thất bại: " + task.getException().getMessage(), Toast.LENGTH_SHORT).show();
                                }
                                Log.e("LoginError", "Error: ", task.getException());
                            }
                        });
            }
        });

    }
}
