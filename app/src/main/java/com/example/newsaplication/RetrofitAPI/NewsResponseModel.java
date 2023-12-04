package com.example.newsaplication.RetrofitAPI;

import java.util.List;

public class NewsResponseModel {
    private String Status;
    private int Totalresults;
    private List<NewsModel> articles;

    public NewsResponseModel(List<NewsModel> articles) {
        this.articles = articles;
    }

    public String getStatus() {
        return Status;
    }

    public void setStatus(String status) {
        Status = status;
    }


    public List<NewsModel> getArticles() {
        return articles;
    }

    public void setArticles(List<NewsModel> articles) {
        this.articles = articles;
    }

    public int getTotalresults() {
        return Totalresults;
    }

    public void setTotalresults(int totalresults) {
        Totalresults = totalresults;
    }
}
