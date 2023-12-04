package com.example.newsaplication.RetrofitAPI;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.example.newsaplication.R;

import java.security.AccessController;
import java.util.List;

public class NewsAdapter extends RecyclerView.Adapter<NewsAdapter.ListViewHolder> {

    private List<NewsModel> listnews;
    public NewsAdapter(List<NewsModel> listnews){
        this.listnews = listnews;
    }
    @NonNull
    @Override
    public NewsAdapter.ListViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.listitem,parent,false);
        return  new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull NewsAdapter.ListViewHolder holder, int position) {
        NewsModel newsModel = listnews.get(position);
        // Tambahkan null check pada URL gambar
        if (newsModel.getUrlToImage() != null) {
            Glide.with(holder.itemView.getContext())
                    .load(newsModel.getUrlToImage())
                    .apply(new RequestOptions().override(800, 400))
                    .fitCenter()
                    .into(holder.ivnews);
        }
        else {
            // Menampilkan gambar placeholder
            Glide.with(holder.itemView.getContext())
                    .load(R.drawable.image)
                    .fitCenter()
                    .into(holder.ivnews);
        }
        holder.textjudul.setText(newsModel.getTittle());
        holder.textauthor.setText(newsModel.getAuthor());
    }

    @Override
    public int getItemCount() {
        // Mengambil jumlah item yang akan ditampilkan, yaitu 10 atau jumlah artikel yang tersedia jika kurang dari 10
        return Math.min(listnews.size(), 20);
    }

    public class ListViewHolder extends RecyclerView.ViewHolder {
        ImageView ivnews;
        TextView textjudul,textauthor;
        public  ListViewHolder(@NonNull View itemView){
            super(itemView);

            ivnews = itemView.findViewById(R.id.imageberita);
            textjudul = itemView.findViewById(R.id.judulberita);
            textauthor = itemView.findViewById(R.id.authorsnews);

        }
    }
}
