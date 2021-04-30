package com.example.newsapi.Database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.newsapi.Data.RetrofitResponse.News;

import java.util.List;

@Dao
public interface NewsDao {
    @Insert
    void insert(News news);

    @Query("SELECT * FROM news_database")
    LiveData<List<News>> getAllBookMarks();

    @Query("DELETE FROM news_database WHERE id = :id ")
    void delete(int id);
}