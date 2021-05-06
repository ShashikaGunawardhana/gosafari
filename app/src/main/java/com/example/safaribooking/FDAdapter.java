package com.example.safaribooking;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class FDAdapter extends ArrayAdapter<AddComment> {
    private Activity context;
    private List<AddComment> commentList;

    public FDAdapter (Activity context,List<AddComment> commentList){
        super(context,R.layout.fd_layout,commentList);
        this.context = context;
        this.commentList = commentList;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater = context.getLayoutInflater();
        View listViewItem = inflater.inflate(R.layout.fd_layout,null,true);
        TextView textuID= (TextView)listViewItem.findViewById(R.id.fd_userID);
        TextView textemail= (TextView)listViewItem.findViewById(R.id.fd_email);
        TextView textcomment= (TextView)listViewItem.findViewById(R.id.fd_comment);
        TextView textcID= (TextView)listViewItem.findViewById(R.id.fd_commnt_key);

        AddComment addComment = commentList.get(position);

        textuID.setText(addComment.getUserID());
        textemail.setText(addComment.getEmail());
        textcomment.setText(addComment.getComment());
        textcID.setText(addComment.getCommentKey());

        return listViewItem;

    }
}
