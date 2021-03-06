package com.example.safaribooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;

import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;

import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;


public class ticketsearch extends AppCompatActivity {

    //create widgets
    Spinner txtpark,txttype;
    EditText txtfull,txthalf,txtDate;
    Button btnBook,check_rates;
    DatabaseReference reff,available,dbwill,dbyala,dbmin;
    Calendar myCalendar = Calendar.getInstance();
    FirebaseAuth fauth = FirebaseAuth.getInstance();
    double fulltktAmount =0 ,halftktAmount = 0,fullAmount = 0;
    String userID,email;
    TextView yal,min,wil;
    long yalanew,minnew,wilnew,newTickt,numberOfTkt,wil_loc_adult_price,wil_loc_child_price,wil_for_adult_price,wil_for_child_price,
            yal_loc_adult_price,yal_loc_child_price,yal_for_adult_price,yal_for_child_price, min_loc_adult_price,min_loc_child_price,min_for_adult_price,min_for_child_price;

    int fulltkt ;
    int halftkt;
    ProgressBar prgbar;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ticketsearch);
        getSupportActionBar().hide();

        //assign id's
        txtpark = (Spinner)findViewById(R.id.t_placeInput);
        txttype = (Spinner)findViewById(R.id.t_nationalityInput);
        txtfull = (EditText)findViewById(R.id.t_full_tkt);
        txthalf = (EditText)findViewById(R.id.t_half_tkt);
        txtDate = (EditText)findViewById(R.id.t_date);
        btnBook = (Button)findViewById(R.id.t_booknwBtn);
        check_rates = (Button)findViewById(R.id.s_check_rate);

        yal = (TextView)findViewById(R.id.avail_yal);
        min = (TextView)findViewById(R.id.avail_min);
        wil = (TextView)findViewById(R.id.avail_wil);
        prgbar = (ProgressBar) findViewById(R.id.t_order_progress);

        userID = getIntent().getStringExtra("keyuserID");
        email = getIntent().getStringExtra("keyEmail");

        //create db connection for get the available number of ticket
        available = FirebaseDatabase.getInstance().getReference("AvailableTicket");

        //create database connection for the rates
        dbwill = FirebaseDatabase.getInstance().getReference("TicketRates").child("Wilpaththuwa_park");
        dbyala = FirebaseDatabase.getInstance().getReference("TicketRates").child("Yala_park");
        dbmin = FirebaseDatabase.getInstance().getReference("TicketRates").child("Minneriya_park");


        //create date picker
        DatePickerDialog.OnDateSetListener date = new
                DatePickerDialog.OnDateSetListener() {

                    @Override
                    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
                        myCalendar.set(Calendar.YEAR, year);

                        myCalendar.set(Calendar.MONTH, month);
                        myCalendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);
                        updateLabel();
                    }



                };


        txtDate.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    new DatePickerDialog(ticketsearch.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                return false;
            }
        });



        //get value from the database
        available.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                String Option1=String.valueOf(snapshot.child("MinneriyaPark").getValue(Long.class));
                String Option2=String.valueOf(snapshot.child("WilpaththuwaPark").getValue(Long.class));
                String Option3=String.valueOf(snapshot.child("YalaPark").getValue(Long.class));

                min.setText(Option1);
                wil.setText(Option2);
                yal.setText(Option3);

                //COnvert  String value to the Long type
                yalanew = Long.parseLong(Option3);
                wilnew = Long.parseLong(Option2);
                minnew = Long.parseLong(Option1);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        //get rates in the database
        dbwill.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                String Option1 = String.valueOf(snapshot.child("local_adult").getValue(Long.class));
                String Option2 = String.valueOf(snapshot.child("local_child").getValue(Long.class));
                String Option3 = String.valueOf(snapshot.child("foreign_adult").getValue(Long.class));
                String Option4 = String.valueOf(snapshot.child("foreign_child").getValue(Long.class));


                wil_loc_adult_price = Long.parseLong(Option1);
                wil_loc_child_price = Long.parseLong(Option2);
                wil_for_adult_price = Long.parseLong(Option3);
                wil_for_child_price = Long.parseLong(Option4);

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


                yal_loc_adult_price = Long.parseLong(Option1);
                yal_loc_child_price = Long.parseLong(Option2);
                yal_for_adult_price = Long.parseLong(Option3);
                yal_for_child_price = Long.parseLong(Option4);

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


                min_loc_adult_price = Long.parseLong(Option1);
                min_loc_child_price = Long.parseLong(Option2);
                min_for_adult_price = Long.parseLong(Option3);
                min_for_child_price = Long.parseLong(Option4);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //direcct to the rates page
        check_rates.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(ticketsearch.this, TicketRateTable.class));
            }
        });

        //database connectivity
        reff = FirebaseDatabase.getInstance().getReference("Order").child("TicletBooking").child(userID);


        btnBook.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                sendEmail();
                insertData();


            }
        });

    }
    public void insertData(){


        //assign vbalue to the variables
        String full = txtfull.getText().toString().trim();
        String half = txthalf.getText().toString().trim();

        String park = txtpark.getSelectedItem().toString();
        String type = txttype.getSelectedItem().toString();
        String date = txtDate.getText().toString();

        //get current user id
        String id = reff.push().getKey();

        //showing progress bar
        prgbar.setVisibility(View.VISIBLE);

        //validation part
        if(TextUtils.isEmpty(date)){
            txtDate.setError("Date is Required!");
            prgbar.setVisibility(View.GONE);
            return;
        }
        if(TextUtils.isEmpty(full) && TextUtils.isEmpty(half)){
            txtfull.setError("At least one field is required!");
            txthalf.setError("At least one field is required!");
            prgbar.setVisibility(View.GONE);
            return;
        }

    //String value convert to integer
        if(full.isEmpty())
        {
            fulltkt = 0;
        }else{
            fulltkt = Integer.parseInt(full);
        }
        if(half.isEmpty())
        {
            halftkt = 0;
        }else{
            halftkt =Integer.parseInt(half);
        }
        //validation part2
        numberOfTkt = fulltkt + halftkt;


        //total amount calculation part
        if(park.equals("Wilpaththuwa National Park")){

            if(numberOfTkt > wilnew )
            {
                txtfull.setError("Your request cannot be fulfilled.please reduce the ticket count!");
                txthalf.setError("Your request cannot be fulfilled.please reduce the ticket count!");
                prgbar.setVisibility(View.GONE);
                return;
            }

            if (type.equals("Foriegn"))
            {
                fulltktAmount = fulltkt * wil_for_adult_price;
                halftktAmount = halftkt * wil_for_child_price;
                fullAmount = fulltktAmount + halftktAmount;

            }
            else
            {
                fulltktAmount = fulltkt * wil_loc_adult_price;
                halftktAmount = halftkt * wil_loc_child_price;
                fullAmount = fulltktAmount + halftktAmount;
            }
        }else if (park.equals("Yala National Park")){

            if(numberOfTkt > yalanew )
            {
                txtfull.setError("Your request cannot be fulfilled.please reduce the ticket count!");
                txthalf.setError("Your request cannot be fulfilled.please reduce the ticket count!");
                prgbar.setVisibility(View.GONE);
                return;
            }

            if (type.equals("Foriegn"))
            {
                fulltktAmount = fulltkt * yal_for_adult_price;
                halftktAmount = halftkt * yal_for_child_price;
                fullAmount = fulltktAmount + halftktAmount;
            }
            else
            {
                fulltktAmount = fulltkt * yal_loc_adult_price;
                halftktAmount = halftkt * yal_loc_child_price;
                fullAmount = fulltktAmount + halftktAmount;
            }
        }else if(park.equals("Minneriya National Park")){

            if(numberOfTkt > minnew )
            {
                txtfull.setError("Your request cannot be fulfilled.please reduce the ticket count!");
                txthalf.setError("Your request cannot be fulfilled.please reduce the ticket count!");
                prgbar.setVisibility(View.GONE);
                return;
            }

            if (type.equals("Foriegn"))
            {
                fulltktAmount = fulltkt * min_for_adult_price;
                halftktAmount = halftkt * min_for_child_price;
                fullAmount = fulltktAmount + halftktAmount;
            }
            else
            {
                fulltktAmount = fulltkt * min_loc_adult_price;
                halftktAmount = halftkt * min_loc_child_price;
                fullAmount = fulltktAmount + halftktAmount;
            }
        }


        userID = fauth.getCurrentUser().getUid();
       Member member = new Member( userID,id,park,type,fulltkt,halftkt,date,fulltktAmount,halftktAmount,fullAmount);

        reff.child(id).setValue(member).addOnCompleteListener(ticketsearch.this, new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {

                if(task.isSuccessful()){
                    Toast.makeText(getApplicationContext(),"Order Saved",Toast.LENGTH_SHORT).show();
                    prgbar.setVisibility(View.GONE);
                    updateTicketData();
                }else{
                    Toast.makeText(ticketsearch.this,"Error! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                    prgbar.setVisibility(View.GONE);
                }


            }
        });

    }
    public void updateTicketData(){

        String park = txtpark.getSelectedItem().toString();
        String full = txtfull.getText().toString().trim();
        String half = txthalf.getText().toString().trim();

        if(full.isEmpty())
        {
            fulltkt = 0;
        }else{
            fulltkt = Integer.parseInt(full);
        }
        if(half.isEmpty())
        {
            halftkt = 0;
        }else{
            halftkt =Integer.parseInt(half);
        }

        if(park.equals("Wilpaththuwa National Park")){

            newTickt = wilnew -(halftkt + fulltkt);
            available.child("WilpaththuwaPark").setValue(newTickt);
        }
        else if(park.equals("Yala National Park")){

            newTickt = yalanew -(halftkt + fulltkt);
            available.child("YalaPark").setValue(newTickt);
        }
        else if(park.equals("Minneriya National Park")){

            newTickt = minnew -(halftkt + fulltkt);
            available.child("MinneriyaPark").setValue(newTickt);
        }




    }
    public void sendEmail(){
        String mEmail = email;
        String park = txtpark.getSelectedItem().toString();
        String type = txttype.getSelectedItem().toString();
        System.out.println(mEmail);

        JavaMailAPI ticketMailAPI = new JavaMailAPI(this,mEmail,park,type);


        ticketMailAPI.execute();

    }
    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,
                Locale.US);

        txtDate.setText(sdf.format(myCalendar.getTime()));
    }


}