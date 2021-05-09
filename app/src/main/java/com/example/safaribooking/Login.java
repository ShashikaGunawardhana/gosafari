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
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class Login extends AppCompatActivity {

    Button loginbtn;
    EditText memail, mpassword;
    ProgressBar prologin;
    TextView signup,forgetpass;
    FirebaseAuth fauth = FirebaseAuth.getInstance();
    String userID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        getSupportActionBar().hide();

        loginbtn = findViewById(R.id.d_btnlogin);
        memail = findViewById(R.id.d_loginAddress);
        mpassword = findViewById(R.id.d_loginpassword);
        prologin = findViewById(R.id.progresslogin);
        signup = findViewById(R.id.d_signup_txt);
        forgetpass = findViewById(R.id.d_forgtpass);

        forgetpass.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),ForgetPassword.class));
            }
        });

        signup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(getApplicationContext(),Register.class));
            }
        });

        loginbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = memail.getText().toString().trim();
                String password = mpassword.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    memail.setError("Email is Required!");
                    return;
                }
                if(TextUtils.isEmpty(password)){
                    mpassword.setError("Password is Required!");
                    return;
                }
                if(password.length() < 5){
                    mpassword.setError("Password Must be Greater Than 4 characters!");
                    return;
                }

                prologin.setVisibility(View.VISIBLE);

                //Authenticate the user
                fauth.signInWithEmailAndPassword(email,password).addOnCompleteListener(new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Toast.makeText(Login.this,"Logged in Successfully!",Toast.LENGTH_LONG).show();
                            userID = fauth.getCurrentUser().getUid();
                            startActivity(new Intent(getApplicationContext(),mainpage.class).putExtra("keyEmail",email).putExtra("keyuserID",userID));



                        }else{
                            Toast.makeText(Login.this,"Error! " + task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            prologin.setVisibility(View.GONE);
                        }
                    }
                });
            }
        });


    }
}