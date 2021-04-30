package com.example.newsapi.ui.Fragments;

import android.os.Bundle;
import android.os.CountDownTimer;
import android.os.SystemClock;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.SearchView;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapi.Data.RetrofitResponse.News;
import com.example.newsapi.Database.ExecutorNews;
import com.example.newsapi.R;
import com.example.newsapi.ui.Adapters.BreakingNewsAdapter;
import com.example.newsapi.ui.ViewModels.SearchFragmentViewModel;

import org.w3c.dom.Text;

import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class SearchFragment extends Fragment {
    SearchView searchView;
    ProgressBar progressBar;
    SearchFragmentViewModel searchFragmentViewModel;
    RecyclerView recyclerView;
    CountDownTimer cnt;
    TextView textView;
    private static final String TAG = "SearchFragment";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        searchFragmentViewModel = new ViewModelProvider(getActivity(),
                ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication()))
                .get(SearchFragmentViewModel.class);
    }


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_search_news,container ,false);
        searchView = view.findViewById(R.id.searchQuery);
        recyclerView = view.findViewById(R.id.recyclerView);
        progressBar = view.findViewById(R.id.progressBar);
        textView = view.findViewById(R.id.defaultText);
        return view;
    }


    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
       searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
           @Override
           public boolean onQueryTextSubmit(String query) {
               Log.d(TAG, "onQueryTextSubmit: "+query);

               return false;
           }

           @Override
           public boolean onQueryTextChange(String newText) {
                progressBar.setVisibility(View.VISIBLE);
                if(cnt!=null)
                    cnt.cancel();

                cnt = new CountDownTimer(500 ,1000 ) {
                    @Override
                    public void onTick(long millisUntilFinished) {

                    }
                    @Override
                    public void onFinish() {
                        searchFragmentViewModel.requestSearchQuery(newText);
                    }
                };
                cnt.start();
               return false;
           }
       });

       recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        searchFragmentViewModel.getMutableLiveData().observe(getActivity(), new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                if(news!=null && news.size()>0){
                    textView.setVisibility(View.GONE);
                    progressBar.setVisibility(View.GONE);
                    recyclerView.setVisibility(View.VISIBLE);
                    Log.d(TAG, "onChanged: "+news.get(0).getTitle());
                    BreakingNewsAdapter breakingNewsAdapter = new BreakingNewsAdapter(news, "BreakingNewsFragment");
                    recyclerView.setAdapter(breakingNewsAdapter);
                }else{
                    textView.setVisibility(View.VISIBLE);
                }
            }
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
    }
    
}
