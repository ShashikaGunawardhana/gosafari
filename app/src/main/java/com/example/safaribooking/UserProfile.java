package com.example.safaribooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class UserProfile extends AppCompatActivity {

    TextView uUsername,uEmail,uPhone,ulogOut;
    String userID,email;
    DatabaseReference reff;
    ImageView v1,v2,v3;
 //   FirebaseDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);

        userID = getIntent().getStringExtra("keyuserID");
        email = getIntent().getStringExtra("keyEmail");
        System.out.println(userID + email);
        uUsername = findViewById(R.id.u_userName);
        uEmail = findViewById(R.id.user_email_text);
        uPhone = findViewById(R.id.u_phone_txt);
        ulogOut = findViewById(R.id.u_logout_btn);
        v1 = findViewById(R.id.u_log_view);
        v2 = findViewById(R.id.u_phoneView);
        v3 = findViewById(R.id.email_view);


        //user.getId().equals(firebaseUser.getUid())
        //database = FirebaseDatabase.getInstance();
       // userref = database.getInstance().getReference("User").child(userID);

        reff = FirebaseDatabase.getInstance().getReference("User").child(userID);


        //if(user.getId() != null && user.getId().equals(firebaseUser.getUid())

        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
              //  for(DataSnapshot dataSnapshot: snapshot.getChildren()){

                       uEmail.setText(snapshot.child("email").getValue(String.class));
                        uUsername.setText(snapshot.child("name").getValue(String.class));
                        uPhone.setText(snapshot.child("phone").getValue(String.class));


               // }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });

        ulogOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(getApplicationContext(), Login.class));
            }
        });


    }
}