package com.example.newsapi.ui.Fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.bumptech.glide.Glide;
import com.example.newsapi.Data.RetrofitResponse.News;
import com.example.newsapi.R;

public class SingleNewsFragment extends Fragment {
        News singleNews;
        TextView title, description , content;
        ImageView imageView;
        public SingleNewsFragment(){
        }

        @Nullable
        @Override
        public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
                View view = inflater.inflate(R.layout.fragment_single_news, container, false);
                Bundle bundle = getArguments();
                singleNews = (News) bundle.getSerializable("Single News");

                title = view.findViewById(R.id.newsTitle);
                description = view.findViewById(R.id.newsDescription);
                content = view.findViewById(R.id.newsContent);
                imageView = view.findViewById(R.id.newsImage);


                title.setText(singleNews.getTitle());
                description.setText(singleNews.getDescription());
                content.setText(singleNews.getContent());

                Glide.with(view.getContext())
                        .load(singleNews.getUrlToImage())
                        .placeholder(R.drawable.ic_placeholder_image)
                        .into(imageView);

                return view;
        }
}
