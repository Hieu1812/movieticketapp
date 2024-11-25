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
import com.example.duanvexemphim.adapters.PhotoAdapter;
import com.example.duanvexemphim.adapters.TheLoaiAdapter;
import com.example.duanvexemphim.models.Actor;
import com.example.duanvexemphim.models.Movie;
import com.example.duanvexemphim.models.Photo;
import com.example.duanvexemphim.models.TheLoai;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;

import java.util.ArrayList;
import java.util.List;

import me.relex.circleindicator.CircleIndicator3;

public class MainActivity extends AppCompatActivity {

    private ViewPager2 mViewPager2;
    private CircleIndicator3 mCircleIndicator3;
    private List<Photo> mListPhoto;
    private RecyclerView rcvTheLoai;
    private TheLoaiAdapter theLoaiAdapter;
    private PhotoAdapter photoAdapter;
    private FirebaseStorage firebaseStorage;
    private StorageReference storageReference;
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

        photoAdapter = new PhotoAdapter(mListPhoto);
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
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Movies");

        List<TheLoai> listTheLoai = new ArrayList<>();

        //phim nổi bật
        List<Movie> listMovie1 = new ArrayList<>();
//        listPhim1.add(new Phim(R.drawable.cam, "Cám"));
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String movieID = dataSnapshot.child("movieID").getValue(String.class);
                    String name =  dataSnapshot.child("name").getValue(String.class);
                    String img = dataSnapshot.child("posterImage").getValue(String.class);
                    String description = dataSnapshot.child("description").getValue(String.class);
                    String durationTime = dataSnapshot.child("durationTime").getValue(String.class);
                    String genre = dataSnapshot.child("genre").getValue(String.class);
                    String trailer = dataSnapshot.child("trailer").getValue(String.class);
                    Double vote = dataSnapshot.child("vote").getValue(Double.class);
                    ArrayList<Actor> actorList = new ArrayList<>();
                    DataSnapshot actorsSnapshot = dataSnapshot.child("actorList");
                    if (actorsSnapshot.exists()) {
                        for (DataSnapshot actorSnapshot : actorsSnapshot.getChildren()) {
                            String actorName = actorSnapshot.child("actorName").getValue(String.class);
                            String actorImage = actorSnapshot.child("actorImage").getValue(String.class);
                            Actor actor = new Actor(actorName, actorImage);
                            actorList.add(actor);
                        }
                    }
                    listMovie1.add(new Movie(movieID, name, img, description, genre, durationTime, new ArrayList<>(), trailer, vote, actorList));
                }
                theLoaiAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        listTheLoai.add(new TheLoai("Phim nổi bật", listMovie1));


        //phim kinh dị
        List<Movie> listMovie2 = new ArrayList<>();
        filter("Kinh dị", listMovie2);

        listTheLoai.add(new TheLoai("Phim kinh dị", listMovie2));

        //phim hành động
        List<Movie> listMovie3 = new ArrayList<>();
        filter("Hành động", listMovie3);

        listTheLoai.add(new TheLoai("Phim hành động", listMovie3));

        //phim tình cảm
        List<Movie> listMovie4 = new ArrayList<>();
        filter("Tình cảm", listMovie4);

        listTheLoai.add(new TheLoai("Phim tình cảm", listMovie4));

        return listTheLoai;
    }

    public void filter(String name, List<Movie> listPhim){
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Movies");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot : snapshot.getChildren()){
                    String gernes =  dataSnapshot.child("genre").getValue(String.class);
                    if (gernes.equals(name)){
                        String movieID = dataSnapshot.child("movieID").getValue(String.class);
                        String name =  dataSnapshot.child("name").getValue(String.class);
                        String img = dataSnapshot.child("posterImage").getValue(String.class);
                        String description = dataSnapshot.child("description").getValue(String.class);
                        String durationTime = dataSnapshot.child("durationTime").getValue(String.class);
                        String genre = dataSnapshot.child("genre").getValue(String.class);
                        String trailer = dataSnapshot.child("trailer").getValue(String.class);
                        Double vote = dataSnapshot.child("vote").getValue(Double.class);
                        listPhim.add(new Movie(movieID, name, img, description, genre, durationTime, new ArrayList<>(), trailer, vote, new ArrayList<>()));
                    }
                }
                theLoaiAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }


    private List<Photo> getListPhpto(){
        List<Photo> list = new ArrayList<>();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Movies");
        db.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    String img = dataSnapshot.child("posterImage").getValue(String.class);
                    list.add(new Photo(img));
                }
                photoAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

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