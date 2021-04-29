package com.example.newsapi.Data.PackageRetrofit;


import com.example.newsapi.Data.RetrofitResponse.News;
import com.example.newsapi.Data.RetrofitResponse.ReturnResponse;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface GetDataService {

    @GET("top-headlines")
    Call<ReturnResponse> getNews(@Query("country") String country , @Query("apiKey") String apiKey);
}
