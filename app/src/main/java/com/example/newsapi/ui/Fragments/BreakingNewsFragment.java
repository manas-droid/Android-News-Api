package com.example.newsapi.ui.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Lifecycle;
import androidx.lifecycle.LifecycleEventObserver;
import androidx.lifecycle.LifecycleOwner;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapi.Data.RetrofitResponse.News;
import com.example.newsapi.R;
import com.example.newsapi.ui.Adapters.BreakingNewsAdapter;
import com.example.newsapi.ui.ViewModels.BreakingNewsViewModel;

import java.util.List;

public class BreakingNewsFragment extends Fragment {
    private BreakingNewsViewModel breakingNewsViewModel;
    private Observer<List<News>> observer;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    private static final String TAG = "BreakingNewsFragment";
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        breakingNewsViewModel = new ViewModelProvider(getActivity(),
                ViewModelProvider.AndroidViewModelFactory.getInstance(getActivity().getApplication())).get(BreakingNewsViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view =  inflater.inflate(R.layout.fragment_breaking_news, container , false);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recyclerView);


        recyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        observer = news -> {
            if(news!=null){
                progressBar.setVisibility(View.GONE);
                BreakingNewsAdapter breakingNewsAdapter = new BreakingNewsAdapter(news , "BreakingNewsFragment");
                recyclerView.setAdapter(breakingNewsAdapter);
            }else{
                progressBar.setVisibility(View.VISIBLE);
            }
        };

        breakingNewsViewModel.getMutableLiveDataNews().observe(getActivity(), observer);
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

    }

}
