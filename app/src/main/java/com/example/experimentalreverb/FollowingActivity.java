package com.example.experimentalreverb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

public class FollowingActivity extends AppCompatActivity {

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
                Log.d("Following", "doInBackground: " + " " + "Getting Following");
                return client.getFollowing(token);
            } catch (IOException e) {
                Log.d("GetFollowingTask", "Error getting following", e);
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<GitHubUser> following) {
            if (following != null) {
                for (GitHubUser follower : following) {
                    Log.d("Following", "Username: " + follower.getLogin());
                    Log.d("Following", "Name: " + follower.getName());
                    Log.d("Following", "HTML URL: " + follower.getHtml_url());
                    Log.d("Following", "Avatar URL: " + follower.getAvatar_url());
                    Log.d("-","-------------------------------");
                }
            }
        }
    }
}