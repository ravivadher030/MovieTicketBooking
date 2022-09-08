package com.example.myticket;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.res.ColorStateList;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.Layout;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;

import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.navigation.NavigationBarView;

public class MainActivity extends AppCompatActivity {
    BottomNavigationView bnv;
    LinearLayout layout;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(Build.VERSION.SDK_INT>=21) {
            getWindow().setStatusBarColor(getResources().getColor(R.color.black));
        }
        layout=findViewById(R.id.status);
        bnv=findViewById(R.id.bottom_nav);
        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Home()).commit();
        ColorStateList ctl=new ColorStateList(new int[][]{
                new int[]{-android.R.attr.state_checked},
                new int[]{android.R.attr.state_checked}
        },new int[]{
                Color.parseColor("#B21807"),
                Color.parseColor("#FF0000")
        });

        bnv.setItemIconTintList(ctl);
        bnv.setItemTextColor(ctl);
        bnv.setOnItemSelectedListener(new NavigationBarView.OnItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem item) {
                switch(item.getItemId()){
                    case R.id.home:
                        bnv.getItemTextColor();
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Home()).commit();
                        return true;
                    case R.id.buzz:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Buzz()).commit();
                        return true;
                    case R.id.profile:
                        getSupportFragmentManager().beginTransaction().replace(R.id.container,new Profile()).commit();
                        return true;
                }
                return false;
            }
        });
    }
}
