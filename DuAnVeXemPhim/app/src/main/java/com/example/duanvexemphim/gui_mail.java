package com.example.duanvexemphim;

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
        //
        edtEmailTo = findViewById(R.id.edtEmailTo);
        edtSubject = findViewById(R.id.edtSubject);
        edtContent = findViewById(R.id.edtContent);
        btnSend = findViewById(R.id.btnSend);
        //
        btnSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    String fromEmail = "didonglaptrinh@gmail.com";
                    String emailPassword = "eyhiqtwxulmevecp";
                    String toEmail = edtEmailTo.getText().toString().trim();
                    String subject = edtSubject.getText().toString().trim();
//                    String userID = getIntent().getStringExtra("userID");
//                    String movieName = getIntent().getStringExtra("movieName");
//                    String showTime = getIntent().getStringExtra("showTime");
//                    String seats = getIntent().getStringExtra("seats");
//                    String food = getIntent().getStringExtra("food");
//                    int totalAmount = getIntent().getIntExtra("totalAmount", 0);

                    String content = edtContent.getText().toString().trim();
                    String host = "smtp.gmail.com";
                    Properties properties = System.getProperties();
                    properties.put("mail.smtp.host", host);
                    properties.put("mail.smtp.port", "465");
                    properties.put("mail.smtp.ssl.enable", "true");
                    properties.put("mail.smtp.auth", "true");

                    //xác thực tài khoản
                    Session session = Session.getInstance(properties, new Authenticator() {
                        @Override
                        protected PasswordAuthentication getPasswordAuthentication() {
                            return new PasswordAuthentication(fromEmail, emailPassword);
                        }
                    });
                    //gửi nội dung đi
                    MimeMessage mimeMessage = new MimeMessage(session);
                    mimeMessage.addRecipient(Message.RecipientType.TO, new InternetAddress(toEmail));
                    mimeMessage.setSubject(subject);
                    mimeMessage.setText(content);
                    Thread emailThread = new Thread(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Transport.send(mimeMessage);
                            }catch (Exception e) {
                                Log.d("Lỗi Thread email", e.toString());
                                Toast.makeText(gui_mail.this, e.toString(),
                                        Toast.LENGTH_SHORT).show();
                            }
                        }
                    });
                    emailThread.start();
                    edtEmailTo.setText("");
                    edtSubject.setText("");
                    edtContent.setText("");

                    Toast.makeText(gui_mail.this,
                            "Email đã được gửi tới " + toEmail, Toast.LENGTH_SHORT).show();
                }catch (Exception e){
                    Log.d("Lỗi gửi email", e.toString());
                    Toast.makeText(gui_mail.this, e.toString(), Toast.LENGTH_SHORT).show();
                }
            }
        });
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
    }
}