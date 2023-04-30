package com.example.experimentalreverb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

public class Notification_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);
        String token = Accesstoken.getInstance().getToken();
        new GetNotificationsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, token);
    }


    static class GetNotificationsTask extends AsyncTask<String, Void, List<GitHubNotification>> {

        @Override
        protected List<GitHubNotification> doInBackground(String... strings) {
            GitHubApiClient client = new GitHubApiClient(strings[0]);
            try {
                Log.d("P", "doInBackground: " + " " + "Getting notifications");
                return client.getNotifications();
            } catch (IOException e) {
                Log.d("P", "onCreate: " + " " + "Error in getting notifications" + " " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<GitHubNotification> notifications) {
            if (notifications != null) {
                for (GitHubNotification notification : notifications) {
                    Log.d("Profile", "Subject: " + notification.getSubject().getTitle());
                    Log.d("Profile", "Updated at: " + notification.getUpdated_at());
                    Log.d("Profile", "Reason: " + notification.getReason());
                    Log.d("Profile", "Read at: " + notification.getLast_read_at());
                }
            }
        }

    }
}