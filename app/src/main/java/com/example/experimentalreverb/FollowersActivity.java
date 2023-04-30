package com.example.experimentalreverb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

public class FollowersActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);
        new GetFollowersTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, Accesstoken.getInstance().getToken());

    }


    public class GetFollowersTask extends AsyncTask<String, Void, List<GitHubUser>> {

        @Override
        protected List<GitHubUser> doInBackground(String... strings) {
            GitHubApiClient client = new GitHubApiClient(strings[0]);
            try {
                String token = Accesstoken.getInstance().getToken();
                Log.d("Followers", "doInBackground: " + " " + "Getting Followers");
                return client.getFollowers(token);
            } catch (IOException e) {
                Log.d("GetFollowersTask", "Error getting followers", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<GitHubUser> followers) {
            if (followers != null) {
                for (GitHubUser follower : followers) {
                    Log.d("Followers", "Username: " + follower.getLogin());
                    Log.d("Followers", "Name: " + follower.getName());
                    Log.d("Followers", "HTML URL: " + follower.getHtml_url());
                    Log.d("Followers", "Avatar URL: " + follower.getAvatar_url());
                }
            }
        }
    }
}