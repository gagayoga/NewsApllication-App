package com.example.newsaplication.RetrofitAPI;

import retrofit2.Call;
import retrofit2.http.GET;

public interface InterfaceRetrofit {
    @GET("v2/top-headlines?country=id&category=sports&apiKey=287f7deb49e8425baaf381b034b41e92")
    Call<NewsResponseModel> newsHeadline();

}
