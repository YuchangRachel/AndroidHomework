package com.example.phoebee.newsapp.firebaseServer;


import com.example.phoebee.newsapp.data.NewsItemRepository;
import com.example.phoebee.newsapp.utilities.NotificationUtils;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;

import android.os.AsyncTask;
import android.util.Log;
import android.widget.Toast;

public class NewsItemFireBaseJobService extends JobService {

    private NewsItemRepository newsItemRepository;

    private AsyncTask<Void, Void, Void> mAsyncTask;

    public NewsItemFireBaseJobService() {

    }

    @Override
    public boolean onStartJob(final JobParameters job) {

        mAsyncTask = new AsyncTask<Void, Void, Void>() {
            @Override
            protected void onPreExecute() {
                Toast.makeText(NewsItemFireBaseJobService.this, "News refreshed", Toast.LENGTH_SHORT).show();
                super.onPreExecute();
            }


            @Override
            protected Void doInBackground(Void... voids) {
                newsItemRepository.syncURL();
                NotificationUtils.notifyUserofUpdatedNews(NewsItemFireBaseJobService.this);
                return null;
            }

            @Override
            protected void onPostExecute(Void o) {
                jobFinished(job, false);

            }
        };
        mAsyncTask.execute();
        return true;
    }

    @Override
    public boolean onStopJob(JobParameters job) {
        if (mAsyncTask != null) {
            mAsyncTask.cancel(true);
        }
        return true;
    }
}

