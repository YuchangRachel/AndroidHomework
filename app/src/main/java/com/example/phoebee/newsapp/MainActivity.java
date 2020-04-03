package com.example.phoebee.newsapp;

import android.arch.lifecycle.Observer;
import android.arch.lifecycle.ViewModelProvider;
import android.arch.lifecycle.ViewModelProviders;
import android.content.Context;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;

import com.example.phoebee.newsapp.adapter.NewsRecyclerViewAdapter;
import com.example.phoebee.newsapp.data.NewsItemViewModel;
import com.example.phoebee.newsapp.firebaseServer.NewsItemFireBaseJobDispatcher;
import com.example.phoebee.newsapp.model.NewsItem;

import java.util.List;

public class MainActivity extends AppCompatActivity{

    private static final String TAG = "MainActivity";


    private RecyclerView mRecyclerView;
    private NewsRecyclerViewAdapter mAdapter;
    private NewsItemViewModel mNewsItemViewModel;

    private List<NewsItem> mNewsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        mRecyclerView = (RecyclerView)findViewById(R.id.news_recyclerview);

        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(layoutManager);
        mRecyclerView.setHasFixedSize(true);


        mAdapter = new NewsRecyclerViewAdapter(MainActivity.this, mNewsList);
        mRecyclerView.setAdapter(mAdapter);

        //store db
        mNewsItemViewModel = ViewModelProviders.of(this).get(NewsItemViewModel.class);

        mNewsItemViewModel.getmAllNewsItem().observe(this, new Observer<List<NewsItem>>() {
            @Override
            public void onChanged(@Nullable List<NewsItem> newsItems) {
                mAdapter.setNewsList(newsItems);
            }
        });


        //schedule service when main activity load
        NewsItemFireBaseJobDispatcher.getNewsItemFireBaseJobService().ScheduleTask(this);

    }




    @Override
    public boolean onCreateOptionsMenu(Menu menu){
        getMenuInflater().inflate(R.menu.main,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int itemThatClickedId = item.getItemId();
        if (itemThatClickedId == R.id.action_search){
            mNewsItemViewModel.syncURL();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
}
