package com.example.myticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class PurchaseHistory extends AppCompatActivity {
    List<PurchaseModel> model=new ArrayList<>();
    UserTicketAdapter adapter;
    RecyclerView recyclerView;
    ImageView back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_purchase_history);

        back_btn=findViewById(R.id.back_btn);
        recyclerView=findViewById(R.id.rec_view);

        //On BackedPress
        back_btn.setOnClickListener(V->{super.onBackPressed();});

        FirebaseAuth mauth=FirebaseAuth.getInstance();
        String user=mauth.getCurrentUser().getUid();
        FirebaseDatabase db=FirebaseDatabase.getInstance();
        DatabaseReference ref=db.getReference().child(user);
        ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                for(DataSnapshot dataSnapshot : snapshot.getChildren()){
                    PurchaseModel ph=dataSnapshot.getValue(PurchaseModel.class);
                    model.add(ph);
                }adapter.notifyDataSetChanged();
            }
            @Override
            public void onCancelled(@NonNull DatabaseError error) {}
        });
            LinearLayoutManager layoutManager = new LinearLayoutManager(getApplicationContext(), LinearLayoutManager.VERTICAL, false);
            recyclerView.setLayoutManager(layoutManager);
            adapter = new UserTicketAdapter(model, getApplicationContext());
            recyclerView.setAdapter(adapter);
    }

    @Override
    public void onBackPressed() {
        Intent intent=new Intent(getApplicationContext(),Profile.class);
        startActivity(intent);
    }
}