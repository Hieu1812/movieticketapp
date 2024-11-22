package com.example.duanvexemphim.Adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanvexemphim.R;
import com.example.duanvexemphim.models.TheLoai;

import java.util.List;

public class TheLoaiAdapter extends RecyclerView.Adapter<TheLoaiAdapter.TheLoaiViewHolder> {
    private Context mContext;
    private List<TheLoai> mListTheLoai;

    public TheLoaiAdapter(Context mContext) {
        this.mContext = mContext;
    }

    public void setData(List<TheLoai> list){
        this.mListTheLoai = list;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TheLoaiViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_the_loai, parent, false);
        return new TheLoaiViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TheLoaiViewHolder holder, int position) {
        TheLoai theLoai = mListTheLoai.get(position);
        if (theLoai == null){
            return;
        }

        holder.tvTenTheLoai.setText(theLoai.getTenTheLoai());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(mContext, RecyclerView.HORIZONTAL, false);
        holder.rcvPhim.setLayoutManager(linearLayoutManager);

        PhimAdapter phimAdapter = new PhimAdapter();
        phimAdapter.setData(theLoai.getPhims());
        holder.rcvPhim.setAdapter(phimAdapter);

    }

    @Override
    public int getItemCount() {
        if (mListTheLoai != null){
            return mListTheLoai.size();
        }
        return 0;
    }

    public class TheLoaiViewHolder extends RecyclerView.ViewHolder {

        private TextView tvTenTheLoai;
        private RecyclerView rcvPhim;

        public TheLoaiViewHolder(@NonNull View itemView) {
            super(itemView);

            tvTenTheLoai = itemView.findViewById(R.id.tv_ten_the_loai);
            rcvPhim = itemView.findViewById(R.id.rcv_Phim);
        }
    }
}
