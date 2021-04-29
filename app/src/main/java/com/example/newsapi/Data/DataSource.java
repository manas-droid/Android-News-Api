package com.example.newsapi.Data;

import android.os.Handler;
import android.os.Looper;
import android.util.Log;

import com.example.newsapi.Data.PackageRetrofit.GetDataService;
import com.example.newsapi.Data.PackageRetrofit.RetrofitInstance;
import com.example.newsapi.Data.RetrofitResponse.News;
import com.example.newsapi.Data.RetrofitResponse.ReturnResponse;

import java.io.IOException;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

import static com.example.newsapi.Data.Commons.apiKey;

public class DataSource {
    GetDataService getDataService;
    Handler handler;
    private static final String TAG = "DataSource";
    public DataSource(){
        getDataService = RetrofitInstance.getInstance().create(GetDataService.class);
        handler = new Handler(Looper.getMainLooper());
    }

    public void getNews(String countryCode , GetNews getNews){

        Call<ReturnResponse> dataServiceNews = getDataService.getNews(countryCode , apiKey);

        dataServiceNews.enqueue(new Callback<ReturnResponse>() {
            @Override
            public void onResponse(Call<ReturnResponse> call, Response<ReturnResponse> response) {
                if(response.isSuccessful()){
                   getNews.getNews(response.body().getArticles());
                }else{
                    try {
                        Log.d(TAG, "onResponse: "+response.errorBody().string());
                    } catch (IOException e) {
                        Log.d(TAG, "onResponse: IOException");
                        e.printStackTrace();
                    }
                }
            }

            @Override
            public void onFailure(Call<ReturnResponse> call, Throwable t) {

            }
        });

    }


    public interface GetNews{
        void getNews(List<News> news);
    }
}
