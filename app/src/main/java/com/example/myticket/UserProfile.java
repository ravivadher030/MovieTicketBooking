package com.example.myticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.squareup.picasso.Picasso;

public class UserProfile extends AppCompatActivity {

    TextView user_name;
    ImageView user_image;
    private FirebaseAuth mAuth;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_profile);
        mAuth=FirebaseAuth.getInstance();
        FirebaseUser user=mAuth.getCurrentUser();
        user_name=findViewById(R.id.user_name);
        user_image=findViewById(R.id.user_image);
        if(user!=null){
            user_name.setText(user.getDisplayName().toString());
            Picasso.get()
                    .load(user.getPhotoUrl())
                    .into(user_image);
        }
    }
}