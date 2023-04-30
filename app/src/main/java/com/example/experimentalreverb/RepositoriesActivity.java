package com.example.experimentalreverb;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;
import java.util.List;

public class RepositoriesActivity extends AppCompatActivity {

    private ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repo);

        //listView = findViewById(R.id.listView);
        String token = Accesstoken.getInstance().getToken();
        new GetRepositoriesTask().executeOnExecutor(AsyncTask.THREAD_POOL_EXECUTOR, token);
    }

    private void displayRepositories(List<GitHubRepository> repositories) {
        ArrayAdapter<GitHubRepository> adapter =
                new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, repositories);
        listView.setAdapter(adapter);
    }

    static class GetRepositoriesTask extends AsyncTask<String, Void, List<GitHubRepository>> {

        @Override
        protected List<GitHubRepository> doInBackground(String... strings) {
            GitHubApiClient client = new GitHubApiClient(strings[0]);
            try {
                String token = Accesstoken.getInstance().getToken();
                Log.d("Repositories", "doInBackground: " + " " + "Getting repositories");
                return client.getRepositories(token);
            } catch (IOException e) {
                Log.d("Repositories", "onCreate: " + " " + "Error in getting repositories" + " " + e.getMessage());
                e.printStackTrace();
                return null;
            }
        }

        @Override
        protected void onPostExecute(List<GitHubRepository> repositories) {
            if (repositories != null) {
                for (GitHubRepository repository : repositories) {
                    Log.d("Repositories", "Name: " + repository.getName());
                    Log.d("Repositories", "Description: " + repository.getDescription());
                    Log.d("Repositories", "Language: " + repository.getLanguage());
                    Log.d("Repositories", "Created at: " + repository.getCreatedAt());
                    Log.d("Repositories", "Updated at: " + repository.getUpdatedAt());
                    Log.d("Repositories", "Default branch: " + repository.getDefaultBranch());
                    Log.d("Repositories", "Forks count: " + repository.getForksCount());
                    Log.d("Repositories", "Watchers count: " + repository.getWatchersCount());
                    Log.d("Repositories", "Owner: " + repository.getOwner().getLogin());
                    Log.d("Repositories", "--------------------------");
                    //((RepositoriesActivity) getContext()).displayRepositories(repositories);
                }
            }

        }
    }
}
