package com.example;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.example.duanvexemphim.R;
import com.example.duanvexemphim.models.Ticket;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

public class ChiTietGiaoDich extends AppCompatActivity {

    private DatabaseReference mDatabase;
    private DatabaseReference mSeatsDatabase;
    Button btnThoat, btnDongY;
    TextView tvTenPhim, tvSuatChieu, tvTenRap, tvSoGhe, tvdoan, tvTongTien;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_giao_dich);

        mDatabase = FirebaseDatabase.getInstance().getReference("Tickets");
        mSeatsDatabase = FirebaseDatabase.getInstance().getReference("Seats");

        btnThoat = findViewById(R.id.btnThoat);
        btnDongY = findViewById(R.id.btnDongY);
        tvTenPhim = findViewById(R.id.tvTenPhim);
        tvSuatChieu = findViewById(R.id.tvSuatChieu);
        tvTenRap = findViewById(R.id.tvTenRap);
        tvdoan = findViewById(R.id.tvdoan);
        tvSoGhe = findViewById(R.id.tvSoGhe);
        tvTongTien = findViewById(R.id.tvTongTien);

        Intent intent = getIntent();
        String movieID = intent.getStringExtra("movieID");
        String movieNameNo3 = intent.getStringExtra("movieNameNo3");
        String gioChieu2 = intent.getStringExtra("gioChieu2");
        String tenRap = intent.getStringExtra("tenRapToChiTiet1");
        String ghe = intent.getStringExtra("ghe");
        String doAn = intent.getStringExtra("doAn");
        int tongTien = intent.getIntExtra("tongTien", 0);
        String userID = intent.getStringExtra("userID");

        if (doAn != null && !doAn.isEmpty()) {
            tvdoan.setText("Đồ ăn: " + doAn);
        } else {
            tvdoan.setText("Đồ ăn: Bạn không mua đồ ăn");
        }
        tvTenRap.setText("Tên rạp: " + tenRap);
        tvSoGhe.setText("Số ghế: " + ghe);
        tvSuatChieu.setText("Suất chiếu: " + gioChieu2);
        tvTongTien.setText("Tổng tiền: " + tongTien + " VNĐ");
        tvTenPhim.setText("Tên phim: " + movieNameNo3);

        btnThoat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("movieNameNo3", tvTenPhim.getText().toString());
                returnIntent.putExtra("gioChieu2", tvSuatChieu.getText().toString());
                returnIntent.putExtra("tenRapToChiTiet1", tvTenRap.getText().toString());
                returnIntent.putExtra("ghe", tvSoGhe.getText().toString());
                returnIntent.putExtra("doAn", tvdoan.getText().toString());
                returnIntent.putExtra("tongTien", tvTongTien.getText().toString());
                setResult(RESULT_OK, returnIntent);
                finish();
            }
        });

        btnDongY.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AlertDialog.Builder dialogDongY = new AlertDialog.Builder(ChiTietGiaoDich.this);
                dialogDongY.setTitle("Xác nhận giao dịch");
                dialogDongY.setMessage("Bạn có chắc chắn đồng ý thanh toán?");

                dialogDongY.setPositiveButton("Đồng ý", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        isSeatSold(movieNameNo3, gioChieu2, Arrays.asList(ghe.split(",")), new OnSeatCheckListener() {
                            @Override
                            public void onSeatChecked(boolean isSold) {
                                if (isSold) {
                                    new AlertDialog.Builder(ChiTietGiaoDich.this)
                                            .setTitle("Chỗ ngồi đã được bán")
                                            .setMessage("Chỗ ngồi bạn chọn đã được bán. Vui lòng chọn chỗ ngồi khác.")
                                            .setPositiveButton(android.R.string.ok, new DialogInterface.OnClickListener() {
                                                @Override
                                                public void onClick(DialogInterface dialogInterface, int i) {
                                                    finish();
                                                }
                                            }).show();
                                } else {
                                    Intent intent = new Intent(ChiTietGiaoDich.this, ChiTietGiaoDich2Activity.class);
                                    intent.putExtra("movieNameNo3", movieNameNo3);
                                    intent.putExtra("gioChieu2", gioChieu2);
                                    intent.putExtra("tenRapToChiTiet2", tenRap);
                                    intent.putExtra("ghe", ghe);
                                    intent.putExtra("doAn", doAn);
                                    intent.putExtra("tongTien", tongTien);
                                    List<String> bookedSeats = Arrays.asList(ghe.split(","));
                                    saveTicketDataToDatabase(userID, movieNameNo3, gioChieu2, bookedSeats, tongTien, "Paid", movieID);
                                    updateSeatsStatus(movieNameNo3, gioChieu2, bookedSeats);
                                    startActivity(intent);
                                }
                            }
                        });
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

    private interface OnSeatCheckListener {
        void onSeatChecked(boolean isSold);
    }

    private void isSeatSold(String movieName, String showTime, List<String> bookedSeats, OnSeatCheckListener listener) {
        mSeatsDatabase.child(movieName).child(showTime).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                boolean isAnySeatSold = false;
                for (String seat : bookedSeats) {
                    if (seat != null && !seat.trim().isEmpty()) {
                        Log.d("ChiTietGiaoDich", "Checking seat: " + seat);
                        if (snapshot.child(seat).exists() && "sold".equals(snapshot.child(seat).getValue(String.class))) {
                            isAnySeatSold = true;
                            Log.d("ChiTietGiaoDich", "Seat " + seat + " is already sold.");
                            break;
                        }
                    } else {
                        Log.d("ChiTietGiaoDich", "Invalid seat: " + seat);
                    }
                }
                listener.onSeatChecked(isAnySeatSold);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Log.e("ChiTietGiaoDich", "Error checking seat status: ", error.toException());
                listener.onSeatChecked(false);
            }
        });
    }

    private void saveTicketDataToDatabase(String userID, String movieName, String showTime, List<String> bookedSeats, int totalAmount, String paymentStatus, String movieId) {
        String ticketId = mDatabase.push().getKey();
        userID = FirebaseAuth.getInstance().getCurrentUser().getUid();
        LocalTime purchaseTime = null;
        if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
            purchaseTime = LocalTime.now();
        }
        int ticketPrice = totalAmount / bookedSeats.size();

        Ticket ticket = new Ticket(ticketId, userID, showTime, ticketPrice, paymentStatus, bookedSeats, movieName, movieId);
        mDatabase.child(ticketId).setValue(ticket);

        for (String seat : bookedSeats) {
            if (seat != null && !seat.trim().isEmpty()) {
                mSeatsDatabase.child(movieName).child(showTime).child(seat).setValue("sold");
                Log.d("ChiTietGiaoDich", "Seat " + seat + " set as sold for showTime " + showTime + " and movie " + movieName);
            } else {
                Log.d("ChiTietGiaoDich", "Invalid seat: " + seat);
            }
        }
    }

    private void updateSeatsStatus(String movieName, String showTime, List<String> bookedSeats) {
        for (String seat : bookedSeats) {
            if (seat != null && !seat.trim().isEmpty()) {
                mSeatsDatabase.child(movieName).child(showTime).child(seat).setValue("sold");
                Log.d("ChiTietGiaoDich", "Seat " + seat + " updated as sold for showTime " + showTime + " and movie " + movieName);
            } else {
                Log.d("ChiTietGiaoDich", "Invalid seat: " + seat);
            }
        }
    }
}
