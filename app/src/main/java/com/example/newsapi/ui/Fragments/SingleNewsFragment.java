package com.example.newsapi.ui.Fragments;

import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.bumptech.glide.Glide;
import com.example.newsapi.Data.RetrofitResponse.News;
import com.example.newsapi.R;
import com.example.newsapi.ui.ViewModels.SingleNewsFragmentViewModel;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class SingleNewsFragment extends Fragment {

        News singleNews;
        TextView title, description , content;
        ImageView imageView;
        FloatingActionButton floatingActionButton;
        Context context;
        SingleNewsFragmentViewModel singleNewsFragmentViewModel;
        String cameFrom;
        public SingleNewsFragment(){
        }

        @Override
        public void onCreate(@Nullable Bundle savedInstanceState) {
                super.onCreate(savedInstanceState);
                singleNewsFragmentViewModel = new ViewModelProvider(getActivity(), ViewModelProvider.AndroidViewModelFactory
                        .getInstance(getActivity().getApplication())).get(SingleNewsFragmentViewModel.class);
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_single_news, container, false);
                Bundle bundle = getArguments();
                singleNews = (News) bundle.getSerializable("Single News");
                cameFrom = bundle.getString("Came From");
                context = view.getContext();
                title = view.findViewById(R.id.newsTitle);
                description = view.findViewById(R.id.newsDescription);
                content = view.findViewById(R.id.newsContent);
                imageView = view.findViewById(R.id.newsImage);


                floatingActionButton = view.findViewById(R.id.bookmark);


                return view;
        }

        @Override
        public void onActivityCreated(@Nullable Bundle savedInstanceState) {
                super.onActivityCreated(savedInstanceState);

                title.setText(singleNews.getTitle());
                description.setText(singleNews.getDescription());
                content.setText(singleNews.getContent());
                if(cameFrom == "SavedNewsFragment")
                        floatingActionButton.setImageResource(R.drawable.ic_bookmark_remove);
                else
                        floatingActionButton.setImageResource(R.drawable.ic_bookmark);


                Glide.with(context)
                        .load(singleNews.getUrlToImage())
                        .placeholder(R.drawable.ic_placeholder_image)
                        .into(imageView);

                if(cameFrom == "BreakingNewsFragment") {
                        floatingActionButton.setOnClickListener(v -> {
                                singleNewsFragmentViewModel.insert(singleNews);
                                Toast.makeText(getActivity(), "News Saved for Offline read", Toast.LENGTH_SHORT).show();
                        });
                }

                else{
                        floatingActionButton.setOnClickListener(v-> {
                                singleNewsFragmentViewModel.delete(singleNews.getId());
                                Toast.makeText(getActivity(), "News deleted from offline read", Toast.LENGTH_SHORT).show();
                        });
                }

        }
}
