package com.example.newsapi.Data.RetrofitResponse;

import java.util.List;

public class ReturnResponse {
    private List<News> articles;

    public List<News> getArticles() {
        return articles;
    }

    public void setArticles(List<News> articles) {
        this.articles = articles;
    }
}
