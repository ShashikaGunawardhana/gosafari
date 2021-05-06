package com.example.safaribooking;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class Feedback extends AppCompatActivity {

    EditText comment;
    Button sendbtn;
    String userid,email;
    DatabaseReference reff;
  //  DatabaseReference fed;
    ListView listViewcomment;
    List <AddComment> commentList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feedback);

        sendbtn = (Button)findViewById(R.id.d_feedbackBtn);
        comment = (EditText) findViewById(R.id.d_feedInput);
        listViewcomment = (ListView)findViewById(R.id.listViewComment);
        commentList = new ArrayList<>();
        userid = getIntent().getStringExtra("keyuserID");
        email = getIntent().getStringExtra("keyEmail");

        reff = FirebaseDatabase.getInstance().getReference().child("FeedBack");
      //  fed = FirebaseDatabase.getInstance().getReference("FeedBack");

        sendbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addComment();
            }
        });

    }

    @Override
    protected void onStart() {
        super.onStart();
        reff.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                commentList.clear();
                for(DataSnapshot commentSnapshot : snapshot.getChildren()){

                     AddComment addComment = commentSnapshot.getValue(AddComment.class);
                     commentList.add(addComment);
                }
                FDAdapter adapter = new FDAdapter(Feedback.this,commentList);
                listViewcomment.setAdapter(adapter);
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

    private void addComment(){
        String uComment = comment.getText().toString().trim();
        String id = reff.push().getKey();

        if(!(TextUtils.isEmpty(uComment))){
            AddComment addComment = new AddComment(uComment, userid, id, email);
            reff.child(id).setValue(addComment);
            Toast.makeText(this,"Saved FeedBack",Toast.LENGTH_LONG).show();

        }else {
            comment.setError("Comment is Required!");
            return;
        }
    }
}