package com.example.newsaplication.ConnectAPI;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsaplication.R;

import java.util.List;

public class AdapterRetrofitLokal extends RecyclerView.Adapter<AdapterRetrofitLokal.ViewHolder> {

   private List<ModelNewsLokal> newsLokals;

    public AdapterRetrofitLokal( List<ModelNewsLokal> newsLokals) {
        Log.d("AdapterRetrofitLokal", "Initializing newsLokals with " + newsLokals.size() + " items.");
        this.newsLokals = newsLokals;
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.headlinenews,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        ModelNewsLokal newsLokal = newsLokals.get(position);

        holder.Judul.setText(newsLokal.getJudulberita());
        holder.Deskripsi.setText(newsLokal.getDeskripsiberita());
        holder.Penulis.setText(newsLokal.getPenulis());
    }

    @Override
    public int getItemCount() {
        if (newsLokals != null) {
            return newsLokals.size();
        } else {
            return 0;
        }
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        TextView Judul,Deskripsi,Penulis;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            Judul = itemView.findViewById(R.id.judulberita);
            Deskripsi=itemView.findViewById(R.id.deskripsiberita);
            Penulis=itemView.findViewById(R.id.authorsnews);
        }
    }

}
