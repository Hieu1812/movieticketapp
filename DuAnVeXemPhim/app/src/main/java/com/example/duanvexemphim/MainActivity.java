package com.example.duanvexemphim;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.view.MenuItem;
import android.view.View;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager2.widget.CompositePageTransformer;
import androidx.viewpager2.widget.MarginPageTransformer;
import androidx.viewpager2.widget.ViewPager2;

import com.example.PhimYeuThich;
import com.example.TaiKhoanCuaToi;
import com.example.VeCuaToi;
import com.example.duanvexemphim.Adapter.PhotoAdapter;
import com.example.duanvexemphim.Adapter.TheLoaiAdapter;
import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mViewPager2;
    private CircleIndicator3 mCircleIndicator3;
    private List<Photo> mListPhoto;
    private RecyclerView rcvTheLoai;
    private TheLoaiAdapter theLoaiAdapter;
    BottomNavigationView bottomNavigationView;

    private Handler mHandler = new Handler(Looper.getMainLooper());
    private Runnable mRunnable = new Runnable() {
        @Override
        public void run() {
            int currentPosition = mViewPager2.getCurrentItem();
            if (currentPosition == mListPhoto.size()-1){
                mViewPager2.setCurrentItem(0);
            }else {
                mViewPager2.setCurrentItem(currentPosition +1);
            }
        }
    };

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        mViewPager2 = findViewById(R.id.view_page2);
        mCircleIndicator3 = findViewById(R.id.cicle_indicator_3);

        rcvTheLoai = findViewById(R.id.rcv_TheLoai);
        theLoaiAdapter = new TheLoaiAdapter(this);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvTheLoai.setLayoutManager(linearLayoutManager);

        theLoaiAdapter.setData(getListTheLoai());
        rcvTheLoai.setAdapter(theLoaiAdapter);

        //setting viewPager2
        mViewPager2.setOffscreenPageLimit(3);
        mViewPager2.setClipToPadding(false);
        mViewPager2.setClipChildren(false);
        CompositePageTransformer compositePageTransformer = new CompositePageTransformer();
        compositePageTransformer.addTransformer(new MarginPageTransformer(40));
        compositePageTransformer.addTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float r = 1- Math.abs(position);
                page.setScaleY(0.85f + (r*0.15f));
            }
        });
        mViewPager2.setPageTransformer(compositePageTransformer);

        mListPhoto = getListPhpto();

        PhotoAdapter photoAdapter = new PhotoAdapter(mListPhoto);
        mViewPager2.setAdapter(photoAdapter);

        mCircleIndicator3.setViewPager(mViewPager2);

        mViewPager2.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mHandler.removeCallbacks(mRunnable);
                mHandler.postDelayed(mRunnable, 3000);
            }
        });
        bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnNavigationItemSelectedListener(new BottomNavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                if (item.getItemId() == R.id.home) {
                    Intent intent = new Intent(MainActivity.this, MainActivity.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.ticket) {
                    Intent intent = new Intent(MainActivity.this, VeCuaToi.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.favorite) {
                    Intent intent = new Intent(MainActivity.this, PhimYeuThich.class);
                    startActivity(intent);
                    return true;
                } else if (item.getItemId() == R.id.account) {
                    Intent intent = new Intent(MainActivity.this, TaiKhoanCuaToi.class);
                    startActivity(intent);
                    return true;
                }
                return false;
            }
        });


    }

    private List<TheLoai> getListTheLoai(){
        List<TheLoai> listTheLoai = new ArrayList<>();

        //phim nổi bật
        List<Phim> listPhim1 = new ArrayList<>();
        listPhim1.add(new Phim(R.drawable.cam, "Cám"));
        listPhim1.add(new Phim(R.drawable.phim2, "Đố anh còng được tôi"));
        listPhim1.add(new Phim(R.drawable.hai_muoi, "Hai muối"));
        listPhim1.add(new Phim(R.drawable.minh_hon, "Minh hôn"));
        listPhim1.add(new Phim(R.drawable.lam_giau_voi_ma, "Làm giàu với ma"));

        listTheLoai.add(new TheLoai("Phim nổi bật", listPhim1));

        //phim kinh dị
        List<Phim> listPhim2 = new ArrayList<>();
        listPhim2.add(new Phim(R.drawable.cam, "Cám"));
        listPhim2.add(new Phim(R.drawable.lam_giau_voi_ma, "Làm giàu với ma"));

        listTheLoai.add(new TheLoai("Phim kinh dị", listPhim2));

        //phim hành động
        List<Phim> listPhim3 = new ArrayList<>();
        listPhim3.add(new Phim(R.drawable.phim2, "Đố anh còng được tôi"));

        listTheLoai.add(new TheLoai("Phim hành động", listPhim3));

        //phim tình cảm
        List<Phim> listPhim4 = new ArrayList<>();
        listPhim4.add(new Phim(R.drawable.hai_muoi, "Đố anh còng được tôi"));

        listTheLoai.add(new TheLoai("Phim tình cảm", listPhim4));

        return listTheLoai;
    }



    private List<Photo> getListPhpto(){
        List<Photo> list = new ArrayList<>();
        list.add(new Photo(R.drawable.cam));
        list.add(new Photo(R.drawable.hai_muoi));
        list.add(new Photo(R.drawable.phim2));

        return list;
    }

    @Override
    protected void onPause() {
        super.onPause();
        mHandler.removeCallbacks(mRunnable);
    }

    @Override
    protected void onResume() {
        super.onResume();
        mHandler.postDelayed(mRunnable, 3000);
    }
}