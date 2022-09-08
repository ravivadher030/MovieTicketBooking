package com.example.myticket;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

public class SelectSeat extends AppCompatActivity {
    ArrayList<SeatItem> items=new ArrayList<>();
    ImageView p_activity;
    TextView m_t;
    RecyclerView seatView;
    Button payment;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_select_seat);
        p_activity = findViewById(R.id.p_act);
        m_t = findViewById(R.id.m_t);
        seatView = findViewById(R.id.seat_list);
        payment = findViewById(R.id.payment);

        p_activity.setOnClickListener(v -> SelectSeat.super.onBackPressed());
        String title = getIntent().getStringExtra("m_title");
        m_t.setText(title);

        GridLayoutManager glm = new GridLayoutManager(getApplicationContext(), 10);
        //GridLayoutManager glm1=new GridLayoutManager(getApplicationContext(),4);
        SharedPreferences sharedPreferences=getSharedPreferences("MyTicket", Context.MODE_PRIVATE);
        boolean loggedIn=sharedPreferences.getBoolean("LoggedIn",false);
        /*List bookedTicket=new ArrayList<>();
        if(loggedIn==true){
            FirebaseAuth mAuth = FirebaseAuth.getInstance();
            String user=mAuth.getCurrentUser().getUid();
            DatabaseReference ref= FirebaseDatabase.getInstance().getReference(title);
            ref.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot snapshot) {
                    for(DataSnapshot dataSnapshot: snapshot.getChildren()){
                        if(dataSnapshot.exists()) {
                            bookedTicket.add(dataSnapshot.child("ticket").getValue(bookedTicket.getClass()));
                        }
                    }
                }
                @Override
                public void onCancelled(@NonNull DatabaseError error) {}
            });
        }else{
            Intent i=new Intent(getApplicationContext(),Login.class);
            startActivity(i);
        }*/
        totalseat(80);

        SeatAdapter sa = new SeatAdapter(items, getApplicationContext(),title);
        seatView.setLayoutManager(glm);
        seatView.setAdapter(sa);
            payment.setOnClickListener(V -> {
                if(loggedIn) {
                    if (sa.getSelectedSeat().size() != 0) {
                        /*FirebaseDatabase db = FirebaseDatabase.getInstance();
                        DatabaseReference ref = db.getReference();
                        DatabaseReference ref1 = db.getReference();
                        FirebaseAuth mAuth = FirebaseAuth.getInstance();
                        String user = mAuth.getCurrentUser().getUid();
                        String userName = mAuth.getCurrentUser().getEmail();
                        Date c= Calendar.getInstance().getTime();
                        SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
                        String date=df.format(c);
                        float price=sa.getSelectedSeat().size()*220;
                        UserTicketModel model = new UserTicketModel(userName, title, price, sa.getSelectedSeat(), date + "");
                        TicketModel ticketModel = new TicketModel(title, sa.getSelectedSeat());
                        ref.child(user).child(title).setValue(model);
                        ref1.child(title).setValue(ticketModel);
                        */
                        Intent intent= new Intent(getApplicationContext(),Payment.class);
                        int a[]=new int[3];
                        for(int i=0;i<sa.getSelectedSeat().size();i++){
                            a[i]=sa.getSelectedSeat().get(i);
                        }
                        intent.putExtra("m_title",title);
                        intent.putExtra("seats",a);
                        startActivity(intent);
                        //Toast.makeText(this, sa.getSelectedSeat().get(0)+"", Toast.LENGTH_SHORT).show();
                    } else {
                        Toast.makeText(this, "Please Select al least one seat!", Toast.LENGTH_SHORT).show();
                    }
                }else{
                    Intent in=new Intent(getApplicationContext(),Login.class);
                    startActivity(in);
                }
            });
    }
    private void totalseat(int n) {
        for(int i=1;i<=n;i++){
            items.add(new SeatItem(i));
        }
    }

}