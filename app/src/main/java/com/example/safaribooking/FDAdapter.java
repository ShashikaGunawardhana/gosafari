package com.example.safaribooking;

import android.app.Activity;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.List;

public class FDAdapter extends ArrayAdapter<AddComment> {
    private Activity context;
    private List<AddComment> commentList;
    private String email;

    public FDAdapter (Activity context,List<AddComment> commentList,String email){
        super(context,R.layout.fd_layout,commentList);
        this.context = context;
        this.commentList = commentList;
        this.email = email;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        TextView fedDelete;

        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.fd_layout,null,true);
        TextView textuID= (TextView)listViewItem.findViewById(R.id.fd_userID);
        TextView textemail= (TextView)listViewItem.findViewById(R.id.fd_email);
        TextView textcomment= (TextView)listViewItem.findViewById(R.id.fd_comment);
        TextView textcID= (TextView)listViewItem.findViewById(R.id.fd_commnt_key);
        TextView prevEmail = (TextView)listViewItem.findViewById(R.id.fd_deleteBtn);
        fedDelete = (TextView) listViewItem.findViewById(R.id.fd_deleteBtn);

        AddComment addComment = commentList.get(position);

        textuID.setText(addComment.getUserID());
        //get currnt user email
        String newEmail = String.valueOf(addComment.getEmail());
        textemail.setText(addComment.getEmail());

        textcomment.setText(addComment.getComment());
        textcID.setText(addComment.getCommentKey());
        if(email.equals(newEmail))
       // if ((email != null) && email.equalsIgnoreCase(newEmail){
            prevEmail.setText("Delete");
      //  }


        fedDelete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String euid = textuID.getText().toString();
                String eemail = textemail.getText().toString();
                String ecomment = textcomment.getText().toString();
                String ecid = textcID.getText().toString();

                AddComment addComment1 = new AddComment(ecomment,euid,ecid,eemail);
                DatabaseReference dbref = FirebaseDatabase.getInstance().getReference().child("FeedBack").child(addComment.getCommentKey());
                Task<Void> mTsk = dbref.removeValue();
                //Toast.makeText(context,"Remove Succesfully!",Toast.LENGTH_SHORT).show();
                mTsk.addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        context.startActivity(new Intent(context, Feedback.class).putExtra("keyuserID", euid).putExtra("keyEmail",eemail));
                        Toast.makeText(context,"Remove Succesfully!",Toast.LENGTH_SHORT).show();
                        //showToast("Deleted Success!");
                    }
                }).addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(context,"UnSuccessfull",Toast.LENGTH_SHORT).show();
                    }
                });

            }
        });

        return listViewItem;

    }
}
