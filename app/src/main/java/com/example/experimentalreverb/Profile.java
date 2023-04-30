package com.example.experimentalreverb;

import static com.example.experimentalreverb.R.id.LoadEmail;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

public class Profile extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView tvLoadEmail = findViewById(LoadEmail);
        Log.d("Profile", "onCreate: " +"On created started !!!");
        String email = getIntent().getStringExtra("email");
        String name = getIntent().getStringExtra("name");
        String accessToken = getIntent().getStringExtra("AccessToken");
        String photoUrl = getIntent().getStringExtra("photo");
        Log.d("Profi", "onCreate: " + " " + email + " " + name + " " + accessToken + " " + photoUrl);

        tvLoadEmail.setText(email);

        new GetNotificationsTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, accessToken);
    }

    private static class GetNotificationsTask extends AsyncTask<String, Void, List<GitHubNotification>> {

        @Override
        protected List<GitHubNotification> doInBackground(String... strings) {
            GitHubApiClient client = new GitHubApiClient(strings[0]);
            try {
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
                    Log.d("Pr", "onPostExecute: " + " " + notification);
                }
            }
        }
    }
}
