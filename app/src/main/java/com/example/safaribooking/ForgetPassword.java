package com.example.safaribooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;

public class ForgetPassword extends AppCompatActivity {

    EditText forgetMail;
    Button forgetBtn;
    ProgressBar frgtpro;
    FirebaseAuth fAuth;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_forget_password);

        forgetMail = (EditText)findViewById(R.id.d_forgetAddress);
        forgetBtn = (Button)findViewById(R.id.forgetbtn);
        frgtpro = findViewById(R.id.forgetProgress);

        fAuth = FirebaseAuth.getInstance();

        forgetBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                frgtpro.setVisibility(View.VISIBLE);
                String email = forgetMail.getText().toString().trim();
                if(TextUtils.isEmpty(email)){
                    forgetMail.setError("Email is Required!");
                    return;
                }
                fAuth.sendPasswordResetEmail(email).addOnCompleteListener(new OnCompleteListener<Void>() {
                    @Override
                    public void onComplete(@NonNull Task<Void> task) {
                            if(task.isSuccessful()){
                                frgtpro.setVisibility(View.GONE);
                                Toast.makeText(ForgetPassword.this,"Check Your Email!",Toast.LENGTH_LONG).show();
                            }else{
                                frgtpro.setVisibility(View.GONE);
                                Toast.makeText(ForgetPassword.this,task.getException().getMessage(),Toast.LENGTH_LONG).show();
                            }
                    }
                });
            }
        });


    }
}