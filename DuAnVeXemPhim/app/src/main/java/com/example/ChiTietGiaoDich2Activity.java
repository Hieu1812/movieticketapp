package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.R;
import com.example.duanvexemphim.gui_mail;

public class ChiTietGiaoDich2Activity extends AppCompatActivity {

    TextView tvTenPhim, tvSuatChieu, tvTenRap, tvSoGhe, tvPTThanhToan, tvSoTien;
    Button btnTrangChu, btnGuiEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_giao_dich2);
        //
        tvTenPhim = findViewById(R.id.tvTenPhim);
        tvSuatChieu = findViewById(R.id.tvSuatChieu);
        tvTenRap = findViewById(R.id.tvTenRap);
        tvSoGhe = findViewById(R.id.tvSoGhe);
        tvPTThanhToan = findViewById(R.id.tvPTThanhToan);
        tvSoTien = findViewById(R.id.tvSoTien);
        btnTrangChu = findViewById(R.id.btnTrangChu);
        btnGuiEmail = findViewById(R.id.btnGuiEmail);
        //
        Intent intent = getIntent();
        String movieNameNo3 = intent.getStringExtra("movieNameNo3");
        String gioChieu2 = intent.getStringExtra("gioChieu2");
        String ghe = intent.getStringExtra("ghe");
        String doAn = intent.getStringExtra("doAn");
        int tongTien = intent.getIntExtra("tongTien", 0);
        String userID = intent.getStringExtra("userID");
        //
        tvTenPhim.setText("Tên phim: " + movieNameNo3);
        tvSuatChieu.setText("Suất chiếu: " + gioChieu2);
        tvSoGhe.setText("Số ghế: " + ghe);
        tvSoTien.setText("Tổng tiền: " + tongTien + " VNĐ");

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //
        btnTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTrangChu = new Intent(ChiTietGiaoDich2Activity.this, MainActivity.class);
                startActivity(intentTrangChu);
            }
        });
        //
        btnGuiEmail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Gửi email xác nhận giao dịch
                Intent emailIntent = new Intent(ChiTietGiaoDich2Activity.this, gui_mail.class);
                emailIntent.putExtra("ghe1", ghe);
                emailIntent.putExtra("tongTien1", tongTien);
                emailIntent.putExtra("gioChieu3", gioChieu2);
                emailIntent.putExtra("movieNameNo4", movieNameNo3);
                startActivity(emailIntent);
            }
        });
    }
}