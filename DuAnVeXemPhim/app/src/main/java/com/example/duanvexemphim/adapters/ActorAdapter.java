package com.example.duanvexemphim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duanvexemphim.models.Actor;
import com.example.duanvexemphim.R;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.ActorViewHolder> {
    private Context context;
    private List<Actor> actorList;

    public ActorAdapter(Context context, List<Actor> actorList) {
        this.context = context;
        this.actorList = actorList;
    }

    @Override
    public ActorViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_actor, parent, false);
        return new ActorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ActorViewHolder holder, int position) {
        Actor actor = actorList.get(position);
        holder.tvActorName.setText(actor.getActorName());
        Glide.with(context).load(actor.getActorImage()).into(holder.ivActor);
    }

    @Override
    public int getItemCount() {
        return actorList.size();
    }

    public static class ActorViewHolder extends RecyclerView.ViewHolder {
        TextView tvActorName;
        ImageView ivActor;

        public ActorViewHolder(View itemView) {
            super(itemView);
            tvActorName = itemView.findViewById(R.id.tvActorName);
            ivActor = itemView.findViewById(R.id.ivActor);
        }
    }
}
