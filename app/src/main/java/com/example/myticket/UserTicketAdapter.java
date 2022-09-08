package com.example.myticket;

import android.content.Context;
import java.text.SimpleDateFormat;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
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

import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class UserTicketAdapter extends RecyclerView.Adapter<UserTicketAdapter.ViewHolder> {
    List<PurchaseModel> model;
    Context context;

    public UserTicketAdapter(List<PurchaseModel> model, Context context) {
        this.model = model;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.activity_user_purchase,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        PurchaseModel m=model.get(position);
        Date d= Calendar.getInstance().getTime();
        SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        String[] days=m.getDate().split("-");
        String[] days1=df.format(d).split("-");
        int day=Integer.parseInt(days[0]);
        int day1=Integer.parseInt(days1[0]);
        if(day!=day1){
            holder.btn_remove.setVisibility(View.VISIBLE);
            holder.btn_remove.setOnClickListener(v->{
                FirebaseAuth mauth=FirebaseAuth.getInstance();
                String user=mauth.getCurrentUser().getUid();
                DatabaseReference ref= FirebaseDatabase.getInstance().getReference(user).child(m.getMovieName());
                ref.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot snapshot) {
                        snapshot.getRef().removeValue();
                        notifyDataSetChanged();
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError error) {

                    }
                });
            });
        }
        holder.date.setText("Date: " + m.getDate());
        holder.price.setText("Price: " + m.getPrice() + "");
        holder.ticketNo.setText("Total Seat: " + m.getSeatNo() + "");
        holder.title.setText("Movie Name: " + m.getMovieName());
    }

    @Override
    public int getItemCount() {
        return model.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView title,ticketNo,price,date;
        Button btn_remove;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date=itemView.findViewById(R.id.date);
            title=itemView.findViewById(R.id.title);
            ticketNo=itemView.findViewById(R.id.ticket);
            price=itemView.findViewById(R.id.price);
            btn_remove=itemView.findViewById(R.id.btn_remove);
        }
    }
}
