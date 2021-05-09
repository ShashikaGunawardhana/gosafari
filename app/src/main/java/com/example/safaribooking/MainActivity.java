package com.example.safaribooking;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Settings;
import android.text.TextUtils;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.basgeekball.awesomevalidation.AwesomeValidation;
import com.basgeekball.awesomevalidation.ValidationStyle;
import com.basgeekball.awesomevalidation.utility.RegexTemplate;
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


public class MainActivity extends AppCompatActivity {

    EditText Seat4, Seat6, Seat8, s_date;
    Button btn1,s_check_rate_btn;
    Spinner place;
    V_Booking v_booking;
    String userID,userid,email;
    Calendar myCalendar = Calendar.getInstance();
    FirebaseAuth fauth = FirebaseAuth.getInstance();
    DatabaseReference reff,s_dbwill_rates,s_dbyala_rates,s_dbmin_rates,yala_avail,min_avail,wil_avail;
    int S4,S6,S8,Tot_vehicle;
    long tb_yal_4_prc,tb_yal_6_prc,tb_yal_8_prc,tb_yal_4_avl,tb_yal_6_avl,tb_yal_8_avl,tb_wil_4_prc,
            tb_wil_6_prc,tb_wil_8_prc,tb_wil_4_avl,tb_wil_6_avl,tb_wil_8_avl,tb_min_4_prc,tb_min_6_prc,tb_min_8_prc,
            tb_min_4_avl,tb_min_6_avl,tb_min_8_avl;
    double S4_price,S6_price,S8_price,fullAmount;

    AwesomeValidation awesomeValidation;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        getSupportActionBar().hide();


        Seat4 = findViewById(R.id.s_4input);
        Seat6 = findViewById(R.id.s_6input);
        Seat8 = findViewById(R.id.s_8input);
        place = findViewById(R.id.s_placeInput);
        btn1 = findViewById(R.id.s_bookBtn);
        s_date = findViewById(R.id.ss_date);
        s_check_rate_btn = findViewById(R.id.s_check_rate);

        userid = getIntent().getStringExtra("keyuserID");
        email = getIntent().getStringExtra("keyEmail");

        v_booking = new V_Booking();

        //Create DB connection
        reff = FirebaseDatabase.getInstance().getReference("Order").child("SafariBooking").child(userid);

        yala_avail = FirebaseDatabase.getInstance().getReference("AvailableVehicle").child("Yala_park");
        wil_avail = FirebaseDatabase.getInstance().getReference("AvailableVehicle").child("Wilpaththuwa_park");
        min_avail = FirebaseDatabase.getInstance().getReference("AvailableVehicle").child("Minneriya_park");

        //create db connection
        s_dbwill_rates = FirebaseDatabase.getInstance().getReference("SafariRates").child("Wilpaththuwa_park");
        s_dbyala_rates = FirebaseDatabase.getInstance().getReference("SafariRates").child("Yala_park");
        s_dbmin_rates = FirebaseDatabase.getInstance().getReference("SafariRates").child("Minneriya_park");
        
        
        //Create date picker
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


