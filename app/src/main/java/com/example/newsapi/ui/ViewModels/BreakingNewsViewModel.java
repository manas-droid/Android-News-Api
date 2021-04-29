package com.example.newsapi.ui.ViewModels;

import android.util.Log;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapi.Data.DataSource;
import com.example.newsapi.Data.RetrofitResponse.News;

import java.util.List;

public class BreakingNewsViewModel extends ViewModel implements DataSource.GetNews {
    private MutableLiveData<List<News>> mutableLiveDataNews;
    private DataSource dataSource;
    private static final String TAG = "BreakingNewsViewModel";
    public BreakingNewsViewModel(){
        dataSource=new DataSource();
        mutableLiveDataNews = new MutableLiveData<>();
        mutableLiveDataNews.setValue(null);
        dataSource.getNews("de", this);
    }

    public MutableLiveData<List<News>> getMutableLiveDataNews() {
        return this.mutableLiveDataNews;
    }

    @Override
    public void getNews(List<News> news) {
        this.mutableLiveDataNews.setValue(news);
    }
}
