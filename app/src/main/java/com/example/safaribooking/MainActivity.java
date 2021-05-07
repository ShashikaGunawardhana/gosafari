package com.example.safaribooking;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
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


public class MainActivity extends AppCompatActivity {

    EditText Seat4, Seat6, Seat8, date;
    Button btn1;
    Spinner place;
    V_Booking v_booking;
    String userID,userid,email;
    long maxID = 0;
    FirebaseAuth fauth = FirebaseAuth.getInstance();
    DatabaseReference reff;

    AwesomeValidation awesomeValidation;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        Seat4 = findViewById(R.id.s_4input);
        Seat6 = findViewById(R.id.s_6input);
        Seat8 = findViewById(R.id.s_8input);
        place = findViewById(R.id.s_placeInput);
        btn1 = findViewById(R.id.s_bookBtn);
        date = findViewById(R.id.s_date);
        userid = getIntent().getStringExtra("keyuserID");
        email = getIntent().getStringExtra("keyEmail");




        v_booking = new V_Booking();

        reff = FirebaseDatabase.getInstance().getReference("Order").child("SafariBooking").child(userid);

        /*reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    maxID = (snapshot.getChildrenCount());
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });*/

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v){

                userID = fauth.getCurrentUser().getUid();
                String id = reff.push().getKey();



                int S4 = Integer.parseInt(Seat4.getText().toString().trim());
                int S6 = Integer.parseInt(Seat6.getText().toString().trim());
                int S8 = Integer.parseInt(Seat8.getText().toString().trim());

                int Tot_vehicle = S4 + S6 + S8;

                double S4_price = S4 * 3000;
                double S6_price = S6 * 6000;
                double S8_price = S6 * 9000;

                double fullAmount = S4_price + S6_price + S8_price;
                String s4seat = String.valueOf(S4);
                String s6eat = String.valueOf(S6);
                String s8seat = String.valueOf(S8);
                long SafKeyValue = maxID + 1;

                V_Booking v_booking = new V_Booking(userid,id,s4seat, s6eat, s8seat, date.getText().toString(), place.getSelectedItem().toString(), Tot_vehicle, fullAmount);


                reff.child(id).setValue(v_booking).addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                        if (task.isSuccessful())
                            Toast.makeText(MainActivity.this, "Your order success", Toast.LENGTH_SHORT).show();
                        else
                            Toast.makeText(MainActivity.this, " Your order Unsuccessful ", Toast.LENGTH_SHORT).show();
                    }

                });

            }
        });





    }





    }
