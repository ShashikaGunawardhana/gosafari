package com.example.safaribooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;


public class ticketsearch extends AppCompatActivity {

    Spinner txtpark,txttype;
    EditText txtfull,txthalf,txtDate;
    Button btnBook;
    DatabaseReference reff;
    Member member;
    long maxID = 0;
    FirebaseAuth fauth = FirebaseAuth.getInstance();
    String userID;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketsearch);
        txtpark = (Spinner)findViewById(R.id.t_placeInput);
        txttype = (Spinner)findViewById(R.id.t_nationalityInput);
        txtfull = (EditText)findViewById(R.id.t_full_tkt);
        txthalf = (EditText)findViewById(R.id.t_half_tkt);
        txtDate = (EditText)findViewById(R.id.t_date);
        btnBook = (Button)findViewById(R.id.t_booknwBtn);


        reff = FirebaseDatabase.getInstance().getReference("Order").child("TicletBooking");


        reff.addValueEventListener(new ValueEventListener() {

            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    maxID = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                insertData();
            }
        });




    }
    public void insertData(){
        String park = txtpark.getSelectedItem().toString();
        String type = txttype.getSelectedItem().toString();
        int fulltkt = Integer.parseInt(txtfull.getText().toString().trim());
        int halftkt = Integer.parseInt(txthalf.getText().toString().trim());
        double fulltktAmount,halftktAmount,fullAmount;
        String date = txtDate.getText().toString();
        long tktKeyValue = maxID + 1;

        if (type.equals("Foriegn"))
        {
            fulltktAmount = fulltkt * 200;
            halftktAmount = halftkt * 100;
            fullAmount = fulltktAmount + halftktAmount;
        }
        else
        {
            fulltktAmount = fulltkt * 100;
            halftktAmount = halftkt * 50;
            fullAmount = fulltktAmount + halftktAmount;
        }

        userID = fauth.getCurrentUser().getUid();
       Member member = new Member(tktKeyValue,park,type,fulltkt,halftkt,date,fulltktAmount,halftktAmount,fullAmount);

        reff.child(String.valueOf(maxID+1)).setValue(member);
        Toast.makeText(getApplicationContext(),"Successfull",Toast.LENGTH_SHORT).show();
    }
}