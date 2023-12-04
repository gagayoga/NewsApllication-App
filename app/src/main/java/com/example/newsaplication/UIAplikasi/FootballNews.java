package com.example.newsaplication.UIAplikasi;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;


import com.example.newsaplication.R;
import com.example.newsaplication.RetrofitAPI.ConnectAPI;
import com.example.newsaplication.RetrofitAPI.InterfaceRetrofit;
import com.example.newsaplication.RetrofitAPI.NewsAdapter;
import com.example.newsaplication.RetrofitAPI.NewsModel;
import com.example.newsaplication.RetrofitAPI.NewsResponseModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class FootballNews extends AppCompatActivity {
    InterfaceRetrofit interfaec;
    List<NewsModel> listNews;
    RecyclerView rvNews;
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_news);

        interfaec = ConnectAPI.getHeadlineNews().create(InterfaceRetrofit.class);

        rvNews = findViewById(R.id.recyle_view);
        rvNews.setLayoutManager(new LinearLayoutManager(this));

        getDataFromApi();
    }

    private void getDataFromApi() {
        Call<NewsResponseModel> newsCall = interfaec.newsHeadline();
        newsCall.enqueue(new Callback<NewsResponseModel>() {
            @Override
            public void onResponse(Call<NewsResponseModel> call, Response<NewsResponseModel> response) {
                if (response.isSuccessful()) {
                    NewsResponseModel newsResponse = response.body();
                    if (newsResponse != null && newsResponse.getArticles() != null) {
                        listNews = newsResponse.getArticles();
                        // ambil 10 artikel pertama
                        if (listNews.size() > 20) {
                            listNews = listNews.subList(0, 20);
                        }
                        newsAdapter = new NewsAdapter(listNews);
                        rvNews.setAdapter(newsAdapter);
                        newsAdapter.notifyDataSetChanged();

                    } else {
                        Toast.makeText(FootballNews.this, "Empty response", Toast.LENGTH_SHORT).show();
                        Log.d("RESPONSE", "response: " + response.body().toString());
                    }
                } else {
                    Toast.makeText(FootballNews.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                }
            }

            @Override
            public void onFailure(Call<NewsResponseModel> call, Throwable t) {
                Toast.makeText(FootballNews.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }

    /**
    InterfaceRetrofit interfaec;
    List<NewsModel> listNews;
    RecyclerView rvNews;
    NewsAdapter newsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {


        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_football_news);

        new GetDataTask().execute();

        interfaec = ConnectAPI.getHeadlineNews().create(InterfaceRetrofit.class);

        rvNews = findViewById(R.id.recyle_view);
        rvNews.setLayoutManager(new LinearLayoutManager(this));

        Call<NewsResponseModel> newsCall = interfaec.newsHeadline();
        newsCall.enqueue(new Callback<NewsResponseModel>() {
            @Override
            public void onResponse(Call<NewsResponseModel> call, Response<NewsResponseModel> response) {
                NewsResponseModel newsResponse = response.body();
                listNews = newsResponse.getArticle();
                newsAdapter = new NewsAdapter(listNews);
                rvNews.setAdapter(newsAdapter);
                rvNews.setLayoutManager(new LinearLayoutManager(getApplicationContext()));
                newsAdapter.notifyDataSetChanged();
            }

            @Override
            public void onFailure(Call<NewsResponseModel> call, Throwable t) {
                Toast.makeText(FootballNews.this, "", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }
        });
    }
    private class GetDataTask extends AsyncTask<Void, Void, List<NewsModel>> {

        @Override
        protected List<NewsModel> doInBackground(Void... voids) {
            // Kode untuk mengambil data dari API
            // ...

            return listNews;
        }

        @Override
        protected void onPostExecute(List<NewsModel> listnews) {
            super.onPostExecute(listnews);

            // Set data ke adapter RecyclerView
            newsAdapter = new NewsAdapter(listnews);
            rvNews.setAdapter(newsAdapter);
        }
    }

     */

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                onBackPressed();
                return true;
            case R.id.share:
                Intent intent = new Intent(Intent.ACTION_SEND);
                intent.putExtra(intent.EXTRA_TEXT,"Read complete sports news only at Sports New, hurry up and download it now at www.sportsnews.id");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent,"Share to :"));
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}