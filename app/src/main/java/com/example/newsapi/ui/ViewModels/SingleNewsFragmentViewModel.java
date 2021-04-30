package com.example.newsapi.ui.ViewModels;

import android.app.Application;
import android.content.pm.ApplicationInfo;

import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.ViewModel;

import com.example.newsapi.Data.RetrofitResponse.News;
import com.example.newsapi.Database.NewsRepository;

public class SingleNewsFragmentViewModel extends AndroidViewModel {
    private NewsRepository newsRepository;

    public SingleNewsFragmentViewModel(Application application){
        super(application);
        newsRepository = new NewsRepository(application);
    }

    public void insert(News news){
        newsRepository.insert(news);
    }

    public void delete(int id){ newsRepository.delete(id);}

}
