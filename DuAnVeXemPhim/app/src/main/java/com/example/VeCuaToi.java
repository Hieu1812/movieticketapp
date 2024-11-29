package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.R;
import com.example.duanvexemphim.adapters.VeCuaToiAdapter;
import com.example.duanvexemphim.models.Movie;
import com.example.duanvexemphim.models.Ticket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Objects;

public class VeCuaToi extends AppCompatActivity {
    ArrayList<Ticket> tickets;
    VeCuaToiAdapter adapter;
    Button btnThoat;
    ListView lvVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_ve_cua_toi);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        btnThoat = findViewById(R.id.btnThoat);
        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(VeCuaToi.this, MainActivity.class);
                startActivity(intent);
            }
        });
        lvVe = findViewById(R.id.lvListVe);
        tickets = new ArrayList<>();
        adapter = new VeCuaToiAdapter(VeCuaToi.this, R.layout.item_ve_cua_toi, tickets);
        lvVe.setAdapter(adapter);
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Tickets");
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                tickets.clear();
                Log.d("VeCuaToi", "Data snapshot: " + snapshot.getValue());
                for (DataSnapshot dataSnapshot : snapshot.getChildren()) {
                    String curID = dataSnapshot.child("userID").getValue(String.class);
                    if (curID.equals(id)) {
                        String movieName = dataSnapshot.child("movieName").getValue(String.class); // Lấy movieName
                        String ticketID = dataSnapshot.child("ticketId").getValue(String.class);
                        String showTimeID = dataSnapshot.child("showTimeID").getValue(String.class);
                        Integer ticketPrice = dataSnapshot.child("ticketPrice").getValue(Integer.class);
                        String paymentStatus = dataSnapshot.child("paymentStatus").getValue(String.class);
                        List<String> seats = (List<String>) dataSnapshot.child("bookedSeats").getValue();
                        String movieID = dataSnapshot.child("movieID").getValue(String.class);
                        if (movieID!=null){
                            DatabaseReference img = FirebaseDatabase.getInstance().getReference("Movies").child(movieID);
                            img.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot snapshot) {
                                    String imgpath = snapshot.child("posterImage").getValue(String.class);
                                    tickets.add(new Ticket(ticketID, curID, showTimeID, ticketPrice, paymentStatus, seats, movieName, imgpath));
                                    adapter.notifyDataSetChanged();
                                }
                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {
                                    Log.e("Firebase", "Error fetching movie details: " + error.getMessage());
                                }
                            });
                        }
                    }
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
            }
        });

    }
}
