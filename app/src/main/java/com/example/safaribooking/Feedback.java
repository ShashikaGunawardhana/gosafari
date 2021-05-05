package com.example.safaribooking;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

public class Feedback extends AppCompatActivity {

    EditText comment;
    Button sendbtn,dltbtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        sendbtn = (Button)findViewById(R.id.d_feedbackBtn);
        dltbtn = (Button)findViewById(R.id.d_deletefeed);
        comment = (EditText) findViewById(R.id.d_feedInput);
    }
}