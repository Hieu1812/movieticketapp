package com.example.duanvexemphim.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanvexemphim.Phim;
import com.example.duanvexemphim.R;

import java.util.List;

public class PhimAdapter extends  RecyclerView.Adapter<PhimAdapter.PhimViewHolder> {

    private List<Phim> mPhim;

    public void setData(List<Phim> list){
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
        Phim phim = mPhim.get(position);
        if (phim == null){
            return;
        }

        holder.imgPhim.setImageResource(phim.getResourceId());
        holder.tvTitle.setText(phim.getTitle());

    }

    @Override
    public int getItemCount() {
        if (mPhim != null){
            return  mPhim.size();
        }
        return 0;
    }

    public  class PhimViewHolder extends RecyclerView.ViewHolder{
        private ImageView imgPhim;
        private TextView tvTitle;

        public PhimViewHolder(@NonNull View itemView) {
            super(itemView);

            imgPhim = itemView.findViewById(R.id.imgPoster);
            tvTitle = itemView.findViewById(R.id.tvName);
        }
    }
}
