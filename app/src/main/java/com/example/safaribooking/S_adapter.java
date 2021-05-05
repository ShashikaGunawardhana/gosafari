package com.example.safaribooking;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

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

        String amount = String.valueOf(model.getFullamount());
        holder.amount.setText(amount);

        holder.park.setText(model.getPlace());



        String S4= String.valueOf(model.getSeat4());
        holder.seat4.setText(S4);

        String S6 = String.valueOf(model.getSeat6());
        holder.seat6.setText(S6);

        String S8 = String.valueOf(model.getSeat8());
        holder.seat8.setText(S6);

        String tot = String.valueOf(model.getTot_vehicle());
        holder.total.setText(tot);





    }

    @Override
    public int getItemCount() {
        return mylist.size();
    }

    public static class MyViweHolder extends RecyclerView.ViewHolder{

        TextView seat4,seat6,seat8,park,total,amount,date;
        public MyViweHolder(@NonNull View itemView) {
            super(itemView);

            seat4=itemView.findViewById(R.id.S4_txt);
            seat6=itemView.findViewById(R.id.S6_txt);
            seat8=itemView.findViewById(R.id.S8_txt);
            park=itemView.findViewById(R.id.park_txt);
            total=itemView.findViewById(R.id.Total_txt);
            amount=itemView.findViewById(R.id.totalAmount_txt);
            date=itemView.findViewById(R.id.S_date_txt);

        }
    }





}
