package com.example.duanvexemphim.Quanliphim;

import android.content.Context;
import android.graphics.Movie;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanvexemphim.R;

import java.util.List;

public class MovieAdapter extends RecyclerView.Adapter<MovieAdapter.MovieViewHolder> {
    private List<Movie> movies;
    private Context context;

    public MovieAdapter(Context context, List<Movie> movies) {
        this.context = context;
        this.movies = movies;
    }

    @NonNull
    @Override
    public MovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.danh_sach_phim, parent, false);
        return new MovieViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MovieViewHolder holder, int position) {
        Movie movie = movies.get(position);
//        holder.tvMovieTitle.setText(movie.getTitle());
//        holder.tvMovieGenre.setText(movie.getGenre());

        // Xử lý sự kiện nhấn nút "Sửa"
        holder.btnEdit.setOnClickListener(v -> {
            // Code để sửa phim
            // Ví dụ: Chuyển sang màn hình sửa phim và truyền thông tin phim
        });

        // Xử lý sự kiện nhấn nút "Xóa"
        holder.btnDelete.setOnClickListener(v -> {
            // Code để xóa phim khỏi danh sách
            movies.remove(position);
            notifyItemRemoved(position);
            notifyItemRangeChanged(position, movies.size());
        });
    }

    @Override
    public int getItemCount() {
        return movies.size();
    }

    static class MovieViewHolder extends RecyclerView.ViewHolder {
        TextView tvMovieTitle;
        TextView tvMovieGenre;
        Button btnEdit;
        Button btnDelete;

        public MovieViewHolder(@NonNull View itemView) {
            super(itemView);
            tvMovieTitle = itemView.findViewById(R.id.tvMovieTitle);
            tvMovieGenre = itemView.findViewById(R.id.tvMovieGenre);
            btnEdit = itemView.findViewById(R.id.btnEdit);
            btnDelete = itemView.findViewById(R.id.btnDelete);
        }
    }
}
