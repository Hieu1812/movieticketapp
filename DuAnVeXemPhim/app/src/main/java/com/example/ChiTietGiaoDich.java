package com.example;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanvexemphim.R;

public class ChiTietGiaoDich extends AppCompatActivity {

    Button btnThoat, btnDongY;
    TextView tvTenPhim, tvSuatChieu, tvSoGhe, tvdoan, tvTongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_giao_dich);
        //
        btnThoat = findViewById(R.id.btnThoat);
        btnDongY = findViewById(R.id.btnDongY);
        tvTenPhim = findViewById(R.id.tvTenPhim);
        tvSuatChieu = findViewById(R.id.tvSuatChieu);
        tvdoan = findViewById(R.id.tvdoan);
        tvSoGhe = findViewById(R.id.tvSoGhe);
        tvTongTien = findViewById(R.id.tvTongTien);
        //
        Intent intent = getIntent();
        String movieNameNo3 = intent.getStringExtra("movieNameNo3");
        String gioChieu2 = intent.getStringExtra("gioChieu2");
        String ghe = intent.getStringExtra("ghe");
        String doAn = intent.getStringExtra("doAn");
        int tongTien = intent.getIntExtra("tongTien", 0);
        //
        if (doAn != null && !doAn.isEmpty()) {
            tvdoan.setText("Đồ ăn: " + doAn);
        } else {
            tvdoan.setText("Đồ ăn: Bạn không mua đồ ăn");
        }
        tvSoGhe.setText("Số ghế: " + ghe);
        tvSuatChieu.setText("Suất chiếu: " + gioChieu2);
        tvTongTien.setText("Tổng tiền: " + tongTien + " VNĐ");
        tvTenPhim.setText("Tên phim: " + movieNameNo3);
        //
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("movieNameNo3", tvTenPhim.getText().toString());
                returnIntent.putExtra("gioChieu2", tvSuatChieu.getText().toString());
                returnIntent.putExtra("ghe", tvSoGhe.getText().toString());
                returnIntent.putExtra("doAn", tvdoan.getText().toString());
                returnIntent.putExtra("tongTien", tvTongTien.getText().toString());
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
        //
        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogDongY = new AlertDialog.Builder(ChiTietGiaoDich.this);
                dialogDongY.setTitle("Xác nhận giao dịch");
                dialogDongY.setMessage("Bạn có chắc chắn đồng ý thanh toán?");

                dialogDongY.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(ChiTietGiaoDich.this, ChiTietGiaoDich2Activity.class);
                        startActivity(intent);
                        finish();
                    }
                });

                dialogDongY.setNegativeButton("Hủy bỏ", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        dialog.cancel();
                    }
                });
                dialogDongY.create().show();
            }
        });
    }
}