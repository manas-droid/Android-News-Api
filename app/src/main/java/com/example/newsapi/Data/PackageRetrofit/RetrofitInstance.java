package com.example.newsapi.Data.PackageRetrofit;

import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

import static com.example.newsapi.Data.Commons.BASE_URL;

public class RetrofitInstance {
    private static Retrofit retrofit;
    private static final String TAG = "RetrofitInstance";
    public static Retrofit getInstance(){
        if(retrofit == null){
            retrofit = new Retrofit.Builder()
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build();
        }
        return retrofit;
    }

}
