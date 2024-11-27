package com.example;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.R;
import com.example.duanvexemphim.gui_mail;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class ChiTietGiaoDich2Activity extends AppCompatActivity {

    TextView tvTenPhim, tvSuatChieu, tvTenRap, tvSoGhe, tvPTThanhToan, tvSoTien, tvEmailTo, tvSubject, tvContent;
    Button btnTrangChu;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_chi_tiet_giao_dich2);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        //
        tvTenPhim = findViewById(R.id.tvTenPhim);
        tvSuatChieu = findViewById(R.id.tvSuatChieu);
        tvTenRap = findViewById(R.id.tvTenRap);
        tvSoGhe = findViewById(R.id.tvSoGhe);
        tvPTThanhToan = findViewById(R.id.tvPTThanhToan);
        tvSoTien = findViewById(R.id.tvSoTien);
        btnTrangChu = findViewById(R.id.btnTrangChu);
        tvEmailTo = findViewById(R.id.tvEmailTo);
        tvSubject = findViewById(R.id.tvSubject);
        tvContent = findViewById(R.id.tvContent);
        //
        Intent intent = getIntent();
        String movieNameNo3 = intent.getStringExtra("movieNameNo3");
        String gioChieu2 = intent.getStringExtra("gioChieu2");
        String tenRap = intent.getStringExtra("tenRapToChiTiet2");
        String ghe = intent.getStringExtra("ghe");
        int tongTien = intent.getIntExtra("tongTien", 0);
        //String userID = intent.getStringExtra("userID");
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email", null);
        // Thông tin vào email
        String content = "Thông tin giao dịch:\n"
                + "Tên phim: " + movieNameNo3 + "\n"
                + "Suất chiếu: " + gioChieu2 + "\n"
                + "Tên rạp: " + tenRap + "\n"
                + "Số ghế: " + ghe + "\n"
                + "Tổng tiền: " + tongTien + " VNĐ";
        //
        tvTenPhim.setText("Tên phim: " + movieNameNo3);
        tvSuatChieu.setText("Suất chiếu: " + gioChieu2);
        tvTenRap.setText("Tên rạp: " + tenRap);
        tvSoGhe.setText("Số ghế: " + ghe);
        tvSoTien.setText("Tổng tiền: " + tongTien + " VNĐ");
        tvEmailTo.setText(userEmail);
        tvSubject.setText("Xác nhận giao dịch của bạn");
        tvContent.setText(content);

        //
        btnTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTrangChu = new Intent(ChiTietGiaoDich2Activity.this, MainActivity.class);
                startActivity(intentTrangChu);
            }
        });
        //
        String fromEmail = "didonglaptrinh@gmail.com";
        String emailPassword = "eyhiqtwxulmevecp";
        String toEmail = tvEmailTo.getText().toString().trim();
        String subject = tvSubject.getText().toString().trim();

        if (!android.util.Patterns.EMAIL_ADDRESS.matcher(toEmail).matches()) {
            Toast.makeText(ChiTietGiaoDich2Activity.this, "Email không hợp lệ. Vui lòng kiểm tra lại.", Toast.LENGTH_SHORT).show();
            return;
        }

        // Cấu hình gửi email
        Properties properties = System.getProperties();
        properties.put("mail.smtp.host", "smtp.gmail.com");
        properties.put("mail.smtp.port", "465");
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.auth", "true");

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected javax.mail.PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(fromEmail, emailPassword);
            }
        });

        new Thread(() -> {
            try {
                MimeMessage mimeMessage = new MimeMessage(session);
                mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
                mimeMessage.setSubject(subject);
                mimeMessage.setText(content);
                Transport.send(mimeMessage);
                runOnUiThread(() -> Toast.makeText(ChiTietGiaoDich2Activity.this, "Email đã gửi thành công!", Toast.LENGTH_LONG).show());
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(ChiTietGiaoDich2Activity.this, "Gửi email thất bại: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }).start();
    }
}