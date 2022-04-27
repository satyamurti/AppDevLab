package com.satyamurti.webank;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.app.PendingIntent;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.telephony.SmsManager;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class
MainActivity extends AppCompatActivity {

    public static final int REQUEST_CODE = 101;
    private CardView sendCv, verifyCv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button buttonSend = findViewById(R.id.bt_sendOtp);
        EditText editTextSend = findViewById(R.id.et_phone);

        Button buttonVerify = findViewById(R.id.bt_verifyOtp);
        EditText editTextVerify = findViewById(R.id.et_otp);

        sendCv = findViewById(R.id.cv_send);
        verifyCv = findViewById(R.id.cv_verify);

        buttonSend.setOnClickListener((view) -> {
            if (ActivityCompat.checkSelfPermission(MainActivity.this,
                    Manifest.permission.SEND_SMS) != PackageManager.PERMISSION_GRANTED) {
                Toast.makeText(this, "Please grant permission to message", Toast.LENGTH_SHORT).show();
                return;
            }
            String phoneNumber = editTextSend.getText().toString();
            messageUser(phoneNumber);
        });

        buttonVerify.setOnClickListener((view -> {
            String otp = editTextVerify.getText().toString();
            verifyOtp(otp);
        }));

        checkPermission();
    }

    private void messageUser(String phoneNumber) {
        Intent intent = new Intent();
        PendingIntent pendingIntent = PendingIntent.getActivity(getApplicationContext(), 0, intent, 0);

        if (phoneNumber.length() != 10) {
            Toast.makeText(this, "Incorrect field for phone number", Toast.LENGTH_SHORT).show();
            return;
        }

        String otp = OTPGenerator.OTP(6);
        String message = "Hello Dear User your otp is " + otp;
        SmsManager sms = SmsManager.getDefault();
        sms.sendTextMessage(phoneNumber, null, message, pendingIntent, null);

        Animation slideIn = AnimationUtils.loadAnimation(this, R.anim.slide_in_right);
        Animation slideOut = AnimationUtils.loadAnimation(this, R.anim.slide_out_left);

        sendCv.setVisibility(View.GONE);
        sendCv.startAnimation(slideIn);
        verifyCv.setVisibility(View.VISIBLE);
        verifyCv.startAnimation(slideOut);
    }

    private void verifyOtp(String otp) {
        if (otp.length() != 6) {
            Toast.makeText(getApplicationContext(), "Invalid OTP field entered", Toast.LENGTH_SHORT).show();
            return;
        }

        if (otp.equals(OTPGenerator.currentOtp)) {
            Toast.makeText(getApplicationContext(), "Verified successfully", Toast.LENGTH_SHORT).show();
            startActivity(new Intent(getApplicationContext(), WelcomeActivity.class));
            finish();
        } else {
            Toast.makeText(getApplicationContext(), "Invalid OTP entered", Toast.LENGTH_SHORT).show();
        }
    }

    private void checkPermission() {
        if (ContextCompat.checkSelfPermission(this, Manifest.permission.SEND_SMS)
                != PackageManager.PERMISSION_GRANTED &&
                ContextCompat.checkSelfPermission(this, Manifest.permission.RECEIVE_SMS)
                != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this,
                    new String[]{Manifest.permission.SEND_SMS, Manifest.permission.RECEIVE_SMS}, REQUEST_CODE);
        }
    }
}