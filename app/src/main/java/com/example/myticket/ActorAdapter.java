package com.example.myticket;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import java.util.List;

public class ActorAdapter extends RecyclerView.Adapter<ActorAdapter.VHolder> {
    List<ActorList> al;
    Context context;

    public ActorAdapter(List<ActorList> al, Context context) {
        this.al = al;
        this.context = context;
    }

    @NonNull
    @Override
    public VHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v=LayoutInflater.from(context).inflate(R.layout.actor_list,parent,false);
        return new VHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull VHolder holder, int position) {
        ActorList alist=al.get(position);
        Picasso.get()
                .load(alist.getImage())
                .resize(200,200)
                .into(holder.actor_img);
        String[] a_n=alist.getName().split(" ");
        String[] as_a=alist.getAsCharacter().split(" ");
        String l_name=new String();
        if(a_n.length>=2){l_name=a_n[1];};
        holder.actor_name.setText(a_n[0]+" "+l_name);
        if(as_a.length>=2) {
            holder.as_actor.setText("as "+as_a[1]);
        }else{
            holder.as_actor.setText("as "+as_a[0]);
        }
    }

    @Override
    public int getItemCount() {
        return al.size();
    }

    public class VHolder extends RecyclerView.ViewHolder {
        ImageView actor_img;
        TextView actor_name,as_actor;
        public VHolder(@NonNull View v) {
            super(v);
            actor_img=v.findViewById(R.id.actor_img);
            actor_name=v.findViewById(R.id.actor_name);
            as_actor=v.findViewById(R.id.as_actor);
        }
    }
}
