package com.example.duanvexemphim.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.duanvexemphim.R;
import com.example.duanvexemphim.models.Ticket;

import java.util.ArrayList;
import java.util.List;

public class VeCuaToiAdapter extends RecyclerView.Adapter<VeCuaToiAdapter.VeCuaToiViewHolder> {
    private Context context;
    private List<Ticket> tickets;
    public VeCuaToiAdapter(Context context) {
        this.context = context;
    }

    public void setTickets(List<Ticket> tickets) {
        this.tickets = tickets;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public VeCuaToiAdapter.VeCuaToiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_ve_cua_toi, parent, false);
        return new VeCuaToiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull VeCuaToiAdapter.VeCuaToiViewHolder holder, int position) {
        Ticket ticket = tickets.get(position);
        if (ticket == null){
            return;
        }
//        holder.tvName.append(ticket.getMovie().getName());
        holder.tvTime.setText(ticket.getShowTimeID());
        holder.tvTicketID.setText(ticket.getTicketId());
//        Glide.with(context).load(ticket.getMovie().getPosterImage()).into(holder.Poster);
    }

    @Override
    public int getItemCount() {
        return 0;
    }
    public class VeCuaToiViewHolder extends RecyclerView.ViewHolder {
        ImageView Poster;
        TextView tvName,tvSoGhe,tvTime,tvTicketID;
        public VeCuaToiViewHolder(@NonNull View itemView) {
            super(itemView);
            Poster = itemView.findViewById(R.id.imgPoster);
            tvName = itemView.findViewById(R.id.tvTenPhim);
            tvSoGhe = itemView.findViewById(R.id.tvSoGhe);
            tvTime = itemView.findViewById(R.id.tvThoiGian);
            tvTicketID = itemView.findViewById(R.id.tvMaVe);
        }
    }
}
