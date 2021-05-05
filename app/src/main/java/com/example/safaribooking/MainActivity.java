package com.example.safaribooking;

import android.os.Bundle;
import android.text.TextUtils;
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
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;



public class MainActivity extends AppCompatActivity {

    EditText Seat4, Seat6, Seat8, date;
    Button btn1;
    Spinner place;
    V_Booking v_booking;

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


        awesomeValidation = new AwesomeValidation(ValidationStyle.BASIC);


        awesomeValidation.addValidation(this, R.id.s_date,
                RegexTemplate.NOT_EMPTY,R.string.validation);



        v_booking = new V_Booking();

        reff = FirebaseDatabase.getInstance().getReference("V_book");


        btn1.setOnClickListener(new View.OnClickListener() {
            @Override

            public void onClick(View v){









                          if(awesomeValidation.validate() ){

                              Toast.makeText(getApplicationContext(), "Form validation success", Toast.LENGTH_LONG).show();


                          }else{

                              Toast.makeText(getApplicationContext(), "Form validation Unsuccessful", Toast.LENGTH_LONG).show();
                          }




                int S4 = Integer.parseInt(Seat4.getText().toString().trim());
                int S6 = Integer.parseInt(Seat6.getText().toString().trim());
                int S8 = Integer.parseInt(Seat8.getText().toString().trim());

                int Tot_vehicle = S4 + S6 + S8;

                double S4_price = S4 * 3000;
                double S6_price = S6 * 6000;
                double S8_price = S6 * 9000;

                double fullAmount = S4_price + S6_price + S8_price;


                V_Booking v_booking = new V_Booking(Seat4.getText().toString(), Seat6.getText().toString(), Seat8.getText().toString(), date.getText().toString(), place.getSelectedItem().toString(), Tot_vehicle, fullAmount);


                reff.child(Seat4.getText().toString()).setValue(v_booking).addOnCompleteListener(MainActivity.this, new OnCompleteListener<Void>() {
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
