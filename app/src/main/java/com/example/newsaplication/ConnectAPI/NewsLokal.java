package com.example.newsaplication.ConnectAPI;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface NewsLokal {

    // Didalam get yang dipanggil nama yang ada di web api
    @GET("api/News")
    Call<List<ModelNewsLokal>> LokalNews();
}