        s_date.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if(event.getAction() == MotionEvent.ACTION_DOWN) {
                    new DatePickerDialog(MainActivity.this, date, myCalendar
                            .get(Calendar.YEAR), myCalendar.get(Calendar.MONTH),
                            myCalendar.get(Calendar.DAY_OF_MONTH)).show();
                }
                return false;
            }
        });




        s_check_rate_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SafariRateTable.class));
            }
        });



    //get the values of rates and availble vehicle
        s_dbwill_rates.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                String Option1 = String.valueOf(snapshot.child("4_seat").getValue(Long.class));
                String Option2 = String.valueOf(snapshot.child("6_seat").getValue(Long.class));
                String Option3 = String.valueOf(snapshot.child("8_seat").getValue(Long.class));


                tb_wil_4_prc = Long.parseLong(Option1);
                tb_wil_6_prc = Long.parseLong(Option2);
                tb_wil_8_prc = Long.parseLong(Option3);



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

                tb_yal_4_prc = Long.parseLong(Option1);
                tb_yal_6_prc = Long.parseLong(Option2);
                tb_yal_8_prc = Long.parseLong(Option3);


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

                tb_min_4_prc = Long.parseLong(Option1);
                tb_min_6_prc = Long.parseLong(Option2);
                tb_min_8_prc = Long.parseLong(Option3);

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

                tb_yal_4_avl = Long.parseLong(Option1);
                tb_yal_6_avl = Long.parseLong(Option2);
                tb_yal_8_avl = Long.parseLong(Option3);

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

                tb_wil_4_avl = Long.parseLong(Option1);
                tb_wil_6_avl = Long.parseLong(Option2);
                tb_wil_8_avl = Long.parseLong(Option3);


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

                tb_min_4_avl = Long.parseLong(Option1);
                tb_min_6_avl = Long.parseLong(Option2);
                tb_min_8_avl = Long.parseLong(Option3);

            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


        //insert data in to the DB

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v){

                userID = fauth.getCurrentUser().getUid();
                String id = reff.push().getKey();

                String num4seat = Seat4.getText().toString().trim();
                String num6seat = Seat6.getText().toString().trim();
                String num8seat = Seat8.getText().toString().trim();
                String newdate = s_date.getText().toString().trim();
                String park1 = place.getSelectedItem().toString();

                //Validation part
                if(TextUtils.isEmpty(num4seat) && TextUtils.isEmpty(num6seat) && TextUtils.isEmpty(num8seat)){
                    Seat4.setError("At least one field is required!");
                    Seat6.setError("At least one field is required!");
                    Seat8.setError("At least one field is required!");
                    return;
                }
                if(TextUtils.isEmpty(newdate)){
                    s_date.setError("Date is Required!");
                    return;
                }
                System.out.println(num6seat);

                //String value convert to
                if(num4seat.isEmpty())
                {
                    S4 = 0;
                }else{
                    S4 = Integer.parseInt(num4seat);
                }
                if(num6seat.isEmpty())
                {
                    S6 = 0;
                }else{
                    S6 =Integer.parseInt(num6seat);
                    System.out.println(S6);

                }
                if(num8seat.isEmpty()){
                    S8 = 0;
                }else{
                    S8 =Integer.parseInt(num8seat);
                }



                if(park1.equals("Minneriya National Park")){

                    S4_price = S4 * tb_min_4_prc;
                    S6_price = S6 * tb_min_6_prc;
                    S8_price = S8 * tb_min_8_prc;
                    fullAmount = S4_price + S6_price + S8_price;


                        if(S4 > tb_min_4_avl){
                            Seat4.setError("Your request cannot be fulfilled.please reduce the vehicle count!");
                            return;
                        }
                        else if(S6 > tb_min_6_avl){
                            Seat6.setError("Your request cannot be fulfilled.please reduce the vehicle count!");
                            return;
                        }
                        else if(S8 > tb_min_8_avl){
                            Seat8.setError("Your request cannot be fulfilled.please reduce the vehicle count!");
                            return;
                        }

                }else if(park1.equals("Yala National Park")){

                    S4_price = S4 * tb_yal_4_prc;
                    S6_price = S6 * tb_yal_6_prc;
                    S8_price = S8 * tb_yal_8_prc;
                    fullAmount = S4_price + S6_price + S8_price;

                    //System.out.println(S6);
                   /* System.out.println(S6_price);
                    System.out.println(S8_price);

                    System.out.println(S4_price + S6_price + S8_price);
                    System.out.println(tb_yal_4_prc + tb_yal_6_prc + tb_yal_8_prc);*/

                    if(S4 > tb_yal_4_avl){
                        Seat4.setError("Your request cannot be fulfilled.please reduce the vehicle count!");
                        return;

                    }
                    else if(S6 > tb_yal_6_avl){
                        System.out.println("is this run???????");
                        Seat6.setError("Your request cannot be fulfilled.please reduce the vehicle count!");
                        return;
                    }
                    else if(S8 > tb_yal_8_avl){
                        Seat8.setError("Your request cannot be fulfilled.please reduce the vehicle count!");
                        return;
                    }


                }else if(park1.equals("Wilpaththuwa National Park")){

                    S4_price = S4 * tb_wil_4_prc;
                    S6_price = S6 * tb_wil_6_prc;
                    S8_price = S8 * tb_wil_8_prc;
                    fullAmount = S4_price + S6_price + S8_price;

                    if(S4 > tb_wil_4_avl){
                        Seat4.setError("Your request cannot be fulfilled.please reduce the vehicle count!");
                        return;
                    }
                    else if(S6 > tb_wil_6_avl){
                        Seat6.setError("Your request cannot be fulfilled.please reduce the vehicle count!");
                        return;
                    }
                    else if(S8 > tb_wil_8_avl){
                        Seat8.setError("Your request cannot be fulfilled.please reduce the vehicle count!");
                        return;
                    }

                }

                Tot_vehicle = S4 + S6 + S8;


                String s4seat = String.valueOf(S4);
                String s6eat = String.valueOf(S6);
                String s8seat = String.valueOf(S8);


                V_Booking v_booking = new V_Booking(userid,id,s4seat, s6eat, s8seat, newdate,park1 , Tot_vehicle, fullAmount);


                reff.child(id).setValue(v_booking).addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful()) {
                            updateavailablevehicle();
                            Toast.makeText(MainActivity.this, "Your order success", Toast.LENGTH_SHORT).show();
                        } else
                            Toast.makeText(MainActivity.this, " Your order Unsuccessful ", Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });

    }

    public void updateavailablevehicle(){
        String num4seat = Seat4.getText().toString().trim();
        String num6seat = Seat6.getText().toString().trim();
        String num8seat = Seat8.getText().toString().trim();
        String park1 = place.getSelectedItem().toString();

        if(num4seat.isEmpty())
        {
            S4 = 0;
        }else{
            S4 = Integer.parseInt(num4seat);
        }
        if(num6seat.isEmpty())
        {
            S6 = 0;
        }else{
            S6 =Integer.parseInt(num6seat);
        }
        if(num8seat.isEmpty()){
            S8 = 0;
        }else{
            S8 =Integer.parseInt(num8seat);
        }


        switch (park1) {
            case "Minneriya National Park": {

                long new_4 = tb_min_4_avl - S4;
                long new_6 = tb_min_6_avl - S6;
                long new_8 = tb_min_8_avl - S8;
                min_avail.child("4_seat_v").setValue(new_4);
                min_avail.child("6_seat_v").setValue(new_6);
                min_avail.child("8_seat_v").setValue(new_8);


                break;
            }
            case "Yala National Park": {

                long new_4 = tb_yal_4_avl - S4;
                long new_6 = tb_yal_6_avl - S6;
                long new_8 = tb_yal_8_avl - S8;
                yala_avail.child("4_seat_v").setValue(new_4);
                yala_avail.child("6_seat_v").setValue(new_6);
                yala_avail.child("8_seat_v").setValue(new_8);


                break;
            }
            case "Wilpaththuwa National Park": {

                long new_4 = tb_wil_4_avl - S4;
                long new_6 = tb_wil_6_avl - S6;
                long new_8 = tb_wil_8_avl - S8;
                wil_avail.child("4_seat_v").setValue(new_4);
                wil_avail.child("6_seat_v").setValue(new_6);
                wil_avail.child("8_seat_v").setValue(new_8);

                break;
            }
        }

    }
    private void updateLabel() {

        String myFormat = "MM/dd/yy"; //In which you need put here
        SimpleDateFormat sdf = new SimpleDateFormat(myFormat,
                Locale.US);

        s_date.setText(sdf.format(myCalendar.getTime()));
    }

}
