package com.example.phoebee.newsapp.utilities;

import android.net.Uri;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Scanner;


/**
 * These utilities will be used to communicate with the network.
 */

public final class NetworkUtils {
    //whole resource:"https://newsapi.org/v1/articles?source=the-next-web&sortBy=latest&apiKey=4351a0069059464c975ede3e11de3e22";
    static final String NEWS_BASE_URL = "https://newsapi.org/v1/articles";
    static final String SOURCE_PARAM = "source";
    static final String SORT_BY_PARAM= "sortBy";
    static final String API_KEY_PARAM = "apiKey";


    /**
     * Builds the URL used to query news.org
     * param keyword that will be queried for.
     * return The URL to use to query the GitHub.
     */
    public static URL buildUrl() {
        Uri builtUri = Uri.parse(NEWS_BASE_URL).buildUpon()
                .appendQueryParameter(SOURCE_PARAM, "the-next-web")
                .appendQueryParameter(SORT_BY_PARAM, "latest")
                .appendQueryParameter(API_KEY_PARAM, "4351a0069059464c975ede3e11de3e22")
                .build();

        URL url = null;
        try {
            url = new URL(builtUri.toString());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }


        return url;
    }


    /**
     * getResponseFromHttpUrl method returns the entire result from the HTTP response.
     *
     * @param url The URL to fetch the HTTP response from.
     * @return The contents of the HTTP response.
     * @throws IOException Related to network and stream reading
     */
    public static String getResponseFromHttpUrl(URL url) throws IOException {
        HttpURLConnection urlConnection = (HttpURLConnection) url.openConnection();
        try {
            InputStream in = urlConnection.getInputStream();

            Scanner scanner = new Scanner(in);
            scanner.useDelimiter("\\A");


            boolean hasInput = scanner.hasNext();
            if (hasInput) {
                return scanner.next();
            } else {
                return null;
            }
        } finally {
            urlConnection.disconnect();
        }
    }


}