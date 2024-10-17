package com.example.duanvexemphim;

import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.ListView;
import android.widget.SearchView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanvexemphim.Adapter.ListPhimAdapter;
import com.example.duanvexemphim.Models.ListPhim;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.io.Serializable;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements Serializable {

    ListView lvPhim;
    ArrayList<ListPhim> listPhim;
    ListPhimAdapter adapterPhim;
    Button but;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");
        lvPhim = findViewById(R.id.lvPhim);
        listPhim = new ArrayList<>();
        listPhim.add(new ListPhim("Kinh dị", "Cám", 70000.0, R.drawable.cam));
        listPhim.add(new ListPhim("Hài, Hình sự, Hành động ", "Đố Anh Còng Được Tôi", 65000.0, R.drawable.phim2));
        listPhim.add(new ListPhim("Bí ẩn, Trinh thám, Hình Sự", "Conan", 70000.0, R.drawable.conan));
        listPhim.add(new ListPhim("Tình cảm gia đình", "Hai muối", 65000.0, R.drawable.hai_muoi));
        listPhim.add(new ListPhim("Kinh dị, Hài", "Làm giàu với ma", 60000.0, R.drawable.lam_giau_voi_ma));
        adapterPhim = new ListPhimAdapter(MainActivity.this, R.layout.lv_phim,listPhim );
        lvPhim.setAdapter(adapterPhim);


        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.search_menu, menu);
        MenuItem searchBar = menu.findItem(R.id.searchBar);
        SearchView searchView =(SearchView) searchBar.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                searchView.clearFocus();
                adapterPhim.getFilter().filter(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapterPhim.getFilter().filter(newText);
                return false;
            }
        });
        return super.onCreateOptionsMenu(menu);
    }
}