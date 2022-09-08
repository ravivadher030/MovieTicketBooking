package com.example.myticket;

import android.content.Context;
import android.net.ConnectivityManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class BuzzAdapter extends RecyclerView.Adapter<BuzzAdapter.ViewHolder> {

    List<GetBuzzList> buzzList;
    Context context;

    public BuzzAdapter(List buzzList, Context context) {
        this.buzzList = buzzList;
        this.context = context;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.activity_buzz_layout, parent,false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        GetBuzzList list=buzzList.get(position);
        holder.title.setText(list.getTitle());
        Picasso.get()
                .load(list.getUrlToImage())
                .resize(200,200)
                .into(holder.img);
    }

    @Override
    public int getItemCount() {return buzzList.size();}

    public class ViewHolder extends RecyclerView.ViewHolder {
        ImageView img;
        TextView title;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            img=itemView.findViewById(R.id.img);
            title=itemView.findViewById(R.id.title);
        }
    }
}

