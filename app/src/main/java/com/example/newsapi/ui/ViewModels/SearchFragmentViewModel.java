package com.example.newsapi.ui.ViewModels;

import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.newsapi.Data.DataSource;
import com.example.newsapi.Data.RetrofitResponse.News;

import java.util.List;

public class SearchFragmentViewModel extends ViewModel implements DataSource.GetQuery {
    private MutableLiveData<List<News>> mutableLiveData;
    private DataSource dataSource;


    public SearchFragmentViewModel(){
        dataSource = new DataSource();
        mutableLiveData = new MutableLiveData<>();
        mutableLiveData.setValue(null);
    }
    public void requestSearchQuery(String query){
        dataSource.getSearchQuery(this, query, "de");
    }

    public MutableLiveData<List<News>> getMutableLiveData() {
        return mutableLiveData;
    }

    @Override
    public void getSearchResult(List<News> news) {
        mutableLiveData.setValue(news);
    }
}
