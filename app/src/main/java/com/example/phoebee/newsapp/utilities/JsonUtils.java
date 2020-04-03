package com.example.phoebee.newsapp.utilities;

import com.example.phoebee.newsapp.model.NewsItem;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;


public class JsonUtils {
    public static ArrayList<NewsItem> parseNews(String jsonResult) {
        ArrayList<NewsItem> newsList = new ArrayList<>();
        try {
            JSONObject mainJSONObject = new JSONObject(jsonResult);
            JSONArray items = mainJSONObject.getJSONArray("articles");

            for (int i = 0; i < items.length(); i++) {
                JSONObject item = items.getJSONObject(i);
                String author = item.getString("author");
                String title = item.getString("title");
                String description = item.getString("description");
                String url = item.getString("url");
                String urlToImage = item.getString("urlToImage");
                String publishedAt = item.getString("publishedAt");

                newsList.add(new NewsItem(title, description, url, publishedAt, urlToImage));
            }
        } catch (JSONException e) {
            e.printStackTrace();
        }

        return newsList;
    }
}
