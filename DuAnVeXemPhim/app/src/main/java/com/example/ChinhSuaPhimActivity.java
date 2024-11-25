package com.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.bumptech.glide.Glide;
import com.example.duanvexemphim.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

public class ChinhSuaPhimActivity extends AppCompatActivity {

    Button btnThoat, btnCapNhat;
    EditText edtTenPhim, edtThoiLuong, edtNDPhim, edtTrailerLink;
    Spinner spinnerTheLoai;
    ImageView imgPhim;
    TextView tvAn;
    String movieId, movieName, movieGenre, movieDuration, movieDescription, movieTrailer, moviePoster;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;

    DatabaseReference movieRef;
    Uri imageUri;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chinh_sua_phim);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        firebaseStorage = FirebaseStorage.getInstance("gs://movieticketapp-d1248.firebasestorage.app");
        storageReference = firebaseStorage.getReference();

        btnThoat = findViewById(R.id.btnThoat);
        btnCapNhat = findViewById(R.id.btnCapNhat);
        edtTenPhim = findViewById(R.id.edtTenPhim);
        edtThoiLuong = findViewById(R.id.etThoiLuong);
        edtNDPhim = findViewById(R.id.edtNDPhim);
        edtTrailerLink = findViewById(R.id.edtTrailerLink);
        spinnerTheLoai = findViewById(R.id.spinnerTheLoai);
        imgPhim = findViewById(R.id.imgPhim);
        tvAn = findViewById(R.id.tvAn);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        movieId = intent.getStringExtra("movieId");
        movieName = intent.getStringExtra("movieName");
        movieGenre = intent.getStringExtra("movieGenre");
        movieDuration = intent.getStringExtra("movieDuration");
        movieDescription = intent.getStringExtra("movieDescription");
        movieTrailer = intent.getStringExtra("movieTrailer");
        moviePoster = intent.getStringExtra("moviePoster");

        // Hiển thị dữ liệu vào các trường nhập liệu
        edtTenPhim.setText(movieName);
        edtThoiLuong.setText(movieDuration);
        edtNDPhim.setText(movieDescription);
        edtTrailerLink.setText(movieTrailer);
        // Set hình ảnh cho ImageView (sử dụng Glide để tải ảnh từ URL)
        Glide.with(this).load(moviePoster).into(imgPhim);

        // Cài đặt Spinner với các thể loại từ resources
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this, R.array.the_loai_list, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTheLoai.setAdapter(adapter);
        int genrePosition = adapter.getPosition(movieGenre);
        spinnerTheLoai.setSelection(genrePosition);

        // Hàm thay đổi chiều cao của TextView tvAn
        View.OnFocusChangeListener focusChangeListener = (view, hasFocus) -> {
            if (hasFocus) {
                tvAn.getLayoutParams().height = (int) getResources().getDisplayMetrics().density * 600;
            } else {
                tvAn.getLayoutParams().height = 0;
            }
            tvAn.requestLayout();
        };
        edtNDPhim.setOnFocusChangeListener(focusChangeListener);
        edtTrailerLink.setOnFocusChangeListener(focusChangeListener);


        // Xử lý nút Thoát
        btnThoat.setOnClickListener(view -> {
            Intent exitIntent = new Intent(ChinhSuaPhimActivity.this, QuanTriAdminActivity.class);
            startActivity(exitIntent);
        });

        // Xử lý nút Cập nhật
        btnCapNhat.setOnClickListener(view -> {
            String updatedName = edtTenPhim.getText().toString();
            String updatedDuration = edtThoiLuong.getText().toString();
            String updatedDescription = edtNDPhim.getText().toString();
            String updatedTrailer = edtTrailerLink.getText().toString();
            String updatedGenre = spinnerTheLoai.getSelectedItem().toString();

            if(updatedName.isEmpty() || updatedDuration.isEmpty() || updatedDescription.isEmpty() || updatedTrailer.isEmpty()) {
                Toast.makeText(ChinhSuaPhimActivity.this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }

            // Lấy reference tới phim trong Firebase
            movieRef = FirebaseDatabase.getInstance().getReference("Movies").child(movieId);

            // Cập nhật dữ liệu lên Firebase
            movieRef.child("name").setValue(updatedName);
            movieRef.child("genre").setValue(updatedGenre);
            movieRef.child("durationTime").setValue(updatedDuration);
            movieRef.child("description").setValue(updatedDescription);
            movieRef.child("trailer").setValue(updatedTrailer)
                    .addOnCompleteListener(task -> {
                        if (task.isSuccessful()) {
                            // Hiển thị thông báo thành công
                            Toast.makeText(ChinhSuaPhimActivity.this, "Cập nhật phim thành công!", Toast.LENGTH_SHORT).show();
                            Intent intenta = new Intent(ChinhSuaPhimActivity.this, QuanTriAdminActivity.class);
                            startActivity(intenta);
                            finish();
                        } else {
                            Toast.makeText(ChinhSuaPhimActivity.this, "Có lỗi khi cập nhật dữ liệu!", Toast.LENGTH_SHORT).show();
                        }
                    });
        });
    }
}