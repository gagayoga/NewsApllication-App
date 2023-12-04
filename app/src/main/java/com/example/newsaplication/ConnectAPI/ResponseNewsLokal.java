package com.example.newsaplication.ConnectAPI;

import java.util.List;

public class ResponseNewsLokal {
    private List<ModelNewsLokal> News;

    public List<ModelNewsLokal> getNews() {
        return News;
    }

    public void setNews(List<ModelNewsLokal> news) {
        News = news;
    }
}
