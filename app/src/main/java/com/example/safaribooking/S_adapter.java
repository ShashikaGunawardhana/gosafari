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

public class S_adapter extends RecyclerView.Adapter<S_adapter.MyViweHolder> {


    ArrayList<S_model> mylist;
    Context context;

    public S_adapter(Context context, ArrayList<S_model> mylist){
        this.mylist=mylist;
        this.context=context;
    }

    @NonNull
    @Override
    public MyViweHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v= LayoutInflater.from(context).inflate(R.layout.safari,parent,false);
       return new MyViweHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViweHolder holder, int position) {
        S_model model=mylist.get(position);

        holder.date.setText(model.getDate());

        //String saforder = String.valueOf(model.getSafKeyValue());
        holder.saforder.setText(model.getSafKeyValue());

        String amount = String.valueOf(model.getFullamount());
        holder.amount.setText(amount);

        holder.park.setText(model.getPlace());



       // String S4= String.valueOf(model.getSeat4());
        holder.seat4.setText(model.getSeat4());

       // String S6 = String.valueOf(model.getSeat6());
        holder.seat6.setText(model.getSeat6());

       // String S8 = String.valueOf(model.getSeat8());
        holder.seat8.setText(model.getSeat8());

        String tot = String.valueOf(model.getTot_vehicle());
        holder.total.setText(tot);

        holder.safID.setText(model.getUserID());





    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public class MyViweHolder extends RecyclerView.ViewHolder{

        TextView seat4,seat6,seat8,park,total,amount,date,saforder,safID;
        Button delbtn;
        public MyViweHolder(@NonNull View itemView) {
            super(itemView);
            saforder = itemView.findViewById(R.id.saf_orderkey);
            seat4=itemView.findViewById(R.id.S4_txt);
            seat6=itemView.findViewById(R.id.S6_txt);
            seat8=itemView.findViewById(R.id.S8_txt);
            park=itemView.findViewById(R.id.park_txt);
            total=itemView.findViewById(R.id.Total_txt);
            amount=itemView.findViewById(R.id.totalAmount_txt);
            date=itemView.findViewById(R.id.S_date_txt);
            safID = itemView.findViewById(R.id.safuserID);
            delbtn = itemView.findViewById(R.id.deleteSaf);

            delbtn.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String useID = safID.getText().toString();
                    String park1 = park.getText().toString();
                    String vseat4 = seat4.getText().toString();
                    String vseat6 = seat6.getText().toString();
                    String vseat8 = seat8.getText().toString();
                    int totVehicl = Integer.parseInt(total.getText().toString().trim());
                    String date1 = date.getText().toString();
                    //long safKeyValue = Long.parseLong(saforder.getText().toString().trim());
                    String safKeyValue = saforder.getText().toString();
                    double fullsafAmount = Double.parseDouble(amount.getText().toString().trim());


                    //String tktKeyValue = orderID.getText().toString();




                    V_Booking v_booking = new V_Booking(useID,safKeyValue,vseat4,vseat6,vseat8,date1,park1,totVehicl,fullsafAmount);
                    DatabaseReference dbref = FirebaseDatabase.getInstance().getReference("Order").child("SafariBooking").child(useID).child(String.valueOf(v_booking.getSafKeyValue()));

                    Task<Void> mTsk = dbref.removeValue();
                    //Toast.makeText(context,"Remove Succesfully!",Toast.LENGTH_SHORT).show();
                    mTsk.addOnSuccessListener(new OnSuccessListener<Void>() {
                        @Override
                        public void onSuccess(Void aVoid) {
                            context.startActivity(new Intent(context, Safari_order.class).putExtra("keyuserID", useID));
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
