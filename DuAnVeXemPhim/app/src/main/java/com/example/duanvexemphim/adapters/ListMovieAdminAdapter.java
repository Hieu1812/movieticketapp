package com.example.duanvexemphim.adapters;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.ChinhSuaPhimActivity;
import com.example.LoginActivity;
import com.example.QuanTriAdminActivity;
import com.example.TaiKhoanAdminActivity;
import com.example.ThongTinPhimAdminActivity;
import com.example.duanvexemphim.R;
import com.example.duanvexemphim.models.Actor;
import com.example.duanvexemphim.models.Movie;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;

public class ListMovieAdminAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Movie> listMovies;
    public ListMovieAdminAdapter(Context ctx, int res, ArrayList<Movie> listMovies) {
        super(ctx, res);
        this.context = ctx;
        this.resource = res;
        this.listMovies = listMovies;
    }
    @Override
    public int getCount() {
        return this.listMovies.size();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View customView = layoutInflater.inflate(resource, null);
        //
        ImageView imgPoster = customView.findViewById(R.id.imgPoster);
        TextView tvName  = customView.findViewById(R.id.tvName);
        TextView tvTheLoai = customView.findViewById(R.id.tvTheLoai);
        TextView tvGia = customView.findViewById(R.id.tvGia);
        Button btnXem = customView.findViewById(R.id.btnXem);
        Button btnSua = customView.findViewById(R.id.btnSua);
        Button btnXoa = customView.findViewById(R.id.btnXoa);
        Movie movie = listMovies.get(position);

//        tvName.setText("Phim: " + movie.getName());
//        tvTheLoai.setText("Thể loại: " + movie.getGenre());
//        tvGia.setText("Giá: 50.000 VNĐ");
        if (movie != null) {
            tvName.setText("Phim: " + movie.getName());
            tvTheLoai.setText("Thể loại: " + movie.getGenre());
            tvGia.setText("Giá: 50.000 VNĐ");

            // Xử lý nút Xem
            btnXem.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // Tạo Intent để chuyển sang ThongTinPhimAdminActivity
                    Intent intent = new Intent(context, ThongTinPhimAdminActivity.class);
                    // Gửi các thông tin về bộ phim qua Intent
                    intent.putExtra("movieId", movie.getMovieID());
                    intent.putExtra("movieName", movie.getName());
                    intent.putExtra("movieGenre", movie.getGenre());
                    intent.putExtra("movieDuration", movie.getDurationTime());
                    intent.putExtra("movieDescription", movie.getDescription());
                    intent.putExtra("movieTrailer", movie.getTrailer());
                    intent.putExtra("moviePoster", movie.getPosterImage());
                    // Gửi thông tin các diễn viên qua Intent
                    ArrayList<String> actorNames = new ArrayList<>();
                    ArrayList<String> actorImages = new ArrayList<>();
                    for (Actor actor : movie.getActorList()) {
                        actorNames.add(actor.getActorName());
                        actorImages.add(actor.getActorImage());
                    }
                    intent.putStringArrayListExtra("actorNames", actorNames);
                    intent.putStringArrayListExtra("actorImages", actorImages);
                    // Gửi các thông tin về bộ phim qua Intent
                    context.startActivity(intent);
                }
            });

            // Xử lý nút Sửa
            btnSua.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, ChinhSuaPhimActivity.class);
                    intent.putExtra("movieId", movie.getMovieID());
                    intent.putExtra("movieName", movie.getName());
                    intent.putExtra("movieGenre", movie.getGenre());
                    intent.putExtra("movieDuration", movie.getDurationTime());
                    intent.putExtra("movieDescription", movie.getDescription());
                    intent.putExtra("movieTrailer", movie.getTrailer());
                    intent.putExtra("moviePoster", movie.getPosterImage());

                    context.startActivity(intent);
                }
            });

            // Xử lý nút Xóa
            btnXoa.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    AlertDialog.Builder builder = new AlertDialog.Builder(context);
                    builder.setTitle("Xóa phim");
                    builder.setMessage("Bạn có chắc chắn muốn xóa phim này không?");
                    builder.setPositiveButton("Có", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            DatabaseReference movieRef = FirebaseDatabase.getInstance()
                                    .getReference("Movies")
                                    .child(movie.getMovieID());
                            // Lấy tham chiếu đến Firebase Storage
                            FirebaseStorage firebaseStorage = FirebaseStorage.getInstance("gs://movieticketapp-d1248.firebasestorage.app");
                            StorageReference storageRef = firebaseStorage.getReference();
                            // Xóa ảnh poster
                            String posterPath = "posterImages/" + movie.getMovieID() + ".jpg";
                            StorageReference posterRef = storageRef.child(posterPath);
                            posterRef.delete()
                                    .addOnSuccessListener(unused -> {
                                        Toast.makeText(context, "Đã xóa ảnh poster!", Toast.LENGTH_SHORT).show();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(context, "Không thể xóa ảnh poster: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    });

                            // Xử lý xóa ảnh diễn viên
                            for (Actor actor : movie.getActorList()) {
                                String actorImagePath = "actorImages/" + getFileNameFromUri(Uri.parse(actor.getActorImage()));
                                StorageReference actorImageRef = storageRef.child(actorImagePath);

                                // Xóa ảnh diễn viên
                                actorImageRef.delete()
                                        .addOnSuccessListener(unused -> {
                                            Toast.makeText(context, "Đã xóa ảnh diễn viên: " + actor.getActorName(), Toast.LENGTH_SHORT).show();
                                        })
                                        .addOnFailureListener(e -> {
                                            Toast.makeText(context, "Không thể xóa ảnh diễn viên: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                        });
                            }
                            // Xóa dữ liệu phim
                            movieRef.removeValue()
                                    .addOnSuccessListener(unused -> {
                                        // Hiển thị thông báo thành công
                                        Toast.makeText(context, "Phim đã được xóa thành công!", Toast.LENGTH_SHORT).show();
                                        // Cập nhật danh sách phim trên giao diện
                                        listMovies.remove(position);
                                        notifyDataSetChanged();
                                    })
                                    .addOnFailureListener(e -> {
                                        Toast.makeText(context, "Xóa phim thất bại: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                                    });
                        }
                    });

                    builder.setNegativeButton("Không", new DialogInterface.OnClickListener() {
                        @Override
                        public void onClick(DialogInterface dialog, int which) {
                            dialog.dismiss(); // Đóng hộp thoại
                        }
                    });
                    builder.create().show();
                }
            });
        }
        Glide.with(context)
                .load(movie.getPosterImage())
                .into(imgPoster);
        return customView;
    }
    // Hàm lấy tên tệp từ URI
    private String getFileNameFromUri(Uri uri) {
        String fileName = "";
        String path = uri.getPath();
        if (path != null) {
            int lastSlash = path.lastIndexOf('/');
            if (lastSlash != -1) {
                fileName = path.substring(lastSlash + 1);
            }
        }
        return fileName;
    }
}
