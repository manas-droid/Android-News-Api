package com.example.newsapi.Database;

import android.app.Application;

import androidx.lifecycle.LiveData;

import com.example.newsapi.Data.RetrofitResponse.News;

import java.util.List;
import java.util.concurrent.ExecutorService;

public class NewsRepository {
    private NewsDao newsDao;
    private LiveData<List<News>> newsLiveData;
    ExecutorService executorService;
    private LiveData<List<News>> listLiveData;
    public NewsRepository(Application application){
        NewsDatabase newsDatabase = NewsDatabase.getInstance(application.getApplicationContext());
        newsDao = newsDatabase.newsDao();
        executorService = ExecutorNews.getInstance();
        listLiveData = newsDao.getAllBookMarks();
    }

    public void insert(News news){
        executorService.submit(() -> {
            newsDao.insert(news);
        });
    }

    public void delete(int id){
        executorService.submit(()->{
            newsDao.delete(id);
        });
    }

    public LiveData<List<News>> getAllBookMarks(){
        return this.listLiveData;
    }

}
