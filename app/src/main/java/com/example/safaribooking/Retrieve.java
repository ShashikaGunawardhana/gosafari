package com.example.safaribooking;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

public class Retrieve extends AppCompatActivity {

    Button r_btn1,r_btn2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_retrieve);

        r_btn1=findViewById(R.id.R_btn1);
        r_btn2=findViewById(R.id.R_book);


        r_btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(Retrieve.this, MainActivity.class));
            }
        });



      r_btn1.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View v) {

              startActivity(new Intent(Retrieve.this, Safari_order.class));

          }





          });









}

}