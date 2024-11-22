package com.example.duanvexemphim.adapters;

import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duanvexemphim.models.Movie;
import com.example.duanvexemphim.R;

import java.util.List;

public class PhimAdapter extends  RecyclerView.Adapter<PhimAdapter.PhimViewHolder> {

    private List<Movie> mPhim;


    public void setData(List<Movie> list){
        this.mPhim = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PhimViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_phim,parent,false);
        return new PhimViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhimViewHolder holder, int position) {
        Movie movie = mPhim.get(position);
        if (movie == null){
            return;
        }

        Glide.with(holder.imgPhim.getContext()).load(movie.getPosterImage()).into(holder.imgPhim);
        holder.tvTitle.setText(movie.getName());
        holder.itemView.setOnClickListener(view -> {
            Intent intent = new Intent(view.getContext(), ThongTinPhimActivity.class);
            intent.putExtra("movieID", movie.getMovieID());
            intent.putExtra("movieName", movie.getName());
            intent.putExtra("movieGenre", movie.getGenre());
            intent.putExtra("movieDuration", movie.getDurationTime());
            intent.putExtra("movieDescription", movie.getDescription());
            intent.putExtra("movieTrailer", movie.getTrailer());
            intent.putExtra("moviePoster", movie.getPosterImage());
            view.getContext().startActivity(intent);
        });
    }

    @Override
    public int getItemCount() {
        if (mPhim != null){
            return  mPhim.size();
        }
        return 0;
    }

    public class PhimViewHolder extends RecyclerView.ViewHolder {
        private ImageView imgPhim;
        private TextView tvTitle;

        public PhimViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhim = itemView.findViewById(R.id.imgPoster);
            tvTitle = itemView.findViewById(R.id.tvName);

        }


    }
}
