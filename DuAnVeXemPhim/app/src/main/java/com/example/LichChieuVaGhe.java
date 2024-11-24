package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanvexemphim.R;
import com.example.duanvexemphim.adapters.ThongTinPhimActivity;
import com.example.duanvexemphim.rap_va_lich_chieu;

public class LichChieuVaGhe extends AppCompatActivity {

    TextView tvGioChieu;
    Button btnThoat, btnThanhToan;
    Button  btnNuocCam, btnPepsi, btnBong, btnH1A, btnH1B, btnH1C, btnH2A, btnH2B,
            btnH2C, btnH3A, btnH3B, btnH3C, btnH4A, btnH4B, btnH4C, btnH5A, btnH5B, btnH5C;
    private boolean clickH1A, clickH1B, clickH1C, clickH2A, clickH2B, clickH2C, clickH3A, clickH3B, clickH3C,
            clickH4A, clickH4B, clickH4C, clickH5A, clickH5B, clickH5C, clickNuocCam, clickPepsi, clickBong = false;
    String doAnDaChon = "";
    String gheDaChon = "";
    int tongTien = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_lich_chieu_va_ghe);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        tvGioChieu = findViewById(R.id.tvGioChieu);
        btnThoat = findViewById(R.id.btnThoat);
        btnThanhToan = findViewById(R.id.btnThanhToan);
        btnNuocCam = findViewById(R.id.btnNuocCam);
        btnPepsi = findViewById(R.id.btnPepsi);
        btnBong = findViewById(R.id.btnBong);
        btnH1A = findViewById(R.id.btnH1A);
        btnH1B = findViewById(R.id.btnH1B);
        btnH1C = findViewById(R.id.btnH1C);
        btnH2A = findViewById(R.id.btnH2A);
        btnH2B = findViewById(R.id.btnH2B);
        btnH2C = findViewById(R.id.btnH2C);
        btnH3A = findViewById(R.id.btnH3A);
        btnH3B = findViewById(R.id.btnH3B);
        btnH3C = findViewById(R.id.btnH3C);
        btnH4A = findViewById(R.id.btnH4A);
        btnH4B = findViewById(R.id.btnH4B);
        btnH4C = findViewById(R.id.btnH4C);
        btnH5A = findViewById(R.id.btnH5A);
        btnH5B = findViewById(R.id.btnH5B);
        btnH5C = findViewById(R.id.btnH5C);
        //
        Intent intent = getIntent();
        String gioChieu = intent.getStringExtra("GioChieu");
        String movieNameNo2 = intent.getStringExtra("movieNameNo2");
        if (gioChieu != null) {
            tvGioChieu.setText(gioChieu);
        }
        //
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("movieNameNo1", movieNameNo2.toString());
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (gheDaChon != null && !gheDaChon.isEmpty()) {
                    Intent intent = new Intent(LichChieuVaGhe.this, ChiTietGiaoDich.class);
                    intent.putExtra("doAn", doAnDaChon);
                    intent.putExtra("ghe", gheDaChon);
                    intent.putExtra("tongTien", tongTien);
                    intent.putExtra("gioChieu2", gioChieu);
                    intent.putExtra("movieNameNo3", movieNameNo2);
                    startActivity(intent);
                } else {
                    Toast.makeText(LichChieuVaGhe.this, "Vui lòng chọn ghế ngồi", Toast.LENGTH_SHORT).show();
                }
            }
        });
        //
        btnH1A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH1A) {
                    gheDaChon = gheDaChon.replace("H1A, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H1A", Toast.LENGTH_SHORT).show();
                    btnH1A.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH1A = false;
                } else {
                    gheDaChon += "H1A, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H1A", Toast.LENGTH_SHORT).show();
                    btnH1A.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH1A = true;
                }
            }
        });
        btnH1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH1B) {
                    gheDaChon = gheDaChon.replace("H1B, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H1B", Toast.LENGTH_SHORT).show();
                    btnH1B.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH1B = false;
                } else {
                    gheDaChon += "H1B, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H1B", Toast.LENGTH_SHORT).show();
                    btnH1B.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH1B = true;
                }
            }
        });
        btnH1C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH1C) {
                    gheDaChon = gheDaChon.replace("H1C, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H1C", Toast.LENGTH_SHORT).show();
                    btnH1C.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH1C = false;
                } else {
                    gheDaChon += "H1C, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H1C", Toast.LENGTH_SHORT).show();
                    btnH1C.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH1C = true;
                }
            }
        });
        btnH2A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH2A) {
                    gheDaChon = gheDaChon.replace("H2A, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H2A", Toast.LENGTH_SHORT).show();
                    btnH2A.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH2A = false;
                } else {
                    gheDaChon += "H2A, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H2A", Toast.LENGTH_SHORT).show();
                    btnH2A.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH2A = true;
                }
            }
        });
        btnH2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH2B) {
                    gheDaChon = gheDaChon.replace("H2B, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H2B", Toast.LENGTH_SHORT).show();
                    btnH2B.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH2B = false;
                } else {
                    gheDaChon += "H2B, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H2B", Toast.LENGTH_SHORT).show();
                    btnH2B.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH2B = true;
                }
            }
        });
        btnH2C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH2C) {
                    gheDaChon = gheDaChon.replace("H2C, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H2C", Toast.LENGTH_SHORT).show();
                    btnH2C.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH2C = false;
                } else {
                    gheDaChon += "H2C, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H2C", Toast.LENGTH_SHORT).show();
                    btnH2C.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH2C = true;
                }
            }
        });
        btnH3A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH3A) {
                    gheDaChon = gheDaChon.replace("H3A, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H3A", Toast.LENGTH_SHORT).show();
                    btnH3A.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH3A = false;
                } else {
                    gheDaChon += "H3A, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H3A", Toast.LENGTH_SHORT).show();
                    btnH3A.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH3A = true;
                }
            }
        });
        btnH3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH3B) {
                    gheDaChon = gheDaChon.replace("H3B, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H3B", Toast.LENGTH_SHORT).show();
                    btnH3B.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH3B = false;
                } else {
                    gheDaChon += "H3B, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H3B", Toast.LENGTH_SHORT).show();
                    btnH3B.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH3B = true;
                }
            }
        });
        btnH3C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH3C) {
                    gheDaChon = gheDaChon.replace("H3C, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H3C", Toast.LENGTH_SHORT).show();
                    btnH3C.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH3C = false;
                } else {
                    gheDaChon += "H3C, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H3C", Toast.LENGTH_SHORT).show();
                    btnH3C.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH3C = true;
                }
            }
        });
        btnH4A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH4A) {
                    gheDaChon = gheDaChon.replace("H4A, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H4A", Toast.LENGTH_SHORT).show();
                    btnH4A.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH4A = false;
                } else {
                    gheDaChon += "H4A, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H4A", Toast.LENGTH_SHORT).show();
                    btnH4A.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH4A = true;
                }
            }
        });
        btnH4B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH4B) {
                    gheDaChon = gheDaChon.replace("H4B, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H4B", Toast.LENGTH_SHORT).show();
                    btnH4B.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH4B = false;
                } else {
                    gheDaChon += "H4B, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H4B", Toast.LENGTH_SHORT).show();
                    btnH4B.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH4B = true;
                }
            }
        });
        btnH4C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH4C) {
                    gheDaChon = gheDaChon.replace("H4C, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H4C", Toast.LENGTH_SHORT).show();
                    btnH4C.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH4C = false;
                } else {
                    gheDaChon += "H4C, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H4C", Toast.LENGTH_SHORT).show();
                    btnH4C.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH4C = true;
                }
            }
        });
        btnH5A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH5A) {
                    gheDaChon = gheDaChon.replace("H5A, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H5A", Toast.LENGTH_SHORT).show();
                    btnH5A.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH5A = false;
                } else {
                    gheDaChon += "H5A, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H5A", Toast.LENGTH_SHORT).show();
                    btnH5A.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH5A = true;
                }
            }
        });
        btnH5B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH5B) {
                    gheDaChon = gheDaChon.replace("H5B, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H5B", Toast.LENGTH_SHORT).show();
                    btnH5B.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH5B = false;
                } else {
                    gheDaChon += "H5B, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H5B", Toast.LENGTH_SHORT).show();
                    btnH5B.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH5B = true;
                }
            }
        });
        btnH5C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickH5C) {
                    gheDaChon = gheDaChon.replace("H5C, ", "");
                    tongTien -= 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn ghế H5C", Toast.LENGTH_SHORT).show();
                    btnH5C.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickH5C = false;
                } else {
                    gheDaChon += "H5C, ";
                    tongTien += 50000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn ghế H5C", Toast.LENGTH_SHORT).show();
                    btnH5C.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickH5C = true;
                }
            }
        });
        btnNuocCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickNuocCam) {
                    doAnDaChon = doAnDaChon.replace("Nước cam, ", "");
                    tongTien -= 30000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn Nước cam", Toast.LENGTH_SHORT).show();
                    btnNuocCam.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickNuocCam = false;
                } else {
                    doAnDaChon += "Nước cam, ";
                    tongTien += 30000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn Nước cam", Toast.LENGTH_SHORT).show();
                    btnNuocCam.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickNuocCam = true;
                }
            }
        });
        btnPepsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickPepsi) {
                    doAnDaChon = doAnDaChon.replace("Pepsi, ", "");
                    tongTien -= 30000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn Pepsi", Toast.LENGTH_SHORT).show();
                    btnPepsi.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickPepsi = false;
                } else {
                    doAnDaChon += "Pepsi, ";
                    tongTien += 30000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn Pepsi", Toast.LENGTH_SHORT).show();
                    btnPepsi.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickPepsi = true;
                }
            }
        });

        btnBong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (clickBong) {
                    doAnDaChon = doAnDaChon.replace("Bỏng, ", "");
                    tongTien -= 40000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã bỏ chọn Bỏng", Toast.LENGTH_SHORT).show();
                    btnBong.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    clickBong = false;
                } else {
                    doAnDaChon += "Bỏng, ";
                    tongTien += 40000;
                    Toast.makeText(LichChieuVaGhe.this, "Bạn đã chọn Bỏng", Toast.LENGTH_SHORT).show();
                    btnBong.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    clickBong = true;
                }
            }
        });
    }
}