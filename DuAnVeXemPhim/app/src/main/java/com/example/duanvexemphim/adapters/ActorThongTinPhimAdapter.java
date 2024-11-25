package com.example.duanvexemphim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duanvexemphim.R;
import com.example.duanvexemphim.models.Actor;

import java.util.List;

public class ActorThongTinPhimAdapter extends RecyclerView.Adapter<ActorThongTinPhimAdapter.ActorViewHolder> {
    private Context context;
    private List<Actor> actorList;

    public ActorThongTinPhimAdapter(Context context, List<Actor> actorList) {
        this.context = context;
        this.actorList = actorList;
    }

    @Override
    public ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_actor_ttphim, parent, false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActorViewHolder holder, int position) {
        Actor actor = actorList.get(position);
        holder.tvActorThongTinPhim.setText(actor.getActorName());
        Glide.with(context).load(actor.getActorImage()).into(holder.imgActorThongTinPhim);
    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }

    public static class ActorViewHolder extends RecyclerView.ViewHolder {
        TextView tvActorThongTinPhim;
        ImageView imgActorThongTinPhim;

        public ActorViewHolder(View itemView) {
            super(itemView);
            tvActorThongTinPhim = itemView.findViewById(R.id.tvActorName);
            imgActorThongTinPhim = itemView.findViewById(R.id.imgActor);
        }
    }
}

