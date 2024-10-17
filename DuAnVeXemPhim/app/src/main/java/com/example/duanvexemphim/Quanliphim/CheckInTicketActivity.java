package com.example.duanvexemphim.Quanliphim;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.duanvexemphim.R;

public class CheckInTicketActivity extends AppCompatActivity {
    private EditText etTicketCode;
    private Button btnFindTicket, btnCancel, btnView;
    private LinearLayout layoutTicketInfo;
    private TextView tvTicketInfo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_check_in_ticket);

        etTicketCode = findViewById(R.id.etTicketCode);
        btnFindTicket = findViewById(R.id.btnFindTicket);
        layoutTicketInfo = findViewById(R.id.layoutTicketInfo);
        tvTicketInfo = findViewById(R.id.tvTicketInfo);
        btnCancel = findViewById(R.id.btnCancel);
        btnView = findViewById(R.id.btnView);

        // Sự kiện khi nhấn nút "Tìm Vé"
        btnFindTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String ticketCode = etTicketCode.getText().toString().trim();
                if (ticketCode.isEmpty()) {
                    Toast.makeText(CheckInTicketActivity.this, "Vui lòng nhập mã vé", Toast.LENGTH_SHORT).show();
                    return;
                }

                // Ở đây bạn có thể thêm logic để tìm vé từ cơ sở dữ liệu hoặc một danh sách nào đó.
                // Giả sử vé đã tìm thấy và hiển thị thông tin vé.
                String ticketInfo = "Thông tin vé: \nMã vé: " + ticketCode + "\nPhim: Avengers: Endgame\nNgày chiếu: 2024-10-20\nGiờ: 19:00";
                tvTicketInfo.setText(ticketInfo);
                layoutTicketInfo.setVisibility(View.VISIBLE);
            }
        });

        // Sự kiện khi nhấn nút "Hủy"
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                layoutTicketInfo.setVisibility(View.GONE);
                etTicketCode.setText(""); // Xóa mã vé
            }
        });

        // Sự kiện khi nhấn nút "Vào Xem"
//        btnView.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                // Chuyển đến trang xem phim
//                Intent intent = new Intent(CheckInTicketActivity.this, MovieDetailActivity.class);
//                startActivity(intent);
//            }
//        });
    }
}
