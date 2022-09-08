package com.example.myticket;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.ViewHolder> {
    List<SeatItem> list;
    Context context;
    String title;
    List<Integer> select = new ArrayList<>();
    int i = 1, t = -1;
    int k = 0;
    List bookedTicket = new ArrayList<>();
    List booked;

    public SeatAdapter(ArrayList<SeatItem> list, Context context, String title) {
        this.list = list;
        this.title = title;
        this.context = context;
    }
    String date;
    String [] b=new String[3];
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_seat_list, parent, false);
        return new ViewHolder(v);
    }
    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        if (i == 11)
            i = 1;
        holder.seat.setText("" + i++);
       /* DatabaseReference ref1 = FirebaseDatabase.getInstance().getReference(title).child("date");
        ref1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot){
                    if(snapshot!=null)
                        date=snapshot.getValue().toString();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });*/
        SharedPreferences sharedPreferences = context.getSharedPreferences("MyTicket", Context.MODE_PRIVATE);
        boolean loggedIn = sharedPreferences.getBoolean("LoggedIn", false);
        //if (loggedIn == true) {
            /*DatabaseReference ref = FirebaseDatabase.getInstance().getReference(title).child("ticket");
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    bookedTicket.add(snapshot.getValue());
                    booked=new ArrayList();
                    booked.add(bookedTicket.get(0));
                    b=booked.get(0).toString().split(",");
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });*/
        //} else {
           // Intent i = new Intent(context, Login.class);
            //context.startActivity(i);
        //}
        selectSeat(holder,position);
       // selectBooked(holder,position);
    }
    public void selectSeat(ViewHolder holder,int position){
        int m = 3;
        holder.seat.setOnClickListener(v -> {
            //Toast.makeText(context, ""+b[0], Toast.LENGTH_SHORT).show();
            if (k >= m) {
                Toast.makeText(context, "you can select maximum three seat only!", Toast.LENGTH_SHORT).show();
            } else {
                holder.seat.setBackground(context.getResources().getDrawable(R.drawable.seat_square_green));
                select.add(position);
                k++;
            }
        });

    }
    public void selectBooked(ViewHolder holder,int position){
        Date d= Calendar.getInstance().getTime();
        SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String da=df.format(d);
        /*for (int i = 0; i < booked.size(); i++) {
            int p = ((Number) booked.get(i)).intValue();
            if (p == position && da.equals(date)) {
                holder.seat.setBackground(context.getResources().getDrawable(R.drawable.seat_square_gray));
            }
        }*/
    }
    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        TextView seat;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            seat = itemView.findViewById(R.id.seat);
        }
    }

    public List<Integer> getSelectedSeat() {
        return select;
    }
}
