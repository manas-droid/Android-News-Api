package com.example.newsapi.ui.Fragments;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ProgressBar;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModel;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.newsapi.Data.RetrofitResponse.News;
import com.example.newsapi.R;
import com.example.newsapi.ui.Adapters.BreakingNewsAdapter;
import com.example.newsapi.ui.ViewModels.SavedFragmentViewModel;

import java.io.BufferedReader;
import java.util.List;

public class SavedFragment extends Fragment {

    private static final String TAG = "SavedFragment";
    SavedFragmentViewModel savedFragmentViewModel;
    private ProgressBar progressBar;
    private RecyclerView recyclerView;
    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        savedFragmentViewModel = new ViewModelProvider(getActivity(), ViewModelProvider.AndroidViewModelFactory
                .getInstance(getActivity().getApplication()))
                .get(SavedFragmentViewModel.class);

    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_saved_news,container ,false);
        progressBar = view.findViewById(R.id.progressBar);
        recyclerView = view.findViewById(R.id.recyclerView);
        recyclerView.setLayoutManager(new LinearLayoutManager(view.getContext()));
        return view;
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);

        savedFragmentViewModel.getListLiveData().observe(getActivity(), new Observer<List<News>>() {
            @Override
            public void onChanged(List<News> news) {
                BreakingNewsAdapter breakingNewsAdapter = new BreakingNewsAdapter(news , "SavedNewsFragment");
                recyclerView.setAdapter(breakingNewsAdapter);
            }
        });


    }
}
