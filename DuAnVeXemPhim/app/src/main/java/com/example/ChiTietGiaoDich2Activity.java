package com.example;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.example.duanvexemphim.MainActivity;
import com.example.duanvexemphim.R;

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

        Intent intent = getIntent();
        String movieNameNo3 = intent.getStringExtra("movieNameNo3");
        String gioChieu2 = intent.getStringExtra("gioChieu2");
        String tenRap = intent.getStringExtra("tenRapToChiTiet2");
        String ghe = intent.getStringExtra("ghe");
        String doAn = intent.getStringExtra("doAn");// Nhận thêm thông tin "doAn" nếu cần hiển thị
        int tongTien = intent.getIntExtra("tongTien", 0);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email", null);
        // Thông tin vào email
        String content = "<!DOCTYPE html>" +
                "<html lang= en>" +
                "<head>" +
                "    <meta charset=UTF-8>" +
                "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">" +
                "    <title>Movie Ticket Bill</title>" +
                "    <style>" +
                "        body {" +
                "            font-family: Arial, sans-serif;" +
                "            line-height: 1.6;" +
                "            max-width: 600px;" +
                "            margin: 0 auto;" +
                "            padding: 20px;" +
                "            background-color: #f4f4f4;" +
                "        }" +
                "        .ticket {" +
                "            background-color: white;" +
                "            border-radius: 10px;" +
                "            box-shadow: 0 4px 6px rgba(0,0,0,0.1);" +
                "            padding: 20px;" +
                "            text-align: center;" +
                "        }" +
                "        .ticket-header {" +
                "            background-color: #333;" +
                "            color: white;" +
                "            padding: 10px;" +
                "            border-radius: 5px 5px 0 0;" +
                "        }" +
                "        .ticket-details {" +
                "            display: flex;" +
                "            justify-content: space-between;" +
                "            margin: 20px 0;" +
                "        }" +
                "        .qr-code {" +
                "            max-width: 150px;" +
                "            margin: 20px auto;" +
                "        }" +
                "        .ticket-footer {" +
                "            background-color: #f1f1f1;" +
                "            padding: 10px;" +
                "            border-radius: 0 0 5px 5px;" +
                "        }" +
                "        @media only screen and (max-width: 600px) {" +
                "            .ticket-details {" +
                "                flex-direction: column;" +
                "            }" +
                "        }" +
                "    </style>" +
                "</head>" +
                "<body>" +
                "    <div class=\"ticket\">" +
                "        <div class=\"ticket-header\">" +
                "            <h1>Vé Xem Phim</h1>" +
                "        </div>" +
                "        " +
                "        <div class=\"ticket-details\">" +
                "            <div>" +
                "                <h2>Tên phim: " + movieNameNo3 + "</h2>" +
                "                <p>Giờ chiếu: " + gioChieu2 +"</p>" +
                "            </div>" +
                "            <div>" +
                "                <p>Ghế: "+ ghe+"</p>" +
                "                <p>Rạp: "+tenRap+"</p>" +
                "                <p>Tổng tiền: "+tongTien+" VND"+"</p>" +
                "            </div>" +
                "        </div>" +
                "" +
                "" +
                "        <div class=\"ticket-footer\">" +
                "            <b> Trân trọng cảm ơn Quý khách đã tin tưởng sử dụng dịch vụ!\n</b>" +
                "            <p> Thank you for using our service!</p>" +
                "        </div>" +
                "    </div>" +
                "</body>" +
                "</html>";
        

        tvTenPhim.setText("Tên phim: " + movieNameNo3);
        tvSuatChieu.setText("Suất chiếu: " + gioChieu2);
        tvTenRap.setText("Tên rạp: " + tenRap);
        tvSoGhe.setText("Số ghế: " + ghe);
        tvSoTien.setText("Tổng tiền: " + tongTien + " VNĐ");
        tvEmailTo.setText(userEmail);
        tvSubject.setText("Xác nhận giao dịch của bạn");
        tvContent.setText(content);

        btnTrangChu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intentTrangChu = new Intent(ChiTietGiaoDich2Activity.this, MainActivity.class);
                startActivity(intentTrangChu);
            }
        });

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
                mimeMessage.setContent(content, "text/html; charset=utf-8");
                Transport.send(mimeMessage);
                runOnUiThread(() -> Toast.makeText(ChiTietGiaoDich2Activity.this, "Email đã gửi thành công!", Toast.LENGTH_LONG).show());
            } catch (Exception e) {
                runOnUiThread(() -> Toast.makeText(ChiTietGiaoDich2Activity.this, "Gửi email thất bại: " + e.getMessage(), Toast.LENGTH_LONG).show());
            }
        }).start();
    }

}