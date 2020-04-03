package com.example.phoebee.newsapp.data;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;

import com.example.phoebee.newsapp.model.NewsItem;


import java.util.List;

public class NewsItemViewModel extends AndroidViewModel {

    private static NewsItemRepository mRepository;
    private LiveData<List<NewsItem>> mAllNewsItem;

    public NewsItemViewModel(Application application) {
        super(application);
        mRepository = new NewsItemRepository(application);
        mAllNewsItem = mRepository.getmAllNewsItem();
    }

    public LiveData<List<NewsItem>> getmAllNewsItem(){
        return mAllNewsItem;
    }

    public static void syncURL(){
        mRepository.syncURL();
    }

}

