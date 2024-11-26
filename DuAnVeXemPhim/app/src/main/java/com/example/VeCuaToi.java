package com.example;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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
import java.util.List;
import java.util.Objects;

public class VeCuaToi extends AppCompatActivity {
    ArrayList<Ticket> tickets;
    VeCuaToiAdapter adapter;
    Button btnThoat;
    String ten, poster;
    RecyclerView rcvTicket;


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

        rcvTicket = findViewById(R.id.recyclerViewViewed);
        adapter = new VeCuaToiAdapter(this);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this, RecyclerView.VERTICAL, false);
        rcvTicket.setLayoutManager(linearLayoutManager);

        adapter.setTickets(getTicket());
        rcvTicket.setAdapter(adapter);


    }
    public List<Ticket> getTicket(){
        String id = FirebaseAuth.getInstance().getCurrentUser().getUid();
        DatabaseReference db = FirebaseDatabase.getInstance().getReference("Tickets");
        List<Ticket> ve = new ArrayList<>();
        db.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    if (id.equals(dataSnapshot.child("userID").getValue(String.class))) {
                        Toast.makeText(VeCuaToi.this,"co ve", Toast.LENGTH_SHORT).show();
                        String movieid = dataSnapshot.child("movieID").getValue(String.class);
//                        List<Movie> mv = new ArrayList<>();
//                        getmovie(movieid, mv);
                        String ticketID = dataSnapshot.child("ticketID").getValue(String.class);
                        String showTimeID = dataSnapshot.child("showTimeID").getValue(String.class);
//                        String userID = dataSnapshot.child("userID").getValue(String.class);
                        Integer ticketPrice = dataSnapshot.child("ticketPrice").getValue(Integer.class);
                        Integer totalAmount = dataSnapshot.child("totalAmount").getValue(Integer.class);
                        String paymentStatus = dataSnapshot.child("paymentStatus").getValue(String.class);
//                        String showtime = dataSnapshot.child("showtime").getValue(String.class);
                        List seats = dataSnapshot.child("seats").getValue(List.class);
                        ve.add(new Ticket(ticketID, id, showTimeID, ticketPrice, paymentStatus, seats));
                    }
                }
                adapter.notifyDataSetChanged();

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(VeCuaToi.this, error.getMessage(),Toast.LENGTH_SHORT).show();
            }

        });
        return ve;
    }

    public void getmovie(String id, List<Movie> listMovie){
        DatabaseReference dbs = FirebaseDatabase.getInstance().getReference("Movies");
        dbs.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for (DataSnapshot dataSnapshot: snapshot.getChildren()) {
                    if (dataSnapshot.child("movieID").getValue(String.class).equals(id)){
                        String movieID = dataSnapshot.child("movieID").getValue(String.class);
                        String name =  dataSnapshot.child("name").getValue(String.class);
                        String img = dataSnapshot.child("posterImage").getValue(String.class);
                        String description = dataSnapshot.child("description").getValue(String.class);
                        String durationTime = dataSnapshot.child("durationTime").getValue(String.class);
                        String genre = dataSnapshot.child("genre").getValue(String.class);
                        String trailer = dataSnapshot.child("trailer").getValue(String.class);
                        Double vote = dataSnapshot.child("vote").getValue(Double.class);
                        listMovie.add(new Movie(movieID, name, img, description, genre, durationTime, new ArrayList<>(), trailer, vote, new ArrayList<>()));
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }
}