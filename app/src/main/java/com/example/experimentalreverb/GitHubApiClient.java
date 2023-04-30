package com.example.experimentalreverb;

import com.google.gson.Gson;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class GitHubApiClient {

    private final OkHttpClient httpClient;
    private final Gson gson;
    private final String baseUrl = "https://api.github.com";
    private final String accessToken;

    public GitHubApiClient(String accessToken) {
        this.accessToken = accessToken;
        this.httpClient = new OkHttpClient();
        this.gson = new Gson();
    }

    public GitHubUser getUserDetails(String username, String accessToken) throws IOException {
        String url = baseUrl + "/users/" + username;
        Request request = createAuthorizedRequest(url, accessToken);
        Response response = httpClient.newCall(request).execute();
        String responseBody = response.body().string();
        response.close();
        return gson.fromJson(responseBody, GitHubUser.class);
    }

    public List<GitHubUser> getFollowers(String username, String accessToken) throws IOException {
        String url = baseUrl + "/users/" + username + "/followers";
        Request request = createAuthorizedRequest(url, accessToken);
        Response response = httpClient.newCall(request).execute();
        String responseBody = response.body().string();
        response.close();
        return Arrays.asList(gson.fromJson(responseBody, GitHubUser[].class));
    }

    public List<GitHubUser> getFollowing(String username, String accessToken) throws IOException {
        String url = baseUrl + "/users/" + username + "/following";
        Request request = createAuthorizedRequest(url, accessToken);
        Response response = httpClient.newCall(request).execute();
        String responseBody = response.body().string();
        response.close();
        return Arrays.asList(gson.fromJson(responseBody, GitHubUser[].class));
    }

    public List<GitHubNotification> getNotifications() throws IOException {
        Request request = new Request.Builder()
                .url(baseUrl + "/notifications")
                .header("Authorization", "token " + accessToken)
                .build();
        Response response = httpClient.newCall(request).execute();
        String responseBody = response.body().string();
        response.close();

        if (responseBody.startsWith("{")) {
            // Response is an object, not an array
            return new ArrayList<GitHubNotification>();
        } else {
            // Response is an array
            return Arrays.asList(gson.fromJson(responseBody, GitHubNotification[].class));
        }
    }



    public List<GitHubRepository> getRepositories(String username, String accessToken) throws IOException {
        String url = baseUrl + "/users/" + username + "/repos";
        Request request = createAuthorizedRequest(url, accessToken);
        Response response = httpClient.newCall(request).execute();
        String responseBody = response.body().string();
        response.close();
        return Arrays.asList(gson.fromJson(responseBody, GitHubRepository[].class));
    }

    private Request createAuthorizedRequest(String url, String accessToken) {
        return new Request.Builder()
                .url(url)
                .header("Authorization", "Bearer " + accessToken)
                .build();
    }
}



