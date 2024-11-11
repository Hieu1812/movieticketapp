package com.example;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class TaiKhoanCuaToi extends AppCompatActivity {

    Button btnThoat, btnDangXuat;
    TextView tvTenTK, tvSdt, tvEmail, tvTienDaTieu;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_tai_khoan_cua_toi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvTenTK = findViewById(R.id.tvTenTK);
        tvSdt = findViewById(R.id.tvSĐT);
        tvEmail = findViewById(R.id.tvEmail);
        tvTienDaTieu = findViewById(R.id.tvTienDaTieu);

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();

//        Hiển thị tên
        db.child("Users").child(id).child("name").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String ten = snapshot.getValue(String.class);
                tvTenTK.append(ten);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        // Hiển thị email
        db.child("Users").child(id).child("email").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String email = snapshot.getValue(String.class);
                tvEmail.append(email);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        // Hiển thị sdt
        db.child("Users").child(id).child("").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String phone = snapshot.getValue(String.class);
                tvSdt.append(phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnThoat = findViewById(R.id.btnThoat);
        btnDangXuat = findViewById(R.id.btnDangXuat);
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaiKhoanCuaToi.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Sự kiện click cho nút Đăng Xuất
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogDangXuat = new AlertDialog.Builder(TaiKhoanCuaToi.this);
                dialogDangXuat.setTitle("Đăng xuất");
                dialogDangXuat.setMessage("Bạn có chắc chắn muốn đăng xuất?");

                dialogDangXuat.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Chuyển hướng về trang đăng nhập (LoginActivity)
                        Intent intent = new Intent(TaiKhoanCuaToi.this, LoginActivity.class);
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