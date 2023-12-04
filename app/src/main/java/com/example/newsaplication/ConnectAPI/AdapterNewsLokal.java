package com.example.newsaplication.ConnectAPI;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsaplication.R;

import java.util.ArrayList;

public class AdapterNewsLokal extends RecyclerView.Adapter<AdapterNewsLokal.ViewHolder> {
    Context context;
    ArrayList<ModelNewsLokal> newsLokals;

    public  AdapterNewsLokal(Context context,ArrayList<ModelNewsLokal> newsLokals){
        this.context = context;
        this.newsLokals = newsLokals;
    }
    @NonNull
    @Override
    public AdapterNewsLokal.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View view= LayoutInflater.from(context).inflate(R.layout.headlinenews,parent,false);
       return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AdapterNewsLokal.ViewHolder holder, int position) {
        ModelNewsLokal  model = newsLokals.get(position);

        holder.judul.setText(model.getJudulberita());
        holder.deskripsi.setText(model.getDeskripsiberita());
        holder.penulis.setText(model.getPenulis());
    }

    @Override
    public int getItemCount() {
        return newsLokals.size();
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public  TextView judul,deskripsi,penulis;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            judul=itemView.findViewById(R.id.judulberita);
            deskripsi=itemView.findViewById(R.id.deskripsiberita);
            penulis=itemView.findViewById(R.id.authorsnews);
        }
    }
}
