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

public class TicketRateTable extends AppCompatActivity {

    DatabaseReference dbwill,dbyala,dbmin;
    TextView wil_loc_adult,wil_loc_child,wil_for_adult,wil_for_child, yal_loc_child,
            yal_loc_adult,yal_for_child,yal_for_adult,min_loc_child,min_loc_adult,min_for_child,min_for_adult;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticket_rate_table);
        getSupportActionBar().hide();

        wil_loc_child = findViewById(R.id.tb_wil__loc_child);
        wil_loc_adult = findViewById(R.id.tb_wil_loc_adult);
        wil_for_child = findViewById(R.id.tb_wil_for_child);
        wil_for_adult = findViewById(R.id.tb_wil_for_adult);

        yal_loc_child = findViewById(R.id.tb_yal__loc_child);
        yal_loc_adult = findViewById(R.id.tb_yal_loc_adult);
        yal_for_child = findViewById(R.id.tb_yal_for_child);
        yal_for_adult = findViewById(R.id.tb_yal_for_adult);

        min_loc_child = findViewById(R.id.tb_min__loc_child);
        min_loc_adult = findViewById(R.id.tb_min_loc_adult);
        min_for_child = findViewById(R.id.tb_min_for_child);
        min_for_adult = findViewById(R.id.tb_min_for_adult);


        dbwill = FirebaseDatabase.getInstance().getReference("TicketRates").child("Wilpaththuwa_park");
        dbyala = FirebaseDatabase.getInstance().getReference("TicketRates").child("Yala_park");
        dbmin = FirebaseDatabase.getInstance().getReference("TicketRates").child("Minneriya_park");

        //get value from the database
        dbwill.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                String Option1 = String.valueOf(snapshot.child("local_adult").getValue(Long.class));
                String Option2 = String.valueOf(snapshot.child("local_child").getValue(Long.class));
                String Option3 = String.valueOf(snapshot.child("foreign_adult").getValue(Long.class));
                String Option4 = String.valueOf(snapshot.child("foreign_child").getValue(Long.class));

                wil_loc_adult.setText(Option1);
                wil_loc_child.setText(Option2);
                wil_for_adult.setText(Option3);
                wil_for_child.setText(Option4);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //get value from the database
        dbyala.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                String Option1 = String.valueOf(snapshot.child("local_adult").getValue(Long.class));
                String Option2 = String.valueOf(snapshot.child("local_child").getValue(Long.class));
                String Option3 = String.valueOf(snapshot.child("foreign_adult").getValue(Long.class));
                String Option4 = String.valueOf(snapshot.child("foreign_child").getValue(Long.class));

                yal_loc_adult.setText(Option1);
                yal_loc_child.setText(Option2);
                yal_for_adult.setText(Option3);
                yal_for_child.setText(Option4);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        dbmin.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                String Option1 = String.valueOf(snapshot.child("local_adult").getValue(Long.class));
                String Option2 = String.valueOf(snapshot.child("local_child").getValue(Long.class));
                String Option3 = String.valueOf(snapshot.child("foreign_adult").getValue(Long.class));
                String Option4 = String.valueOf(snapshot.child("foreign_child").getValue(Long.class));

                min_loc_adult.setText(Option1);
                min_loc_child.setText(Option2);
                min_for_adult.setText(Option3);
                min_for_child.setText(Option4);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

    }
}