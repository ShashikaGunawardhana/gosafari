package com.example.safaribooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Register extends AppCompatActivity {

    Button register_u;
    EditText mname,memail,mcontact,mpassword,mconfirmpwd;
    ProgressBar mprogress;
    FirebaseAuth fauth;
    TextView mloginhere;
    DatabaseReference reff;
    String userID;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        //get the value and assign to the variables
        register_u = (Button)findViewById(R.id.d_regBtn);
        mname = (EditText)findViewById(R.id.d_loginAddress);
        memail = (EditText)findViewById(R.id.d_email);
        mcontact =(EditText)findViewById(R.id.d_contact);
        mpassword = (EditText)findViewById(R.id.d_loginpassword);
        mconfirmpwd = (EditText)findViewById(R.id.d_confrimpasswrd);
        mloginhere = (TextView)findViewById(R.id.d_loging_here);
        fauth = FirebaseAuth.getInstance();
        mprogress = findViewById(R.id.regProgress);

        reff = FirebaseDatabase.getInstance().getReference("User");

       /* if(fauth.getCurrentUser() != null){
            //Toast.makeText(Register.this,"User Already exists!",Toast.LENGTH_LONG).show();
            startActivity(new Intent(getApplicationContext(),mainpage.class));
            finish();
        }*/
        mloginhere.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Login.class));

            }
        });

        //storing data
        register_u.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = mname.getText().toString().trim();
                String email = memail.getText().toString().trim();
                String contact =mcontact.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                String confirmpwd = mconfirmpwd.getText().toString().trim();

                //form Validation
                if(TextUtils.isEmpty(name)){
                    mname.setError("Name is Required!");
                    return;
                }
                if(TextUtils.isEmpty(email)){
                    memail.setError("Email is Required!");
                    return;
                }
                if(TextUtils.isEmpty(contact)){
                    mcontact.setError("Contact Number is Required!");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Password is Required!");
                    return;
                }
                if(TextUtils.isEmpty(confirmpwd)){
                    mconfirmpwd.setError("Confirm Password is Required!");
                    return;
                }
                if(password.length() < 5){
                    mpassword.setError("Password Must be Greater Than 4 characters!");
                    return;
                }
                if(!(password.equals(confirmpwd))){
                    mpassword.setError("Password and Confirmation Password Should be the Same.!");
                    return;
                }

                mprogress.setVisibility(View.VISIBLE);

                // register the user to the firbase
                fauth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful())
                        {
                            //email verification
                            FirebaseUser fUser = fauth.getCurrentUser();
                            fUser.sendEmailVerification().addOnSuccessListener(new OnSuccessListener<Void>() {
                                @Override
                                public void onSuccess(Void aVoid) {
                                    Toast.makeText(Register.this,"Verification email has been sent! ",Toast.LENGTH_LONG).show();
                                }
                            }).addOnFailureListener(new OnFailureListener() {
                                @Override
                                public void onFailure(@NonNull Exception e) {
                                    Toast.makeText(Register.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                                }
                            });


                            userID = fauth.getCurrentUser().getUid();
                            UserRegister regUser = new UserRegister(name,email,password,contact,userID);
                            reff.child(String.valueOf(userID)).setValue(regUser);
                            Toast.makeText(Register.this,"User Created!",Toast.LENGTH_LONG).show();

                            startActivity(new Intent(getApplicationContext(),mainpage.class).putExtra("keyEmail",email).putExtra("keyuserID",userID));

                        }else{

                            Toast.makeText(Register.this,"Error! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            mprogress.setVisibility(View.GONE);
                        }
                    }
                });

            }
        });


    }
}