package com.example.duanvexemphim.Adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duanvexemphim.models.Photo;
import com.example.duanvexemphim.R;

import java.util.List;

public class PhotoAdapter extends RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder> {

    private final List<Photo> mListPhoto;

    public PhotoAdapter(List<Photo> mListPhoto) {
        this.mListPhoto = mListPhoto;
    }



    @NonNull
    @Override
    public PhotoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_photo, parent, false);
        return new PhotoViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PhotoViewHolder holder, int position) {
        Photo photo = mListPhoto.get(position);
        if (photo == null){
            return;
        }
        Glide.with(holder.imgPhoto.getContext()).load(photo.getResourceId()).into(holder.imgPhoto);
    }

    @Override
    public int getItemCount() {
        if (mListPhoto != null){
            return mListPhoto.size();
        }
        return 0;
    }

    public static class PhotoViewHolder extends RecyclerView.ViewHolder{

        private final ImageView imgPhoto;

        public PhotoViewHolder(@NonNull View itemView) {
            super(itemView);
            imgPhoto = itemView.findViewById(R.id.img_photo);
        }
    }
}
