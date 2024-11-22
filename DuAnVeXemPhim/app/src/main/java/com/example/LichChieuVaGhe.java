package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanvexemphim.R;
import com.example.duanvexemphim.adapters.ThongTinPhimActivity;

public class LichChieuVaGhe extends AppCompatActivity {

    Button btnThoat, btnThanhToan, btnNuocCam, btnPepsi, btnBong;
    Button  btnH1A, btnH1B, btnH1C, btnH2A, btnH2B, btnH2C, btnH3A, btnH3B, btnH3C, btnH4A, btnH4B, btnH4C, btnH5A, btnH5B, btnH5C;
    private boolean click = false;

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
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentThoat = new Intent(LichChieuVaGhe.this, ThongTinPhimActivity.class);
                startActivity(intentThoat);
            }
        });
        btnThanhToan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentThanhToan = new Intent(LichChieuVaGhe.this, ChiTietGiaoDich.class);
                startActivity(intentThanhToan);
            }
        });
        //
        btnH1A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH1A.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH1A.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnH1B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH1B.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH1B.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnH1C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH1C.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH1C.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnH2A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH2A.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH2A.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnH2B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH2B.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH2B.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnH2C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH2C.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH2C.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnH3A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH3A.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH3A.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnH3B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH3B.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH3B.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnH3C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH3C.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH3C.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnH4A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH4A.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH4A.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnH4B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH4B.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH4B.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnH4C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH4C.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH4C.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnH5A.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH5A.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH5A.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnH5B.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH5B.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH5B.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnH5C.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnH5C.setBackgroundTintList(getResources().getColorStateList(R.color.colorgreen));
                    click = false;
                } else {
                    btnH5C.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnNuocCam.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnNuocCam.setBackgroundTintList(getResources().getColorStateList(R.color.colororange));
                    click = false;
                } else {
                    btnNuocCam.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnPepsi.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnPepsi.setBackgroundTintList(getResources().getColorStateList(R.color.colororange));
                    click = false;
                } else {
                    btnPepsi.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
        btnBong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (click) {
                    btnBong.setBackgroundTintList(getResources().getColorStateList(R.color.colororange));
                    click = false;
                } else {
                    btnBong.setBackgroundTintList(getResources().getColorStateList(R.color.colorblue));
                    click = true;
                }
            }
        });
    }
}