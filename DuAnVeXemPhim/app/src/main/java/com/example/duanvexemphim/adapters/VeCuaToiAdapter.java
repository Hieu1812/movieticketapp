package com.example.duanvexemphim.adapters;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.example.duanvexemphim.R;
import com.example.duanvexemphim.models.Movie;
import com.example.duanvexemphim.models.Ticket;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class VeCuaToiAdapter extends ArrayAdapter {
    Context context;
    int resource;
    ArrayList<Ticket> tickets;
    public VeCuaToiAdapter(Context ctx, int res, ArrayList<Ticket> tickets) {
        super(ctx, res);
        this.context = ctx;
        this.resource = res;
        this.tickets = tickets;
    }

    @Override
    public int getCount() {
        return this.tickets.size();
    }
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater layoutInflater = LayoutInflater.from(context);
        View customView = layoutInflater.inflate(resource, null);
        TextView tvNamePhim = customView.findViewById(R.id.tvNamePhim);
        TextView tvNumberGhe = customView.findViewById(R.id.tvNumberGhe);
        TextView tvTime = customView.findViewById(R.id.tvTime);
        ImageView imgPhim = customView.findViewById(R.id.imgPhim);
        ImageView ivQR = customView.findViewById(R.id.ivQR);
        Ticket pticket = tickets.get(position);
        if(pticket != null){
            tvNamePhim.append(pticket.getMovieName());
            tvNumberGhe.append(pticket.getBookedSeats().toString());
            tvTime.append(pticket.getShowTimeID());
            BarcodeEncoder barcodeEncoder = new BarcodeEncoder();
            try {
                Bitmap bitmap = barcodeEncoder.encodeBitmap(pticket.getTicketId(), BarcodeFormat.QR_CODE, 400, 400);
                ivQR.setImageBitmap(bitmap);
            } catch (WriterException e) {
                throw new RuntimeException(e);
            }
            Glide.with(this.getContext())
                    .load(pticket.getMovieID())
                    .into(imgPhim);
        }
        return customView;
    }
}
