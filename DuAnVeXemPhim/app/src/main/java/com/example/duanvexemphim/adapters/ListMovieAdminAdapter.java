package com.example.duanvexemphim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.duanvexemphim.R;
import com.example.duanvexemphim.models.Movie;

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
        Button btnSua = customView.findViewById(R.id.btnSua);
        Button btnXoa = customView.findViewById(R.id.btnXoa);

        Movie movie = listMovies.get(position);

        tvName.setText("Phim: " + movie.getName());
        tvTheLoai.setText("Thể loại: " + movie.getGenre());
        tvGia.setText("Giá: 50.000 VNĐ");

        Glide.with(context)
                .load(movie.getPosterImage())
                .into(imgPoster);
        return customView;
    }
}