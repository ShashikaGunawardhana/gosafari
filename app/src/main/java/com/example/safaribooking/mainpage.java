package com.example.safaribooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;

public class mainpage extends AppCompatActivity {

    Button prevoioustkt,t_mainBookBtn,logout,safBookingbtn,safprevbtn,rateUs,user_view;
    TextView mainemail,muserID;
    String getmail,userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mainpage);
        getSupportActionBar().hide();

        prevoioustkt = findViewById(R.id.t_previousTktBtn);
        t_mainBookBtn = findViewById(R.id.t_mainBookBtn);


        safBookingbtn = findViewById(R.id.t_mainSafariBtn);
        safprevbtn = findViewById(R.id.t_previousSafari);
        rateUs = findViewById(R.id.t_ratebtn);
        getmail = getIntent().getStringExtra("keyEmail");
        userID = getIntent().getStringExtra("keyuserID");
        user_view = findViewById(R.id.profile_view_btn);


        user_view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainpage.this, UserProfile.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });

        safBookingbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainpage.this, MainActivity.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });
        safprevbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainpage.this, Safari_order.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });


        prevoioustkt.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainpage.this, ViewTktBooking.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });

        t_mainBookBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainpage.this, ticketsearch.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });
        rateUs.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(mainpage.this, Feedback.class).putExtra("keyuserID", userID).putExtra("keyEmail", getmail));
            }
        });

    }

}
