package com.example.myticket;

import android.content.Context;
import android.content.Intent;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class Adapter extends RecyclerView.Adapter<Adapter.ViewHolder> {
    List<Model> models;
    Context context;
    public Adapter(List<Model> models, Context context) {
        this.models = models;
        this.context=context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.movies_list,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Model m=models.get(position);
        holder.m_title.setText(m.getTitle());
        Picasso.get()
                .load("https://image.tmdb.org/t/p/w500"+m.getPoster_path())
                .resize(300,400)
                .into(holder.m_image);
        holder.b_movie.setOnClickListener(v -> {
            Intent i=new Intent(context,ShowMovie.class);
            i.putExtra("m_id",m.getId());
            i.putExtra("m_title",m.getTitle());
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(i);
        });

    }

    @Override
    public int getItemCount() {
        return models.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView m_title;
        ImageView m_image;
        Button b_movie;
        View  home_layout;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            m_title=itemView.findViewById(R.id.m_title);
            m_image=itemView.findViewById(R.id.m_image);
            b_movie=itemView.findViewById(R.id.b_movie);
            home_layout=itemView.findViewById(R.id.home_layout);
        }
    }

}
