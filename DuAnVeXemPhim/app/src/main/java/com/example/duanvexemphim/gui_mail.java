package com.example.duanvexemphim;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import java.util.Properties;

import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class gui_mail extends AppCompatActivity {

    TextView edtEmailTo, edtSubject, edtContent;
    Button btnSend;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_gui_mail);

        edtEmailTo = findViewById(R.id.tvEmailTo);
        edtSubject = findViewById(R.id.tvSubject);
        edtContent = findViewById(R.id.tvContent);
        btnSend = findViewById(R.id.btnSend);

        // Nhận dữ liệu từ Intent
        Intent intent = getIntent();
        String movieName = intent.getStringExtra("movieNameNo4");
        String showTime = intent.getStringExtra("gioChieu3");
        String seats = intent.getStringExtra("ghe1");
        int totalAmount = intent.getIntExtra("tongTien1", 0);
        SharedPreferences sharedPreferences = getSharedPreferences("UserPrefs", MODE_PRIVATE);
        String userEmail = sharedPreferences.getString("email", null);

        // Điền thông tin vào email
        String content = "Thông tin giao dịch:\n"
                + "Tên phim: " + movieName + "\n"
                + "Suất chiếu: " + showTime + "\n"
                + "Số ghế: " + seats + "\n"
                + "Tổng tiền: " + totalAmount + "VNĐ";

        edtEmailTo.setText(userEmail);
        edtSubject.setText("Tiêu đề: Xác nhận giao dịch của bạn");
        edtContent.setText(content);

        btnSend.setOnClickListener(view -> {
            String fromEmail = "didonglaptrinh@gmail.com";
            String emailPassword = "eyhiqtwxulmevecp";
            String toEmail = edtEmailTo.getText().toString().trim();
            String subject = edtSubject.getText().toString().trim();

            if (!android.util.Patterns.EMAIL_ADDRESS.matcher(toEmail).matches()) {
                Toast.makeText(gui_mail.this, "Email không hợp lệ. Vui lòng kiểm tra lại.", Toast.LENGTH_SHORT).show();
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

                    runOnUiThread(() -> Toast.makeText(gui_mail.this, "Email đã gửi thành công!", Toast.LENGTH_LONG).show());
                } catch (Exception e) {
                    runOnUiThread(() -> Toast.makeText(gui_mail.this, "Gửi email thất bại: " + e.getMessage(), Toast.LENGTH_LONG).show());
                }
            }).start();
        });
    }
}