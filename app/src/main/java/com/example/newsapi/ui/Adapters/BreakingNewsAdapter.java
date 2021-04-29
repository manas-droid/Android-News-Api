package com.example.newsapi.ui.Adapters;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.newsapi.Data.RetrofitResponse.News;
import com.example.newsapi.R;
import com.example.newsapi.ui.Fragments.SingleNewsFragment;

import java.util.List;

public class BreakingNewsAdapter extends RecyclerView.Adapter<BreakingNewsAdapter.BreakingNewsViewHolder> {
private List<News> news;
    private static final String TAG = "BreakingNewsAdapter";

    public BreakingNewsAdapter(List<News> news) {
        this.news = news;
    }

    @NonNull
    @Override
    public BreakingNewsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        View view = layoutInflater.inflate(R.layout.item_news_section, parent , false);
        return new BreakingNewsViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BreakingNewsViewHolder holder, int position) {
        News SingleNews = news.get(position);
        holder.description.setText(SingleNews.getDescription());
        holder.title.setText(SingleNews.getTitle());
        Glide.with(holder.itemView.getContext())
                .load(SingleNews.getUrlToImage())
                .placeholder(R.drawable.ic_placeholder_image)
                .into(holder.imageView);

        holder.constraintLayout.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putSerializable("Single News", SingleNews);

            SingleNewsFragment singleNewsFragment = new SingleNewsFragment();
            singleNewsFragment.setArguments(bundle);
            AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
            Log.d(TAG, "onBindViewHolder: "+v.getContext().toString());
            appCompatActivity.getSupportFragmentManager().beginTransaction().replace(R.id.fragment_container, singleNewsFragment)
                    .commit();
        });
    }

    @Override
    public int getItemCount() {
        return news.size();
    }


    public class BreakingNewsViewHolder extends RecyclerView.ViewHolder{
        private ImageView imageView;
        private TextView description, title;
        private ConstraintLayout constraintLayout;

        public BreakingNewsViewHolder(@NonNull View itemView) {
            super(itemView);
            imageView = itemView.findViewById(R.id.ivArticleImage);
            description = itemView.findViewById(R.id.tvDescription);
            title = itemView.findViewById(R.id.tvTitle);
            constraintLayout = itemView.findViewById(R.id.clicked);
        }
    }
}
