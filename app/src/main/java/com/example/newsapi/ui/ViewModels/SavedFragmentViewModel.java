package com.example.newsapi.ui.ViewModels;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.newsapi.Data.RetrofitResponse.News;
import com.example.newsapi.Database.NewsRepository;

import java.util.List;

public class SavedFragmentViewModel extends AndroidViewModel {
    NewsRepository newsRepository;
    LiveData<List<News>> listLiveData;

    public SavedFragmentViewModel(@NonNull Application application) {
        super(application);
        newsRepository = new NewsRepository(application);
        listLiveData = newsRepository.getAllBookMarks();
    }

    public LiveData<List<News>> getListLiveData() {
        return listLiveData;
    }
}
