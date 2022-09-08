package com.example.myticket;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.ImageView;

public class Help extends AppCompatActivity {

    ImageView back_btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help);
        back_btn=findViewById(R.id.back_btn);
        back_btn.setOnClickListener(V->{super.onBackPressed();});
    }
}