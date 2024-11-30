package com.example;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.resource.bitmap.RoundedCorners;
import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

public class TaiKhoanCuaToiActivity extends AppCompatActivity {
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    Button btnThoat, btnDangXuat;
    TextView tvTenTK, tvSdt, tvEmail;
    ImageView imgAvatar;
    private Uri imageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
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
        tvSdt = findViewById(R.id.tvSDT);
        tvEmail = findViewById(R.id.tvEmail);
        imgAvatar = findViewById(R.id.imgAvatar);

        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
        imgAvatar.setOnClickListener(view -> openPosterImageChooser());

        db.child("Users").child(id).child("avatar").addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String avatarUrl = snapshot.getValue(String.class);
                if (avatarUrl != null && !avatarUrl.isEmpty()) {
                    Glide.with(TaiKhoanCuaToiActivity.this)
                            .load(avatarUrl)
                            .transform(new RoundedCorners(800))
                            .placeholder(R.drawable.avtadmin)
                            .into(imgAvatar);
                }
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TaiKhoanCuaToiActivity.this, "Không thể tải avatar!", Toast.LENGTH_SHORT).show();
            }
        });

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
        db.child("Users").child(id).child("phonenumber").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                String phone = snapshot.getValue(String.class);
                tvSdt.append(phone);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(TaiKhoanCuaToiActivity.this,"Loi " + error.getMessage(),Toast.LENGTH_LONG).show();
            }
        });


        btnThoat = findViewById(R.id.btnThoat);
        btnDangXuat = findViewById(R.id.btnDangXuat);
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(TaiKhoanCuaToiActivity.this, MainActivity.class);
                startActivity(intent);
            }
        });

        // Sự kiện click cho nút Đăng Xuất
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogDangXuat = new AlertDialog.Builder(TaiKhoanCuaToiActivity.this);
                dialogDangXuat.setTitle("Đăng xuất");
                dialogDangXuat.setMessage("Bạn có chắc chắn muốn đăng xuất?");

                dialogDangXuat.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        // Chuyển hướng về trang đăng nhập (LoginActivity)
                        Intent intent = new Intent(TaiKhoanCuaToiActivity.this, LoginActivity.class);
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
    private void openPosterImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn avatar"), PICK_IMAGE_REQUEST);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Xử lý chọn ảnh poster cho phim
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();
            imgAvatar.setImageURI(imageUri);
            uploadAvatarToFirebase(imageUri);
        }
    }
    private void uploadAvatarToFirebase(Uri imageUri) {
        String userId = FirebaseAuth.getInstance().getCurrentUser().getUid();
        firebaseStorage = FirebaseStorage.getInstance("gs://movieticketapp-d1248.firebasestorage.app");
        storageReference = firebaseStorage.getReference();
        StorageReference avatarRef = storageReference.child("userImages/" + userId + ".jpg");
        avatarRef.putFile(imageUri)
                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                    @Override
                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                        avatarRef.getDownloadUrl()
                                .addOnSuccessListener(new OnSuccessListener<Uri>() {
                                    @Override
                                    public void onSuccess(Uri uri) {
                                        DatabaseReference db = FirebaseDatabase.getInstance().getReference();
                                        db.child("Users").child(userId).child("avatar")
                                                .setValue(uri.toString())
                                                .addOnCompleteListener(new OnCompleteListener<Void>() {
                                                    @Override
                                                    public void onComplete(@NonNull Task<Void> task) {
                                                        if (task.isSuccessful()) {
                                                            Toast.makeText(
                                                                    TaiKhoanCuaToiActivity.this,
                                                                    "Avatar đã được cập nhật!",
                                                                    Toast.LENGTH_SHORT
                                                            ).show();
                                                        } else {
                                                            Toast.makeText(
                                                                    TaiKhoanCuaToiActivity.this,
                                                                    "Không thể lưu avatar: " + task.getException().getMessage(),
                                                                    Toast.LENGTH_SHORT
                                                            ).show();
                                                        }
                                                    }
                                                });
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(
                                                TaiKhoanCuaToiActivity.this,
                                                "Không thể lấy URL ảnh: " + e.getMessage(),
                                                Toast.LENGTH_SHORT
                                        ).show();
                                    }
                                });
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(
                                TaiKhoanCuaToiActivity.this,
                                "Tải ảnh thất bại: " + e.getMessage(),
                                Toast.LENGTH_SHORT
                        ).show();
                    }
                });
    }
}