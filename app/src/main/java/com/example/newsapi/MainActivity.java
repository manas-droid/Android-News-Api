package com.example.newsapi;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;

import android.os.Bundle;

import com.example.newsapi.ui.Fragments.BreakingNewsFragment;
import com.example.newsapi.ui.Fragments.SavedFragment;
import com.example.newsapi.ui.Fragments.SearchFragment;
import com.google.android.material.bottomnavigation.BottomNavigationView;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigation);
        bottomNavigationView.setOnNavigationItemSelectedListener(navigationItemSelectedListener);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, new BreakingNewsFragment())
                .commit();
    }
    private BottomNavigationView.OnNavigationItemSelectedListener navigationItemSelectedListener
            = item -> {
        Fragment selectedFragment = null;

        switch (item.getItemId()){

            case R.id.breakingNews:
                selectedFragment = new BreakingNewsFragment();
                break;
            case R.id.savedNews:
                selectedFragment = new SavedFragment();
                break;
            case R.id.searchNews:
                selectedFragment = new SearchFragment();
                break;
            default: break;
        }

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.fragment_container, selectedFragment)
                .commit();
        return true;
    };
}