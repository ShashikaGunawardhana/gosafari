package com.example.safaribooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class SafariRateTable extends AppCompatActivity {

    TextView tb_yal_4_prc,tb_yal_6_prc,tb_yal_8_prc,tb_yal_4_avl,tb_yal_6_avl,tb_yal_8_avl,tb_wil_4_prc,
            tb_wil_6_prc,tb_wil_8_prc,tb_wil_4_avl,tb_wil_6_avl,tb_wil_8_avl,tb_min_4_prc,tb_min_6_prc,tb_min_8_prc,
            tb_min_4_avl,tb_min_6_avl,tb_min_8_avl;

    DatabaseReference s_dbwill_rates,s_dbyala_rates,s_dbmin_rates,yala_avail,min_avail,wil_avail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_safari_rate_table);
        getSupportActionBar().hide();

        //assign ids to the variable
        tb_yal_4_prc = (TextView)findViewById(R.id.tb_yal_4_pr);
        tb_yal_6_prc = (TextView)findViewById(R.id.tb_yal_6_pr);
        tb_yal_8_prc = (TextView)findViewById(R.id.tb_yal_8_pr);
        tb_yal_4_avl = (TextView)findViewById(R.id.tb_yal_4_avail);
        tb_yal_6_avl = (TextView)findViewById(R.id.tb_yal_6_avail);
        tb_yal_8_avl = (TextView)findViewById(R.id.tb_yal_8_avail);

        tb_wil_4_prc = (TextView)findViewById(R.id.tb_wil_4_pr);
        tb_wil_6_prc = (TextView)findViewById(R.id.tb_wil_6_pr);
        tb_wil_8_prc = (TextView)findViewById(R.id.tb_wil_8_pr);
        tb_wil_4_avl = (TextView)findViewById(R.id.tb_wil_4_avail);
        tb_wil_6_avl = (TextView)findViewById(R.id.tb_wil_6_avail);
        tb_wil_8_avl = (TextView)findViewById(R.id.tb_wil_8_avail);

        tb_min_4_prc = (TextView)findViewById(R.id.tb_min_4_pr);
        tb_min_6_prc = (TextView)findViewById(R.id.tb_min_6_pr);
        tb_min_8_prc = (TextView)findViewById(R.id.tb_min_8_pr);
        tb_min_4_avl = (TextView)findViewById(R.id.tb_min_4_avail);
        tb_min_6_avl = (TextView)findViewById(R.id.tb_min_6_avail);
        tb_min_8_avl = (TextView)findViewById(R.id.tb_min_8_avail);


        yala_avail = FirebaseDatabase.getInstance().getReference("AvailableVehicle").child("Yala_park");
        wil_avail = FirebaseDatabase.getInstance().getReference("AvailableVehicle").child("Wilpaththuwa_park");
        min_avail = FirebaseDatabase.getInstance().getReference("AvailableVehicle").child("Minneriya_park");

        //create db connection
        s_dbwill_rates = FirebaseDatabase.getInstance().getReference("SafariRates").child("Wilpaththuwa_park");
        s_dbyala_rates = FirebaseDatabase.getInstance().getReference("SafariRates").child("Yala_park");
        s_dbmin_rates = FirebaseDatabase.getInstance().getReference("SafariRates").child("Minneriya_park");


        //get data
        s_dbwill_rates.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                String Option1 = String.valueOf(snapshot.child("4_seat").getValue(Long.class));
                String Option2 = String.valueOf(snapshot.child("6_seat").getValue(Long.class));
                String Option3 = String.valueOf(snapshot.child("8_seat").getValue(Long.class));


                tb_wil_4_prc.setText(Option1);
                tb_wil_6_prc.setText(Option2);
                tb_wil_8_prc.setText(Option3);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //get data
        s_dbyala_rates.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                String Option1 = String.valueOf(snapshot.child("4_seat").getValue(Long.class));
                String Option2 = String.valueOf(snapshot.child("6_seat").getValue(Long.class));
                String Option3 = String.valueOf(snapshot.child("8_seat").getValue(Long.class));


                tb_yal_4_prc.setText(Option1);
                tb_yal_6_prc.setText(Option2);
                tb_yal_8_prc.setText(Option3);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
        //get data
        s_dbmin_rates.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                String Option1 = String.valueOf(snapshot.child("4_seat").getValue(Long.class));
                String Option2 = String.valueOf(snapshot.child("6_seat").getValue(Long.class));
                String Option3 = String.valueOf(snapshot.child("8_seat").getValue(Long.class));


                tb_min_4_prc.setText(Option1);
                tb_min_6_prc.setText(Option2);
                tb_min_8_prc.setText(Option3);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        yala_avail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                String Option1=String.valueOf(snapshot.child("4_seat_v").getValue(Long.class));
                String Option2=String.valueOf(snapshot.child("6_seat_v").getValue(Long.class));
                String Option3=String.valueOf(snapshot.child("8_seat_v").getValue(Long.class));

                tb_yal_4_avl.setText(Option1);
                tb_yal_6_avl.setText(Option2);
                tb_yal_8_avl.setText(Option3);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        wil_avail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                String Option1=String.valueOf(snapshot.child("4_seat_v").getValue(Long.class));
                String Option2=String.valueOf(snapshot.child("6_seat_v").getValue(Long.class));
                String Option3=String.valueOf(snapshot.child("8_seat_v").getValue(Long.class));

                tb_wil_4_avl.setText(Option1);
                tb_wil_6_avl.setText(Option2);
                tb_wil_8_avl.setText(Option3);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        min_avail.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                String Option1=String.valueOf(snapshot.child("4_seat_v").getValue(Long.class));
                String Option2=String.valueOf(snapshot.child("6_seat_v").getValue(Long.class));
                String Option3=String.valueOf(snapshot.child("8_seat_v").getValue(Long.class));

                tb_min_4_avl.setText(Option1);
                tb_min_6_avl.setText(Option2);
                tb_min_8_avl.setText(Option3);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }
}