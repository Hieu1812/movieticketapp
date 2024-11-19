package com.example;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanvexemphim.models.Movie;
import com.example.duanvexemphim.adapters.ActorAdapter;
import com.example.duanvexemphim.models.Actor;
import com.example.duanvexemphim.R;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import java.util.ArrayList;

public class ThemPhimActivity extends AppCompatActivity {
    private ImageView imgPhim, imgDialogActor;
    private EditText etTenPhim, etThoiLuong, etNDPhim, etTrailerLink, etDialogActorName;
    private Spinner spinnerTheLoai;
    private RecyclerView dienVienRecyclerView;
    private Button btnLuu, btnThoat, btnThemDienVien;
    private ArrayList<Actor> actorList;
    private ArrayList<Movie> listMovies;
    private ActorAdapter actorAdapter;
    private Uri imageUri;
    private Uri actorImageUri;
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final int PICK_IMAGE_REQUEST_ACTOR = 2;

    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
    private DatabaseReference movieRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_them_phim);

        // Khởi tạo các thành phần
        imgPhim = findViewById(R.id.imgPhim);
        etTenPhim = findViewById(R.id.etTenPhim);
        spinnerTheLoai = findViewById(R.id.spinnerTheLoai);
        etThoiLuong = findViewById(R.id.etThoiLuong);
        etNDPhim = findViewById(R.id.etNDPhim);
        etTrailerLink = findViewById(R.id.etTrailerLink);
        dienVienRecyclerView = findViewById(R.id.dienVienRecyclerView);
        btnLuu = findViewById(R.id.btnLuuPhim);
        btnThoat = findViewById(R.id.btnThoat);
        btnThemDienVien = findViewById(R.id.btnThemDienVien);

        listMovies = new ArrayList<>();
        actorList = new ArrayList<>();
        actorAdapter = new ActorAdapter(this, actorList);
        dienVienRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        dienVienRecyclerView.setAdapter(actorAdapter);

        firebaseStorage = FirebaseStorage.getInstance("gs://movieticketapp-d1248.firebasestorage.app");
        storageReference = firebaseStorage.getReference();
        movieRef = FirebaseDatabase.getInstance().getReference("Movies");

        imgPhim.setOnClickListener(view -> openPosterImageChooser());

        btnThoat.setOnClickListener(view -> {
            Intent intentThoat = new Intent(ThemPhimActivity.this, QuanTriAdminActivity.class);
            startActivity(intentThoat);
        });

        btnThemDienVien.setOnClickListener(v -> {
            // Tạo dialog để thêm diễn viên
            AlertDialog.Builder builder = new AlertDialog.Builder(ThemPhimActivity.this);
            View view = getLayoutInflater().inflate(R.layout.dialog_add_actor, null);

            etDialogActorName = view.findViewById(R.id.etDialogActorName);
            imgDialogActor = view.findViewById(R.id.imgDialogActor);

            builder.setView(view)
                    .setPositiveButton("Thêm", (dialog, id) -> {
                        String actorName = etDialogActorName.getText().toString().trim();

                        // Kiểm tra nếu tên diễn viên và ảnh không rỗng
                        if (actorName.isEmpty() || actorImageUri == null) {
                            Toast.makeText(ThemPhimActivity.this, "Vui lòng nhập tên diễn viên và chọn ảnh diễn viên", Toast.LENGTH_SHORT).show();
                        } else {
                            // Tạo một Actor mới và thêm vào danh sách
                            Actor newActor = new Actor(actorName, actorImageUri.toString());
                            actorList.add(newActor);
                            actorAdapter.notifyDataSetChanged();
                        }
                    })
                    .setNegativeButton("Hủy", (dialog, id) -> dialog.dismiss());

            AlertDialog dialog = builder.create();
            dialog.show();

            // Xử lý sự kiện nhấn vào ImageView để chọn ảnh
            imgDialogActor.setOnClickListener(v1 -> openActorImageChooser());
        });

        btnLuu.setOnClickListener(view -> {
            String movieID = movieRef.push().getKey();
            String name = etTenPhim.getText().toString().trim();
            String genre = spinnerTheLoai.getSelectedItem().toString();
            String durationTime = etThoiLuong.getText().toString().trim();
            String description = etNDPhim.getText().toString().trim();
            String trailer = etTrailerLink.getText().toString().trim();

            // Kiểm tra các trường thông tin bắt buộc
            if (name.isEmpty() || durationTime.isEmpty() || description.isEmpty()) {
                Toast.makeText(this, "Vui lòng điền đầy đủ thông tin!", Toast.LENGTH_SHORT).show();
                return;
            }
            if(genre.equals("Chọn thể loại")) {
                Toast.makeText(this, "Vui lòng chọn thể loại!", Toast.LENGTH_SHORT).show();
            }
            if (imageUri == null || actorList.isEmpty()) {
                Toast.makeText(this, "Phim phải có ảnh poster và diễn viên!", Toast.LENGTH_SHORT).show();
                return;
            }
            StorageReference posterImageRef = storageReference.child("posterImages/" + movieID + ".jpg");
            // Tải ảnh poster phim lên Firebase Storage
            posterImageRef.putFile(imageUri).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                @Override
                public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                    posterImageRef.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
                        @Override
                        public void onSuccess(Uri uri) {
                            String posterUrl = uri.toString();
                            ArrayList<Actor> updatedActorList = new ArrayList<>();

                            // Tải lên ảnh cho từng diễn viên
                            for (Actor actor : actorList) {
                                String actorID = movieRef.child("actors").push().getKey();
                                StorageReference actorImageRef = storageReference.child("actorImages/" + actorID + ".jpg");

                                // Tải ảnh diễn viên lên Firebase Storage
                                actorImageRef.putFile(Uri.parse(actor.getActorImage())).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        actorImageRef.getDownloadUrl().addOnSuccessListener(actorUri -> {
                                            Actor updatedActor = new Actor(actor.getActorName(), actorUri.toString());
                                            updatedActorList.add(updatedActor);

                                            // Kiểm tra nếu đã tải xong tất cả ảnh diễn viên
                                            if (updatedActorList.size() == actorList.size()) {
                                                // Tạo đối tượng Movies và lưu vào Firebase
                                                Movie movie = new Movie(movieID, name, posterUrl, description, genre, durationTime, new ArrayList<>(), trailer, 0, updatedActorList);
                                                listMovies.add(movie);
                                                movieRef.child(movieID).setValue(movie).addOnSuccessListener(new OnSuccessListener<Void>() {
                                                    @Override
                                                    public void onSuccess(Void unused) {
                                                        Toast.makeText(ThemPhimActivity.this, "Phim đã lưu thành công!", Toast.LENGTH_SHORT).show();
                                                        finish();
                                                    }
                                                }).addOnFailureListener(new OnFailureListener() {
                                                    @Override
                                                    public void onFailure(@NonNull Exception e) {
                                                        Toast.makeText(ThemPhimActivity.this, "Lỗi khi lưu phim: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                                    }
                                                });
                                            }
                                        });
                                    }
                                });
                            }
                        }
                    });
                }
            });
        });
    }

    private void openPosterImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh poster"), PICK_IMAGE_REQUEST);
    }

    private void openActorImageChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(Intent.createChooser(intent, "Chọn ảnh diễn viên"), PICK_IMAGE_REQUEST_ACTOR);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Xử lý chọn ảnh poster cho phim
        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null && data.getData() != null) {
            imageUri = data.getData();  // Lấy URI ảnh poster
            imgPhim.setImageURI(imageUri);  // Hiển thị ảnh vào ImageView imgPhim
        }
        // Xử lý chọn ảnh cho diễn viên
        if (requestCode == PICK_IMAGE_REQUEST_ACTOR && resultCode == RESULT_OK && data != null && data.getData() != null) {
            actorImageUri = data.getData();  // Lấy URI ảnh diễn viên
            if (imgDialogActor != null) {
                imgDialogActor.setImageURI(actorImageUri); // Cập nhật ảnh vào imgDialogActor
            }
        }
    }
}

