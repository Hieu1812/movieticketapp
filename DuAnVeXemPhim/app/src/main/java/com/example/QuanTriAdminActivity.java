package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ListView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanvexemphim.Adapter.ListPhimAdapter;
import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.Models.ListPhim;
import com.example.duanvexemphim.R;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;

public class QuanTriAdminActivity extends AppCompatActivity {

    BottomNavigationView bottomNavigationViewAdmin;
    FloatingActionButton themPhim;
    ListView lvPhim;
    ArrayList<ListPhim> listPhim;
    ListPhimAdapter adapterPhim;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_quan_tri_admin);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        //
        bottomNavigationViewAdmin = findViewById(R.id.bottomNavigationViewAdmin);
        themPhim = findViewById(R.id.themPhim);
        lvPhim = findViewById(R.id.lvPhim);
        //
        listPhim = new ArrayList<>();
        listPhim.add(new ListPhim("Kinh dị", "Cám", 70000.0, R.drawable.cam));
        listPhim.add(new ListPhim("Hài, Hình sự, Hành động ", "Đố Anh Còng Được Tôi", 65000.0, R.drawable.phim2));
        listPhim.add(new ListPhim("Bí ẩn, Trinh thám, Hình Sự", "Conan", 70000.0, R.drawable.conan));
        listPhim.add(new ListPhim("Tình cảm gia đình", "Hai muối", 65000.0, R.drawable.hai_muoi));
        listPhim.add(new ListPhim("Kinh dị, Hài", "Làm giàu với ma", 60000.0, R.drawable.lam_giau_voi_ma));
        adapterPhim = new ListPhimAdapter(QuanTriAdminActivity.this, R.layout.lv_phim, listPhim);
        lvPhim.setAdapter(adapterPhim);

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        themPhim.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(QuanTriAdminActivity.this, ThemPhimActivity.class);
                startActivity(intent);
            }
        });

        bottomNavigationViewAdmin.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.homeAdmin) {
                    Intent intent = new Intent(QuanTriAdminActivity.this, QuanTriAdminActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.revenueAdmin) {
                    Intent intent = new Intent(QuanTriAdminActivity.this, QuanTriAdminActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.accountAdmin) {
                    Intent intent = new Intent(QuanTriAdminActivity.this, QuanTriAdminActivity.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });
    }
}