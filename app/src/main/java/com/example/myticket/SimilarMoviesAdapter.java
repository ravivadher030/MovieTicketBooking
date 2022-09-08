package com.example.myticket;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarMoviesAdapter extends RecyclerView.Adapter<SimilarMoviesAdapter.ViewHolder> {
    List<Similars> same;
    Context context;

    public SimilarMoviesAdapter(List<Similars> same, Context context) {
        this.same = same;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.similar_movies,parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Similars sm=same.get(position);
        Picasso.get()
                .load(sm.getImage())
                .resize(250,350)
                .into(holder.same_movie_img);
        holder.same_movie_name.setText(sm.getTitle());
        holder.same_movie_rate.setText(sm.getImDbRating());
        holder.same_movie_img.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i=new Intent(context,ShowMovie.class);
                i.putExtra("m_title",sm.getTitle());
                i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                context.startActivity(i);
            }
        });
    }

    @Override
    public int getItemCount() {
        return same.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView same_movie_img;
        TextView same_movie_name,same_movie_rate;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            same_movie_img=itemView.findViewById(R.id.same_movie_img);
            same_movie_name=itemView.findViewById(R.id.same_movie_name);
            same_movie_rate=itemView.findViewById(R.id.same_movie_rate);
        }
    }
}
