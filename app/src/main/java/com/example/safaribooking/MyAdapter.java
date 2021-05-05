package com.example.safaribooking;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {

    ArrayList<Member> mList;
    Context context;


    public MyAdapter(Context context, ArrayList<Member> mList){
        this.mList = mList;
        this.context = context;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.item,parent,false);

        return new MyViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        Member member = mList.get(position);

        holder.park.setText(member.getPark());
        holder.customerType.setText(member.getCustomerType());
        holder.date.setText(member.getDate());

        String fulltkt = String.valueOf(member.getFullTicket());
        holder.full.setText(fulltkt);
        String halftkt = String.valueOf(member.getHalfTicket());
        holder.half.setText(halftkt);
        String fulltktAmount = String.valueOf(member.getFulltktAmount());
        holder.fulltot.setText(fulltktAmount);
        String halftot = String.valueOf(member.getHalftktAmount());
        holder.halftot.setText(halftot);
        String tot = String.valueOf(member.getTotalAmount());
        holder.tot.setText(tot);
        String norderId = String.valueOf(member.getTktKeyValue());
        holder.orderID.setText(norderId);


    }

    @Override
    public int getItemCount() {
        return mList.size();
    }


    public class MyViewHolder extends RecyclerView.ViewHolder{

        TextView park,customerType,full,half,fulltot,halftot,date,tot,orderID;
        Button deleteBtn;





        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            park = itemView.findViewById(R.id.park_txt);
            customerType = itemView.findViewById(R.id.type_txt);
            full = itemView.findViewById(R.id.fulltkt_txt);
            half = itemView.findViewById(R.id.halftkt_txt);
            fulltot = itemView.findViewById(R.id.fulltktAmount_txt);
            halftot = itemView.findViewById(R.id.halftktAmount_txt);
            date = itemView.findViewById(R.id.date_txt);
            tot = itemView.findViewById(R.id.totalAmount_txt);
            orderID = itemView.findViewById(R.id.orderID_txt);
            deleteBtn = itemView.findViewById(R.id.heloBtn);



            deleteBtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String park1 = park.getText().toString();
                    String type = customerType.getText().toString();
                    int fulltkt = Integer.parseInt(full.getText().toString().trim());
                    int halftkt = Integer.parseInt(half.getText().toString().trim());
                    double fulltktAmount = Double.parseDouble(fulltot.getText().toString().trim());
                    double halftktAmount = Double.parseDouble(halftot.getText().toString().trim());
                    double fullAmount = Double.parseDouble(fulltot.getText().toString().trim());
                    String date1 = date.getText().toString();
                    //String tktKeyValue = orderID.getText().toString();
                    long tktKeyValue = Long.parseLong(orderID.getText().toString().trim());



                    Member  delmember = new Member(tktKeyValue,park1,type,fulltkt,halftkt,date1,fulltktAmount,halftktAmount,fullAmount);
                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("SDK").child("ms").child("dk").child(String.valueOf(delmember.getTktKeyValue()));
                    Task<Void> mTsk = dbref.removeValue();
                    //Toast.makeText(context,"Remove Succesfully!",Toast.LENGTH_SHORT).show();
                    mTsk.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
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

        }
    }

}
