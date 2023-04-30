package com.example.experimentalreverb;

import static com.example.experimentalreverb.R.id.LoadEmail;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

public class Profile_Activity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        TextView tvLoadEmail = findViewById(LoadEmail);
        TextView tvLoadName = findViewById(R.id.LoadUsername);
        TextView tvLoadfollowers = findViewById(R.id.followers);
        TextView tvLoadfollowing = findViewById(R.id.following);
        String token = Accesstoken.getInstance().getToken();
        Log.d("Profile", "onCreate: " + " " + token);
        Log.d("Profile", "onCreate: " +"On created started !!!");
        String email = getIntent().getStringExtra("email");
        String name = getIntent().getStringExtra("name");
        String accessToken = getIntent().getStringExtra("AccessToken");
        String photoUrl = getIntent().getStringExtra("photo");
        Log.d("Profi", "onCreate: " + " " + email + " " + name + " " + accessToken + " " + photoUrl);

        tvLoadEmail.setText(email);

        new GetUserDetailsTask(tvLoadName,tvLoadfollowers,tvLoadfollowing).executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, accessToken);
    }

    private static class GetUserDetailsTask extends AsyncTask<String, Void, GitHubUser> {

        TextView tvLoadName,tvLoadfollowers,tvLoadfollowing;
        public GetUserDetailsTask(TextView tvLoadName,TextView tvLoadfollowers,TextView tvLoadfollowing) {
            this.tvLoadName = tvLoadName;
            this.tvLoadfollowers = tvLoadfollowers;
            this.tvLoadfollowing = tvLoadfollowing;
        }

        @Override
        protected GitHubUser doInBackground(String... strings) {
            GitHubApiClient client = new GitHubApiClient(strings[0]);
            try {
                Log.d("Profile_Activity", "doInBackground: " + " " + "Getting user details");
                return client.getUserDetails(null, strings[0]);
            } catch (IOException e) {
                Log.d("Profile_Activity", "onCreate: " + " " + "Error in getting user details" + " " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }
        @Override
        protected void onPostExecute(GitHubUser user) {
            if (user != null) {
                tvLoadName.setText(String.valueOf(user.getLogin()));
                tvLoadfollowers.setText(String.valueOf(user.getFollowers()));
                tvLoadfollowing.setText(String.valueOf(user.getFollowing()));
                Log.d("Profile_Activity", "User Details:");
                Log.d("Profile_Activity", "Username: " + user.getLogin());
                Log.d("Profile_Activity", "Name: " + user.getName());
                Log.d("Profile_Activity", "Email: " + user.getEmail());
                Log.d("Profile_Activity", "Location: " + user.getLocation());
                Log.d("Profile_Activity", "Bio: " + user.getBio());
                Log.d("Profile_Activity", "Public Repos: " + user.getPublic_repos());
                Log.d("Profile_Activity", "Public Gists: " + user.getPublic_gists());
                Log.d("Profile_Activity", "Followers: " + user.getFollowers());
                Log.d("Profile_Activity", "Following: " + user.getFollowing());
                Log.d("Profile_Activity", "Created at: " + user.getCreated_at());
                Log.d("Profile_Activity", "Updated at: " + user.getUpdated_at());
            } else {
                Log.d("Profile_Activity", "Error in getting user details");
            }
        }

    }
}
