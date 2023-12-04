package com.example.newsaplication.UIAplikasi;

import static androidx.constraintlayout.helper.widget.MotionEffect.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsaplication.ConnectAPI.AdapterRetrofitLokal;
import com.example.newsaplication.ConnectAPI.ConnectAPILokal;
import com.example.newsaplication.ConnectAPI.ModelNewsLokal;
import com.example.newsaplication.ConnectAPI.NewsLokal;
import com.example.newsaplication.R;
import com.google.android.material.appbar.AppBarLayout;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class BasketNews extends AppCompatActivity {

    RecyclerView Rv;
    AdapterRetrofitLokal newsAdapter;
    List<ModelNewsLokal> list;
    NewsLokal news;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_basket_news);

        news = ConnectAPILokal.getClient().create(NewsLokal.class);
        Rv = findViewById(R.id.Recyle);
        Rv.setLayoutManager(new LinearLayoutManager(this));

        GetDataLokal();
    }

    private void GetDataLokal() {

            Call<List<ModelNewsLokal>> call = news.LokalNews();

        call.enqueue(new Callback<List<ModelNewsLokal>>() {
            @Override
            public void onResponse(Call<List<ModelNewsLokal>> call, Response<List<ModelNewsLokal>> response) {
                list = response.body();
                newsAdapter = new AdapterRetrofitLokal(list);
                Rv.setAdapter(newsAdapter);
            }

            @Override
            public void onFailure(Call<List<ModelNewsLokal>> call, Throwable t) {
                Toast.makeText(BasketNews.this, "Failed to retrieve data", Toast.LENGTH_SHORT).show();
                Log.e(TAG, "onFailure: ", t);
            }

        });
    }

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
                intent.putExtra(intent.EXTRA_TEXT, "Read complete sports news only at Sports New, hurry up and download it now at www.sportsnews.id");
                intent.setType("text/plain");
                startActivity(Intent.createChooser(intent, "Share to :"));
            default:
                return super.onOptionsItemSelected(item);
        }
    }
}