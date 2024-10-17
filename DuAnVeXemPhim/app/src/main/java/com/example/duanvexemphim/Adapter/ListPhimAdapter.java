package com.example.duanvexemphim.Adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Filter;
import android.widget.Filterable;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.ThongTinPhim;
import com.example.duanvexemphim.Models.ListPhim;
import com.example.duanvexemphim.R;

import java.util.ArrayList;

public class ListPhimAdapter extends ArrayAdapter implements Filterable {
    Activity context;
    int resource;
    ArrayList<ListPhim> listPhim, listPhimBackup, listPhimFilter;
    public ListPhimAdapter(Activity context, int resource, ArrayList<ListPhim> listP){
        super(context, resource);
        this.context = context;
        this.resource = resource;
        this.listPhim = this.listPhimBackup = listP;
    }

    @Override
    public int getCount() {
        return listPhim.size();
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = this.context.getLayoutInflater();
        View customView = inflater.inflate(this.resource, null);

        ImageView imgPoster = customView.findViewById(R.id.imgPoster);
        TextView tvName =customView.findViewById(R.id.tvName);
        TextView tvTheLoai =customView.findViewById(R.id.tvTheLoai);
        TextView tvGia =customView.findViewById(R.id.tvGia);
        Button btnXem = customView.findViewById(R.id.btnXem);

        ListPhim phim = this.listPhim.get(position);
        imgPoster.setImageResource(phim.getPosterPhim());
        tvName.setText("Phim: "+phim.getTenPhim());
        tvTheLoai.setText("Thể loại: "+phim.getTheLoai());
        tvGia.setText("Giá: " +phim.getGiaVe()+ "VNĐ ");

        btnXem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String kq = "Phim:" + phim.getTenPhim()+ "\n"
                        + "Thể loại: "+phim.getTheLoai()+ "\n"
                        + "Giá: " + phim.getGiaVe();

                Toast.makeText(context, kq, Toast.LENGTH_LONG).show();
                Intent intent = new Intent(context, ThongTinPhim.class);
                Bundle data = new Bundle();
                data.putString("movieName",phim.getTenPhim());
                intent.putExtras(data);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(intent);


            }
        });

        return  customView;
    }

    @NonNull
    @Override
    public Filter getFilter() {
        return new Filter() {
            @Override
            protected FilterResults performFiltering(CharSequence constraint) {
                String query = constraint.toString().toLowerCase().trim();
                if (query.length() < 1){
                    listPhimFilter = listPhimBackup;
                }else {
                    listPhimFilter = new ArrayList<>();
                    for (ListPhim phim : listPhimBackup){
                        if(phim.getTenPhim().toLowerCase().contains(query) || phim.getTheLoai().toLowerCase().contains(query)){
                            listPhimFilter.add(phim);
                        }
                    }
                }
                FilterResults filterResults = new FilterResults();
                filterResults.values = listPhimFilter;
                return  filterResults;
            }

            @Override
            protected void publishResults(CharSequence contraint, FilterResults results) {
                listPhim = (ArrayList<ListPhim>) results.values;
                notifyDataSetChanged();
            }
        };
    }
}

