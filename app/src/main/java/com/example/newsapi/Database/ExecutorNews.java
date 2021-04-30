package com.example.newsapi.Database;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ExecutorNews {
    public static ExecutorService executorService = null;

    public static ExecutorService getInstance(){
        if(executorService == null){
            executorService = Executors.newSingleThreadExecutor();
        }
        return executorService;
    }
}
